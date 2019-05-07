package com.lzt.operate.codetools.swagger2.handler;

import com.fasterxml.classmate.TypeResolver;
import com.lzt.operate.swagger2.handler.ResponseMessageReader;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.TypeNameExtractor;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

@Primary
@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 3)
public class ResponseMessageReaderEx extends ResponseMessageReader {
    public ResponseMessageReaderEx(TypeNameExtractor typeNameExtractor, TypeResolver typeResolver) {
        super(typeNameExtractor, typeResolver);
    }
}
