package com.lzt.operate.utility.enums;

import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 操作返回码
 *
 * @author luzhitao
 */
public enum OperatorCollection {

	/**
	 * 系统操作
	 */
	System(-1, "系统操作", "系统操作");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "operatorCollectionList";

	private final int flag;

	private final String name;

	private final String description;

	OperatorCollection(int flag, String name, String description) {
		this.flag = flag;
		this.name = name;
		this.description = description;
	}

	public static Optional<OperatorCollection> valueOfFlag(@NotNull Integer flag) {
		OperatorCollection[] values = OperatorCollection.values();

		for (OperatorCollection d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<OperatorCollection> valuesToList() {
		return Arrays.asList(OperatorCollection.values());
	}

	public static boolean existFlag(@NonNull Integer flag) {
		Optional<OperatorCollection> optional = valueOfFlag(flag);

		return optional.isPresent();
	}

	public int getFlag() {
		return this.flag;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}
}
