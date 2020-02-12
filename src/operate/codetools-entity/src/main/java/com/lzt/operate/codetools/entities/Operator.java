package com.lzt.operate.codetools.entities;

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

	/**
	 * 立即从数据库中进行加载数据，一个用户具有多个角色;
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "operator_role", joinColumns = {@JoinColumn(name = "operator_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
	private List<OperatorRole> roleList;

	public Operator() {
		this.userName = "";
		this.password = "";
		this.name = "";
		this.roleList = new ArrayList<>();
	}
}
