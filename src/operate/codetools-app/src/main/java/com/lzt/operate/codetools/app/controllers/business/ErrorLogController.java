package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.common.BaseOperateAuthController;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.common.enums.Channel;
import com.lzt.operate.codetools.common.utils.GlobalString;
import com.lzt.operate.codetools.common.utils.ModelNameCollection;
import com.lzt.operate.codetools.dao.service.ErrorLogService;
import com.lzt.operate.codetools.dao.service.impl.ErrorLogServiceImpl;
import com.lzt.operate.codetools.entities.Account;
import com.lzt.operate.codetools.entities.ErrorLog;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.assists.EnumAssist;
import com.lzt.operate.utility.assists.IGetter;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.general.ConstantCollection;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 错误日志管理
 *
 * @author luzhitao
 * @date 2020-02-27 16:49
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/errorLog")
@Api(tags = {"错误日志管理"})
public class ErrorLogController extends BaseOperateAuthController {

	private static final String CONTROLLER_DESCRIPTION = "错误日志管理/";

	private ErrorLogService errorLogService;

	@Autowired
	public ErrorLogController(CustomJsonWebTokenConfig customJsonWebTokenConfig, ErrorLogServiceImpl errorLogService) {
		super(customJsonWebTokenConfig);

		this.errorLogService = errorLogService;
	}

	public ErrorLogService getErrorLogService() {
		Optional<ErrorLogService> optional = Optional.ofNullable(this.errorLogService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("ErrorLogService获取失败");
	}

	@ApiOperation(value = "错误日志分页列表", notes = "错误日志分页列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ERROR_LOG_LIST, value = {
			@ApiJsonProperty(name = GlobalString.ERROR_LOG_MESSAGE),
			@ApiJsonProperty(name = GlobalString.ERROR_LOG_CHANNEL),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_NO),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_SIZE)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ERROR_LOG_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/page", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "错误日志分页列表", description = "错误日志分页列表", tag = "bb6b8151-c999-4ad4-9f0a-7f6ae46cea8b")
	public ResultListData page(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		int pageNo = paramJson.getStringExByKey(GlobalString.LIST_PAGE_NO, "1").toInt();
		int pageSize = paramJson.getStringExByKey(GlobalString.LIST_PAGE_SIZE, "20").toInt();

		pageNo = Math.max(pageNo, 1);
		pageSize = Math.max(pageSize, 1);

		String message = paramJson.getStringByKey(GlobalString.ERROR_LOG_MESSAGE);
		Integer channel = paramJson.getStringExByKey(GlobalString.ERROR_LOG_CHANNEL, ConstantCollection.SEARCH_UNLIMITED_STRING)
								   .toInt();

		if (!channel.equals(ConstantCollection.SEARCH_UNLIMITED_NUMBER) && !EnumAssist.existTargetValue(Arrays.asList(Channel
				.values()), Channel::getFlag, channel)) {
			return this.pageDataEmpty(pageSize);
		}

		Specification<ErrorLog> specification = new Specification<ErrorLog>() {

			private static final long serialVersionUID = -490480767073213072L;

			@Override
			public Predicate toPredicate(@NonNull Root<ErrorLog> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				if (!StringAssist.isNullOrEmpty(message)) {
					list.add(criteriaBuilder.like(root.get(ReflectAssist.getFieldName(ErrorLog::getMessage)), StringAssist
							.merge("%", message, "%")));
				}

				if (!channel.equals(ConstantCollection.SEARCH_UNLIMITED_NUMBER)) {
					list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(Account::getStatus)), channel));
				}

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, ReflectAssist.getFieldName(ErrorLog::getCreateTime));

		Page<ErrorLog> result = this.errorLogService.page(specification, pageable);

		List<SerializableData> list = result.getContent()
											.stream()
											.map(o -> {
												List<IGetter<ErrorLog>> getterList = new ArrayList<>();

												getterList.add(ErrorLog::getMessage);
												getterList.add(ErrorLog::getUri);
												getterList.add(ErrorLog::getChannel);
												getterList.add(ErrorLog::getChannelNote);
												getterList.add(ErrorLog::getStatus);
												getterList.add(ErrorLog::getStatusNote);
												getterList.add(ErrorLog::getCreateTime);
												getterList.add(ErrorLog::getUpdateTime);

												SerializableData data = SerializableData.toSerializableData(o, getterList);

												data.append(ReflectAssist.getFriendlyIdName(ErrorLog.class), o.getId());

												return data;
											})
											.collect(Collectors.toList());

		int pageIndex = result.getNumber();
		long totalPages = result.getTotalPages();

		return this.pageData(list, pageIndex, pageSize, totalPages);
	}

	@ApiOperation(value = "获取错误日志", notes = "获取错误日志信息", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.ERROR_LOG_GET, value = {
			@ApiJsonProperty(name = GlobalString.ERROR_LOG_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.ERROR_LOG_GET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "错误日志详情", description = "获取错误日志信息", tag = "a0664bb2-75ff-406b-9463-9e5aae7af56e")
	public BaseResultData get(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		long errorLogId = paramJson.getStringExByKey(GlobalString.ERROR_LOG_ID, "0").toLong();

		Optional<ErrorLog> result = getErrorLogService().get(errorLogId);

		if (result.isPresent()) {
			ErrorLog errorLog = result.get();

			List<IGetter<ErrorLog>> getterList = new ArrayList<>();

			getterList.add(ErrorLog::getMessage);
			getterList.add(ErrorLog::getUri);
			getterList.add(ErrorLog::getDegree);
			getterList.add(ErrorLog::getDegreeNote);
			getterList.add(ErrorLog::getResolve);
			getterList.add(ErrorLog::getResolveNote);
			getterList.add(ErrorLog::getScene);
			getterList.add(ErrorLog::getSource);
			getterList.add(ErrorLog::getType);
			getterList.add(ErrorLog::getTypeNote);
			getterList.add(ErrorLog::getStackTrace);
			getterList.add(ErrorLog::getExceptionTypeName);
			getterList.add(ErrorLog::getData);
			getterList.add(ErrorLog::getHeader);
			getterList.add(ErrorLog::getHost);
			getterList.add(ErrorLog::getPort);
			getterList.add(ErrorLog::getRequestQueryString);
			getterList.add(ErrorLog::getRequestBody);
			getterList.add(ErrorLog::getRequestParams);
			getterList.add(ErrorLog::getOtherLog);
			getterList.add(ErrorLog::getSendNotification);
			getterList.add(ErrorLog::getSendResult);
			getterList.add(ErrorLog::getSendUnixTime);
			getterList.add(ErrorLog::getIp);
			getterList.add(ErrorLog::getChannel);
			getterList.add(ErrorLog::getChannelNote);
			getterList.add(ErrorLog::getStatus);
			getterList.add(ErrorLog::getStatusNote);
			getterList.add(ErrorLog::getId);
			getterList.add(ErrorLog::getCreateOperatorId);
			getterList.add(ErrorLog::getCreateTime);
			getterList.add(ErrorLog::getUpdateOperatorId);
			getterList.add(ErrorLog::getUpdateTime);

			SerializableData data = SerializableData.toSerializableData(errorLog, getterList);

			data.append(ReflectAssist.getFriendlyIdName(ErrorLog.class), errorLog.getId());

			return this.singleData(data);
		}

		return this.fail(ReturnDataCode.NoData.toMessage());
	}

}
