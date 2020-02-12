package com.lzt.operate.codetools.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author luzhitao
 */
@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -7716145801326828526L;

	/**
	 * 主键
	 */
	@Id
	@Column(nullable = false)
	long id;

	/**
	 * 程序最后操作备注
	 */
	@Column
	String autoRemark;

	/**
	 * 状态
	 */
	@Column
	int state;

	/**
	 * 状态文本
	 */
	@Column
	String stateNote;

	/**
	 * 最后操作IP
	 */
	@Column
	String ip;

	/**
	 * 创建人标识
	 */
	@Column
	String createOperatorId;

	/**
	 * 创建时间
	 */
	@Column
	@DateTimeFormat
	@JsonFormat
	LocalDateTime createTime;

	/**
	 * 创建时间 UnixTIme
	 */
	@Column
	long createUnixTime;

	/**
	 * 最后更信人
	 */
	@Column
	String updateOperatorId;

	/**
	 * 更新时间
	 */
	@Column
	@DateTimeFormat
	@JsonFormat
	LocalDateTime updateTime;

	/**
	 * 更新时间 UnixTime
	 */
	@Column
	long updateUnixTime;

	BaseEntity() {
		autoRemark = "";
		state = 0;
		stateNote = "";
		ip = "";
		createTime = LocalDateTime.now();
		createOperatorId = "";
		updateTime = LocalDateTime.now();
		updateOperatorId = "";
	}
}
