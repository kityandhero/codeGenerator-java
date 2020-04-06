package com.lzt.operate.utility.pojo.results;

import com.lzt.operate.utility.pojo.ReturnMessage;
import org.springframework.lang.NonNull;

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

	public ListResult(@NonNull ReturnMessage returnMessage) {
		super(returnMessage);

		list = new ArrayList<>();
	}

	public ListResult(@NonNull ReturnMessage returnMessage, List<T> listData) {
		super(returnMessage);

		this.list = listData;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof ListResult)) {
			return false;
		}

		if (!super.equals(o)) {
			return false;
		}

		ListResult<?> that = (ListResult<?>) o;

		return getList() != null ? getList().equals(that.getList()) : that.getList() == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (getList() != null ? getList().hashCode() : 0);
		return result;
	}
}
