package com.lzt.operate.utility.interfaces;

import com.lzt.operate.utility.pojo.SerializableData;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author luzhitao
 */
public interface IObjectExtensionMethod extends Serializable {
	/**
	 * toHashMap
	 *
	 * @return HashMap<String, Serializable>
	 * @throws IllegalAccessException IllegalAccessException
	 */
	default HashMap<String, Object> toHashMap() throws IllegalAccessException {
		HashMap<String, Object> collection = new HashMap<>(5);

		//得到class
		Class<?> cls = this.getClass();

		//得到所有属性
		Field[] fields = cls.getDeclaredFields();

		for (Field field : fields) {
			collection.put(field.getName(), field.get(this));
		}

		return collection;
	}

	/**
	 * toSerializableData
	 *
	 * @param additionalData additionalData
	 * @return SerializableData
	 * @throws IllegalAccessException IllegalAccessException
	 */
	default SerializableData toSerializableData(@NotNull SerializableData additionalData) throws IllegalAccessException {
		HashMap<String, Object> map = this.toHashMap();

		SerializableData result = new SerializableData(map);

		result.appendMultimap(additionalData.getMultimap());

		return new SerializableData(map);
	}

	/**
	 * toSerializableData
	 *
	 * @return SerializableData
	 * @throws IllegalAccessException IllegalAccessException
	 */
	default SerializableData toSerializableData() throws IllegalAccessException {
		HashMap<String, Object> map = this.toHashMap();

		return new SerializableData(map);
	}
}
