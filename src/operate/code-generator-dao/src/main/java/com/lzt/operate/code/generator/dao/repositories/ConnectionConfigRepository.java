package com.lzt.operate.code.generator.dao.repositories;

import com.lzt.operate.code.generator.dao.jpa.JpaRepositoryEx;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import org.springframework.stereotype.Repository;

/**
 * @author luzhitao
 */
@Repository
public interface ConnectionConfigRepository extends JpaRepositoryEx<ConnectionConfig, Long> {
}
