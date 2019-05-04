package com.lzt.operate.codetools.entrance.controller;

import com.lzt.operate.codetools.common.OperateBaseController;
import com.lzt.operate.codetools.domain.Operator;
import com.lzt.operate.codetools.repository.OperatorRepository;
import com.lzt.operate.extensions.StringEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

/**
 * @author lzt
 */
@RestController
@EnableConfigurationProperties
@RequestMapping("/entrance")
public class EntranceController extends OperateBaseController {

    private OperatorRepository operatorRepository;

    @Autowired
    public EntranceController(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    @RequestMapping("/signIn")
    public HashMap<String, Serializable> signIn(@RequestParam("name") String name, @RequestParam("password") String password) {
        Operator operator = new Operator();
        operator.setName(name);
        Example<Operator> example = Example.of(operator);

        Optional<Operator> op = this.operatorRepository.findOne(example);
        Operator searchResult = op.orElse(null);

        if (searchResult == null) {
            Date now = new Date();
            operator = new Operator();

            operator.setName(name);
            operator.setPassword(StringEx.ToMD5("123456").toString());
            operator.setFriendlyName(name);
            operator.setCreateTime(now);

            Operator operatorSave = this.operatorRepository.save(operator);

            return this.success(operatorSave);
        } else {
            return this.success(searchResult);
        }

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

    @RequestMapping("/signUp")
    public HashMap<String, Serializable> signUp() {
        return this.success();
    }
}
