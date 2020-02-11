package com.lzt.operate.codetools.entities;

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
@Table(name = "operator_role")
@EqualsAndHashCode(callSuper = true)
public class OperatorRole extends BaseDomain {
	/**
	 * 账户标识
	 */
	@Column(length = 50)
	String operatorId;

	/**
	 * 角色标识
	 */
	@Column(length = 50)
	String roleId;
}
