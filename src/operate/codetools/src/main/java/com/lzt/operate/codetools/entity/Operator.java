package com.lzt.operate.codetools.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzt
 */
@Data
@Entity
@Table(name = "operator")
@EqualsAndHashCode(callSuper = true)
public class Operator extends BaseDomain {

    /**
     * 登陆名
     */
    @Column(length = 50, unique = true)
    String userName;

    /**
     * 密码
     */
    @Column
    String password;

    /**
     * 名称
     */
    @Column(length = 50)
    String name;

    /**
     * 密码混淆值
     */
    @Column(length = 50)
    String slat;

    /**
     * 立即从数据库中进行加载数据，一个用户具有多个角色;
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "OperatorRole", joinColumns = {@JoinColumn(name = "operatorId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<OperatorRole> roleList;

    public Operator() {
        this.userName = "";
        this.password = "";
        this.name = "";
        this.roleList = new ArrayList<>();
    }
}
