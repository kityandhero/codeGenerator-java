package com.lzt.operate.code.generator.app.controllers.general;

import com.lzt.operate.code.generator.app.common.OperateBaseController;
import com.lzt.operate.code.generator.common.enums.AccountStatus;
import com.lzt.operate.code.generator.common.enums.Channel;
import com.lzt.operate.code.generator.common.enums.ConnectionType;
import com.lzt.operate.code.generator.common.enums.CustomConfigCategory;
import com.lzt.operate.code.generator.common.enums.DataColumnStatus;
import com.lzt.operate.code.generator.common.enums.DatabaseEncoding;
import com.lzt.operate.code.generator.common.enums.DatabaseType;
import com.lzt.operate.code.generator.common.enums.FileEncoding;
import com.lzt.operate.code.generator.common.enums.mybatis.DaoType;
import com.lzt.operate.utility.assists.EnumAssist;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ResultListData;
import com.lzt.operate.utility.pojo.ResultSingleData;
import com.lzt.operate.utility.pojo.SerializableData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/general/metaData")
@Api(tags = {"公共资源"})
public class MetaDataController extends OperateBaseController {

	@ApiOperation(value = "公共数据", notes = "公共数据资源", httpMethod = "POST")
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultListData.class)})
	@PostMapping(path = "/get", consumes = "application/json", produces = "application/json")
	public ResultSingleData get() {

		SerializableData result = new SerializableData();

		result.append(DaoType.META_KEY, EnumAssist.buildFlagDataCollection(
				DaoType.valuesToList(),
				DaoType::getFlag,
				DaoType::getName,
				DaoType::getDescription).toArray());

		result.append(Channel.META_KEY, EnumAssist.buildFlagDataCollection(
				Channel.valuesToList(),
				Channel::getFlag,
				Channel::getName,
				Channel::getDescription).toArray());

		result.append(CustomConfigCategory.META_KEY, EnumAssist.buildFlagDataCollection(
				CustomConfigCategory.valuesToList(),
				CustomConfigCategory::getFlag,
				CustomConfigCategory::getName,
				CustomConfigCategory::getDescription).toArray());

		result.append(DatabaseType.META_KEY, EnumAssist.buildFlagDataCollection(
				DatabaseType.valuesToList(),
				DatabaseType::getFlag,
				DatabaseType::getName,
				DatabaseType::getDescription).toArray());

		result.append(DatabaseEncoding.META_KEY, EnumAssist.buildFlagDataCollection(
				DatabaseEncoding.valuesToList(),
				DatabaseEncoding::getFlag,
				DatabaseEncoding::getName,
				DatabaseEncoding::getDescription).toArray());

		result.append(ConnectionType.META_KEY, EnumAssist.buildFlagDataCollection(
				ConnectionType.valuesToList(),
				ConnectionType::getFlag,
				ConnectionType::getName,
				ConnectionType::getDescription).toArray());

		result.append(AccountStatus.META_KEY, EnumAssist.buildFlagDataCollection(
				AccountStatus.valuesToList(),
				AccountStatus::getFlag,
				AccountStatus::getName,
				AccountStatus::getDescription).toArray());

		result.append(DataColumnStatus.META_KEY, EnumAssist.buildFlagDataCollection(
				DataColumnStatus.valuesToList(),
				DataColumnStatus::getFlag,
				DataColumnStatus::getName,
				DataColumnStatus::getDescription).toArray());

		result.append(FileEncoding.META_KEY, EnumAssist.buildFlagDataCollection(
				FileEncoding.valuesToList(),
				FileEncoding::getFlag,
				FileEncoding::getName,
				FileEncoding::getDescription).toArray());

		return this.singleData(result);
	}

}
