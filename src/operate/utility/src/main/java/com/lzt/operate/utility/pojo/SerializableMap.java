package com.lzt.operate.utility.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author luzhitao
 */
public class SerializableMap<String, Serializable> {

	private static final long serialVersionUID = 7303682875593752763L;

	private HashMultimap<String, Serializable> multimap;

	SerializableMap(Map<String, ? extends Serializable> data) {
		this();
		this.appendMap(data);
	}

	SerializableMap() {
		super();

		multimap = HashMultimap.create();
	}

	public HashMultimap<String, Serializable> getMultimap() {
		return this.multimap;
	}

	public SerializableMap<String, Serializable> append(String key, Serializable value) {
		this.multimap.put(key, value);

		return this;
	}

	public SerializableMap<String, Serializable> appendList(String key, List<? extends Serializable> list) {
		if (Optional.ofNullable(list).isPresent()) {

			list.forEach(o -> {
				this.multimap.put(key, o);
			});
		}

		return this;
	}

	public SerializableMap<String, Serializable> appendMap(Map<String, ? extends Serializable> data) {
		if (Optional.ofNullable(data).isPresent()) {

			data.forEach((key, value) -> this.multimap.put(key, value));
		}

		return this;
	}

	public SerializableMap<String, Serializable> appendMultimap(Multimap<String, ? extends Serializable> data) {
		if (Optional.ofNullable(data).isPresent()) {

			data.forEach((key, value) -> this.multimap.put(key, value));
		}

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
