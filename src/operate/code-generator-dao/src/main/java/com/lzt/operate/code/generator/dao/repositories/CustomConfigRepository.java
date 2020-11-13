package com.lzt.operate.code.generator.dao.repositories;

import com.lzt.operate.code.generator.entities.CustomConfig;
import com.lzt.operate.jpa.base.repository.JpaRepositoryEx;
import org.springframework.stereotype.Repository;

/**
 * @author luzhitao
 */
@Repository
public interface CustomConfigRepository extends JpaRepositoryEx<CustomConfig, Long> {
}
