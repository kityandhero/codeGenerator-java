package com.lzt.operate.utility.pojo;

import com.lzt.operate.utility.enums.ReturnDataCode;
import org.springframework.lang.NonNull;

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

	public static ResultSingleData failData(@NonNull ReturnMessage returnDataCode) {
		return new ResultSingleData(returnDataCode, new SerializableData(), new SerializableData());
	}

	public static ResultSingleData failData(@NonNull ReturnMessage returnMessage, Object data) {
		return new ResultSingleData(returnMessage, data, new SerializableData());
	}

	public static ResultSingleData successSingleData() {
		return new ResultSingleData(ReturnDataCode.Ok.toMessage(), new SerializableData(), new SerializableData());
	}

	public static ResultListData successListData() {
		return new ResultListData(ReturnDataCode.Ok.toMessage(), Collections.emptyList(), new SerializableData());
	}

	public static ResultListData failListData(@NonNull ReturnMessage returnMessage) {
		return new ResultListData(returnMessage, Collections.emptyList(), new SerializableData());
	}

	public static ResultListData failListData(@NonNull ReturnMessage returnMessage, Object extra) {
		return new ResultListData(returnMessage, Collections.emptyList(), extra);
	}

}
