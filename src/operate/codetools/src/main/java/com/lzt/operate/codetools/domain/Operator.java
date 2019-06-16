package com.lzt.operate.codetools.domain;

import com.lzt.operate.utility.RequestAssist;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.var;

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
        java.util.Date now = new java.util.Date();

        this.name = "";
        this.password = "";
        this.friendlyName = "";
        this.createTime = now;
    }

    public static Operator GetCurrent() {
        var request = RequestAssist.getHttpServletRequest();

        var token = request.getHeader("token");

        return null;
    }
}
