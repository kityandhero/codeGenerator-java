package com.lzt.operate.codetools.entities;

import com.lzt.operate.codetools.entities.bases.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author luzhitao
 */
@Data
@Entity
@Table(name = "operator")
@EqualsAndHashCode(callSuper = true)
public class Operator extends BaseEntity {

	private static final long serialVersionUID = 6804364620994420244L;

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

	public Operator() {
		this.userName = "";
		this.password = "";
		this.name = "";
	}
}
