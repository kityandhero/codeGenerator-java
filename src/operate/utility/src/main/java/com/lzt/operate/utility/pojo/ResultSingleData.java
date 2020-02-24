package com.lzt.operate.utility.pojo;

import com.lzt.operate.utility.enums.ReturnDataCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

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

	public Serializable getData() {
		return data;
	}

	public void setData(Serializable data) {
		this.data = data;
	}

	ResultSingleData(int code, boolean success, String message) {
		super(code, success, message);
	}

	public ResultSingleData(ReturnDataCode returnDataCode) {
		this(returnDataCode.toMessage(), new SerializableData());
	}

	public ResultSingleData(@NonNull ReturnMessage returnMessage) {
		this(returnMessage, new SerializableData());
	}

	public ResultSingleData(ReturnDataCode returnDataCode, SerializableData data) {
		this(returnDataCode.toMessage(), data, new SerializableData());
	}

	public ResultSingleData(@NonNull ReturnMessage returnMessage, SerializableData data) {
		this(returnMessage, data, new SerializableData());
	}

	ResultSingleData(@NonNull ReturnMessage returnMessage, SerializableData data, SerializableData extra) {
		super(returnMessage, extra);

		this.data = data;
	}
}
