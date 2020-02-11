package com.lzt.operate.codetools.app.components;

import com.lzt.operate.codetools.app.entity.CustomConfig;
import com.lzt.operate.codetools.app.service.CustomConfigService;
import com.lzt.operate.codetools.app.service.impl.CustomConfigServiceImpl;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * CustomConfig辅助方法集合
 *
 * @author luzhitao
 */
@Component
public class CustomConfigEffectiveVerify {

    private CustomConfigService repository;

    @Autowired
    public CustomConfigEffectiveVerify(CustomConfigServiceImpl repository) {
        this.repository = repository;

        this.checkDataIntegrity();
    }

    /**
     * 检测配饰数据的完整性
     */
    private void checkDataIntegrity() {
        this.checkLoginConfig();
    }

    /**
     * 检测时候启用账户登陆设置
     */
    private void checkLoginConfig() {
        final String needLogin = "needLogin";

        CustomConfig customConfig = new CustomConfig();
        customConfig.setName(needLogin);

        ExampleMatcher matcher = ExampleMatcher.matching()
                                               .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
                                               .withIgnorePaths("createTime", "value", "description");

        Example<CustomConfig> example = Example.of(customConfig, matcher);

        var optionalResult = this.repository.findOne(example);

        if (optionalResult.isPresent()) {
            customConfig = new CustomConfig();

            customConfig.setName(needLogin);
            customConfig.setValue("");
            customConfig.setDescription("请设置是否需要登陆使用，如数据需要保密，请启用账户登陆");

            this.repository.save(customConfig);
        } else {
            var value = customConfig.getValue();

            var set = new HashSet<String>();

            set.add("");
            set.add("0");
            set.add("1");

            if (!set.contains(value)) {
                customConfig.setValue("");

                this.repository.save(customConfig);
            }
        }
    }
}
