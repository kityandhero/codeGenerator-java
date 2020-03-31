package com.lzt.operate.utility.pojo;

import com.lzt.operate.utility.assists.IGetter;
import com.lzt.operate.utility.assists.ReflectAssist;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 空的可序列化类
 *
 * @author luzhitao
 */
public class SerializableData extends SerializableMap<String, Serializable> {

	static final String EMPTY_SERIALIZE_VALUE = "{}";

	public SerializableData() {
		super();
	}

	public SerializableData(String key, Serializable v) {
		super();
		this.append(key, v);
	}

	public SerializableData(Map<String, ? extends Serializable> data) {
		super(data);
	}

	public SerializableData(Exception e) {
		super();

		this.append("message", e.getMessage());
		this.append("stackTrace", e.getStackTrace());
	}

	/**
	 * 转换为SerializableData
	 *
	 * @param entity     entity
	 * @param <T>        T extends BaseEntity
	 * @param listGetter List<IGetter<T>>
	 * @return SerializableData
	 */
	public static <T> SerializableData toSerializableData(T entity, List<IGetter<T>> listGetter) {
		return toSerializableData(entity, listGetter, null);
	}

	/**
	 * 转换为SerializableData
	 *
	 * @param entity     entity
	 * @param <T>        T extends BaseEntity
	 * @param listGetter List<IGetter<T>>
	 * @param dataSource SerializableData
	 * @return SerializableData
	 */
	public static <T> SerializableData toSerializableData(T entity, List<IGetter<T>> listGetter, SerializableData dataSource) {
		SerializableData data;

		if (Optional.ofNullable(dataSource).isPresent()) {
			data = dataSource;
		} else {
			data = new SerializableData();
		}

		for (IGetter<T> t : listGetter) {
			data.append(ReflectAssist.getFieldName(t), t.applyConvert(entity));
		}

		return data;
	}

}
