package com.lzt.operate.code.generator.app.controllers.business;

import com.lzt.operate.code.generator.app.common.OperateBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author luzhitao
 */
@Controller
public class WorkbenchController extends OperateBaseController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

}
