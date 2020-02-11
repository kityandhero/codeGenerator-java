package com.lzt.operate.interfaces;

import com.lzt.operate.entities.SerializableData;
import lombok.var;

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
    default HashMap<String, Serializable> toHashMap() throws IllegalAccessException {
        HashMap<String, Serializable> collection = new HashMap<>(5);

        //得到class
        Class cls = this.getClass();

        //得到所有属性
        Field[] fields = cls.getDeclaredFields();

        for (var field : fields) {
            collection.put(field.getName(), (Serializable) field.get(this));
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
        var map = this.toHashMap();

        var result = new SerializableData(map);

        result.appends(additionalData);

        return new SerializableData(map);
    }

    /**
     * toSerializableData
     *
     * @return SerializableData
     * @throws IllegalAccessException IllegalAccessException
     */
    default SerializableData toSerializableData() throws IllegalAccessException {
        var map = this.toHashMap();

        return new SerializableData(map);
    }
}
