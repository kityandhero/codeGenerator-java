package com.lzt.operate.codetools.dao.repositories;

import com.lzt.operate.codetools.dao.jpa.JpaRepositoryEx;
import com.lzt.operate.codetools.entities.CustomConfig;
import org.springframework.stereotype.Repository;

/**
 * @author luzhitao
 */
@Repository
public interface CustomConfigRepository extends JpaRepositoryEx<CustomConfig, Long> {
}
