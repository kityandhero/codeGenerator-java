package com.lzt.operate.codetools;

import com.lzt.operate.codetools.common.GlobalString;
import com.lzt.operate.swagger2.ModelCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2 extends WebMvcConfigurationSupport {
    //swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    @Bean
    public Docket createRestApi() {
        ModelCache.getInstance().setParamClass(GlobalString.class);

        List<Parameter> pars = new ArrayList<>();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.metaData())
                .globalOperationParameters(pars)
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.lzt.operate.codetools"))
                .paths(PathSelectors.any())
                .build();
    }

    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("codetools RESTful API")
                .description("\"codetools API 描述\"")
                .version("1.0")
                //创建人
                .contact(new Contact("lzt", "localhost:9090", "kityandhero@126.com"))
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
