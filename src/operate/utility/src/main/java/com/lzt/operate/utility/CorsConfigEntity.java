package com.lzt.operate.utility;

import com.lzt.operate.extensions.StringEx;
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
        StringEx first = new StringEx(firstValue);
        StringEx second = new StringEx(secondValue);

        Set<String> set = new HashSet<String>();

        Iterable<String> firstSplitList = first.split(',');
        Iterable<String> secondSplitList = second.split(',');

        for (String item : firstSplitList) {
            set.add(item);
        }

        for (String item : secondSplitList) {
            set.add(item);
        }

        return set;
    }

    //region AccessControlAllowOrigin Methods

    public String getAccessControlAllowOrigins() {
        return this.getAccessControlAllowOrigins("");
    }

    private String getAccessControlAllowOrigins(String existingAllowOrigins) {
        Set<String> set = this.getAccessControlAllowOriginSet(existingAllowOrigins);

        return StringEx.join(",", set).toString();
    }

    public Set<String> getAccessControlAllowOriginSet() {
        return this.getAccessControlAllowOriginSet("");
    }

    private Set<String> getAccessControlAllowOriginSet(String existingAllowOrigins) {
        return this.mergeToSet(existingAllowOrigins, this.allowSites);
    }

    // endregion

    public boolean getAccessControlAllowCredentials() {
        return this.accessControlAllowCredentials;
    }

    //region AccessControlAllowHeader Methods

    public String getAccessControlAllowHeaders() {
        return this.getAccessControlAllowHeaders("");
    }

    private String getAccessControlAllowHeaders(String existingAllowOrigins) {
        Set<String> set = this.getAccessControlAllowHeaderSet(existingAllowOrigins);

        return com.google.common.base.Joiner.on(",").join(set);
    }

    public Set<String> getAccessControlAllowHeaderSet() {
        return this.getAccessControlAllowHeaderSet("");
    }

    private Set<String> getAccessControlAllowHeaderSet(String existingAllowHeaders) {
        return this.mergeToSet(existingAllowHeaders, this.accessControlAllowHeaders);
    }

    //endregion

    public String getAccessControlAllowMethods() {
        return this.accessControlAllowMethods;
    }
}


