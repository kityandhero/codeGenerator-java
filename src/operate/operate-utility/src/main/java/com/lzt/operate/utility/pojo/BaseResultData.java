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

	BaseResultData(final int code, final boolean success, final String message) {
		this.code = code;
		this.success = success;
		this.message = message;
		this.extra = new SerializableData();
	}

	BaseResultData(@NonNull final ReturnMessage returnMessage, final Object extra) {
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
		return this.code;
	}

	public void setCode(final int code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(final boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public Object getExtra() {
		return this.extra;
	}

	public void setExtra(final Object extra) {
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
	 * 转换为最终的待序列化为Json的数据
	 *
	 * @return BaseResultData
	 */
	public abstract T transferToJsonResultData();

	/**
	 * 转换为即将序列化的数据
	 *
	 * @return BaseResultData
	 */
	public final ExecutiveResult<T> toJsonResult(final Class<T> clazz) {
		try {
			final T result = clazz.newInstance();

			result.setCode(this.getCode());
			result.setSuccess(this.isSuccess());
			result.setMessage(this.getMessage());

			final Object extraData = this.getExtra();

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

			final T r = this.toJsonResultWithOther(result);

			return new ExecutiveResult<>(ReturnDataCode.Ok, r);
		} catch (final Exception ex) {
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
