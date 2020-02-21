package com.lzt.operate.utility.pojo.results;

import com.lzt.operate.utility.enums.ReturnDataCode;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author luzhitao
 */
@EqualsAndHashCode(callSuper = true)
public class PageListResult<T> extends ListResult<T> {

	private int pageIndex;

	private int pageSize;

	private long totalSize;

	public PageListResult(ReturnDataCode returnDataCode) {
		super(returnDataCode);

		this.pageIndex = 1;
		this.pageSize = 10;
		this.totalSize = 0;
	}

	public PageListResult(ReturnDataCode returnDataCode, List<T> listData, int index, int size, long total) {
		super(returnDataCode, listData);

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

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
}
