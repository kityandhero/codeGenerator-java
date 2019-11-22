package com.lzt.operate.codetools.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author lzt
 */
@Data
@Entity
@Table(name = "Permission")
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseDomain {
    /**
     * 名称
     */
    @Column(length = 50, unique = true)
    String name;

    @Override
    protected void fixDataBeforeSave() {
    }
}
