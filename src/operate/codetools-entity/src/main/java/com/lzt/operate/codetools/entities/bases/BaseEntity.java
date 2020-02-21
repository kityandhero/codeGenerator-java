package com.lzt.operate.codetools.entities.bases;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lzt.operate.codetools.common.enums.Channel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础数据表结构
 *
 * @author luzhitao
 * @see MappedSuperclass 标识的类表示其不能映射到数据库表，因为其不是一个完整的实体类，但是它所拥有的属性能够隐射在其子类对用的数据库表中
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 6320756028470337915L;

	/**
	 * 主键
	 */
	@Id
	@Column(nullable = false, unique = true)
	private long id;

	/**
	 * 日志产生的来源标识（例如哪个产品的标识）
	 */
	@Column
	private int channel;

	/**
	 * 日志产生的来源标识文本（例如哪个产品的名字）
	 */
	@Column(name = "channel_note")
	private String channelNote;

	/**
	 * 程序最后操作备注
	 */
	@Column(name = "auto_remark")
	private String autoRemark;

	/**
	 * 状态
	 */
	@Column
	private int status;

	/**
	 * 状态文本
	 */
	@Column(name = "status_note")
	private String statusNote;

	/**
	 * 最后操作IP
	 */
	@Column
	private String ip;

	/**
	 * 创建人标识
	 */
	@Column(name = "create_operator_id")
	private long createOperatorId;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	@DateTimeFormat
	@JsonFormat
	private LocalDateTime createTime;

	/**
	 * 创建时间 UnixTIme
	 */
	@Column(name = "create_unix_time")
	private long createUnixTime;

	/**
	 * 最后更信人
	 */
	@Column(name = "update_operator_id")
	private long updateOperatorId;

	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	@DateTimeFormat
	@JsonFormat
	private LocalDateTime updateTime;

	/**
	 * 更新时间 UnixTime
	 */
	@Column(name = "update_unix_time")
	private long updateUnixTime;

	protected BaseEntity() {
		channel = Channel.Unknown.getFlag();
		channelNote = Channel.Unknown.getNote();
		autoRemark = "";
		status = 0;
		statusNote = "";
		ip = "";
		createTime = LocalDateTime.now();
		createOperatorId = 0;
		updateTime = LocalDateTime.now();
		updateOperatorId = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public String getChannelNote() {
		return channelNote;
	}

	public void setChannelNote(String channelNote) {
		this.channelNote = channelNote;
	}

	public String getAutoRemark() {
		return autoRemark;
	}

	public void setAutoRemark(String autoRemark) {
		this.autoRemark = autoRemark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusNote() {
		return statusNote;
	}

	public void setStatusNote(String statusNote) {
		this.statusNote = statusNote;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public long getCreateOperatorId() {
		return createOperatorId;
	}

	public void setCreateOperatorId(long createOperatorId) {
		this.createOperatorId = createOperatorId;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public long getCreateUnixTime() {
		return createUnixTime;
	}

	public void setCreateUnixTime(long createUnixTime) {
		this.createUnixTime = createUnixTime;
	}

	public long getUpdateOperatorId() {
		return updateOperatorId;
	}

	public void setUpdateOperatorId(long updateOperatorId) {
		this.updateOperatorId = updateOperatorId;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public long getUpdateUnixTime() {
		return updateUnixTime;
	}

	public void setUpdateUnixTime(long updateUnixTime) {
		this.updateUnixTime = updateUnixTime;
	}
}
