package com.lzt.operate.codetools.entrance.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzt
 */
@RestController
public class EntranceController {
    // @RequestMapping("/")
    // @ResponseBody
    // public String index(){
    //
    //     return "index";
    // }

    @RequestMapping("/entrance/signIn")
    @ResponseBody
    public String signIn() {

        return "success";
    }
}
