package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.common.BaseOperateAuthController;
import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.dao.service.DataColumnService;
import com.lzt.operate.codetools.dao.service.impl.DataColumnServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * @author luzhitao
 */
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
