package com.lzt.operate.code.generator.app.components;

import com.lzt.operate.utility.components.bases.JsonWebToken.BaseCustomJsonWebTokenConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JwtConfig
 *
 * @author luzhitao
 */
@ConfigurationProperties(prefix = "frame.jwt")
@Component
public class CustomJsonWebTokenConfig extends BaseCustomJsonWebTokenConfig {
}
