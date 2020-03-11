package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.assists.DatabaseAssist;
import com.lzt.operate.codetools.app.common.BaseOperateAuthController;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.common.utils.GlobalString;
import com.lzt.operate.codetools.common.utils.ModelNameCollection;
import com.lzt.operate.codetools.dao.service.ConnectionConfigService;
import com.lzt.operate.codetools.dao.service.impl.ConnectionConfigServiceImpl;
import com.lzt.operate.codetools.entities.ConnectionConfig;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.permissions.NeedAuthorization;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ParamData;
import com.lzt.operate.utility.pojo.ResultListData;
import com.lzt.operate.utility.pojo.ResultSingleData;
import com.lzt.operate.utility.pojo.results.ExecutiveSimpleResult;
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
import java.util.Map;
import java.util.Optional;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/dataTable")
@Api(tags = {"数据库表管理"})
public class DataTableController extends BaseOperateAuthController {

	private static final String CONTROLLER_DESCRIPTION = "数据库表管理/";

	private ConnectionConfigService connectionConfigService;

	@Autowired
	public DataTableController(CustomJsonWebTokenConfig customJsonWebTokenConfig, ConnectionConfigServiceImpl connectionConfigServiceImpl) {
		super(customJsonWebTokenConfig);

		this.connectionConfigService = connectionConfigServiceImpl;
	}

	public ConnectionConfigService getAccessWayService() {
		Optional<ConnectionConfigService> optional = Optional.ofNullable(this.connectionConfigService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("ConnectionConfigService获取失败");
	}

	@ApiOperation(value = "数据库表列表", notes = "数据库表列表", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.DATA_TABLE_LIST, value = {
			@ApiJsonProperty(name = GlobalString.CONNECTION_CONFIG_ID),
			@ApiJsonProperty(name = GlobalString.DATA_TABLE_NAME),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_NO),
			@ApiJsonProperty(name = GlobalString.LIST_PAGE_SIZE)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.DATA_TABLE_LIST)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/pageList", consumes = "application/json", produces = "application/json")
	@NeedAuthorization(name = CONTROLLER_DESCRIPTION + "数据库表列表", description = "数据库表列表", tag = "c501156d-5ff4-4ebd-89f2-2a1692cef567", config = {"显示路径", "显示子权限"})
	public ResultListData pageList(@RequestBody Map<String, Serializable> json) throws Exception {
		ParamData paramJson = getParamData(json);

		Long connectionConfigId = paramJson.getStringExByKey(GlobalString.CONNECTION_CONFIG_ID, "0").toLong();

		if (connectionConfigId <= 0) {
			return this.pageDataEmpty();
		}

		Optional<ConnectionConfig> optional = this.getAccessWayService()
												  .get(connectionConfigId);

		if (optional.isPresent()) {
			ConnectionConfig connectionConfig = optional.get();

			ExecutiveSimpleResult result = DatabaseAssist.tryConnection(connectionConfig);

			if (result.getSuccess()) {
				return this.pageDataEmpty();
			} else {
				return this.pageDataEmpty();
			}
		}

		return this.pageDataEmpty();
	}

}
