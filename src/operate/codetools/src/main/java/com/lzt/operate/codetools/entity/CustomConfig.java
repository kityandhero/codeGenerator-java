package com.lzt.operate.codetools.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

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
        LocalDateTime now = LocalDateTime.now();

        this.name = "";
        this.value = "";
        this.description = "";
        this.createTime = now;
    }
}
