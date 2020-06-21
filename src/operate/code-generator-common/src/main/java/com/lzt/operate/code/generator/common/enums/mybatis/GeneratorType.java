package com.lzt.operate.code.generator.common.enums.mybatis;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */
public enum GeneratorType {

	/**
	 * MybatisGenerator
	 */
	MybatisGenerator(100, "MybatisGenerator", "MybatisGenerator"),

	/**
	 * CustomGenerator
	 */
	CustomGenerator(101, "CustomGenerator", "CustomGenerator");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "generatorTypeList";

	private final Integer flag;
	private final String name;
	private final String description;

	GeneratorType(int flag, String name, String description) {
		this.flag = flag;
		this.name = name;
		this.description = description;
	}

	public static Optional<GeneratorType> valueOfFlag(@NonNull Integer flag) {
		GeneratorType[] values = GeneratorType.values();

		for (GeneratorType d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<GeneratorType> valuesToList() {
		return Arrays.asList(GeneratorType.values());
	}

	public static boolean existFlag(@NonNull Integer flag) {
		Optional<GeneratorType> optional = valueOfFlag(flag);

		return optional.isPresent();
	}

	public Integer getFlag() {
		return this.flag;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

}
