package com.lzt.operate.codetools.workbench.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lzt
 */
@Controller
public class WorkbenchController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String sayHello() {
        return "main";
    }

}
