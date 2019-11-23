package com.lzt.operate.codetools.service;

import com.lzt.operate.codetools.entity.BaseDomain;
import com.lzt.operate.utility.LocalDateTimeAssist;
import lombok.var;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author lzt
 */
public interface BaseService<S extends BaseDomain> {
    /**
     * 获取分页数据
     *
     * @param filter   filter
     * @param pageable pageable
     * @return Page<S>
     */
    Page<S> page(Example<S> filter, Pageable pageable);

    /**
     * 获取指定数据
     *
     * @param id 数据标识
     * @return 数据实体
     */
    Optional<S> get(String id);

    /**
     * 查找一个匹配
     *
     * @param filter filter
     * @return Optional<S>
     */
    Optional<S> findOne(Example<S> filter);

    /**
     * 保存
     *
     * @param entity entity
     * @return S
     */
    S save(S entity);

    /**
     * 保存前预处理
     *
     * @param entity 数据实体
     */
    void fixDataBeforeSave(S entity);

    /**
     * 保存前处理
     *
     * @param entity 数据实体
     */
    default void beforeSave(S entity) {
        fixDataBeforeSave(entity);

        var createTime = entity.getCreateTime();
        var updateTime = entity.getUpdateTime();

        entity.setCreateTime(Optional.of(createTime).orElse(LocalDateTime.now()));
        entity.setCreateUnixTime(LocalDateTimeAssist.toUnixTime(createTime));

        entity.setUpdateTime(Optional.of(updateTime).orElse(LocalDateTime.now()));
        entity.setUpdateUnixTime(LocalDateTimeAssist.toUnixTime(updateTime));
    }
}
