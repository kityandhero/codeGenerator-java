package com.lzt.operate.codetools.swagger2.handler;

import com.fasterxml.classmate.TypeResolver;
import com.lzt.operate.swagger2.handler.ParametersReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.TypeNameExtractor;
import springfox.documentation.spring.web.DescriptionResolver;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ParametersReaderEx extends ParametersReader {
    @Autowired
    public ParametersReaderEx(DescriptionResolver descriptions, TypeNameExtractor nameExtractor, TypeResolver resolver) {
        super(descriptions, nameExtractor, resolver);
    }
}
