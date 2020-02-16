package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.common.OperateBaseController;
import com.lzt.operate.codetools.app.enums.DbType;
import com.lzt.operate.codetools.common.enums.OperatorStatus;
import com.lzt.operate.utility.assists.EnumAssist;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ResultListData;
import com.lzt.operate.utility.pojo.SerializableData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.var;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
	public BaseResultData get(@RequestBody Map<String, String> connectionJson) {

		var result = new SerializableData();

		result.append("dbType", EnumAssist.buildFlagDataCollection(
				DbType.values(),
				DbType::getFlag,
				DbType::getName,
				DbType::getDescription).toArray());

		result.append("operatorStatus", EnumAssist.buildFlagDataCollection(
				OperatorStatus.values(),
				OperatorStatus::getFlag,
				OperatorStatus::getName,
				OperatorStatus::getDescription).toArray());

		return this.singleData(result);
	}

}
