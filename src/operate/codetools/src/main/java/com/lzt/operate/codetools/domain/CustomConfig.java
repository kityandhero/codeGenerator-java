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
@Table(name = "customConfig")
@EqualsAndHashCode(callSuper = false)
public class CustomConfig extends BaseDomain {
    @Column(length = 50)
    String name;

    @Column
    String value;

    @Column
    String description;

    public CustomConfig() {
        java.util.Date now = new java.util.Date();

        this.name = "";
        this.value = "";
        this.description = "";
        this.createTime = now;
    }
}
