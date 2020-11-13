package com.lzt.operate.code.generator.entities.bases;

import com.lzt.operate.code.generator.common.enums.WhetherSuper;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 基础角色表结构
 *
 * @author luzhitao
 * @see MappedSuperclass 标识的类表示其不能映射到数据库表，因为其不是一个完整的实体类，但是它所拥有的属性能够隐射在其子类对用的数据库表中
 */
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public abstract class BaseRole extends BaseEntity {

	private static final long serialVersionUID = 3759586512160670600L;

	/**
	 * 名称
	 */
	@Column(length = 50, nullable = false)
	private String name;

	/**
	 * 描述
	 */
	@Column(length = 500, nullable = false)
	private String description;

	/**
	 * 详情
	 */
	@Column(length = 2000, nullable = false)
	private String content;

	/**
	 * 权限数量
	 */
	@Column(name = "module_count", nullable = false)
	private int moduleCount;

	/**
	 * 权限集合体
	 */
	@Column(nullable = false)
	private String competence;

	/**
	 * 是否超级管理员
	 */
	@Column(name = "whether_super", nullable = false)
	private Integer whetherSuper;

	protected BaseRole() {
		super();

		this.name = "";
		this.description = "";
		this.content = "";
		this.moduleCount = 0;
		this.competence = "";
		this.whetherSuper = WhetherSuper.No.getFlag();
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getModuleCount() {
		return moduleCount;
	}

	public void setModuleCount(int moduleCount) {
		this.moduleCount = moduleCount;
	}

	public String getCompetence() {
		return competence;
	}

	public void setCompetence(String competence) {
		this.competence = competence;
	}

	public Integer getWhetherSuper() {
		return whetherSuper;
	}

	public void setWhetherSuper(Integer whetherSuper) {
		this.whetherSuper = whetherSuper;
	}
}


