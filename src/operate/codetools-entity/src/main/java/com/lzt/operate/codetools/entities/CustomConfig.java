package com.lzt.operate.codetools.entities;

import com.lzt.operate.codetools.entities.bases.BaseEntity;
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

	@Column(length = 50)
	String name;

	@Column
	String value;

	@Column
	String description;

	public CustomConfig() {
		this.name = "";
		this.value = "";
		this.description = "";
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
