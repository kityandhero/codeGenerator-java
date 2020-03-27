package com.lzt.operate.code.generator.entities;

import com.lzt.operate.code.generator.entities.bases.BaseEntity;
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
	@Column(length = 50, nullable = false, unique = true)
	String userName;

	/**
	 * 密码
	 */
	@Column(nullable = false)
	String password;

	/**
	 * 名称
	 */
	@Column(length = 50, nullable = false)
	String name;

	/**
	 * 名称
	 */
	@Column(name = "city_name", nullable = false)
	String cityName;

	/**
	 * 名称
	 */
	@Column(name = "city_code", nullable = false)
	long cityCode;

	/**
	 * 名称
	 */
	@Column(nullable = false)
	String email;

	/**
	 * 名称
	 */
	@Column(nullable = false)
	String phone;

	/**
	 * 名称
	 */
	@Column(nullable = false)
	String avatar;

	/**
	 * 名称
	 */
	@Column(length = 300, nullable = false)
	String description;

	/**
	 * 密码混淆值
	 */
	@Column(length = 50, nullable = false)
	String slat;

	public Account() {
		this.userName = "";
		this.password = "";
		this.name = "";
		this.description = "";
		this.cityName = "";
		this.cityCode = 0;
		this.email = "";
		this.phone = "";
		this.avatar = "";
		this.slat = "";
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public long getCityCode() {
		return cityCode;
	}

	public void setCityCode(long cityCode) {
		this.cityCode = cityCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSlat() {
		return slat;
	}

	public void setSlat(String slat) {
		this.slat = slat;
	}
}
