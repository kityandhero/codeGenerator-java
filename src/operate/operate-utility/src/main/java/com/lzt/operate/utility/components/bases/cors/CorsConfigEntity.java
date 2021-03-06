package com.lzt.operate.utility.components.bases.cors;

import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.extensions.StringEx;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author luzhitao
 */
public class CorsConfigEntity {

	/**
	 * 允许的域名集合，以,分隔
	 */
	protected String allowSites;

	protected boolean accessControlAllowCredentials;

	protected String accessControlAllowHeaders;

	protected String accessControlAllowMethods;

	public String getAllowSites() {
		return allowSites;
	}

	public void setAllowSites(String allowSites) {
		this.allowSites = allowSites;
	}

	public boolean isAccessControlAllowCredentials() {
		return accessControlAllowCredentials;
	}

	public String getAccessControlAllowHeaders() {
		return accessControlAllowHeaders;
	}

	public void setAccessControlAllowHeaders(String accessControlAllowHeaders) {
		this.accessControlAllowHeaders = accessControlAllowHeaders;
	}

	public String getAccessControlAllowMethods() {
		return accessControlAllowMethods;
	}

	public void setAccessControlAllowMethods(String accessControlAllowMethods) {
		this.accessControlAllowMethods = accessControlAllowMethods;
	}

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

	public List<String> getAccessControlAllowOriginList() {
		return getAccessControlAllowOriginList("");
	}

	//region AccessControlAllowOrigin Methods

	private List<String> getAccessControlAllowOriginList(String existingAllowOrigins) {
		Set<String> set = mergeToSet(existingAllowOrigins, allowSites);

		return new ArrayList<>(set);
	}

	public boolean getAccessControlAllowCredentials() {
		return accessControlAllowCredentials;
	}

	// endregion

	public void setAccessControlAllowCredentials(boolean accessControlAllowCredentials) {
		this.accessControlAllowCredentials = accessControlAllowCredentials;
	}

	//region AccessControlAllowHeader Methods

	public List<String> getAccessControlAllowHeaderList() {
		return getAccessControlAllowHeaderList("");
	}

	private List<String> getAccessControlAllowHeaderList(String existingAllowHeaders) {
		Set<String> set = mergeToSet(existingAllowHeaders, accessControlAllowHeaders);

		return new ArrayList<>(set);
	}

	//endregion

	public List<String> getAccessControlAllowMethodList() {
		return ConvertAssist.iterableToList(StringAssist.split(accessControlAllowMethods.toUpperCase(), ','));
	}
}


