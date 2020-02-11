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
@Table(name = "OperatorRole")
@EqualsAndHashCode(callSuper = true)
public class OperatorRole extends BaseDomain {
    /**
     * 账户标识
     */
    @Column(length = 50)
    String operatorId;

    /**
     * 角色标识
     */
    @Column(length = 50)
    String roleId;
}
