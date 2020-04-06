package com.lzt.operate.code.generator.dao.service.bases;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.code.generator.dao.jpa.JpaRepositoryEx;
import com.lzt.operate.code.generator.entities.bases.BaseEntity;
import com.lzt.operate.utility.assists.RequestAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.general.ConstantCollection;
import com.lzt.operate.utility.net.InternetAddressAssist;
import com.lzt.operate.utility.pojo.results.ExecutiveSimpleResult;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */
public interface BaseService<R extends JpaRepositoryEx<S, Long>, S extends BaseEntity> {

	/**
	 * 获取Id构建器
	 *
	 * @return UidGenService
	 */
	UidGenService getUidGenService();

	/**
	 * getRepository
	 *
	 * @return R
	 */
	R getRepository();

	/**
	 * 构建主键标识
	 *
	 * @return long
	 */
	default long generateId() {
		Optional<UidGenService> optional = Optional.ofNullable(getUidGenService());

		if (optional.isPresent()) {
			return optional.get().getUid();
		}

		throw new RuntimeException("Id构建器获取失败");
	}

	/**
	 * 获取分页数据
	 *
	 * @param filter filter
	 * @return ExecutiveResult<Page < S>>
	 */
	default List<S> list(Example<S> filter) {
		return getRepository().findAll(filter);
	}

	/**
	 * 获取列表数据
	 *
	 * @return ExecutiveResult<Page < S>>
	 */
	default List<S> list() {
		return getRepository().findAll();
	}

	/**
	 * 获取分页数据
	 *
	 * @param filter filter
	 * @return ExecutiveResult<Page < S>>
	 */
	default List<S> list(Specification<S> filter) {
		return getRepository().findAll(filter);
	}

	/**
	 * 获取分页数据
	 *
	 * @param pageable pageable
	 * @return ExecutiveResult<Page < S>>
	 */
	default Page<S> page(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

	/**
	 * 获取分页数据
	 *
	 * @param filter   filter
	 * @param pageable pageable
	 * @return ExecutiveResult<Page < S>>
	 */
	default Page<S> page(Example<S> filter, Pageable pageable) {
		return getRepository().findAll(filter, pageable);
	}

	/**
	 * 获取分页数据
	 *
	 * @param filter   filter
	 * @param pageable pageable
	 * @return ExecutiveResult<Page < S>>
	 */
	default Page<S> page(Specification<S> filter, Pageable pageable) {
		return getRepository().findAll(filter, pageable);
	}

	/**
	 * 获取第一个匹配项
	 *
	 * @param filter filter
	 * @return ExecutiveResult<Page < S>>
	 */
	default Optional<S> findFirst(Specification<S> filter) {
		List<S> list = getRepository().findAll(filter);

		if (list.size() > ConstantCollection.ZERO_INT) {
			return Optional.of(list.get(0));
		}

		return Optional.empty();
	}

	/**
	 * 是否存在
	 *
	 * @param filter filter
	 * @return ExecutiveResult<Page < S>>
	 */
	default boolean exist(Specification<S> filter) {
		Optional<S> optional = findFirst(filter);

		return optional.isPresent();
	}

	/**
	 * 获取分页数据
	 *
	 * @param filter filter
	 * @return ExecutiveResult<Page < S>>
	 */
	default long count(Example<S> filter) {
		return getRepository().count(filter);
	}

	/**
	 * 获取分页数据
	 *
	 * @return ExecutiveResult<Page < S>>
	 */
	default long count() {
		return getRepository().count();
	}

	/**
	 * 获取指定数据
	 *
	 * @param id 数据标识
	 * @return 数据实体
	 */
	default Optional<S> get(Long id) {
		return getRepository().findById(id);
	}

	/**
	 * 查找一个匹配
	 *
	 * @param filter filter
	 * @return Optional<S>
	 */
	default Optional<S> findOne(Example<S> filter) {
		return getRepository().findOne(filter);
	}

	/**
	 * 保存
	 *
	 * @param entity entity
	 * @return S
	 */
	default S save(@NonNull S entity) {
		beforeSave(entity);

		return getRepository().save(entity);
	}

	/**
	 * 保存前预处理
	 *
	 * @param entity 数据实体
	 */
	default void fixDataBeforeSave(S entity) {
	}

	/**
	 * 保存前处理
	 *
	 * @param entity 数据实体
	 */
	default void beforeSave(@NotNull S entity) {
		fixDataBeforeSave(entity);

		long id = entity.getId();

		id = id > 0 ? id : generateId();

		entity.setId(id);

		String ip = RequestAssist.getCurrentRequestRemoteAddress();

		if (StringAssist.isNullOrEmpty(ip)) {
			ip = InternetAddressAssist.getLocalHost();
		}

		entity.setIp(Optional.of(ip).orElse(""));

		entity.setUpdateTime(LocalDateTime.now());
	}

	/**
	 * 逻辑删除
	 *
	 * @param id id
	 * @return ExecutiveSimpleResult
	 */
	default ExecutiveSimpleResult removeById(Long id) {
		return new ExecutiveSimpleResult(ReturnDataCode.NeedOverride.toMessage());
	}

	/**
	 * 物理删除
	 *
	 * @param id id
	 */
	default void deleteById(Long id) {
		getRepository().deleteById(id);
	}
}
