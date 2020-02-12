package com.lzt.operate.codetools.entities;

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
@Table(name = "role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

	private static final long serialVersionUID = -7381639390992375293L;

	/**
	 * 名称
	 */
	@Column(length = 50, unique = true)
	String name;

	/**
	 * 描述
	 */
	@Column(length = 500)
	String description;

	/**
	 * 权限数量
	 */
	@Column
	int moduleCount;

	/**
	 * 权限集合体
	 */
	@Column
	String competence;

	/**
	 * 是否超级管理员
	 */
	@Column
	boolean isSuper;

	public Role() {
		this.name = "";
		this.description = "";
		this.moduleCount = 0;
		this.competence = "";
		this.isSuper = false;
	}
}
