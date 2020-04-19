package com.lzt.operate.utility.pojo.results;

import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.pojo.ReturnMessage;
import org.springframework.lang.NonNull;

/**
 * @author luzhitao
 */
public class ExecutiveSimpleResult {

	/**
	 * 是否成功
	 */
	private Boolean success;

	/**
	 * 结果代码
	 */
	private ReturnMessage code;

	/**
	 * 消息
	 */
	private String message;

	public ExecutiveSimpleResult() {
		this.success = ReturnDataCode.Empty.getSuccess();
		code = ReturnDataCode.Empty.toMessage();
		message = ReturnDataCode.Empty.getMessage();
	}

	public ExecutiveSimpleResult(@NonNull ReturnMessage returnMessage) {
		this.success = returnMessage.getSuccess();
		code = returnMessage;
		message = returnMessage.getMessage();
	}

	public ExecutiveSimpleResult(@NonNull ReturnMessage returnMessage, String customMessage) {
		this.success = returnMessage.getSuccess();
		code = returnMessage;
		message = customMessage;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public ReturnMessage getCode() {
		return code;
	}

	public void setCode(ReturnMessage code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
