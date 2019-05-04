package com.lzt.operate.codetools.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lzt
 */
@Data
@Entity
@Table(name = "operator")
public class Operator implements Serializable {

    /**
     * 主键
     * 这个是hibernate的注解/生成32位UUID
     */
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String loginName;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column
    private Date createTime;

}
