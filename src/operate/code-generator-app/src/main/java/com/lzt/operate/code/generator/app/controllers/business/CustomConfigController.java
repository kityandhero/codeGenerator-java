package com.lzt.operate.code.generator.app.controllers.business;

import com.lzt.operate.code.generator.app.common.BaseOperateAuthController;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.common.enums.Channel;
import com.lzt.operate.code.generator.common.enums.CustomConfigCollection;
import com.lzt.operate.code.generator.common.utils.GlobalString;
import com.lzt.operate.code.generator.common.utils.ModelNameCollection;
import com.lzt.operate.code.generator.dao.service.CustomConfigService;
import com.lzt.operate.code.generator.dao.service.impl.CustomConfigServiceImpl;
import com.lzt.operate.code.generator.entities.CustomConfig;
import com.lzt.operate.code.generator.custommessagequeue.customconfig.CustomConfigProducer;
import com.lzt.operate.code.generator.custommessagequeue.customconfig.CustomConfigProducerFactory;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.assists.IGetter;
import com.lzt.operate.utility.assists.ReflectAssist;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/customConfig")
@Api(tags = {"设置项管理"})
public class CustomConfigController extends BaseOperateAuthController {

	private static final String CONTROLLER_DESCRIPTION = "模块管理/";

	private CustomConfigService customConfigService;

	@Autowired
	public CustomConfigController(CustomJsonWebTokenConfig customJsonWebTokenConfig, CustomConfigServiceImpl customConfigService) {
		super(customJsonWebTokenConfig);

		this.customConfigService = customConfigService;
	}

	public CustomConfigService getCustomConfigService() {
		Optional<CustomConfigService> optional = Optional.ofNullable(this.customConfigService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("CustomConfigService获取失败");
	}

	@ApiOperation(value = "设置项列表", notes = "设置项列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CUSTOM_CONFIG_LIST, value = {
			@ApiJsonProperty(name = GlobalString.CUSTOM_CONFIG_CATEGORY)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.CUSTOM_CONFIG_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/list", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "设置项列表", description = "设置项列表", tag = "c44e3865-1fa0-48df-ad0a-bd3f79807a38")
	public ResultListData list(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		Integer category = paramJson.getStringExByKey(GlobalString.CUSTOM_CONFIG_CATEGORY).toInt();

		List<CustomConfig> customConfigList = this.getCustomConfigService().list((root, query, cb) -> {
			Predicate predicate = root.isNotNull();

			predicate = cb.and(predicate, cb.and(root.get(ReflectAssist.getFieldName(CustomConfig::getUuid))
													 .as(String.class)
													 .in(Arrays.stream(CustomConfigCollection.values())
															   .filter(o -> category.equals(o.getCategory().getFlag()))
															   .map(CustomConfigCollection::getUuid)
															   .toArray())));

			return predicate;
		});

		List<CustomConfig> customConfigSourceList = this.getCustomConfigService().listSource(category);

		customConfigSourceList = customConfigSourceList.stream().map(source -> {

			for (CustomConfig c : customConfigList) {
				if (source.getUuid().equals(c.getUuid())) {
					return c;
				}
			}

			return source;
		}).collect(Collectors.toList());

		List<SerializableData> list = customConfigSourceList
				.stream()
				.map(o -> {
					List<IGetter<CustomConfig>> getterList = new ArrayList<>();

					getterList.add(CustomConfig::getName);
					getterList.add(CustomConfig::getDescription);
					getterList.add(CustomConfig::getUuid);
					getterList.add(CustomConfig::getValue);
					getterList.add(CustomConfig::getChannel);
					getterList.add(CustomConfig::getChannelNote);
					getterList.add(CustomConfig::getStatus);
					getterList.add(CustomConfig::getStatusNote);
					getterList.add(CustomConfig::getCreateTime);
					getterList.add(CustomConfig::getUpdateTime);

					SerializableData data = SerializableData.toSerializableData(o, getterList);

					data.append(ReflectAssist.getFriendlyIdName(CustomConfig.class), o
							.getId());

					return data;
				})
				.collect(Collectors.toList());

		return this.listData(list);
	}

	@ApiOperation(value = "获取错误日志", notes = "获取错误日志信息", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CUSTOM_CONFIG_SET, value = {
			@ApiJsonProperty(name = GlobalString.CUSTOM_CONFIG_UUID),
			@ApiJsonProperty(name = GlobalString.CUSTOM_CONFIG_VALUE)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.CUSTOM_CONFIG_SET)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/set", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "错误日志详情", description = "获取错误日志信息", tag = "a0664bb2-75ff-406b-9463-9e5aae7af56e")
	public BaseResultData set(@RequestBody Map<String, Serializable> json) {
		ParamData paramJson = getParamData(json);

		String uuid = paramJson.getStringByKey(GlobalString.CUSTOM_CONFIG_UUID);
		String value = paramJson.getStringByKey(GlobalString.CUSTOM_CONFIG_VALUE);

		CustomConfig customConfig = new CustomConfig();

		customConfig.setUuid(uuid);
		customConfig.setValue(value);
		customConfig.setChannel(Channel.CodeGenerator);

		CustomConfigProducer customConfigProducer = CustomConfigProducerFactory.getInstance().getProducer();

		customConfigProducer.push(customConfig);

		return this.success();
	}

}
