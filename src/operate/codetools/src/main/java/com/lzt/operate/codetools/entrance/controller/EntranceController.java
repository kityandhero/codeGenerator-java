package com.lzt.operate.codetools.entrance.controller;

import com.lzt.operate.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzt
 */
@RestController
@EnableConfigurationProperties
public class EntranceController extends BaseController {

    // private CorsConfig corsConfig;

    // @Autowired
    // public EntranceController(CorsConfig corsConfig) {
    //     this.corsConfig = corsConfig;
    // }
    //
    // @RequestMapping("/")
    // @ResponseBody
    // public String index() {
    //     return corsConfig.getAccessControlAllowMethods();
    // }

    @RequestMapping("/entrance/signIn")
    @ResponseBody
    public String signIn() {
        return "success";
    }
}
