package com.lzt.operate.utility.pojo;

import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.extensions.StringEx;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author luzhitao
 */
public class ParamData extends SerializableMap {

	public ParamData() {
		super();
	}

	public ParamData(Map<String, Object> data) {
		super(data);
	}

	public boolean existKey(String key) {
		return this.getMultimap().containsKey(key);
	}

	public StringEx getStringExByKey(String key) {
		return this.getStringExByKey(key, "");
	}

	public StringEx getStringExByKey(String key, String defaultValue) {
		String v = this.getStringByKey(key, defaultValue);

		return new StringEx(StringAssist.isNullOrEmpty(v) ? defaultValue : v);
	}

	public String getStringByKey(String key) {
		return this.getStringByKey(key, "");
	}

	public String getStringByKey(String key, String defaultValue) {

		HashSet<Object> defaultData = new HashSet<>();

		defaultData.add(defaultValue);

		Set<Object> set = Optional.ofNullable(this.getMultimap().get(key)).orElse(defaultData);

		if (set.isEmpty()) {
			return defaultValue;
		}

		Object v = set.toArray()[0];

		return StringAssist.isNullOrEmpty(v.toString()) ? defaultValue : v.toString();
	}

	public boolean isNullOrEmpty(String key) {
		Set<Object> set = this.getMultimap().get(key);

		if (set.isEmpty()) {
			return true;
		}

		Object v = set.toArray()[0];

		return StringAssist.isNullOrEmpty((String) v);
	}
}