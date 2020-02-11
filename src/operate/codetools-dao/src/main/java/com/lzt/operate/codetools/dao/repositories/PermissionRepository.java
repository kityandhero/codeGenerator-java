package com.lzt.operate.codetools.dao.repositories;

import com.lzt.operate.codetools.dao.jpa.JpaRepositoryEx;
import com.lzt.operate.codetools.entities.Permission;
import org.springframework.stereotype.Repository;

/**
 * @author luzhitao
 */
@Repository
public interface PermissionRepository extends JpaRepositoryEx<Permission, Long> {
}
