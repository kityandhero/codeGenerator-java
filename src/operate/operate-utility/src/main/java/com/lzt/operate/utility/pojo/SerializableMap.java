package com.lzt.operate.utility.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.lzt.operate.utility.assists.ConvertAssist;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author luzhitao
 */
public class SerializableMap {

	private static final long serialVersionUID = 7303682875593752763L;

	private HashMultimap<String, Object> multimap;

	SerializableMap(Map<String, Object> data) {
		this();

		this.appendMap(data);
	}

	SerializableMap() {
		super();

		multimap = HashMultimap.create();
	}

	public HashMultimap<String, Object> getMultimap() {
		return this.multimap;
	}

	public SerializableMap append(String key, Object value) {
		this.multimap.put(key, value);

		return this;
	}

	public <T> SerializableMap appendList(String key, List<T> list) {
		List<java.lang.Object> objectList = ConvertAssist.toObjectList(list);

		for (Object o : objectList) {
			this.multimap.put(key, o);
		}

		return this;
	}

	public SerializableMap appendMap(Map<String, Object> data) {
		if (Optional.ofNullable(data).isPresent()) {

			data.forEach((key, value) -> this.multimap.put(key, value));
		}

		return this;
	}

	public SerializableMap appendMultimap(Multimap<String, Object> data) {
		if (Optional.ofNullable(data).isPresent()) {

			data.forEach((key, value) -> this.multimap.put(key, value));
		}

		return this;
	}

	public java.lang.String serialize() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(ConvertAssist.toObjectMix(this));
		} catch (Exception e) {
			return "{}";
		}
	}

}
