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
@Table(name = "operator")
@EqualsAndHashCode(callSuper = true)
public class Operator extends BaseDomain {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column
    private String friendlyName;

}
