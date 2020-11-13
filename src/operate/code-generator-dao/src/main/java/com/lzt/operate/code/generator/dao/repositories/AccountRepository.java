package com.lzt.operate.code.generator.dao.repositories;

import com.lzt.operate.code.generator.entities.Account;
import com.lzt.operate.jpa.base.repository.JpaRepositoryEx;
import org.springframework.stereotype.Repository;

/**
 * @author luzhitao
 */
@Repository
public interface AccountRepository extends JpaRepositoryEx<Account, Long> {
}
