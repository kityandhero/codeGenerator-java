package com.lzt.operate.utility.pojo;

import com.google.common.collect.Multimap;
import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author luzhitao
 * @date 2019-05-07 19:44
 */
@EqualsAndHashCode(callSuper = true)
public class ResultListData extends BaseResultData<ResultListData> {

	private static final long serialVersionUID = -2793790712990675266L;

	@ApiModelProperty(notes = "列表数据体", example = SerializableData.EMPTY_SERIALIZE_VALUE, position = 4)
	private List<Object> list;

	public ResultListData() {
		super();
		this.list = new ArrayList<>();
	}

	public ResultListData(ReturnMessage returnMessage) {
		this(returnMessage, new ArrayList<>());
	}

	private ResultListData(ReturnMessage returnMessage, List<Object> list) {
		this(returnMessage, list, new SerializableData());
	}

	ResultListData(ReturnMessage returnMessage, List<Object> list, Object extra) {
		super(returnMessage, extra);

		this.list = list;
	}

	@Override
	protected ResultListData getBaseClone(@NotNull ResultListData resultData) {
		ResultListData result = new ResultListData();

		result.setSuccess(resultData.isSuccess());
		result.setCode(resultData.getCode());
		result.setMessage(resultData.getMessage());
		result.setExtra(resultData.getExtra());

		result.setList(new ArrayList<>());

		return result;
	}

	@Override
	protected ResultListData toJsonResultWithOther(ResultListData resultData) {
		ResultListData result = getBaseClone(resultData);

		List<Object> list = this.getList();

		if (Optional.ofNullable(list).isPresent()) {

			List<Object> listHandled = new ArrayList<>();

			for (Object o : list) {
				Object one;

				if (o instanceof SerializableMap) {
					one = ConvertAssist.serializableMapToObjectMixMap((SerializableMap) o);
				} else if (o instanceof Multimap) {
					one = ConvertAssist.multiMapToObjectMixMap((Multimap<?, ?>) o);
				} else if (o instanceof Map) {
					one = ConvertAssist.mapToObjectMixMap((Map<?, ?>) o);
				} else if (o instanceof List) {
					one = ConvertAssist.toObjectMixCollection((List<?>) o);
				} else {
					one = o;
				}

				listHandled.add(one);
			}

			result.list = listHandled;
		} else {
			result.list = new ArrayList<>();
		}

		return result;
	}

	@Override
	public ResultListData transferToJsonResultData() {
		ExecutiveResult<ResultListData> result = this.toJsonResult(ResultListData.class);

		if (result.getSuccess()) {
			return result.getData();
		}

		return new ResultListData(result.getCode());
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}
}
