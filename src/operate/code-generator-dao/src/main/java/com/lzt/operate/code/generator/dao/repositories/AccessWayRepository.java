package com.lzt.operate.code.generator.dao.repositories;

import com.lzt.operate.code.generator.dao.jpa.JpaRepositoryEx;
import com.lzt.operate.code.generator.entities.AccessWay;
import org.springframework.stereotype.Repository;

/**
 * @author luzhitao
 */
@Repository
public interface AccessWayRepository extends JpaRepositoryEx<AccessWay, Long> {
}
