package com.lzt.operate.code.generator.app.components;

import com.lzt.operate.utility.components.bases.cors.CorsConfigEntity;
import com.lzt.operate.utility.io.support.YamlConfigFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author luzhitao
 */
@Component
@Configuration
@PropertySource(value = "classpath:cors.yml", factory = YamlConfigFactory.class)
@ConfigurationProperties(prefix = "cors")
public class CorsConfig extends CorsConfigEntity {
}
