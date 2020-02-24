package com.lzt.operate.utility.pojo.results;

import com.lzt.operate.utility.pojo.ReturnMessage;
import lombok.EqualsAndHashCode;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author luzhitao
 */
@EqualsAndHashCode(callSuper = true)
public class PageListResult<T> extends ListResult<T> {

	private int pageIndex = 1;

	private int pageSize = 10;

	private long totalSize = 0;

	public PageListResult(@NonNull ReturnMessage returnMessage) {
		super(returnMessage);
	}

	public PageListResult(@NonNull ReturnMessage returnMessage, List<T> listData, int index, int size, long total) {
		super(returnMessage, listData);

		this.pageIndex = index;
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
