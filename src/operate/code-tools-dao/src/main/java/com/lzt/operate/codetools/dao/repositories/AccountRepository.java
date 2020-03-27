package com.lzt.operate.codetools.dao.repositories;

import com.lzt.operate.codetools.dao.jpa.JpaRepositoryEx;
import com.lzt.operate.codetools.entities.Account;
import org.springframework.stereotype.Repository;

/**
 * @author luzhitao
 */
@Repository
public interface AccountRepository extends JpaRepositoryEx<Account, Long> {
}
