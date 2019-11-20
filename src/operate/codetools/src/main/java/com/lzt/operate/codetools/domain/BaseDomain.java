package com.lzt.operate.codetools.domain;

import com.lzt.operate.utility.LocalDateTimeAssist;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author lzt
 */
@Data
@MappedSuperclass
public abstract class BaseDomain<virtual> implements Serializable {

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
        this.updateTime = LocalDateTime.now();
    }

    /**
     * 保存前预处理
     */
    protected abstract void fixDataBeforeSave();

    /**
     * 保存前处理
     */
    public void beforeSave() {
        fixDataBeforeSave();

        this.createTime = Optional.of(this.createTime).orElse(LocalDateTime.now());
        this.createUnixTime = LocalDateTimeAssist.toUnixTime(this.createTime);

        this.updateTime = Optional.of(this.updateTime).orElse(LocalDateTime.now());
        this.updateUnixTime = LocalDateTimeAssist.toUnixTime(this.updateTime);
    }
}
