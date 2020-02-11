package com.lzt.operate.utility.pojo;

import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.extensions.StringEx;
import lombok.var;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

/**
 * @author luzhitao
 */
public class ParamData extends SerializableMap<String, Serializable> {

	private static final long serialVersionUID = -4972871655628192893L;

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
		var v = Optional.ofNullable(get(key)).orElse(defaultValue);

		return new StringEx(StringAssist.isNullOrEmpty(v.toString()) ? defaultValue : v.toString());
	}

	public String getStringByKey(String key) {
		return getStringByKey(key, "");
	}

	public String getStringByKey(String key, String defaultValue) {
		var v = Optional.ofNullable(get(key)).orElse(defaultValue);

		return StringAssist.isNullOrEmpty(v.toString()) ? defaultValue : v.toString();
	}

	public boolean isNullOrEmpty(String key) {
		var v = get(key);

		return StringAssist.isNullOrEmpty((String) v);
	}
}