package com.lzt.operate.codetools.app.repositories;

import com.lzt.operate.codetools.app.entity.CustomConfig;
import com.lzt.operate.codetools.app.jpa.JpaRepositoryEx;
import org.springframework.stereotype.Repository;

/**
 * @author luzhitao
 */
@Repository
public interface CustomConfigRepository extends JpaRepositoryEx<CustomConfig, Long> {
}
