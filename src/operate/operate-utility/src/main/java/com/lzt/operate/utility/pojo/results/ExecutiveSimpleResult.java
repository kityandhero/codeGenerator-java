package com.lzt.operate.utility.pojo.results;

import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.pojo.ReturnMessage;
import lombok.Data;
import org.springframework.lang.NonNull;

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

}
