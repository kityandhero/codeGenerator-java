package com.lzt.operate.utility.assists;

import com.lzt.operate.utility.pojo.SerializableData;
import lombok.var;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */
public class EnumAssist {

	public static <T extends Enum<T>> List<SerializableData> buildFlagDataCollection(
			T[] valueCollection,
			IGetter<T> flagGetter,
			IGetter<T> nameGetter) {
		List<SerializableData> list = new ArrayList<>();

		return buildFlagDataCollection(valueCollection, flagGetter, nameGetter, null);
	}

	public static <T extends Enum<T>> List<SerializableData> buildFlagDataCollection(
			T[] valueCollection,
			IGetter<T> flagGetter,
			IGetter<T> nameGetter,
			IGetter<T> descriptionGetter) {
		List<SerializableData> list = new ArrayList<>();

		for (T item : valueCollection) {
			SerializableData data = new SerializableData();

			var flag = flagGetter.apply(item);
			var name = nameGetter.apply(item);

			data.append("flag", flag.toString());
			data.append("name", name.toString());

			if (Optional.ofNullable(descriptionGetter).isPresent()) {
				var description = descriptionGetter.apply(item);

				data.append("description", description.toString());
			}

			list.add(data);
		}

		return list;
	}
}
