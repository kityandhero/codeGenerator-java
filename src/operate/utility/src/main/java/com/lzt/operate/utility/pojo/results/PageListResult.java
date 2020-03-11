package com.lzt.operate.utility.pojo.results;

import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.pojo.ReturnMessage;
import lombok.EqualsAndHashCode;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	public static <T> PageListResult<T> buildFromList(List<T> list, int index, int size) {
		int pageNo = index <= 0 ? 1 : index;
		int pageSize = size <= 0 ? 10 : size;

		if (Optional.ofNullable(list).isPresent()) {
			List<T> listResult = list.stream()
									 .skip((pageNo - 1) * pageSize)
									 .limit(pageSize).collect(Collectors.toList());

			return new PageListResult<>(ReturnDataCode.Ok.toMessage(), listResult, pageNo, pageSize, list.size());
		}

		return new PageListResult<>(ReturnDataCode.Ok.toMessage(), new ArrayList<>(), index, size, 0);
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
