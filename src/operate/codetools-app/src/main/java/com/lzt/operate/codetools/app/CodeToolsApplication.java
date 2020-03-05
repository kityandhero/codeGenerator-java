package com.lzt.operate.codetools.app;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.lzt.operate.swagger2.EnableSwagger2Doc;
import com.lzt.operate.utility.general.ConstantCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author luzhitao
 * <p>
 * other Bean
 * SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
 * MapperScan("com.lzt.operate.codetools.mapper")
 * MapperScan({"com.baidu.fsg.uid.worker.service.impl"})
 * EnableJpaRepositories("com.lzt.operate.codetools.repository")
 */
@EnableAsync
@SpringBootApplication
@EnableSwagger2Doc
@EnableCaching
@ComponentScan(basePackages = {"com.lzt.operate.codetools.app.**", "com.lzt.operate.codetools.dao.**", "com.baidu.fsg.uid.worker.**"})
@EnableJpaRepositories({"com.lzt.operate.codetools.dao.repositories", "com.baidu.fsg.uid.worker.repository"})
@EntityScan(basePackages = {"com.lzt.operate.codetools.entities", "com.baidu.fsg.uid.worker.entity"})
public class CodeToolsApplication {

	private static final Logger LOG = LoggerFactory.getLogger(CodeToolsApplication.class);

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
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

		JavaTimeModule javaTimeModule = new JavaTimeModule();

		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(ConstantCollection.DEFAULT_DATE_TIME_FORMAT)));
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(ConstantCollection.DEFAULT_DATE_FORMAT)));
		javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(ConstantCollection.DEFAULT_TIME_FORMAT)));
		javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(ConstantCollection.DEFAULT_DATE_TIME_FORMAT)));
		javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(ConstantCollection.DEFAULT_DATE_FORMAT)));
		javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(ConstantCollection.DEFAULT_TIME_FORMAT)));

		objectMapper.registerModule(javaTimeModule).registerModule(new ParameterNamesModule());

		SimpleModule simpleModule = new SimpleModule();

		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

		objectMapper.registerModule(simpleModule);

		return objectMapper;
	}
}
