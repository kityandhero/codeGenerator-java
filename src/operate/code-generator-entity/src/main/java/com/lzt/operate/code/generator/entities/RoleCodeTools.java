package com.lzt.operate.code.generator.entities;

import com.lzt.operate.code.generator.entities.bases.BaseRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author luzhitao
 */
@Entity
@Table(name = "role_code_tools")
@EqualsAndHashCode(callSuper = true)
public class RoleCodeTools extends BaseRole {

	private static final long serialVersionUID = -8450387032840618147L;

	public RoleCodeTools() {
		super();
	}

}
