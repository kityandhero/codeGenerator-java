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
	 * 系统角色权限题集合
	 */
	@Column(name = "role_universal_collection")
	String roleUniversalCollection;

	/**
	 * 自建角色权限题集合
	 */
	@Column(name = "role_code_tools_collection")
	String roleCodeToolsCollection;

	public OperatorRole() {
		super();

		operatorId = 0;
		roleUniversalCollection = "";
		roleCodeToolsCollection = "";
	}

}
