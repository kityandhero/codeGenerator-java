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

    /**
     * 程序最后操作备注
     */
    @Column
    String autoRemark;

    /**
     * 状态
     */
    @Column
    Integer state;

    /**
     * 状态文本
     */
    @Column
    String stateNote;

    /**
     * 最后操作IP
     */
    @Column
    String ip;

    /**
     * 创建人标识
     */
    @Column
    String createOperatorId;

    /**
     * 创建时间
     */
    @Column
    LocalDateTime createTime;

    /**
     * 创建时间 UnixTIme
     */
    @Column
    long createUnixTime;

    /**
     * 最后更信人
     */
    @Column
    String updateOperatorId;

    /**
     * 更新时间
     */
    @Column
    LocalDateTime updateTime;

    /**
     * 更新时间 UnixTime
     */
    @Column
    long updateUnixTime;

    BaseDomain() {
        this.createTime = LocalDateTime.now();
    }
}
