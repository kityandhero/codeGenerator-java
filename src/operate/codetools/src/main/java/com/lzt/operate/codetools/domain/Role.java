package com.lzt.operate.codetools.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author lzt
 */
@Data
@Entity
@Table(name = "Role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseDomain {
    /**
     * 名称
     */
    @Column(length = 50, unique = true)
    String name;

    /**
     * 描述
     */
    @Column(length = 500)
    String description;

    /**
     * 角色 -- 权限关系：多对多关系
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<Permission> permissions;

    /**
     * 用户 - 角色关系定义;一个角色对应多个用户
     */
    @ManyToMany
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "operatorId")})
    private List<Operator> userInfos;

    @Override
    protected void fixDataBeforeSave() {
    }
}
