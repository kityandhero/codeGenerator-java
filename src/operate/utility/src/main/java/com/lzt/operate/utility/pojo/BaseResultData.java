package com.lzt.operate.utility.pojo;

import com.google.common.collect.Multimap;
import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author luzhitao
 * @date 2019-05-07 19:43
 */
public abstract class BaseResultData<T extends BaseResultData<?>> implements Serializable {

	public static final int CODE_ACCESS_SUCCESS = 200;

	public static final String MESSAGE_ACCESS_SUCCESS = "OK";

	private static final long serialVersionUID = 7488513125692159039L;

	@ApiModelProperty(notes = "是否执行成功", position = 2)
	private boolean success;

	@ApiModelProperty(notes = "返回码", example = "200", position = 1)
	private int code;

	@ApiModelProperty(notes = "消息描述", example = "success", position = 3)
	private String message;

	@ApiModelProperty(notes = "其他数据", position = 5)
	private Object extra;

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

		this.extra = extra;

	}

	/**
	 * getBaseClone
	 *
	 * @param resultData resultData
	 * @return T
	 */
	protected abstract T getBaseClone(@NotNull T resultData);

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

	public void setExtra(Object extra) {
		this.extra = extra;
	}

	public void setExtra(Serializable extra) {
		this.extra = extra;
	}

	/**
	 * 转换为即将序列化的数据
	 *
	 * @param resultData resultData
	 * @return BaseResultData
	 */
	protected abstract T toJsonResultWithOther(T resultData);

	/**
	 * 转换为即将序列化的数据
	 *
	 * @return BaseResultData
	 */
	public final ExecutiveResult<T> toJsonResult(Class<T> clazz) {
		try {
			T result = clazz.newInstance();

			result.setCode(this.getCode());
			result.setSuccess(this.isSuccess());
			result.setMessage(this.getMessage());

			Object extraData = this.getExtra();

			if (Optional.ofNullable(extraData).isPresent()) {
				if (extraData instanceof SerializableMap) {
					result.setExtra(ConvertAssist.serializableMapToObjectMixMap((SerializableMap) extraData));
				} else if (extraData instanceof Multimap) {
					result.setExtra(ConvertAssist.multiMapToObjectMixMap((Multimap<?, ?>) extraData));
				} else if (extraData instanceof Map) {
					result.setExtra(ConvertAssist.mapToObjectMixMap((Map<?, ?>) extraData));
				} else if (extraData instanceof List) {
					result.setExtra(ConvertAssist.toObjectMixCollection((List<?>) extraData));
				} else {
					result.setExtra(extraData);
				}
			} else {
				result.setExtra(new SerializableData().getMultimap().asMap());
			}

			T r = toJsonResultWithOther(result);

			return new ExecutiveResult<>(ReturnDataCode.Ok, r);
		} catch (Exception ex) {
			return new ExecutiveResult<>(ReturnDataCode.Exception.toMessage(ex.getLocalizedMessage()));
		}
	}

	// public final ExecutiveResult<> toJsonResult() {
	// 	try {
	// 		return this.toBaseResultData(this.getClass());
	// 	} catch (Exception ex) {
	// 		return new ResultSingleData(ReturnDataCode.EXCEPTION_ERROR.appendMessage(ex.getLocalizedMessage()));
	// 	}
	// }

}
