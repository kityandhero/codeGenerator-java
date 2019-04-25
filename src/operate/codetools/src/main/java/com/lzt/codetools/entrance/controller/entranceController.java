package com.lzt.codetools.entrance.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class entranceController {
    @RequestMapping("/entrance/signIn")
    @ResponseBody
    public String signIn(){

        return "success";
    }
}
