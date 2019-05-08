package com.lzt.operate.codetools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lzt.operate.codetools.common.CorsConfig;
import com.lzt.operate.swagger2.EnableSwagger2Doc;
import com.lzt.operate.web.filters.CorsCustomFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// import org.mybatis.spring.annotation.MapperScan;

/**
 * @author lzt
 */
// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
// @MapperScan("com.lzt.operate.codetools.mapper")
@SpringBootApplication
@EnableJpaRepositories("com.lzt.operate.codetools.repository")
@EnableSwagger2Doc
public class CodeToolsApplication {

    private static final Logger _LOG = LoggerFactory.getLogger(CodeToolsApplication.class);

    private CorsConfig corsConfig;

    @Autowired
    public CodeToolsApplication(CorsConfig corsConfig) {
        this.corsConfig = corsConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(CodeToolsApplication.class, args);
    }

    @Bean
    @Description("全局跨域配置")
    public FilterRegistrationBean<CorsCustomFilter> corsAllUrlFilterRegistration() {
        FilterRegistrationBean<CorsCustomFilter> registration = new FilterRegistrationBean<>(new CorsCustomFilter(this.corsConfig));
        registration.addUrlPatterns("/*");
        registration.setName("corsAllUrlFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}
