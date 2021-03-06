package com.lzt.operate.code.generator.entities;

import com.lzt.operate.code.generator.entities.bases.BaseEntity;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author luzhitao
 */
@Entity
@Table(name = "custom_config")
@EqualsAndHashCode(callSuper = false)
public class CustomConfig extends BaseEntity {

	private static final long serialVersionUID = -8937636579334404642L;

	@Column(length = 36, nullable = false)
	String uuid;

	@Column(length = 50, nullable = false)
	String name;

	@Column(nullable = false)
	String value;

	@Column(nullable = false)
	String description;

	public CustomConfig() {
		this.name = "";
		this.value = "";
		this.description = "";
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
