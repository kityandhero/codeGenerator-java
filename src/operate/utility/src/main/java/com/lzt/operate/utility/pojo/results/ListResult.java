package com.lzt.operate.utility.pojo.results;

import com.lzt.operate.utility.enums.ReturnDataCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhtiao
 */
public class ListResult<T> extends ExecutiveSimpleResult {

	private List<T> list;

	/**
	 * 获取容量
	 *
	 * @return int
	 */
	public int getCount() {
		Optional<List<T>> optional = Optional.ofNullable(this.list);

		return optional.map(List::size).orElse(0);
	}

	public ListResult(ReturnDataCode returnDataCode) {
		super(returnDataCode);

		list = new ArrayList<>();
	}

	public ListResult(ReturnDataCode returnDataCode, List<T> listData) {
		super(returnDataCode);

		this.list = listData;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
