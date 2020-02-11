package com.lzt.operate.codetools.app.controllers.business;

import com.lzt.operate.codetools.app.common.OperateBaseController;
import com.lzt.operate.codetools.app.repositories.OperatorRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luzhitao
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/business/operator")
@Api(tags = {"操作者信息"})
public class OperatorController extends OperateBaseController {

	private OperatorRepository operatorRepository;

	@Autowired
	public OperatorController(OperatorRepository operatorRepository) {
		this.operatorRepository = operatorRepository;
	}

}
