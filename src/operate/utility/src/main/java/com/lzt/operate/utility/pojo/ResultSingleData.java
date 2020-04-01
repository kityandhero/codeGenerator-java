package com.lzt.operate.utility.pojo;

import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author luzhitao
 * @date 2019-05-07 19:44
 */
@EqualsAndHashCode(callSuper = true)
public class ResultSingleData extends BaseResultData<ResultSingleData> {

	private static final long serialVersionUID = 5710902373699158999L;

	@ApiModelProperty(notes = "数据体", example = SerializableData.EMPTY_SERIALIZE_VALUE, position = 4)
	private Object data;

	public ResultSingleData() {
		super();
		this.data = new SerializableData().getMultimap().asMap();
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

	public ResultSingleData(ReturnDataCode returnDataCode, Object data) {
		this(returnDataCode.toMessage(), data, new SerializableData());
	}

	public ResultSingleData(ReturnMessage returnMessage, Object data) {
		this(returnMessage, data, new SerializableData());
	}

	ResultSingleData(ReturnMessage returnMessage, Object data, Object extra) {
		super(returnMessage, extra);

		this.data = data;
	}

	@Override
	protected ResultSingleData getBaseClone(@NotNull ResultSingleData resultData) {
		ResultSingleData result = new ResultSingleData();

		result.setSuccess(resultData.isSuccess());
		result.setCode(resultData.getCode());
		result.setMessage(resultData.getMessage());
		result.setExtra(resultData.getExtra());

		result.setData(new SerializableData());

		return result;
	}

	@Override
	protected ResultSingleData toJsonResultWithOther(ResultSingleData resultData) {
		ResultSingleData result = getBaseClone(resultData);

		Object data = this.getData();

		if (Optional.ofNullable(data).isPresent()) {
			if (data instanceof SerializableMap) {
				result.data = ConvertAssist.toObjectMixMap(((SerializableMap) data).getMultimap().asMap());
			} else {
				result.data = data;
			}
		} else {
			result.data = new SerializableData().getMultimap().asMap();
		}

		return result;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
