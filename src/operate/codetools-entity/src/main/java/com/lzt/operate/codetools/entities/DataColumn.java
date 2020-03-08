package com.lzt.operate.codetools.entities;

import com.lzt.operate.codetools.entities.bases.BaseEntity;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 数据表列设置
 *
 * @author luzhitao
 */
@Entity
@Table(name = "data_column")
@EqualsAndHashCode(callSuper = true)
public class DataColumn extends BaseEntity {

	private static final long serialVersionUID = 828097585595882930L;

	private long connectionConfigId;

	private String aliasName;

	public DataColumn() {
		super();

		this.connectionConfigId = 0;
		this.aliasName = "";
	}

	public long getConnectionConfigId() {
		return connectionConfigId;
	}

	public void setConnectionConfigId(long connectionConfigId) {
		this.connectionConfigId = connectionConfigId;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
}
