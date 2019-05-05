package com.lzt.operate.codetools;

import com.lzt.operate.utility.ReturnData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {
    //swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    @Bean
    public Docket createRestApi() {
        List<Parameter> pars = new ArrayList<>();

        ReturnData success = ReturnData.createSuccessData();
        ReturnData paramError = ReturnData.createParamErrorData("参数名", "错误描述");


        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(success.code)
                                                            .message(success.message)
                                                            .responseModel(new ModelRef(ReturnData.class.getTypeName()))
                                                            .build());
        responseMessageList.add(new ResponseMessageBuilder().code(paramError.code)
                                                            .message(paramError.message)
                                                            .responseModel(new ModelRef(ReturnData.class.getTypeName()))
                                                            .build());
        responseMessageList.add(new ResponseMessageBuilder().code(404)
                                                            .message("找不到资源")
                                                            .responseModel(new ModelRef(ReturnData.class.getTypeName()))
                                                            .build());
        responseMessageList.add(new ResponseMessageBuilder().code(409)
                                                            .message("业务逻辑异常")
                                                            .responseModel(new ModelRef(ReturnData.class.getTypeName()))
                                                            .build());
        responseMessageList.add(new ResponseMessageBuilder().code(422)
                                                            .message("参数校验异常")
                                                            .responseModel(new ModelRef(ReturnData.class.getTypeName()))
                                                            .build());
        responseMessageList.add(new ResponseMessageBuilder().code(500)
                                                            .message("服务器内部错误")
                                                            .responseModel(new ModelRef(ReturnData.class.getTypeName()))
                                                            .build());
        responseMessageList.add(new ResponseMessageBuilder().code(503)
                                                            .message("Hystrix异常")
                                                            .responseModel(new ModelRef(ReturnData.class.getTypeName()))
                                                            .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .globalOperationParameters(pars)
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.lzt.operate.codetools"))
                .paths(PathSelectors.any())
                .build();
    }

    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot 测试使用 Swagger2 构建RESTful API")
                //创建人
                .contact(new Contact("Gentle", "localhost:8080", "hh@qq.com"))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }
}
