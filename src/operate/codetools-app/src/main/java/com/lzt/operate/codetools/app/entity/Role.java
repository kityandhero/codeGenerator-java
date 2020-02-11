package com.lzt.operate.codetools.app.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author luzhitao
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
     * 权限数量
     */
    @Column
    int moduleCount;

    /**
     * 权限集合体
     */
    @Column
    String competence;

    /**
     * 是否超级管理员
     */
    @Column
    boolean isSuper;

    public Role() {
        this.name = "";
        this.description = "";
        this.moduleCount = 0;
        this.competence = "";
        this.isSuper = false;
    }
}
