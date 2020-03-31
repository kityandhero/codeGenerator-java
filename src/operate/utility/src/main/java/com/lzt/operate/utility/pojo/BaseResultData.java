package com.lzt.operate.utility.pojo;

import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author luzhitao
 * @date 2019-05-07 19:43
 */
public abstract class BaseResultData implements Serializable {

	public static final int CODE_ACCESS_SUCCESS = 200;
	public static final String MESSAGE_ACCESS_SUCCESS = "OK";
	private static final long serialVersionUID = 7488513125692159039L;
	@ApiModelProperty(notes = "返回码", example = "200", position = 1)
	public int code;

	@ApiModelProperty(notes = "是否执行成功", position = 2)
	public boolean success;

	@ApiModelProperty(notes = "消息描述", example = "success", position = 3)
	public String message;

	@ApiModelProperty(notes = "其他数据", position = 5)
	public Object extra;

	BaseResultData() {
		this.code = ReturnDataCode.Ok.getCode();
		this.success = ReturnDataCode.Ok.getSuccess();
		this.message = ReturnDataCode.Ok.getMessage();
		this.extra = new SerializableData();
	}

	BaseResultData(int code, boolean success, String message) {
		this.code = code;
		this.success = success;
		this.message = message;
		this.extra = new SerializableData();
	}

	BaseResultData(@NonNull ReturnMessage returnMessage, Object extra) {
		this.code = returnMessage.getCode();
		this.success = returnMessage.getSuccess();
		this.message = returnMessage.getMessage();
		this.extra = Optional.of(extra).orElse(new SerializableData());
	}

	public String serialize() {
		return ConvertAssist.serializeObject(this);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getExtra() {
		return extra;
	}

	public void setExtra(Serializable extra) {
		this.extra = extra;
	}
}
