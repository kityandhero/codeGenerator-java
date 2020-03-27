package com.lzt.operate.code.generator.app.controllers.business;

import com.lzt.operate.code.generator.app.common.BaseOperateAuthController;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.common.enums.RoleUniversalStatus;
import com.lzt.operate.code.generator.common.utils.GlobalString;
import com.lzt.operate.code.generator.common.utils.ModelNameCollection;
import com.lzt.operate.code.generator.dao.service.RoleUniversalService;
import com.lzt.operate.code.generator.dao.service.impl.RoleUniversalServiceImpl;
import com.lzt.operate.code.generator.entities.RoleUniversal;
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
 * 公共角色管理
 *
 * @author luzhitao
 * @date 2020-02-24 16:31
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/roleUniversal")
@Api(tags = {"公共角色管理"})
public class RoleUniversalController extends BaseOperateAuthController {

	private static final String CONTROLLER_DESCRIPTION = "公共角色管理/";

	private RoleUniversalService roleUniversalService;

	@Autowired
	public RoleUniversalController(CustomJsonWebTokenConfig customJsonWebTokenConfig, RoleUniversalServiceImpl roleUniversalService) {
		super(customJsonWebTokenConfig);

		this.roleUniversalService = roleUniversalService;
	}

	public RoleUniversalService getRoleUniversalService() {
		Optional<RoleUniversalService> optional = Optional.ofNullable(this.roleUniversalService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("RoleUniversalService获取失败");
	}

	@ApiOperation(value = "公共角色分页列表", notes = "公共角色分页列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ROLE_UNIVERSAL_PAGE_LIST, value = {
			@ApiJsonProperty(name = GlobalString.ROLE_UNIVERSAL_NAME),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_NO),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_SIZE)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ROLE_UNIVERSAL_PAGE_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/pageList", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "公共角色分页列表", description = "公共角色分页列表", tag = "7ab17e1a-4fff-4ed8-8cb0-24e5bcca089b")
	public ResultListData pageList(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		int pageNo = paramJson.getStringExByKey(GlobalString.LIST_PAGE_NO, "1").toInt();
		int pageSize = paramJson.getStringExByKey(GlobalString.LIST_PAGE_SIZE, "20").toInt();

		pageNo = Math.max(pageNo, 1);
		pageSize = Math.max(pageSize, 1);

		String name = paramJson.getStringByKey(GlobalString.ROLE_UNIVERSAL_NAME);

		Specification<RoleUniversal> specification = new Specification<RoleUniversal>() {

			private static final long serialVersionUID = 5447618581311966365L;

			@Override
			public Predicate toPredicate(@NonNull Root<RoleUniversal> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				if (!StringAssist.isNullOrEmpty(name)) {
					list.add(criteriaBuilder.like(root.get(ReflectAssist.getFieldName(RoleUniversal::getName)), StringAssist
							.merge("%", name, "%")));
				}

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, ReflectAssist.getFieldName(RoleUniversal::getCreateTime));

		Page<RoleUniversal> result = this.roleUniversalService.page(specification, pageable);

		List<SerializableData> list = result.getContent()
											.stream()
											.map(o -> {
												List<IGetter<RoleUniversal>> getterList = new ArrayList<>();

												getterList.add(RoleUniversal::getName);
												getterList.add(RoleUniversal::getDescription);
												getterList.add(RoleUniversal::getChannel);
												getterList.add(RoleUniversal::getChannelNote);
												getterList.add(RoleUniversal::getStatus);
												getterList.add(RoleUniversal::getStatusNote);
												getterList.add(RoleUniversal::getCreateTime);
												getterList.add(RoleUniversal::getUpdateTime);

												SerializableData data = SerializableData.toSerializableData(o, getterList);

												data.append(ReflectAssist.getFriendlyIdName(RoleUniversal.class), o.getId());

												return data;
											})
											.collect(Collectors.toList());

		int pageIndex = result.getNumber();
		long totalPages = result.getTotalPages();

		return this.pageData(list, pageIndex, pageSize, totalPages);
	}

	@ApiOperation(value = "获取公共角色", notes = "获取公共角色", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ROLE_UNIVERSAL_GET, value = {
			@ApiJsonProperty(name = GlobalString.ROLE_UNIVERSAL_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ROLE_UNIVERSAL_GET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "公共角色详情", tag = "15991393-196b-4b7b-b74f-9b773989368f")
	public BaseResultData get(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		long accountId = paramJson.getStringExByKey(GlobalString.ROLE_UNIVERSAL_ID, "0").toLong();

		Optional<RoleUniversal> result = getRoleUniversalService().get(accountId);

		if (result.isPresent()) {
			RoleUniversal roleUniversal = result.get();

			List<IGetter<RoleUniversal>> getterList = new ArrayList<>();

			getterList.add(RoleUniversal::getName);
			getterList.add(RoleUniversal::getDescription);
			getterList.add(RoleUniversal::getChannel);
			getterList.add(RoleUniversal::getChannelNote);
			getterList.add(RoleUniversal::getStatus);
			getterList.add(RoleUniversal::getStatusNote);
			getterList.add(RoleUniversal::getCreateTime);
			getterList.add(RoleUniversal::getUpdateTime);

			SerializableData data = SerializableData.toSerializableData(roleUniversal, getterList);

			data.append(ReflectAssist.getFriendlyIdName(RoleUniversal.class), roleUniversal.getId());

			return this.singleData(data);
		}

		return this.fail(ReturnDataCode.NoData.toMessage());
	}

	@ApiOperation(value = "创建公共角色", notes = "创建公共角色", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ROLE_UNIVERSAL_ADD_BASIC_INFO, value = {
			@ApiJsonProperty(name = GlobalString.ROLE_UNIVERSAL_NAME),
			@ApiJsonProperty(name = GlobalString.ROLE_UNIVERSAL_DESCRIPTION),
			@ApiJsonProperty(name = GlobalString.ROLE_UNIVERSAL_NAME)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ROLE_UNIVERSAL_ADD_BASIC_INFO)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/addBasicInfo", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "新增公共角色", tag = "f1fbd4fd-2360-4c6b-bdee-fdd0d030966d")
	public BaseResultData addBasicInfo(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		String name = paramJson.getStringByKey(GlobalString.ROLE_UNIVERSAL_NAME).trim();

		if (StringAssist.isNullOrEmpty(name)) {
			return this.paramError(GlobalString.ROLE_UNIVERSAL_NAME, "角色名称不能为空");
		}

		String description = paramJson.getStringByKey(GlobalString.ROLE_UNIVERSAL_DESCRIPTION);

		RoleUniversal data = new RoleUniversal();

		data.setName(name);
		data.setDescription(description);

		data.setStatus(RoleUniversalStatus.Enabled, RoleUniversalStatus::getFlag, RoleUniversalStatus::getName);

		long operatorId = getOperatorId();

		data.setCreateOperatorId(operatorId);
		data.setUpdateOperatorId(operatorId);

		data = getRoleUniversalService().save(data);

		return this.singleData(new SerializableData().append(GlobalString.ROLE_UNIVERSAL_ID, data.getId()));
	}

	@ApiOperation(value = "更新公共角色", notes = "更新公共角色", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ROLE_UNIVERSAL_UPDATE_BASIC_INFO, value = {
			@ApiJsonProperty(name = GlobalString.ROLE_UNIVERSAL_ID),
			@ApiJsonProperty(name = GlobalString.ROLE_UNIVERSAL_NAME),
			@ApiJsonProperty(name = GlobalString.ROLE_UNIVERSAL_DESCRIPTION)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ROLE_UNIVERSAL_UPDATE_BASIC_INFO)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/updateBasicInfo", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "更新基本信息", tag = "67e38843-c168-486a-9472-484078ad75b4")
	public BaseResultData updateBasicInfo(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		long accountId = paramJson.getStringExByKey(GlobalString.ROLE_UNIVERSAL_ID, "0").toLong();

		if (accountId <= 0) {
			return this.paramError(GlobalString.ROLE_UNIVERSAL_ID, "数据无效");
		}

		String name = paramJson.getStringByKey(GlobalString.ROLE_UNIVERSAL_NAME);

		if (StringAssist.isNullOrEmpty(name)) {
			return this.paramError(GlobalString.ROLE_UNIVERSAL_NAME, "角色名称不能为空");
		}

		String description = paramJson.getStringByKey(GlobalString.ROLE_UNIVERSAL_DESCRIPTION);

		Optional<RoleUniversal> result = getRoleUniversalService().get(accountId);

		if (result.isPresent()) {
			RoleUniversal data = result.get();

			data.setName(name);
			data.setDescription(description);

			long operatorId = getOperatorId();

			data.setUpdateOperatorId(operatorId);

			data = getRoleUniversalService().save(data);

			return this.singleData(new SerializableData().append(GlobalString.ROLE_UNIVERSAL_ID, data.getId()));
		}

		return this.fail(ReturnDataCode.NoData.toMessage());
	}

}
