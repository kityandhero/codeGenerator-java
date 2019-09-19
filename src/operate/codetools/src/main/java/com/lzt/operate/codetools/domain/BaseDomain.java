package com.lzt.operate.codetools.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lzt
 */
@Data
@MappedSuperclass
abstract class BaseDomain implements Serializable {

    /**
     * 主键
     * 这个是hibernate的注解/生成32位UUID
     */
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    String id;

    @Column
    LocalDateTime createTime;

    BaseDomain() {
        LocalDateTime createTime = LocalDateTime.now();
    }
}
