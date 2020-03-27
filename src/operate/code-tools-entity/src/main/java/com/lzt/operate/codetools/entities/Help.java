package com.lzt.operate.codetools.entities;

import com.lzt.operate.codetools.entities.bases.BaseEntity;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 帮助文档
 *
 * @author luzhitao
 */
@Entity
@Table(name = "help")
@EqualsAndHashCode(callSuper = true)
public class Help extends BaseEntity {

	private static final long serialVersionUID = -3870233202537737085L;

	/**
	 * 名称
	 */
	@Column(nullable = false)
	private String title;

	/**
	 * 名称
	 */
	@Column(name = "help_category_id", nullable = false)
	private int helpCategoryId;

	/**
	 * 简介描述
	 */
	@Column(nullable = false)
	private String description;

	/**
	 * 简介描述
	 */
	@Lob
	@Column(nullable = false)
	private String content;

	public Help() {
		super();

		this.title = "";
		this.helpCategoryId = 0;
		this.description = "";
		this.content = "";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public int getHelpCategoryId() {
		return helpCategoryId;
	}

	public void setHelpCategoryId(int helpCategoryId) {
		this.helpCategoryId = helpCategoryId;
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
}
