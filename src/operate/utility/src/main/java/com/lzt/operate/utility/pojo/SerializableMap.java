package com.lzt.operate.utility.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luzhitao
 */
public class SerializableMap<String, Serializable> extends HashMap<String, Serializable> {

	private static final long serialVersionUID = 7303682875593752763L;

	SerializableMap() {
		super();
	}

	SerializableMap(Map<String, ? extends Serializable> data) {
		this();
		this.appends(data);
	}

	public SerializableMap<String, Serializable> append(String key, Serializable value) {
		this.put(key, value);

		return this;
	}

	public SerializableMap<String, Serializable> appends(Map<String, ? extends Serializable> data) {
		this.putAll(data);

		return this;
	}

	public java.lang.String serialize() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(this);
		} catch (Exception e) {
			return "{}";
		}
	}
}
