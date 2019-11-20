package com.lzt.operate.codetools;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.lzt.operate.codetools.util.Constants;
import com.lzt.operate.swagger2.EnableSwagger2Doc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    // private CorsConfig corsConfig;

    // @Autowired
    // public CodeToolsApplication(CorsConfig corsConfig) {
    //     this.corsConfig = corsConfig;
    // }

    public static void main(String[] args) {
        SpringApplication.run(CodeToolsApplication.class, args);
    }

    // @Bean
    // @Description("全局跨域配置")
    // public FilterRegistrationBean<CorsCustomFilter> corsAllUrlFilterRegistration() {
    //     FilterRegistrationBean<CorsCustomFilter> registration = new FilterRegistrationBean<>(new CorsCustomFilter(this.corsConfig));
    //     registration.addUrlPatterns("/*");
    //     registration.setName("corsAllUrlFilter");
    //     registration.setOrder(1);
    //     return registration;
    // }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

        JavaTimeModule javaTimeModule = new JavaTimeModule();

        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_DATE_FORMAT)));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_DATE_FORMAT)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_TIME_FORMAT)));

        objectMapper.registerModule(javaTimeModule).registerModule(new ParameterNamesModule());

        return objectMapper;
    }
}
