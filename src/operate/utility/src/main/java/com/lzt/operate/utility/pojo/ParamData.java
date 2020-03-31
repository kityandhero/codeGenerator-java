package com.lzt.operate.utility.pojo;

import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.extensions.StringEx;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author luzhitao
 */
public class ParamData extends SerializableMap<String, Serializable> {

	public ParamData() {
		super();
	}

	public ParamData(Map<String, ? extends Serializable> data) {
		super(data);
	}

	public StringEx getStringExByKey(String key) {
		return getStringExByKey(key, "");
	}

	public StringEx getStringExByKey(String key, String defaultValue) {
		HashSet<Serializable> defaultData = new HashSet<>();

		defaultData.add(defaultValue);

		Object v = Optional.ofNullable(this.getMultimap().get(key)).orElse(defaultData);

		return new StringEx(StringAssist.isNullOrEmpty(v.toString()) ? defaultValue : v.toString());
	}

	public String getStringByKey(String key) {
		return getStringByKey(key, "");
	}

	public String getStringByKey(String key, String defaultValue) {

		HashSet<Serializable> defaultData = new HashSet<>();

		defaultData.add(defaultValue);

		Object v = Optional.ofNullable(this.getMultimap().get(key)).orElse(defaultData);

		return StringAssist.isNullOrEmpty(v.toString()) ? defaultValue : v.toString();
	}

	public boolean isNullOrEmpty(String key) {
		Set<Serializable> set = this.getMultimap().get(key);

		if (set.isEmpty()) {
			return true;
		}

		Object v = set.toArray()[0];

		return StringAssist.isNullOrEmpty((String) v);
	}
}