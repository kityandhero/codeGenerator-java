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

	public StringEx getStringExByKey(String key) {
		return getStringExByKey(key, "");
	}

	public StringEx getStringExByKey(String key, String defaultValue) {
		String v = getStringByKey(key, defaultValue);

		return new StringEx(StringAssist.isNullOrEmpty(v) ? defaultValue : v);
	}

	public String getStringByKey(String key) {
		return getStringByKey(key, "");
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