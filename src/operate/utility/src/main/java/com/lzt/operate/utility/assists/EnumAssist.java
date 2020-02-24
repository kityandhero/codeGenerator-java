package com.lzt.operate.utility.assists;

import com.lzt.operate.utility.pojo.SerializableData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author luzhitao
 */
public class EnumAssist {

	public static <T extends Enum<T>> boolean existTargetValue(
			Collection<T> valueCollection,
			IGetter<T> targetGetter,
			Object targetValue) {
		return existTargetValue(valueCollection, targetGetter, targetValue, new ArrayList<>());
	}

	public static <T extends Enum<T>> boolean existTargetValue(
			Collection<T> valueCollection,
			IGetter<T> targetGetter,
			Object targetValue,
			Collection<T> excludeCollection) {

		Collection<T> filteredCollection;

		if (!excludeCollection.isEmpty()) {
			filteredCollection = valueCollection.stream().filter(v -> excludeCollection.stream()
																					   .noneMatch(o -> targetGetter
																							   .apply(o)
																							   .equals(targetGetter
																									   .apply(v))))
												.collect(Collectors.toList());
		} else {
			filteredCollection = valueCollection;
		}

		for (T item : filteredCollection) {
			Object target = targetGetter.apply(item);

			if (target.equals(targetValue)) {
				return true;
			}
		}

		return false;
	}

	public static <T extends Enum<T>> List<SerializableData> buildFlagDataCollection(
			Collection<T> valueCollection,
			IGetter<T> flagGetter,
			IGetter<T> nameGetter) {
		return buildFlagDataCollection(valueCollection, flagGetter, nameGetter, null);
	}

	public static <T extends Enum<T>> List<SerializableData> buildFlagDataCollection(
			Collection<T> valueCollection,
			IGetter<T> flagGetter,
			IGetter<T> nameGetter,
			IGetter<T> descriptionGetter) {
		List<SerializableData> list = new ArrayList<>();

		for (T item : valueCollection) {
			SerializableData data = new SerializableData();

			Object flag = flagGetter.apply(item);
			Object name = nameGetter.apply(item);

			data.append("flag", flag.toString());
			data.append("name", name.toString());

			if (Optional.ofNullable(descriptionGetter).isPresent()) {
				Object description = descriptionGetter.apply(item);

				data.append("description", description.toString());
			}

			list.add(data);
		}

		return list;
	}
}
