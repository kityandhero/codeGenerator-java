package com.lzt.operate.codetools.dao.repositories;

import com.lzt.operate.codetools.dao.jpa.JpaRepositoryEx;
import com.lzt.operate.codetools.entities.ConnectionConfig;
import org.springframework.stereotype.Repository;

/**
 * @author luzhitao
 */
@Repository
public interface ConnectionConfigRepository extends JpaRepositoryEx<ConnectionConfig, Long> {
}
