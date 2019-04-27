package com.lzt.operate.utility;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author lzt
 */
@Component
@PropertySource(value = "classpath:cors.properties")
@ConfigurationProperties(prefix = "cors")
public class CorsConfig {

    /**
     * 允许的域名集合，以,分隔
     */
    private String allowSites;
    private boolean needAccessControlAllowCredentials;
    private String accessControlAllowCredentials;
    private String accessControlAllowHeaders;
    private String accessControlAllowMethods;

    public String getAllowSites() {
        return allowSites;
    }

    public String getNeedAccessControlAllowCredentials() {
        return allowSites;
    }

    public String getAccessControlAllowCredentials() {
        return allowSites;
    }

    public String getAccessControlAllowHeaders() {
        return allowSites;
    }

    public String getAccessControlAllowMethods() {
        return allowSites;
    }
}


