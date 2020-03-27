package com.lzt.operate.code.generator.entities.bases;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lzt.operate.code.generator.common.enums.Channel;
import com.lzt.operate.utility.assists.IGetter;
import com.lzt.operate.utility.assists.LocalDateTimeAssist;
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
	@Column(nullable = false)
	private int channel;

	/**
	 * 日志产生的来源标识文本（例如哪个产品的名字）
	 */
	@Column(name = "channel_note", nullable = false)
	private String channelNote;

	/**
	 * 程序最后操作备注
	 */
	@Column(name = "auto_remark", nullable = false)
	private String autoRemark;

	/**
	 * 状态
	 */
	@Column(nullable = false)
	private int status;

	/**
	 * 状态文本
	 */
	@Column(name = "status_note", nullable = false)
	private String statusNote;

	/**
	 * 最后操作IP
	 */
	@Column(nullable = false)
	private String ip;

	/**
	 * 创建人标识
	 */
	@Column(name = "create_operator_id", nullable = false)
	private long createOperatorId;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time", nullable = false)
	@DateTimeFormat
	@JsonFormat
	private LocalDateTime createTime;

	/**
	 * 创建时间 UnixTIme
	 */
	@Column(name = "create_unix_time", nullable = false)
	private long createUnixTime;

	/**
	 * 最后更信人
	 */
	@Column(name = "update_operator_id", nullable = false)
	private long updateOperatorId;

	/**
	 * 更新时间
	 */
	@Column(name = "update_time", nullable = false)
	@DateTimeFormat
	@JsonFormat
	private LocalDateTime updateTime;

	/**
	 * 更新时间 UnixTime
	 */
	@Column(name = "update_unix_time", nullable = false)
	private long updateUnixTime;

	protected BaseEntity() {
		channel = Channel.Unknown.getFlag();
		channelNote = Channel.Unknown.getName();
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

	public void setChannel(Channel channel) {
		this.channel = channel.getFlag();
		this.channelNote = channel.getName();
	}

	public String getChannelNote() {
		return channelNote;
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

	public <T extends Enum<T>> void setStatus(T status, IGetter<T> valueGetter, IGetter<T> noteGetter) {
		this.status = valueGetter.applyConvert(status);

		this.statusNote = noteGetter.applyConvert(status);

	}

	public String getStatusNote() {
		return statusNote;
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

		this.createUnixTime = LocalDateTimeAssist.toUnixTime(createTime);
	}

	public long getCreateUnixTime() {
		return createUnixTime;
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

		this.updateUnixTime = LocalDateTimeAssist.toUnixTime(updateTime);
	}

	public long getUpdateUnixTime() {
		return updateUnixTime;
	}

}
