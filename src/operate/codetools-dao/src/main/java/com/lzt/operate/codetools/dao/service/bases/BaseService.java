package com.lzt.operate.codetools.dao.service.bases;

import com.baidu.fsg.uid.service.UidGenService;
import com.lzt.operate.codetools.dao.jpa.JpaRepositoryEx;
import com.lzt.operate.codetools.entities.bases.BaseEntity;
import com.lzt.operate.utility.assists.LocalDateTimeAssist;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.pojo.results.ExecutiveSimpleResult;
import lombok.var;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
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
	 * @param filter   filter
	 * @param pageable pageable
	 * @return Page<S>
	 */
	default Page<S> page(Example<S> filter, Pageable pageable) {
		Optional<R> optional = Optional.ofNullable(getRepository());

		if (optional.isPresent()) {
			return optional.get().findAll(filter, pageable);
		}

		throw new RuntimeException("默认Repository获取失败");
	}

	/**
	 * 获取指定数据
	 *
	 * @param id 数据标识
	 * @return 数据实体
	 */
	default Optional<S> get(Long id) {
		Optional<R> optional = Optional.ofNullable(getRepository());

		if (optional.isPresent()) {
			return optional.get().findById(id);
		}

		throw new RuntimeException("默认Repository获取失败");
	}

	/**
	 * 查找一个匹配
	 *
	 * @param filter filter
	 * @return Optional<S>
	 */
	default Optional<S> findOne(Example<S> filter) {
		Optional<R> optional = Optional.ofNullable(getRepository());

		if (optional.isPresent()) {
			return optional.get().findOne(filter);
		}

		throw new RuntimeException("默认Repository获取失败");
	}

	/**
	 * 保存
	 *
	 * @param entity entity
	 * @return S
	 */
	default S save(S entity) {
		Optional<R> optional = Optional.ofNullable(getRepository());

		if (optional.isPresent()) {
			beforeSave(entity);

			return optional.get().save(entity);
		}

		throw new RuntimeException("默认Repository获取失败");
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
	default void beforeSave(S entity) {
		fixDataBeforeSave(entity);

		var id = entity.getId();

		id = id > 0 ? id : generateId();

		entity.setId(id);

		var createTime = entity.getCreateTime();
		var updateTime = entity.getUpdateTime();

		entity.setCreateTime(Optional.of(createTime).orElse(LocalDateTime.now()));
		entity.setCreateUnixTime(LocalDateTimeAssist.toUnixTime(createTime));

		entity.setUpdateTime(Optional.of(updateTime).orElse(LocalDateTime.now()));
		entity.setUpdateUnixTime(LocalDateTimeAssist.toUnixTime(updateTime));
	}

	/**
	 * 逻辑删除
	 *
	 * @param id id
	 * @return ExecutiveSimpleResult
	 */
	default ExecutiveSimpleResult removeById(Long id) {
		return new ExecutiveSimpleResult(ReturnDataCode.NeedOverride);
	}

	/**
	 * 物理删除
	 *
	 * @param id id
	 * @return ExecutiveSimpleResult
	 */
	default ExecutiveSimpleResult deleteById(Long id) {
		this.getRepository().deleteById(id);

		return new ExecutiveSimpleResult(ReturnDataCode.Ok);
	}
}
