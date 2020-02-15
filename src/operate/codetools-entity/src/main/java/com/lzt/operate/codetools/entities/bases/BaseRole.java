package com.lzt.operate.codetools.entities.bases;

import com.lzt.operate.codetools.common.enums.WhetherSuper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 基础角色表结构
 *
 * @author luzhitao
 * @see MappedSuperclass 标识的类表示其不能映射到数据库表，因为其不是一个完整的实体类，但是它所拥有的属性能够隐射在其子类对用的数据库表中
 */
@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public abstract class BaseRole extends BaseEntity {

	private static final long serialVersionUID = 3759586512160670600L;

	/**
	 * 名称
	 */
	@Column(length = 50, unique = true)
	private String name;

	/**
	 * 描述
	 */
	@Column(length = 500)
	private String description;

	/**
	 * 详情
	 */
	@Column(length = 2000)
	private String content;

	/**
	 * 权限数量
	 */
	@Column(name = "module_count")
	private int moduleCount;

	/**
	 * 权限集合体
	 */
	@Column
	private String competence;

	/**
	 * 是否超级管理员
	 */
	@Column(name = "whether_super")
	private Integer whetherSuper;

	protected BaseRole() {
		super();

		this.name = "";
		this.description = "";
		this.content = "";
		this.moduleCount = 0;
		this.competence = "";
		this.whetherSuper = WhetherSuper.No.getValue();
	}

}

