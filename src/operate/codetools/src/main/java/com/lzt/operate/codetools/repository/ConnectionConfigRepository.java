package com.lzt.operate.codetools.repository;

import com.lzt.operate.codetools.entity.ConnectionConfig;
import com.lzt.operate.codetools.jpa.JpaRepositoryEx;
import org.springframework.stereotype.Repository;

/**
 * @author luzhitao
 */
@Repository
public interface ConnectionConfigRepository extends JpaRepositoryEx<ConnectionConfig, Long> {
}
