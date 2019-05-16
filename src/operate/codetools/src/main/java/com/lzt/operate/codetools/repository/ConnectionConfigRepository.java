package com.lzt.operate.codetools.repository;

import com.lzt.operate.codetools.domain.Operator;
import com.lzt.operate.codetools.jpa.JpaRepositoryEx;
import org.springframework.stereotype.Repository;

/**
 * @author lzt
 */
@Repository
public interface ConnectionConfigRepository extends JpaRepositoryEx<Operator, String> {
}
