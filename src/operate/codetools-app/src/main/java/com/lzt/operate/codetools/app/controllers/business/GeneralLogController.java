package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.common.BaseOperateAuthController;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.common.enums.Channel;
import com.lzt.operate.codetools.common.utils.GlobalString;
import com.lzt.operate.codetools.common.utils.ModelNameCollection;
import com.lzt.operate.codetools.dao.service.GeneralLogService;
import com.lzt.operate.codetools.dao.service.impl.GeneralLogServiceImpl;
import com.lzt.operate.codetools.entities.Account;
import com.lzt.operate.codetools.entities.GeneralLog;
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
 * 一般日志管理
 *
 * @author luzhitao
 * @date 2020-02-27 16:49
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/generalLog")
@Api(tags = {"一般日志管理"})
public class GeneralLogController extends BaseOperateAuthController {

	private static final String CONTROLLER_DESCRIPTION = "一般日志管理/";

	private GeneralLogService generalLogService;

	@Autowired
	public GeneralLogController(CustomJsonWebTokenConfig customJsonWebTokenConfig, GeneralLogServiceImpl generalLogService) {
		super(customJsonWebTokenConfig);

		this.generalLogService = generalLogService;
	}

	public GeneralLogService getGeneralLogService() {
		Optional<GeneralLogService> optional = Optional.ofNullable(this.generalLogService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("GeneralLogService获取失败");
	}

	@ApiOperation(value = "一般日志分页列表", notes = "一般日志分页列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.GENERAL_LOG_LIST, value = {
			@ApiJsonProperty(name = GlobalString.GENERAL_LOG_MESSAGE),
			@ApiJsonProperty(name = GlobalString.GENERAL_LOG_CHANNEL),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_NO),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_SIZE)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.GENERAL_LOG_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/pageList", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = GeneralLogController.CONTROLLER_DESCRIPTION + "一般日志分页列表", description = "一般日志分页列表", tag = "93231249-6171-4d8a-b7e1-a66006385bdc")
	public ResultListData pageList(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = this.getParamData(json);

		int pageNo = paramJson.getStringExByKey(GlobalString.LIST_PAGE_NO, "1").toInt();
		int pageSize = paramJson.getStringExByKey(GlobalString.LIST_PAGE_SIZE, "20").toInt();

		pageNo = Math.max(pageNo, 1);
		pageSize = Math.max(pageSize, 1);

		String message = paramJson.getStringByKey(GlobalString.GENERAL_LOG_MESSAGE);
		Integer channel = paramJson.getStringExByKey(GlobalString.GENERAL_LOG_CHANNEL, ConstantCollection.SEARCH_UNLIMITED_STRING)
								   .toInt();

		if (!channel.equals(ConstantCollection.SEARCH_UNLIMITED_INT) && !EnumAssist.existTargetValue(Arrays.asList(Channel
				.values()), Channel::getFlag, channel)) {
			return this.pageDataEmpty(pageSize);
		}

		Specification<GeneralLog> specification = new Specification<GeneralLog>() {

			private static final long serialVersionUID = -490480767073213072L;

			@Override
			public Predicate toPredicate(@NonNull Root<GeneralLog> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				if (!StringAssist.isNullOrEmpty(message)) {
					list.add(criteriaBuilder.like(root.get(ReflectAssist.getFieldName(GeneralLog::getMessage)), StringAssist
							.merge("%", message, "%")));
				}

				if (!channel.equals(ConstantCollection.SEARCH_UNLIMITED_INT)) {
					list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(Account::getStatus)), channel));
				}

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, ReflectAssist.getFieldName(GeneralLog::getCreateTime));

		Page<GeneralLog> result = this.generalLogService.page(specification, pageable);

		List<SerializableData> list = result.getContent()
											.stream()
											.map(o -> {
												List<IGetter<GeneralLog>> getterList = new ArrayList<>();

												getterList.add(GeneralLog::getMessage);
												getterList.add(GeneralLog::getChannel);
												getterList.add(GeneralLog::getChannelNote);
												getterList.add(GeneralLog::getStatus);
												getterList.add(GeneralLog::getStatusNote);
												getterList.add(GeneralLog::getCreateTime);
												getterList.add(GeneralLog::getUpdateTime);

												SerializableData data = SerializableData.toSerializableData(o, getterList);

												data.append(ReflectAssist.getFriendlyIdName(GeneralLog.class), o.getId());

												return data;
											})
											.collect(Collectors.toList());

		int pageIndex = result.getNumber();
		long totalPages = result.getTotalPages();

		return this.pageData(list, pageIndex, pageSize, totalPages);
	}

	@ApiOperation(value = "获取一般日志", notes = "获取一般日志信息", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.GENERAL_LOG_GET, value = {
			@ApiJsonProperty(name = GlobalString.GENERAL_LOG_ID)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.GENERAL_LOG_GET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = GeneralLogController.CONTROLLER_DESCRIPTION + "一般日志详情", description = "获取一般日志信息", tag = "ef791176-510c-442b-aac6-7da67f8c51a4")
	public BaseResultData get(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = this.getParamData(json);

		long generalLogId = paramJson.getStringExByKey(GlobalString.GENERAL_LOG_ID, "0").toLong();

		Optional<GeneralLog> result = this.getGeneralLogService().get(generalLogId);

		if (result.isPresent()) {
			GeneralLog generalLog = result.get();

			List<IGetter<GeneralLog>> getterList = new ArrayList<>();

			getterList.add(GeneralLog::getMessage);
			getterList.add(GeneralLog::getContent);
			getterList.add(GeneralLog::getIp);
			getterList.add(GeneralLog::getChannel);
			getterList.add(GeneralLog::getChannelNote);
			getterList.add(GeneralLog::getStatus);
			getterList.add(GeneralLog::getStatusNote);
			getterList.add(GeneralLog::getId);
			getterList.add(GeneralLog::getCreateOperatorId);
			getterList.add(GeneralLog::getCreateTime);
			getterList.add(GeneralLog::getUpdateOperatorId);
			getterList.add(GeneralLog::getUpdateTime);

			SerializableData data = SerializableData.toSerializableData(generalLog, getterList);

			data.append(ReflectAssist.getFriendlyIdName(GeneralLog.class), generalLog.getId());

			return this.singleData(data);
		}

		return this.fail(ReturnDataCode.NoData.toMessage());
	}
}
