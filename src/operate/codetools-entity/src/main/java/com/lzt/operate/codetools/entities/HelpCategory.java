package com.lzt.operate.codetools.entities;

import com.lzt.operate.codetools.entities.bases.BaseEntity;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 帮助文档类别
 *
 * @author luzhitao
 */
@Entity
@Table(name = "help_category")
@EqualsAndHashCode(callSuper = true)
public class HelpCategory extends BaseEntity {

	private static final long serialVersionUID = 6406205023863791746L;

	/**
	 * 名称
	 */
	@Column
	private
	String name;

	/**
	 * 简介描述
	 */
	@Column
	private
	String description;

	public HelpCategory() {
		super();

		this.name = "";
		this.description = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
