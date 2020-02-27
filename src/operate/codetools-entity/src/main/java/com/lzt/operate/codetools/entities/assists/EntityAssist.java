package com.lzt.operate.codetools.entities.assists;

import com.lzt.operate.codetools.common.utils.CustomConstants;
import com.lzt.operate.codetools.entities.bases.BaseEntity;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.pojo.SerializableData;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.stream.Stream;

/**
 * @author luzhitao
 * @date 2020-02-27 17:26 create
 */
public class EntityAssist {

	/**
	 * 转换为SerializableData
	 *
	 * @param entity entity
	 * @param <T>    T extends BaseEntity
	 * @return SerializableData
	 */
	public static <T extends BaseEntity> SerializableData toSerializableData(T entity) {
		Field[] fields = entity.getClass().getDeclaredFields();

		SerializableData data = new SerializableData();

		Stream.of(fields).forEach(field -> {
			String name = field.getName();
			try {
				Object value = field.get(entity);

				if (value instanceof Serializable) {
					name = name.equals(CustomConstants.ENTIRY_Id) ? StringAssist.merge(name, CustomConstants.ENTIRY_Id) : name;

					data.append(name, (Serializable) value);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		});

		return data;
	}

}
