package com.lzt.operate.codetools.controllers.business;

import com.lzt.operate.codetools.common.OperateBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lzt
 */
@Controller
public class WorkbenchController extends OperateBaseController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

}
