package com.lzt.operate.utility.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luzhitao
 * @date 2019-05-07 19:44
 */
@EqualsAndHashCode(callSuper = true)
public class ResultListData extends BaseResultData {

	private static final long serialVersionUID = -2793790712990675266L;

	@ApiModelProperty(notes = "列表数据体", example = SerializableData.EMPTY_SERIALIZE_VALUE, position = 4)
	public List<? extends Serializable> list;

	public ResultListData() {
		super();
		this.list = new ArrayList<>();
	}

	public ResultListData(ReturnMessage returnMessage) {
		this(returnMessage, new ArrayList<>());
	}

	private ResultListData(ReturnMessage returnMessage, List<? extends Serializable> list) {
		this(returnMessage, list, new SerializableData());
	}

	ResultListData(ReturnMessage returnMessage, List<? extends Serializable> list, Serializable extra) {
		super(returnMessage, extra);

		this.list = list;
	}

	public List<? extends Serializable> getList() {
		return list;
	}

	public void setList(List<? extends Serializable> list) {
		this.list = list;
	}
}
