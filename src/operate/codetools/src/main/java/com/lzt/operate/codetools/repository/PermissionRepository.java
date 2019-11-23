package com.lzt.operate.codetools.repository;

import com.lzt.operate.codetools.entity.Permission;
import com.lzt.operate.codetools.jpa.JpaRepositoryEx;
import org.springframework.stereotype.Repository;

/**
 * @author lzt
 */
@Repository
public interface PermissionRepository extends JpaRepositoryEx<Permission, String> {
}
