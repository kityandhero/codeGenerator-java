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
@Table(name = "role_universal")
@EqualsAndHashCode(callSuper = true)
public class RoleUniversal extends BaseRole {

	private static final long serialVersionUID = -7381639390992375293L;

	public RoleUniversal() {
		super();
	}

}
