package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.assists.DatabaseAssist;
import com.lzt.operate.codetools.app.common.BaseOperateAuthController;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.common.utils.GlobalString;
import com.lzt.operate.codetools.common.utils.ModelNameCollection;
import com.lzt.operate.codetools.dao.service.ConnectionConfigService;
import com.lzt.operate.codetools.dao.service.DataColumnService;
import com.lzt.operate.codetools.dao.service.impl.ConnectionConfigServiceImpl;
import com.lzt.operate.codetools.dao.service.impl.DataColumnServiceImpl;
import com.lzt.operate.codetools.entities.ConnectionConfig;
import com.lzt.operate.codetools.entities.DataColumnInfo;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.assists.StringAssist;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/dataColumn")
@Api(tags = {"数据库表列管理"})
public class DataColumnController extends BaseOperateAuthController {

	private static final String CONTROLLER_DESCRIPTION = "数据表列配置管理/";

	private ConnectionConfigService connectionConfigService;

	private DataColumnService dataColumnService;

	@Autowired
	public DataColumnController(CustomJsonWebTokenConfig customJsonWebTokenConfig, ConnectionConfigServiceImpl connectionConfigServiceImpl, DataColumnServiceImpl dataColumnService) {
		super(customJsonWebTokenConfig);

		this.connectionConfigService = connectionConfigServiceImpl;
		this.dataColumnService = dataColumnService;
	}

	public ConnectionConfigService getConnectionConfigService() {
		Optional<ConnectionConfigService> optional = Optional.ofNullable(this.connectionConfigService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("ConnectionConfigService获取失败");
	}

	public DataColumnService getDataColumnService() {
		Optional<DataColumnService> optional = Optional.ofNullable(this.dataColumnService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("DataColumnService获取失败");
	}

	@ApiOperation(value = "数据库表列列表", notes = "数据库表列列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATA_COLUMN_LIST, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_NAME)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATA_COLUMN_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/list", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "数据库表列列表", description = "数据库表列列表", tag = "51711b03-dc26-4dea-a3ed-a72dba610fab")
	public ResultListData list(@RequestBody Map<String, Serializable> json) throws Exception {
		ParamData paramJson = getParamData(json);

		Long connectionConfigId = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ID, "0").toLong();

		if (connectionConfigId <= 0) {
			return this.pageDataEmpty();
		}

		String name = paramJson.getStringByKey(GlobalString.DATA_TABLE_NAME);

		if (StringAssist.isNullOrEmpty(name)) {
			return this.listDataEmpty();
		}

		Optional<ConnectionConfig> optional = this.getConnectionConfigService()
												  .get(connectionConfigId);

		if (optional.isPresent()) {
			ConnectionConfig connectionConfig = optional.get();

			List<DataColumnInfo> list = DatabaseAssist.listTableColumn(connectionConfig, name);

			return this.listData(list);
		}

		return this.listDataEmpty();
	}

}
