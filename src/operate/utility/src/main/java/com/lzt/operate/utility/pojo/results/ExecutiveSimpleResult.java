package com.lzt.operate.utility.pojo.results;

import com.lzt.operate.utility.enums.ReturnDataCode;
import lombok.Data;

/**
 * @author luzhitao
 */
@Data
public class ExecutiveSimpleResult {

	/**
	 * 是否成功
	 */
	private Boolean success;

	/**
	 * 结果代码
	 */
	private ReturnDataCode code;

	/**
	 * 消息
	 */
	private String message;

	public ExecutiveSimpleResult() {
		this.success = ReturnDataCode.Empty.getSuccess();
		code = ReturnDataCode.Empty;
		message = ReturnDataCode.Empty.getMessage();
	}

	public ExecutiveSimpleResult(ReturnDataCode returnDataCode) {
		this.success = returnDataCode.getSuccess();
		code = returnDataCode;
		message = returnDataCode.getMessage();
	}

	public ExecutiveSimpleResult(ReturnDataCode returnDataCode, String customMessage) {
		this.success = returnDataCode.getSuccess();
		code = returnDataCode;
		message = customMessage;
	}

}
