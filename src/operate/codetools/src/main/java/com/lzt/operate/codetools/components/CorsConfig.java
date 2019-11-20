package com.lzt.operate.codetools.components;

import com.lzt.operate.entities.CorsConfigEntity;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author lzt
 */
@Component
@Configuration
@PropertySource(value = "classpath:cors.properties")
@ConfigurationProperties(prefix = "cors")
public class CorsConfig extends CorsConfigEntity {
}
