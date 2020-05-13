package com.lzt.operate.code.generator.entities;

import com.lzt.operate.utility.enums.CustomDataType;
import com.lzt.operate.code.generator.entities.bases.BaseEntity;
import com.lzt.operate.code.generator.interfaces.IGeneralLog;
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
	@Column(nullable = false)
	private String message;

	/**
	 * 自定义数据类型
	 */
	@Column(name = "message_type", nullable = false)
	private int messageType;

	/**
	 * 详细内容
	 */
	@Lob
	@Column(nullable = false)
	private String content;

	/**
	 * 自定义数据类型
	 */
	@Column(name = "content_type", nullable = false)
	private int contentType;

	public GeneralLog() {
		super();

		this.message = "";
		this.messageType = CustomDataType.PlainValue.getFlag();
		this.content = "";
		this.messageType = CustomDataType.PlainValue.getFlag();
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(CustomDataType customDataType) {
		this.messageType = customDataType.getFlag();
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getContentType() {
		return contentType;
	}

	public void setContentType(CustomDataType customDataType) {
		this.contentType = customDataType.getFlag();
	}

}
