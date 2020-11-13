package com.lzt.operate.code.generator.common.config.generator;

import java.io.Serializable;

/**
 * @author luzhitao
 */
public class Table implements Serializable {

	private static final long serialVersionUID = 5254194287040558993L;

	private String tableName;

	private int useGenerateKey;

	private String generateKeys;

	private String domainObjectName;

	private int useActualColumnNames;

	private int useTableNameAlias;

	private String aliasName;

	public Table() {
		this.tableName = "";
		this.useGenerateKey = 0;
		this.generateKeys = "";
		this.domainObjectName = "";
		this.useActualColumnNames = 0;
		this.useTableNameAlias = 0;
		this.aliasName = "";
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getUseGenerateKey() {
		return this.useGenerateKey;
	}

	public void setUseGenerateKey(int useGenerateKey) {
		this.useGenerateKey = useGenerateKey;
	}

	public String getGenerateKeys() {
		return this.generateKeys;
	}

	public void setGenerateKeys(String generateKeys) {
		this.generateKeys = generateKeys;
	}

	public String getDomainObjectName() {
		return this.domainObjectName;
	}

	public void setDomainObjectName(String domainObjectName) {
		this.domainObjectName = domainObjectName;
	}

	public int getUseActualColumnNames() {
		return this.useActualColumnNames;
	}

	public void setUseActualColumnNames(int useActualColumnNames) {
		this.useActualColumnNames = useActualColumnNames;
	}

	public int getUseTableNameAlias() {
		return this.useTableNameAlias;
	}

	public void setUseTableNameAlias(int useTableNameAlias) {
		this.useTableNameAlias = useTableNameAlias;
	}

	public String getAliasName() {
		return this.aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
}
