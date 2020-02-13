package com.lzt.operate.codetools.entities;

import com.lzt.operate.codetools.entities.bases.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author luzhitao
 */
@Data
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

}
