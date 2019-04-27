package com.lzt.operate.utility;

import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


/**
 * @author lzt
 */
public class CorsConfigEntity {

    /**
     * 允许的域名集合，以,分隔
     */
    @Setter
    protected String allowSites;

    @Setter
    protected boolean needAccessControlAllowCredentials;

    @Setter
    protected String accessControlAllowCredentials;

    @Setter
    protected String accessControlAllowHeaders;

    @Setter
    protected String accessControlAllowMethods;

    private Set<String> mergeToSet(String firstValue, String secordValue) {
        String v = Optional.of(firstValue).or("");
        Set<String> set = new HashSet<String>();


        Iterable<String> firstValueList = Splitter.on(',').split(v);
        Iterable<String> secondValueList = Splitter.on(',').split(Optional.of(secordValue).or(""));

        for (String item : firstValueList) {
            set.add(item);
        }

        for (String item : secondValueList) {
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
        return accessControlAllowCredentials;
    }

    public Set<String> getAccessControlAllowHeaderSet(String existingAllowHeaders) {

        return mergeToSet(existingAllowHeaders, accessControlAllowHeaders);
    }

    public String getAccessControlAllowMethods() {
        return accessControlAllowMethods;
    }
}


