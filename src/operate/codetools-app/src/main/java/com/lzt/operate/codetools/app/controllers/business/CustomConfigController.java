package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.common.BaseOperateAuthController;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.common.enums.CustomConfigCollection;
import com.lzt.operate.codetools.common.utils.GlobalString;
import com.lzt.operate.codetools.common.utils.ModelNameCollection;
import com.lzt.operate.codetools.dao.service.CustomConfigService;
import com.lzt.operate.codetools.dao.service.impl.CustomConfigServiceImpl;
import com.lzt.operate.codetools.entities.CustomConfig;
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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

	@ApiOperation(value = "设置列表", notes = "设置列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.CUSTOM_CONFIG_LIST, value = {
			@ApiJsonProperty(name = GlobalString.CUSTOM_CONFIG_CATEGORY)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.CUSTOM_CONFIG_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/list", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "设置列表", description = "设置列表", tag = "c44e3865-1fa0-48df-ad0a-bd3f79807a38")
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

		List<SerializableData> list = customConfigList
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

}
