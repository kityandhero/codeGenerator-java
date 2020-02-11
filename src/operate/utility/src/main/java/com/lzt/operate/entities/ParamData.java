package com.lzt.operate.entities;

import com.lzt.operate.extensions.StringEx;
import com.lzt.operate.utility.StringAssist;
import lombok.var;

import java.io.Serializable;
import java.util.Map;

/**
 * @author lzt
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
		var v = get(key);

		return new StringEx(StringAssist.isNullOrEmpty(v.toString()) ? defaultValue : v.toString());
	}

	public boolean isNullOrEmpty(String key) {
		var v = get(key);

		return StringAssist.isNullOrEmpty((String) v);
	}
}