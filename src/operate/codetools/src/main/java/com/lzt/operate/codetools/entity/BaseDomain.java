package com.lzt.operate.codetools.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

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
public abstract class BaseDomain implements Serializable {

    /**
     * 主键
     * 这个是hibernate的注解/生成32位UUID
     *
     * @see GeneratedValue 指定生成器名称
     * @see GenericGenerator 生成器名称，uuid生成类
     */
    @Id
    @Column(nullable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
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
    int state;

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
    @DateTimeFormat
    @JsonFormat
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
    @DateTimeFormat
    @JsonFormat
    LocalDateTime updateTime;

    /**
     * 更新时间 UnixTime
     */
    @Column
    long updateUnixTime;

    BaseDomain() {
        this.autoRemark = "";
        this.state = 0;
        this.stateNote = "";
        this.ip = "";
        this.createTime = LocalDateTime.now();
        this.createOperatorId = "";
        this.updateTime = LocalDateTime.now();
        this.updateOperatorId = "";
    }
}
