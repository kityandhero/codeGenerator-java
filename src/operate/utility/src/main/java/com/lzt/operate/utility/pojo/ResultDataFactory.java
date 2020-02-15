package com.lzt.operate.utility.pojo;

import com.lzt.operate.utility.enums.ReturnDataCode;
import lombok.NonNull;

import java.util.Collections;

/**
 * 参数异常类
 * CreteTime:2019-04-29 22:06
 * UpdateTIme:2019-04-29 22:06
 *
 * @author luzhitao
 */
public class ResultDataFactory {

	public static ResultSingleData failCustomData(int code, boolean success, String message) {
		return new ResultSingleData(code, success, message);
	}

	public static ResultSingleData failData(@NonNull ReturnDataCode returnDataCode) {
		return new ResultSingleData(returnDataCode, new SerializableData(), new SerializableData());
	}

	public static ResultSingleData failData(@NonNull ReturnDataCode returnDataCode, SerializableData data) {
		return new ResultSingleData(returnDataCode, data, new SerializableData());
	}

	public static ResultSingleData successSingleData() {
		return new ResultSingleData(ReturnDataCode.Ok, new SerializableData(), new SerializableData());
	}

	public static ResultListData successListData() {
		return new ResultListData(ReturnDataCode.Ok, Collections.emptyList(), new SerializableData());
	}

}
