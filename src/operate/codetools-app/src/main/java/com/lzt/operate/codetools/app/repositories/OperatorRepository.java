package com.lzt.operate.codetools.app.repositories;

import com.lzt.operate.codetools.app.entity.Operator;
import com.lzt.operate.codetools.app.jpa.JpaRepositoryEx;
import org.springframework.stereotype.Repository;

/**
 * @author luzhitao
 */
@Repository
public interface OperatorRepository extends JpaRepositoryEx<Operator, Long> {
}
