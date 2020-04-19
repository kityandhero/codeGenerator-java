package com.lzt.operate.utility.enums;

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

	public int getFlag() {
		return flag;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
