package com.lzt.operate.codetools.app.repositories;

import com.lzt.operate.codetools.app.entity.ConnectionConfig;
import com.lzt.operate.codetools.app.jpa.JpaRepositoryEx;
import org.springframework.stereotype.Repository;

/**
 * @author luzhitao
 */
@Repository
public interface ConnectionConfigRepository extends JpaRepositoryEx<ConnectionConfig, Long> {
}
