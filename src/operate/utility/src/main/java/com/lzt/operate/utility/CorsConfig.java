package com.lzt.operate.utility;

import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


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

    private Set<String> mergeToSet(String firstValue, String secordValue) {
        String v = Optional.of(firstValue).or("");
        Set<String> set = new HashSet<String>();


        Iterable<String> firstValueList = Splitter.on(',').split(v);
        Iterable<String> secordValueList = Splitter.on(',').split(Optional.of(secordValue).or(""));

        for (String item : firstValueList) {
            set.add(item);
        }

        for (String item : secordValueList) {
            set.add(item);
        }

        return set;
    }

    public Set<String> getAccessControlAllowOriginSet(String existingAllowOrigins) {
        return mergeToSet(existingAllowOrigins, allowSites);
    }

    public boolean getNeedAccessControlAllowCredentials() {
        return needAccessControlAllowCredentials;
    }

    public String getAccessControlAllowCredentials() {
        return allowSites;
    }

    public Set<String> getAccessControlAllowHeaderSet(String existingAllowHeaders) {

        return mergeToSet(existingAllowHeaders, accessControlAllowHeaders);
    }

    public String getAccessControlAllowMethods() {
        return allowSites;
    }
}


