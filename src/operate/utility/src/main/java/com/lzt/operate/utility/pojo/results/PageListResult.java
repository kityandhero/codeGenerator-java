package com.lzt.operate.utility.pojo.results;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author luzhitao
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageListResult<T> extends ListResult<T> {

	private int pageIndex;

	private int pageSize;

	private long totalSize;

	public PageListResult() {
		super();

		this.pageIndex = 1;
		this.pageSize = 10;
		this.totalSize = 0;
	}

	public PageListResult(List<T> listData, int index, int size, long total) {
		super(listData);

		this.pageSize = index;
		this.pageSize = size;
		this.totalSize = total;
	}

	/**
	 * 是否含有数据
	 *
	 * @return boolean
	 */
	private boolean hasData() {
		int size = getCount();

		return size > 0;
	}
}
