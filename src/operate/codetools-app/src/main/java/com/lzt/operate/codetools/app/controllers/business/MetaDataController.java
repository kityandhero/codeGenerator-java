package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.common.OperateBaseController;
import com.lzt.operate.codetools.app.enums.ConnectionType;
import com.lzt.operate.codetools.app.enums.DatabaseEncoding;
import com.lzt.operate.codetools.app.enums.DatabaseType;
import com.lzt.operate.codetools.common.enums.AccountStatus;
import com.lzt.operate.codetools.common.enums.Channel;
import com.lzt.operate.codetools.common.enums.CustomConfigCategory;
import com.lzt.operate.utility.assists.EnumAssist;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ResultListData;
import com.lzt.operate.utility.pojo.SerializableData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/metaData")
@Api(tags = {"公共资源"})
public class MetaDataController extends OperateBaseController {

	@ApiOperation(value = "公共数据", notes = "公共数据资源", httpMethod = "POST")
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultListData.class)})
	@PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	public BaseResultData get() {

		SerializableData result = new SerializableData();

		result.append(Channel.META_KEY, EnumAssist.buildFlagDataCollection(
				Arrays.asList(Channel.values()),
				Channel::getFlag,
				Channel::getName,
				Channel::getDescription).toArray());

		result.append(CustomConfigCategory.META_KEY, EnumAssist.buildFlagDataCollection(
				Arrays.asList(CustomConfigCategory.values()),
				CustomConfigCategory::getFlag,
				CustomConfigCategory::getName,
				CustomConfigCategory::getDescription).toArray());

		result.append(DatabaseType.META_KEY, EnumAssist.buildFlagDataCollection(
				Arrays.asList(DatabaseType.values()),
				DatabaseType::getFlag,
				DatabaseType::getName,
				DatabaseType::getDescription).toArray());

		result.append(DatabaseEncoding.META_KEY, EnumAssist.buildFlagDataCollection(
				Arrays.asList(DatabaseEncoding.values()),
				DatabaseEncoding::getFlag,
				DatabaseEncoding::getName,
				DatabaseEncoding::getDescription).toArray());

		result.append(ConnectionType.META_KEY, EnumAssist.buildFlagDataCollection(
				Arrays.asList(ConnectionType.values()),
				ConnectionType::getFlag,
				ConnectionType::getName,
				ConnectionType::getDescription).toArray());

		result.append(AccountStatus.META_KEY, EnumAssist.buildFlagDataCollection(
				Arrays.asList(AccountStatus.values()),
				AccountStatus::getFlag,
				AccountStatus::getName,
				AccountStatus::getDescription).toArray());

		return this.singleData(result);
	}

}
