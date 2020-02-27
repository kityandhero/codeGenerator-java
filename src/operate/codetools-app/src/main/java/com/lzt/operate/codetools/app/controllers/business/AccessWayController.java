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
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.permissions.NeedAuthorization;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.ResultListData;
import com.lzt.operate.utility.pojo.ResultSingleData;
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
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "模块列表", description = "模块列表", tag = "0d7ad276-9398-415f-a84b-40f31dee695a")
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
					list.add(criteriaBuilder.like(root.get(ReflectAssist.getFieldName(AccessWay::getName)), name));
				}

				if (!StringAssist.isNullOrEmpty(tag)) {
					list.add(criteriaBuilder.like(root.get(ReflectAssist.getFieldName(AccessWay::getTag)), tag));
				}

				if (!StringAssist.isNullOrEmpty(relativePath)) {
					list.add(criteriaBuilder.like(root.get(ReflectAssist.getFieldName(AccessWay::getRelativePath)), relativePath));
				}

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, ReflectAssist.getFieldName(AccessWay::getCreateTime));

		Page<AccessWay> result = this.accessWayService.page(specification, pageable);

		return this.pageData(result);
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
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "模块详情", description = "获取模块信息", tag = "383bee52-0a21-4ec2-a46d-586efb38264d")
	public BaseResultData get(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		long accessWayId = paramJson.getStringExByKey(GlobalString.ACCESS_WAY_ID, "0").toLong();

		Optional<AccessWay> result = getAccessWayService().get(accessWayId);

		if (result.isPresent()) {
			return this.singleData(result.get());
		}

		return this.fail(ReturnDataCode.NoData.toMessage());
	}

}
