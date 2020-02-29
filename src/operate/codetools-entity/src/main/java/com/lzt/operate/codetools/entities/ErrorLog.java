package com.lzt.operate.codetools.entities;

import com.lzt.operate.codetools.common.enums.ErrorLogDegree;
import com.lzt.operate.codetools.common.enums.ErrorLogResolve;
import com.lzt.operate.codetools.common.enums.ErrorLogType;
import com.lzt.operate.codetools.entities.bases.BaseEntity;
import com.lzt.operate.codetools.interfaces.IErrorLog;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author luzhitao
 */
@Entity
@Table(name = "error_log")
@EqualsAndHashCode(callSuper = true)
public class ErrorLog extends BaseEntity implements IErrorLog {

	private static final long serialVersionUID = 1158997988177942619L;

	/**
	 * Uri入口
	 */
	@Column
	private String uri;

	/**
	 * 消息描述
	 */
	@Column
	private String message;

	/**
	 * 堆栈跟踪
	 */
	@Column(name = "stack_trace")
	private String stackTrace;

	/**
	 * 源代码信息
	 */
	@Column
	private String source;

	/**
	 * 场景描述
	 */
	@Column
	private String scene;

	/**
	 * 类型值
	 */
	@Column
	private int type;

	/**
	 * 类型描述
	 */
	@Column(name = "type_note")
	private String typeNote;

	/**
	 * 重要程度值
	 */
	@Column
	private int degree;

	/**
	 * 重要程度描述
	 */
	@Column(name = "degree_note")
	private String degreeNote;

	/**
	 * 错误产生时是否发送通知
	 */
	@Column(name = "send_notification")
	private int sendNotification;

	/**
	 * 通知发送结果（成功/失败）
	 */
	@Column(name = "send_result")
	private int sendResult;

	/**
	 * 发送时间
	 */
	@Column(name = "send_unix_time")
	private long sendUnixTime;

	/**
	 * Uri头信息
	 */
	@Column
	private String header;

	/**
	 * Uri参数
	 */
	@Column(name = "uri_params")
	private String uriParams;

	/**
	 * Payload参数
	 */
	@Column(name = "payload_params")
	private String payloadParams;

	/**
	 * form参数
	 */
	@Column(name = "form_params")
	private String formParams;

	/**
	 * 域信息
	 */
	@Column
	private String host;

	/**
	 * 端口
	 */
	@Column
	private String port;

	/**
	 * 其他附属日志
	 */
	@Column(name = "other_log")
	private String otherLog;

	/**
	 * 自定义数据
	 */
	@Column
	private String data;

	/**
	 * 解决状态值
	 */
	@Column
	private int resolve;

	/**
	 * 解决状态描述
	 */
	@Column(name = "resolve_note")
	private String resolveNote;

	/**
	 * 异常类型的名称
	 */
	@Column(name = "exception_type_name")
	private String exceptionTypeName;

	public ErrorLog() {
		this.uri = "";
		this.message = "";
		this.stackTrace = "";
		this.source = "";
		this.scene = "";
		this.type = ErrorLogType.Exception.getValue();
		this.typeNote = ErrorLogType.Exception.getName();
		this.degree = ErrorLogDegree.Error.getValue();
		this.degreeNote = ErrorLogDegree.Error.getName();
		this.sendNotification = 0;
		this.sendResult = 0;
		this.sendUnixTime = 0;
		this.header = "";
		this.uriParams = "";
		this.payloadParams = "";
		this.formParams = "";
		this.host = "";
		this.port = "";
		this.otherLog = "";
		this.data = "";
		this.resolve = ErrorLogResolve.Unresolved.getValue();
		this.resolveNote = ErrorLogResolve.Unresolved.getName();
		this.exceptionTypeName = "";
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public int getType() {
		return type;
	}

	public void setType(ErrorLogType type) {
		this.type = type.getValue();
		this.typeNote = type.getName();
	}

	public String getTypeNote() {
		return typeNote;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(ErrorLogDegree degree) {
		this.degree = degree.getValue();
		this.degreeNote = degree.getName();
	}

	public String getDegreeNote() {
		return degreeNote;
	}

	public int getSendNotification() {
		return sendNotification;
	}

	public void setSendNotification(int sendNotification) {
		this.sendNotification = sendNotification;
	}

	public int getSendResult() {
		return sendResult;
	}

	public void setSendResult(int sendResult) {
		this.sendResult = sendResult;
	}

	public long getSendUnixTime() {
		return sendUnixTime;
	}

	public void setSendUnixTime(long sendUnixTime) {
		this.sendUnixTime = sendUnixTime;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getUriParams() {
		return uriParams;
	}

	public void setUriParams(String uriParams) {
		this.uriParams = uriParams;
	}

	public String getPayloadParams() {
		return payloadParams;
	}

	public void setPayloadParams(String payloadParams) {
		this.payloadParams = payloadParams;
	}

	public String getFormParams() {
		return formParams;
	}

	public void setFormParams(String formParams) {
		this.formParams = formParams;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getOtherLog() {
		return otherLog;
	}

	public void setOtherLog(String otherLog) {
		this.otherLog = otherLog;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getResolve() {
		return resolve;
	}

	public void setResolve(ErrorLogResolve resolve) {
		this.resolve = resolve.getValue();
		this.resolveNote = resolve.getName();
	}

	public String getResolveNote() {
		return resolveNote;
	}

	public String getExceptionTypeName() {
		return exceptionTypeName;
	}

	public void setExceptionTypeName(String exceptionTypeName) {
		this.exceptionTypeName = exceptionTypeName;
	}
}
