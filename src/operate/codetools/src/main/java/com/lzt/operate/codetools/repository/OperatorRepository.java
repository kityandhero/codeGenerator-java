package com.lzt.operate.codetools.repository;

import com.lzt.operate.codetools.domain.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lzt
 */
@Repository
public interface OperatorRepository extends JpaRepository<Operator, String> {
}
