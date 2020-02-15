package com.lzt.operate.utility.pojo.results;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhtiao
 */
@Data
public class ListResult<T> {

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

	public ListResult() {
		list = new ArrayList<>();
	}

	public ListResult(List<T> listData) {
		this.list = listData;
	}

}
