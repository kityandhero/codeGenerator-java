package com.lzt.operate.code.generator.common.enums.mybatis;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */
public enum DaoType {

	/**
	 * XmlMapper
	 */
	XmlMapper(100, "XmlMapper", "XmlMapper", "XMLMAPPER"),

	/**
	 * AnnotatedMapper
	 */
	AnnotatedMapper(101, "AnnotatedMapper", "AnnotatedMapper", "ANNOTATEDMAPPER");

	/**
	 * 元数据键值集合中的key
	 */
	public static final String META_KEY = "daoTypeList";

	private final Integer flag;
	private final String name;
	private final String description;
	private final String type;

	DaoType(int flag, String name, String description, String type) {
		this.flag = flag;
		this.name = name;
		this.description = description;
		this.type = type;
	}

	public static Optional<DaoType> valueOfFlag(@NonNull Integer flag) {
		DaoType[] values = DaoType.values();

		for (DaoType d : values) {
			if (flag.equals(d.getFlag())) {
				return Optional.of(d);
			}
		}

		return Optional.empty();
	}

	public static List<DaoType> valuesToList() {
		return Arrays.asList(DaoType.values());
	}

	public static boolean existFlag(@NonNull Integer flag) {
		Optional<DaoType> optional = valueOfFlag(flag);

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

	public String getType() {
		return this.type;
	}
}