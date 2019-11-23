package com.lzt.operate.codetools.repository;

import com.lzt.operate.codetools.entity.CustomConfig;
import com.lzt.operate.codetools.jpa.JpaRepositoryEx;
import org.springframework.stereotype.Repository;

/**
 * @author lzt
 */
@Repository
public interface CustomConfigRepository extends JpaRepositoryEx<CustomConfig, String> {
}
