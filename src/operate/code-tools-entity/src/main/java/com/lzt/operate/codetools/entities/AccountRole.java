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
@Table(name = "account_role")
@EqualsAndHashCode(callSuper = true)
public class AccountRole extends BaseEntity {

	private static final long serialVersionUID = -3413222825054232328L;

	/**
	 * 账户标识
	 */
	@Column(name = "account_id", nullable = false)
	long accountId;

	/**
	 * 系统角色权限题集合
	 */
	@Column(name = "role_universal_collection", nullable = false)
	String roleUniversalCollection;

	/**
	 * 自建角色权限题集合
	 */
	@Column(name = "role_code_tools_collection", nullable = false)
	String roleCodeToolsCollection;

	public AccountRole() {
		super();

		accountId = 0;
		roleUniversalCollection = "";
		roleCodeToolsCollection = "";
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getRoleUniversalCollection() {
		return roleUniversalCollection;
	}

	public void setRoleUniversalCollection(String roleUniversalCollection) {
		this.roleUniversalCollection = roleUniversalCollection;
	}

	public String getRoleCodeToolsCollection() {
		return roleCodeToolsCollection;
	}

	public void setRoleCodeToolsCollection(String roleCodeToolsCollection) {
		this.roleCodeToolsCollection = roleCodeToolsCollection;
	}
}
