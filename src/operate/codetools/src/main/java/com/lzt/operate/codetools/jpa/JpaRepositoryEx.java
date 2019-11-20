package com.lzt.operate.codetools.jpa;

import com.lzt.operate.codetools.domain.BaseDomain;
import lombok.var;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @param <T>
 * @param <ID>
 * @author lzt
 */
public interface JpaRepositoryEx<T extends BaseDomain, ID> extends JpaRepository<T, ID> {

    /**
     * findFirst 查找第一个匹配项目
     *
     * @param example 匹配源
     * @param <S>     类型
     * @param sort    排序方式
     * @return 返回结果
     */
    default <S extends T> Optional<S> findFirst(Example<S> example, Sort sort) {

        var list = this.findAll(example, sort);

        if (list.size() > 0) {
            var o = list.get(0);

            return Optional.of(o);
        }

        return Optional.empty();
    }

    /**
     * 预处理后保存
     *
     * @param entity 需要保存的数据
     * @param <S>    类型
     * @return 保存后的数据
     */
    default <S extends T> S saveAfterPretreatment(S entity) {

        entity.beforeSave();

        return this.save(entity);
    }

}
