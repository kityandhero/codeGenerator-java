package com.lzt.operate.codetools.jpa;

import lombok.var;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @param <T>
 * @param <ID>
 * @author lzt
 */
public interface JpaRepositoryEx<T, ID> extends JpaRepository<T, ID> {

    /**
     * findFirst 查找第一个匹配项目
     *
     * @param example 匹配源
     * @param <S>     类型
     * @return 返回结果
     */
    default <S extends T> Optional<S> findFirst(Example<S> example) {

        var listFind = this.findAll(example);

        var list = Optional.of(listFind).orElse(new ArrayList<>());
        if (list.size() > 0) {
            return Optional.of(list.get(0));
        }

        return Optional.empty();
    }

}
