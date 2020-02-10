package com.lzt.operate.codetools.components;

import com.lzt.operate.entities.BaseCustomJsonWebTokenConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JwtConfig
 *
 * @author lzt
 */
@ConfigurationProperties(prefix = "frame.jwt")
@Component
public class CustomJsonWebTokenConfig extends BaseCustomJsonWebTokenConfig {
}
