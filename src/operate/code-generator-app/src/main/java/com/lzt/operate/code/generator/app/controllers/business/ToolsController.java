package com.lzt.operate.code.generator.app.controllers.business;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.lzt.operate.code.generator.app.common.BaseOperateAuthController;
import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.code.generator.common.utils.GlobalString;
import com.lzt.operate.code.generator.common.utils.ModelNameCollection;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.utility.pojo.BaseResultData;
import com.lzt.operate.utility.pojo.ParamData;
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

import java.util.Map;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/tools")
@Api(tags = {"工具"})
public class ToolsController extends BaseOperateAuthController {

	@Autowired
	public ToolsController(
			LoadingCache<String, Object> loadingCache,
			CustomJsonWebTokenConfig customJsonWebTokenConfig) {
		super(loadingCache, customJsonWebTokenConfig);
	}

	@ApiOperation(value = "打开文件夹", notes = "打开文件夹", httpMethod = "POST")
	@ApiJsonObject(name = ModelNameCollection.TOOLS_OPEN_FOLDER, value = {
			@ApiJsonProperty(name = GlobalString.TOOLS_OPEN_FOLDER_FOLDER)},
			result = @ApiJsonResult({}))
	@ApiImplicitParams({
			@ApiImplicitParam(name = "json", required = true, dataType = ModelNameCollection.TOOLS_OPEN_FOLDER)
	})
	@ApiResponses({@ApiResponse(code = BaseResultData.CODE_ACCESS_SUCCESS, message = BaseResultData.MESSAGE_ACCESS_SUCCESS, response = ResultSingleData.class)})
	@PostMapping(path = "/openFolder", produces = "application/json")
	public ResultSingleData openFolder(@RequestBody Map<String, Object> json) {
		ParamData paramJson = getParamData(json);

		String folder = paramJson.getStringByKey(GlobalString.TOOLS_OPEN_FOLDER_FOLDER).trim();

		ExecutiveSimpleResult result = this.openFolder(folder);

		if (result.getSuccess()) {
			return this.successWithTimestamp();
		}

		return this.fail(result.getCode());
	}

}
