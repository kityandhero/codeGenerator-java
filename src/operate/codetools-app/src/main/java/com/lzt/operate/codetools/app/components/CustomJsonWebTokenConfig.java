package com.lzt.operate.codetools.app.components;

import com.lzt.operate.utility.pojo.BaseCustomJsonWebTokenConfig;
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
