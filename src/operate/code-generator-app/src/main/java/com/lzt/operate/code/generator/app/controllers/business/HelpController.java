package com.lzt.operate.code.generator.app.controllers.business;

import com.lzt.operate.code.generator.app.common.BaseOperateAuthController;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.common.utils.GlobalString;
import com.lzt.operate.code.generator.common.utils.ModelNameCollection;
import com.lzt.operate.code.generator.dao.service.HelpCategoryService;
import com.lzt.operate.code.generator.dao.service.HelpService;
import com.lzt.operate.code.generator.dao.service.impl.HelpCategoryServiceImpl;
import com.lzt.operate.code.generator.dao.service.impl.HelpServiceImpl;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.code.generator.entities.Help;
import com.lzt.operate.code.generator.entities.HelpCategory;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.assists.IGetter;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.general.ConstantCollection;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.ResultListData;
import com.lzt.operate.utility.pojo.ResultSingleData;
import com.lzt.operate.utility.pojo.SerializableData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 帮助管理
 *
 * @author luzhitao
 * @date 2020-02-27 16:49
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/help")
@Api(tags = {"帮助管理"})
public class HelpController extends BaseOperateAuthController {

	private HelpService helpService;

	private HelpCategoryService helpCategoryService;

	@Autowired
	public HelpController(CustomJsonWebTokenConfig customJsonWebTokenConfig, HelpCategoryServiceImpl helpCategoryService, HelpServiceImpl helpService) {
		super(customJsonWebTokenConfig);

		this.helpCategoryService = helpCategoryService;
		this.helpService = helpService;
	}

	public HelpService getHelpService() {
		Optional<HelpService> optional = Optional.ofNullable(this.helpService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("HelpService获取失败");
	}

	public HelpCategoryService getHelpCategoryService() {
		Optional<HelpCategoryService> optional = Optional.ofNullable(this.helpCategoryService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("HelpCategoryService获取失败");
	}

	@ApiOperation(value = "帮助类别列表", notes = "帮助类别列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.HELP_PAGE_LIST, value = {
			@ApiJsonProperty(name = GlobalString.HELP_TITLE),
			@ApiJsonProperty(name = GlobalString.HELP_HELP_CATEGORY_ID),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_NO),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_SIZE)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.HELP_PAGE_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/pageList", consumes = "application/json", produces = "application/json")
	public ResultListData pageList(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		int pageNo = paramJson.getStringExByKey(GlobalString.LIST_PAGE_NO, "1").toInt();
		int pageSize = paramJson.getStringExByKey(GlobalString.LIST_PAGE_SIZE, "20").toInt();

		pageNo = Math.max(pageNo, 1);
		pageSize = Math.max(pageSize, 1);

		String title = paramJson.getStringByKey(GlobalString.HELP_TITLE);
		Long helpCategoryId = paramJson.getStringExByKey(GlobalString.HELP_HELP_CATEGORY_ID).toLong();

		String helpCategoryName = "";

		if (!helpCategoryId.equals(ConstantCollection.SEARCH_UNLIMITED_LONG)) {
			Optional<HelpCategory> optional = this.getHelpCategoryService().get(helpCategoryId);

			if (optional.isPresent()) {
				helpCategoryName = optional.get().getName();
			}
		}

		Specification<Help> specification = new Specification<Help>() {

			private static final long serialVersionUID = -8176720278621861132L;

			@Override
			public Predicate toPredicate(@NonNull Root<Help> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				if (!StringAssist.isNullOrEmpty(title)) {
					list.add(criteriaBuilder.like(root.get(ReflectAssist.getFieldName(Help::getTitle)), StringAssist
							.merge("%", title, "%")));
				}

				if (!helpCategoryId.equals(ConstantCollection.SEARCH_UNLIMITED_LONG)) {
					list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(Help::getHelpCategoryId)), helpCategoryId));
				}

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, ReflectAssist.getFieldName(ConnectionConfig::getCreateTime));

		Page<Help> result = this.getHelpService().page(specification, pageable);

		List<SerializableData> list = result.getContent()
											.stream()
											.map(o -> {
												List<IGetter<Help>> getterList = new ArrayList<>();

												getterList.add(Help::getTitle);
												getterList.add(Help::getHelpCategoryId);
												getterList.add(Help::getDescription);

												SerializableData data = SerializableData.toSerializableData(o, getterList);

												data.append(ReflectAssist.getFriendlyIdName(Help.class), o.getId());

												return data;
											})
											.collect(Collectors.toList());

		int pageIndex = result.getNumber();
		long totalPages = result.getTotalPages();

		SerializableData extra = new SerializableData();

		extra.append("helpCategoryName", helpCategoryName);

		return this.pageData(list, pageIndex, pageSize, totalPages, extra);
	}

	@ApiOperation(value = "帮助类别详情", notes = "帮助类别详情", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.HELP_GET, value = {
			@ApiJsonProperty(name = GlobalString.HELP_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.HELP_GET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	public BaseResultData get(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		long helpId = paramJson.getStringExByKey(GlobalString.HELP_ID, "0").toLong();

		Optional<Help> result = this.getHelpService().get(helpId);

		if (result.isPresent()) {
			Help help = result.get();

			List<IGetter<Help>> getterList = new ArrayList<>();

			getterList.add(Help::getTitle);
			getterList.add(Help::getHelpCategoryId);
			getterList.add(Help::getDescription);
			getterList.add(Help::getContent);
			getterList.add(Help::getChannel);
			getterList.add(Help::getChannelNote);
			getterList.add(Help::getStatus);
			getterList.add(Help::getStatusNote);
			getterList.add(Help::getCreateTime);
			getterList.add(Help::getUpdateTime);

			SerializableData data = SerializableData.toSerializableData(help, getterList);

			data.append(ReflectAssist.getFriendlyIdName(Help.class), help.getId());

			return this.singleData(data);
		}

		return this.fail(ReturnDataCode.NoData.toMessage());
	}

}
