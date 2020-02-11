package com.lzt.operate.codetools.repository;

import com.lzt.operate.codetools.entity.Operator;
import com.lzt.operate.codetools.jpa.JpaRepositoryEx;
import org.springframework.stereotype.Repository;

/**
 * @author luzhitao
 */
@Repository
public interface OperatorRepository extends JpaRepositoryEx<Operator, Long> {
}
