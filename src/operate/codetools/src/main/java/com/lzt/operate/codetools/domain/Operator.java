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

    @Column(length = 50)
    String name;

    @Column
    String password;

    @Column(length = 50)
    String friendlyName;

    public Operator() {
        this.name = "";
        this.password = "";
        this.friendlyName = "";
    }
}
