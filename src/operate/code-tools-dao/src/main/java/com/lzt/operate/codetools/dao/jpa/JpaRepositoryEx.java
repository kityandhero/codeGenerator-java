package com.lzt.operate.codetools.dao.jpa;

import com.lzt.operate.codetools.entities.bases.BaseEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * @param <T>
 * @param <ID>
 * @author luzhitao
 */
public interface JpaRepositoryEx<T extends BaseEntity, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

	/**
	 * findFirst 查找第一个匹配项目
	 *
	 * @param example 匹配源
	 * @param <S>     类型
	 * @param sort    排序方式
	 * @return 返回结果
	 */
	default <S extends T> Optional<S> findFirst(Example<S> example, Sort sort) {

		List<S> list = this.findAll(example, sort);

		if (list.size() > 0) {
			S o = list.get(0);

			return Optional.of(o);
		}

		return Optional.empty();

	}
}
