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
public class OperatorRole extends BaseEntity {

	private static final long serialVersionUID = -3413222825054232328L;

	/**
	 * 账户标识
	 */
	@Column(name = "operator_id")
	long operatorId;

	/**
	 * 角色标识
	 */
	@Column(name = "role_id")
	long roleId;
}
