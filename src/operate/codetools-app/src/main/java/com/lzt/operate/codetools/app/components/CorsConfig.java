package com.lzt.operate.codetools.app.components;

import com.lzt.operate.utility.pojo.CorsConfigEntity;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author luzhitao
 */
@Component
@Configuration
@PropertySource(value = "classpath:cors.properties")
@ConfigurationProperties(prefix = "cors")
public class CorsConfig extends CorsConfigEntity {
}
