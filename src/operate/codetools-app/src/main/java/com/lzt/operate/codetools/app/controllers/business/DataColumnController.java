package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.common.BaseOperateAuthController;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.dao.service.DataColumnService;
import com.lzt.operate.codetools.dao.service.impl.DataColumnServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	private DataColumnService dataColumnService;

	@Autowired
	public DataColumnController(CustomJsonWebTokenConfig customJsonWebTokenConfig, DataColumnServiceImpl dataColumnService) {
		super(customJsonWebTokenConfig);

		this.dataColumnService = dataColumnService;
	}

	public DataColumnService getDataColumnService() {
		Optional<DataColumnService> optional = Optional.ofNullable(this.dataColumnService);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("DataColumnService获取失败");
	}

}
