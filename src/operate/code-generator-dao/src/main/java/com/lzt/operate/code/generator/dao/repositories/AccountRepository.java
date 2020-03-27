package com.lzt.operate.code.generator.dao.repositories;

import com.lzt.operate.code.generator.dao.jpa.JpaRepositoryEx;
import com.lzt.operate.code.generator.entities.Account;
import org.springframework.stereotype.Repository;

/**
 * @author luzhitao
 */
@Repository
public interface AccountRepository extends JpaRepositoryEx<Account, Long> {
}
