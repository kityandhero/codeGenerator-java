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
@Table(name = "account")
@EqualsAndHashCode(callSuper = true)
public class Account extends BaseEntity {

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

	public Account() {
		this.userName = "";
		this.password = "";
		this.name = "";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlat() {
		return slat;
	}

	public void setSlat(String slat) {
		this.slat = slat;
	}
}
