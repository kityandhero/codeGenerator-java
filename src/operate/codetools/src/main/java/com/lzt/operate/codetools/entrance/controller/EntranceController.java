package com.lzt.operate.codetools.entrance.controller;

import com.lzt.operate.web.controllers.BaseController;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;

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
    // @RequestMapping("/test")
    // @ResponseBody
    // public String index() {
    //     return corsConfig.getAccessControlAllowMethods();
    // }

    @RequestMapping("/entrance/signIn")
    @ResponseBody
    public HashMap<String, Serializable> signIn() {
        return this.success();
    }

    // @RequestMapping("/entrance/dataTest")
    // @ResponseBody
    // public HashMap<String, Serializable> dataTest() {
    //     return this.success();
    // }
    //
    // @RequestMapping("/entrance/listTest")
    // @ResponseBody
    // public HashMap<String, Serializable> listTest() {
    //     ArrayList<String> a = new ArrayList<String>();
    //     a.add("1");
    //     a.add("2");
    //     a.add("3");
    //     a.add("4");
    //
    //     return this.pageData(a);
    // }
}
