package com.lzt.operate.utility.pojo;

import com.lzt.operate.utility.enums.ReturnDataCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author luzhitao
 * @date 2019-05-07 19:44
 */
@EqualsAndHashCode(callSuper = true)
public class ResultSingleData extends BaseResultData {

	private static final long serialVersionUID = 5710902373699158999L;

	@ApiModelProperty(notes = "数据体", example = SerializableData.EMPTY_SERIALIZE_VALUE, position = 4)
	public Serializable data;

	public ResultSingleData() {
		super();
		this.data = new SerializableData();
	}

	ResultSingleData(int code, boolean success, String message) {
		super(code, success, message);
	}

	public ResultSingleData(ReturnDataCode returnDataCode) {
		this(returnDataCode.toMessage(), new SerializableData());
	}

	public ResultSingleData(ReturnMessage returnMessage) {
		this(returnMessage, new SerializableData());
	}

	public ResultSingleData(ReturnDataCode returnDataCode, Serializable data) {
		this(returnDataCode.toMessage(), data, new SerializableData());
	}

	public ResultSingleData(ReturnMessage returnMessage, Serializable data) {
		this(returnMessage, data, new SerializableData());
	}

	ResultSingleData(ReturnMessage returnMessage, Serializable data, Serializable extra) {
		super(returnMessage, extra);

		this.data = data;
	}

	public Serializable getData() {
		return data;
	}

	public void setData(Serializable data) {
		this.data = data;
	}
}
