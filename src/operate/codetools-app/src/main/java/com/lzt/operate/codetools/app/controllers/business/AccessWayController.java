package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.common.BaseOperateAuthController;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.common.utils.GlobalString;
import com.lzt.operate.codetools.common.utils.ModelNameCollection;
import com.lzt.operate.codetools.dao.service.AccessWayService;
import com.lzt.operate.codetools.dao.service.impl.AccessWayServiceImpl;
import com.lzt.operate.codetools.entities.AccessWay;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.assists.IGetter;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.permissions.NeedAuthorization;
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
 * 权限模块管理
 *
 * @author luzhitao
 * @date 2020-02-27 16:49
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/accessWay")
@Api(tags = {"模块管理"})
public class AccessWayController extends BaseOperateAuthController {

	private static final String CONTROLLER_DESCRIPTION = "模块管理/";

	private AccessWayService accessWayService;

	@Autowired
	public AccessWayController(CustomJsonWebTokenConfig customJsonWebTokenConfig, AccessWayServiceImpl accessWayService) {
		super(customJsonWebTokenConfig);

		this.accessWayService = accessWayService;
	}

	public AccessWayService getAccessWayService() {
		Optional<AccessWayService> optional = Optional.ofNullable(this.accessWayService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("AccessWayService获取失败");
	}

	@ApiOperation(value = "模块列表", notes = "模块列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ACCESS_WAY_LIST, value = {
			@ApiJsonProperty(name = GlobalString.ACCESS_WAY_NAME),
			@ApiJsonProperty(name = GlobalString.ACCESS_WAY_TAG),
			@ApiJsonProperty(name = GlobalString.ACCESS_WAY_RELATIVE_PATH),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_NO),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_SIZE)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ACCESS_WAY_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/list", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "模块列表", description = "模块列表", tag = "c44e3865-1fa0-48df-ad0a-bd3f79807a38")
	public ResultListData list(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		int pageNo = paramJson.getStringExByKey(GlobalString.LIST_PAGE_NO, "1").toInt();
		int pageSize = paramJson.getStringExByKey(GlobalString.LIST_PAGE_SIZE, "20").toInt();

		pageNo = Math.max(pageNo, 1);
		pageSize = Math.max(pageSize, 1);

		String name = paramJson.getStringByKey(GlobalString.ACCESS_WAY_NAME);
		String tag = paramJson.getStringByKey(GlobalString.ACCESS_WAY_TAG);
		String relativePath = paramJson.getStringByKey(GlobalString.ACCESS_WAY_RELATIVE_PATH);

		Specification<AccessWay> specification = new Specification<AccessWay>() {

			private static final long serialVersionUID = -490480767073213072L;

			@Override
			public Predicate toPredicate(@NonNull Root<AccessWay> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				if (!StringAssist.isNullOrEmpty(name)) {
					list.add(criteriaBuilder.like(root.get(ReflectAssist.getFieldName(AccessWay::getName)), StringAssist
							.merge("%", name, "%")));
				}

				if (!StringAssist.isNullOrEmpty(tag)) {
					list.add(criteriaBuilder.like(root.get(ReflectAssist.getFieldName(AccessWay::getTag)), StringAssist
							.merge("%", tag, "%")));
				}

				if (!StringAssist.isNullOrEmpty(relativePath)) {
					list.add(criteriaBuilder.like(root.get(ReflectAssist.getFieldName(AccessWay::getRelativePath)), StringAssist
							.merge("%", relativePath, "%")));
				}

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, ReflectAssist.getFieldName(AccessWay::getCreateTime));

		Page<AccessWay> result = this.accessWayService.page(specification, pageable);

		List<SerializableData> list = result.getContent()
											.stream()
											.map(o -> {
												List<IGetter<AccessWay>> getterList = new ArrayList<>();

												getterList.add(AccessWay::getName);
												getterList.add(AccessWay::getDescription);
												getterList.add(AccessWay::getTag);
												getterList.add(AccessWay::getRelativePath);
												getterList.add(AccessWay::getExpand);
												getterList.add(AccessWay::getChannel);
												getterList.add(AccessWay::getChannelNote);
												getterList.add(AccessWay::getStatus);
												getterList.add(AccessWay::getStatusNote);
												getterList.add(AccessWay::getCreateTime);
												getterList.add(AccessWay::getUpdateTime);

												SerializableData data = SerializableData.toSerializableData(o, getterList);

												data.append(ReflectAssist.getFriendlyIdName(AccessWay.class), o.getId());

												return data;
											})
											.collect(Collectors.toList());

		int pageIndex = result.getNumber();
		long totalPages = result.getTotalPages();

		return this.pageData(list, pageIndex, pageSize, totalPages);
	}

	@ApiOperation(value = "获取模块", notes = "获取模块信息", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ACCESS_WAY_GET, value = {
			@ApiJsonProperty(name = GlobalString.ACCESS_WAY_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ACCESS_WAY_GET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "模块详情", description = "获取模块信息", tag = "a0664bb2-75ff-406b-9463-9e5aae7af56e")
	public BaseResultData get(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		long accessWayId = paramJson.getStringExByKey(GlobalString.ACCESS_WAY_ID, "0").toLong();

		Optional<AccessWay> result = getAccessWayService().get(accessWayId);

		if (result.isPresent()) {
			AccessWay accessWay = result.get();

			List<IGetter<AccessWay>> getterList = new ArrayList<>();

			getterList.add(AccessWay::getName);
			getterList.add(AccessWay::getDescription);
			getterList.add(AccessWay::getTag);
			getterList.add(AccessWay::getRelativePath);
			getterList.add(AccessWay::getExpand);
			getterList.add(AccessWay::getChannel);
			getterList.add(AccessWay::getChannelNote);
			getterList.add(AccessWay::getStatus);
			getterList.add(AccessWay::getStatusNote);
			getterList.add(AccessWay::getCreateTime);
			getterList.add(AccessWay::getUpdateTime);

			SerializableData data = SerializableData.toSerializableData(accessWay, getterList);

			data.append(ReflectAssist.getFriendlyIdName(AccessWay.class), accessWay.getId());

			return this.singleData(data);
		}

		return this.fail(ReturnDataCode.NoData.toMessage());
	}

}
