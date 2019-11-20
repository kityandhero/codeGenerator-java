package com.lzt.operate.entities;

import com.lzt.operate.extensions.StringEx;
import com.lzt.operate.utility.ConvertAssist;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lzt
 */
@Data
public class CorsConfigEntity {

    /**
     * 允许的域名集合，以,分隔
     */
    protected String allowSites;

    protected boolean accessControlAllowCredentials;

    protected String accessControlAllowHeaders;

    protected String accessControlAllowMethods;

    private Set<String> mergeToSet(String firstValue, String secondValue) {
        StringEx first = new StringEx(firstValue);
        StringEx second = new StringEx(secondValue);

        Set<String> set = new HashSet<>();

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

    public List<String> getAccessControlAllowOriginList() {
        return this.getAccessControlAllowOriginList("");
    }

    private List<String> getAccessControlAllowOriginList(String existingAllowOrigins) {
        Set<String> set = this.mergeToSet(existingAllowOrigins, this.allowSites);

        return new ArrayList<>(set);
    }

    // endregion

    public boolean getAccessControlAllowCredentials() {
        return this.accessControlAllowCredentials;
    }

    //region AccessControlAllowHeader Methods

    public List<String> getAccessControlAllowHeaderList() {
        return this.getAccessControlAllowHeaderList("");
    }

    private List<String> getAccessControlAllowHeaderList(String existingAllowHeaders) {
        Set<String> set = this.mergeToSet(existingAllowHeaders, this.accessControlAllowHeaders);

        return new ArrayList<>(set);
    }

    //endregion

    public List<String> getAccessControlAllowMethodList() {
        return ConvertAssist.iterableToList(StringEx.split(this.accessControlAllowMethods.toUpperCase(), ','));
    }
}


