package com.lzt.operate.code.generator.app.controllers.business;

import com.lzt.operate.code.generator.app.common.BaseOperateAuthController;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.common.pojos.HelpCategoryTreeItem;
import com.lzt.operate.code.generator.common.utils.GlobalString;
import com.lzt.operate.code.generator.common.utils.ModelNameCollection;
import com.lzt.operate.code.generator.dao.service.HelpCategoryService;
import com.lzt.operate.code.generator.dao.service.impl.HelpCategoryServiceImpl;
import com.lzt.operate.code.generator.entities.HelpCategory;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.assists.IGetter;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 帮助类别管理
 *
 * @author luzhitao
 * @date 2020-02-27 16:49
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/helpCategory")
@Api(tags = {"帮助类别管理"})
public class HelpCategoryController extends BaseOperateAuthController {

	private HelpCategoryService helpCategoryService;

	@Autowired
	public HelpCategoryController(CustomJsonWebTokenConfig customJsonWebTokenConfig, HelpCategoryServiceImpl helpCategoryService) {
		super(customJsonWebTokenConfig);

		this.helpCategoryService = helpCategoryService;
	}

	public HelpCategoryService getHelpCategoryService() {
		Optional<HelpCategoryService> optional = Optional.ofNullable(this.helpCategoryService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("HelpCategoryService获取失败");
	}

	@ApiOperation(value = "帮助类别列表", notes = "帮助类别列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.HELP_CATEGORY_LIST, value = {
			@ApiJsonProperty(name = GlobalString.HELP_CATEGORY_NAME)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.HELP_CATEGORY_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/list", consumes = "application/json", produces = "application/json")
	public ResultListData list(@RequestBody Map<String, Object> json) {
		ParamData paramJson = getParamData(json);

		String name = paramJson.getStringByKey(GlobalString.HELP_CATEGORY_NAME);

		Specification<HelpCategory> specification = new Specification<HelpCategory>() {

			private static final long serialVersionUID = -9048917003338760769L;

			@Override
			public Predicate toPredicate(@NonNull Root<HelpCategory> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				if (!StringAssist.isNullOrEmpty(name)) {
					list.add(criteriaBuilder.like(root.get(ReflectAssist.getFieldName(HelpCategory::getName)), StringAssist
							.merge("%", name, "%")));
				}

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		List<HelpCategory> result = this.getHelpCategoryService().list(specification);

		List<SerializableData> list = result
				.stream()
				.map(o -> {
					List<IGetter<HelpCategory>> getterList = new ArrayList<>();

					getterList.add(HelpCategory::getName);

					SerializableData data = SerializableData.toSerializableData(o, getterList);

					data.append(ReflectAssist.getFriendlyIdName(HelpCategory.class), o.getId());

					return data;
				})
				.collect(Collectors.toList());

		return this.listData(list);
	}

	@ApiOperation(value = "帮助类别树列表", notes = "帮助类别树列表", httpMethod = "POST")
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/treeList", consumes = "application/json", produces = "application/json")
	public ResultListData treeList(@RequestBody Map<String, Object> json) {
		List<HelpCategoryTreeItem> list = this.getHelpCategoryService().treeList();

		return this.listData(list);
	}

	@ApiOperation(value = "帮助类别详情", notes = "帮助类别详情", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.HELP_CATEGORY_GET, value = {
			@ApiJsonProperty(name = GlobalString.HELP_CATEGORY_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.HELP_CATEGORY_GET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	public ResultSingleData get(@RequestBody Map<String, Object> json) {
		ParamData paramJson = getParamData(json);

		long helpCategoryId = paramJson.getStringExByKey(GlobalString.HELP_CATEGORY_ID, "0").toLong();

		Optional<HelpCategory> result = this.getHelpCategoryService().get(helpCategoryId);

		if (result.isPresent()) {
			HelpCategory helpCategory = result.get();

			List<IGetter<HelpCategory>> getterList = new ArrayList<>();

			getterList.add(HelpCategory::getName);
			getterList.add(HelpCategory::getDescription);
			getterList.add(HelpCategory::getChannel);
			getterList.add(HelpCategory::getChannelNote);
			getterList.add(HelpCategory::getStatus);
			getterList.add(HelpCategory::getStatusNote);
			getterList.add(HelpCategory::getCreateTime);
			getterList.add(HelpCategory::getUpdateTime);

			SerializableData data = SerializableData.toSerializableData(helpCategory, getterList);

			data.append(ReflectAssist.getFriendlyIdName(HelpCategory.class), helpCategory.getId());

			return this.singleData(data);
		}

		return this.fail(ReturnDataCode.NoData.toMessage());
	}

}
