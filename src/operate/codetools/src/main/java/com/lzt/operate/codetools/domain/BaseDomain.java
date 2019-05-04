package com.lzt.operate.codetools.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lzt
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract class BaseDomain implements Serializable {

    /**
     * 主键
     * 这个是hibernate的注解/生成32位UUID
     */
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    private String id;

    @Column
    private Date createTime;

}
