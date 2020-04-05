package com.lzt.operate.code.generator.entities;

import com.lzt.operate.code.generator.common.enums.ErrorLogDataType;
import com.lzt.operate.code.generator.common.enums.ErrorLogDegree;
import com.lzt.operate.code.generator.common.enums.ErrorLogResolve;
import com.lzt.operate.code.generator.common.enums.ErrorLogType;
import com.lzt.operate.code.generator.entities.bases.BaseEntity;
import com.lzt.operate.code.generator.interfaces.IErrorLog;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
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
	@Lob
	@Column(nullable = false)
	private String uri;

	/**
	 * 消息描述
	 */
	@Lob
	@Column(nullable = false)
	private String message;

	/**
	 * 消息描述
	 */
	@Lob
	@Column(nullable = false)
	private String causeMessage;

	/**
	 * 堆栈跟踪
	 */
	@Lob
	@Column(name = "stack_trace", nullable = false)
	private String stackTrace;

	/**
	 * 源代码信息
	 */
	@Lob
	@Column(nullable = false)
	private String source;

	/**
	 * 场景描述
	 */
	@Lob
	@Column(nullable = false)
	private String scene;

	/**
	 * 类型值
	 */
	@Column(nullable = false)
	private int type;

	/**
	 * 类型描述
	 */
	@Column(name = "type_note", nullable = false)
	private String typeNote;

	/**
	 * 重要程度值
	 */
	@Column(nullable = false)
	private int degree;

	/**
	 * 重要程度描述
	 */
	@Column(name = "degree_note", nullable = false)
	private String degreeNote;

	/**
	 * 错误产生时是否发送通知
	 */
	@Column(name = "send_notification", nullable = false)
	private int sendNotification;

	/**
	 * 通知发送结果（成功/失败）
	 */
	@Column(name = "send_result", nullable = false)
	private int sendResult;

	/**
	 * 发送时间
	 */
	@Column(name = "send_unix_time", nullable = false)
	private long sendUnixTime;

	/**
	 * Uri头信息
	 */
	@Lob
	@Column(nullable = false)
	private String header;

	/**
	 * request queryString
	 */
	@Lob
	@Column(name = "request_query_string", nullable = false)
	private String requestQueryString;

	/**
	 * request body
	 */
	@Lob
	@Column(name = "request_body", nullable = false)
	private String requestBody;

	/**
	 * 域信息
	 */
	@Lob
	@Column(name = "request_params", nullable = false)
	private String requestParams;

	/**
	 * 域信息
	 */
	@Column(nullable = false)
	private String host;

	/**
	 * 端口
	 */
	@Column(nullable = false)
	private int port;

	/**
	 * 其他附属日志
	 */
	@Lob
	@Column(name = "other_log", nullable = false)
	private String otherLog;

	/**
	 * 自定义数据
	 */
	@Lob
	@Column(nullable = false)
	private String data;

	/**
	 * 自定义数据类型
	 */
	@Column(nullable = false)
	private int dataType;

	/**
	 * 自定义数据类型描述
	 */
	@Column(name = "data_type_note", nullable = false)
	private String dataTypeNote;

	/**
	 * 解决状态值
	 */
	@Column(nullable = false)
	private int resolve;

	/**
	 * 解决状态描述
	 */
	@Column(name = "resolve_note", nullable = false)
	private String resolveNote;

	/**
	 * 异常类型的名称
	 */
	@Column(name = "exception_type_name", nullable = false)
	private String exceptionTypeName;

	public ErrorLog() {
		this.uri = "";
		this.message = "";
		this.causeMessage = "";
		this.stackTrace = "";
		this.source = "";
		this.scene = "";
		this.type = ErrorLogType.Exception.getFlag();
		this.typeNote = ErrorLogType.Exception.getName();
		this.degree = ErrorLogDegree.Error.getFlag();
		this.degreeNote = ErrorLogDegree.Error.getName();
		this.sendNotification = 0;
		this.sendResult = 0;
		this.sendUnixTime = 0;
		this.header = "";
		this.requestQueryString = "";
		this.requestBody = "";
		this.requestParams = "";
		this.host = "";
		this.port = 0;
		this.otherLog = "";
		this.data = "";
		this.dataType = ErrorLogDataType.CommonValue.getFlag();
		this.dataTypeNote = ErrorLogDataType.CommonValue.getName();
		this.resolve = ErrorLogResolve.Unresolved.getFlag();
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

	public String getCauseMessage() {
		return causeMessage;
	}

	public void setCauseMessage(String causeMessage) {
		this.causeMessage = causeMessage;
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
		this.type = type.getFlag();
		this.typeNote = type.getName();
	}

	public String getTypeNote() {
		return typeNote;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(ErrorLogDegree degree) {
		this.degree = degree.getFlag();
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

	public String getRequestQueryString() {
		return requestQueryString;
	}

	public void setRequestQueryString(String requestQueryString) {
		this.requestQueryString = requestQueryString;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(String requestParams) {
		this.requestParams = requestParams;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
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

	public int getDataType() {
		return dataType;
	}

	public void setDataType(ErrorLogDataType dataType) {
		this.dataType = dataType.getFlag();
		this.dataTypeNote = dataType.getName();
	}

	public String getDataTypeNote() {
		return dataTypeNote;
	}

	public int getResolve() {
		return resolve;
	}

	public void setResolve(ErrorLogResolve resolve) {
		this.resolve = resolve.getFlag();
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
