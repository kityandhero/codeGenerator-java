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
    protected boolean accessControlAllowCredentials;

    @Setter
    protected String accessControlAllowHeaders;

    @Setter
    protected String accessControlAllowMethods;

    private Set<String> mergeToSet(String firstValue, String secondValue) {
        String v = Optional.of(firstValue).or("");
        Set<String> set = new HashSet<String>();

        Iterable<String> firstValueList = Splitter.on(',').split(v);
        Iterable<String> secondValueList = Splitter.on(',').split(Optional.of(secondValue).or(""));

        for (String item : firstValueList) {
            set.add(item);
        }

        for (String item : secondValueList) {
            set.add(item);
        }

        return set;
    }

    //region AccessControlAllowOrigin Methods

    public String getAccessControlAllowOrigins() {
        return getAccessControlAllowOrigins("");
    }

    private String getAccessControlAllowOrigins(String existingAllowOrigins) {
        Set<String> set = this.getAccessControlAllowOriginSet(existingAllowOrigins);

        return com.google.common.base.Joiner.on(",").join(set);
    }

    public Set<String> getAccessControlAllowOriginSet() {
        return getAccessControlAllowOriginSet("");
    }

    private Set<String> getAccessControlAllowOriginSet(String existingAllowOrigins) {
        return mergeToSet(existingAllowOrigins, allowSites);
    }

    // endregion


    public boolean getAccessControlAllowCredentials() {
        return accessControlAllowCredentials;
    }

    //region AccessControlAllowHeader Methods

    public String getAccessControlAllowHeaders() {
        return getAccessControlAllowHeaders("");
    }

    private String getAccessControlAllowHeaders(String existingAllowOrigins) {
        Set<String> set = this.getAccessControlAllowHeaderSet(existingAllowOrigins);

        return com.google.common.base.Joiner.on(",").join(set);
    }

    public Set<String> getAccessControlAllowHeaderSet() {
        return getAccessControlAllowHeaderSet("");
    }

    private Set<String> getAccessControlAllowHeaderSet(String existingAllowHeaders) {
        return mergeToSet(existingAllowHeaders, accessControlAllowHeaders);
    }

    //endregion


    public String getAccessControlAllowMethods() {
        return accessControlAllowMethods;
    }
}


