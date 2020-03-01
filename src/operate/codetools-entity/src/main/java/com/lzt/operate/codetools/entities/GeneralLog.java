package com.lzt.operate.codetools.entities;

import com.lzt.operate.codetools.entities.bases.BaseEntity;
import com.lzt.operate.codetools.interfaces.IGeneralLog;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 一般日志
 *
 * @author lzt
 */
@Entity
@Table(name = "general_log")
@EqualsAndHashCode(callSuper = true)
public class GeneralLog extends BaseEntity implements IGeneralLog {

	private static final long serialVersionUID = 8929574522787445324L;

	/**
	 * 简介描述
	 */
	@Column
	private String message;

	/**
	 * 详细内容
	 */
	@Lob
	@Column
	private String content;

	public GeneralLog() {
		super();

		this.message = "";
		this.content = "";
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
