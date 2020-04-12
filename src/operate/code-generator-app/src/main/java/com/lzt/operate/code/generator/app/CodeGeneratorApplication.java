package com.lzt.operate.code.generator.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzt.operate.code.generator.common.jackson.ObjectMapperAssist;
import com.lzt.operate.swagger2.EnableSwagger2Doc;
import com.lzt.operate.utility.net.CustomServletRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author luzhitao
 * <p>
 * other Bean
 * SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
 * MapperScan("com.lzt.operate.code.generator.mapper")
 * MapperScan({"com.baidu.fsg.uid.worker.service.impl"})
 * EnableJpaRepositories("com.lzt.operate.code.generator.repository")
 */
@EnableAsync
@SpringBootApplication
@EnableSwagger2Doc
@EnableCaching
@ComponentScan(basePackages = {"com.lzt.operate.code.generator.app.**", "com.lzt.operate.code.generator.dao.**", "com.baidu.fsg.uid.worker.**"})
@EnableJpaRepositories({"com.lzt.operate.code.generator.dao.repositories", "com.baidu.fsg.uid.worker.repository"})
@EntityScan(basePackages = {"com.lzt.operate.code.generator.entities", "com.baidu.fsg.uid.worker.entity"})
public class CodeGeneratorApplication implements WebMvcConfigurer {

    private static final Logger LOG = LoggerFactory.getLogger(CodeGeneratorApplication.class);

    // private CorsConfig corsConfig;

    // @Autowired
    // public CodeToolsApplication(CorsConfig corsConfig) {
    //     this.corsConfig = corsConfig;
    // }

    public static void main(String[] args) {
        SpringApplication.run(CodeGeneratorApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/operationPanel").setViewName("index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public FilterRegistrationBean<CustomServletRequestFilter> corsAllUrlFilterRegistration() {
        FilterRegistrationBean<CustomServletRequestFilter> registration = new FilterRegistrationBean<>(new CustomServletRequestFilter());
        registration.addUrlPatterns("/*");
        registration.setName("customServletRequestFilter");
        registration.setOrder(1);
        return registration;
    }

    /**
     * 配置spring boot内嵌的Jackson序列化与反序列化类型映射
     * <p>
     * -日期配置：
     * map.put(LocalDateTime.class, localDateTimeSerializer());
     * Spring Boot 提供了 spring.jackson.date-format配置可以让我们进行日期格式化，
     * 但它只能格式化 java.util.Date。
     * 定义一个配置类，在配置类注入Bean 实现日期全局格式化，同时还兼顾了 Date 和 LocalDateTime 并存。
     * 需要配置
     * spring:
     * jackson:
     * date-format: yyyy-MM-dd HH:mm:ss
     * time-zone: GMT+8
     * <p>
     * -Long类型转为字符串：
     * map.put(Long.class, ToStringSerializer.instance);
     * 解决Long类型数据返回到前端精度丢失问题
     */
    @Bean
    public ObjectMapper objectMapper() {
        return ObjectMapperAssist.createObjectMapper();
    }
}
