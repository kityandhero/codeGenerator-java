package com.lzt.operate.utility.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luzhitao
 */
public class SerializableMap<String, Serializable> extends HashMap<String, Serializable> {

	SerializableMap() {
		super();
	}

	SerializableMap(Map<String, ? extends Serializable> data) {
		this();
		this.appends(data);
	}

	public void append(String key, Serializable value) {
		this.put(key, value);
	}

	public void appends(Map<String, ? extends Serializable> data) {
		this.putAll(data);
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
