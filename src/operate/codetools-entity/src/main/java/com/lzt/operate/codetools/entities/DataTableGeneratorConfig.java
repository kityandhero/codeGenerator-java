package com.lzt.operate.codetools.entities;

import com.lzt.operate.codetools.entities.bases.BaseEntity;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * GeneratorConfig is the Config of mybatis generator config exclude database
 * config
 * <p>
 *
 * @author luzhitao
 * @date 2020/03/17
 */
@Entity
@Table(name = "data_table_generator_config")
@EqualsAndHashCode(callSuper = true)
public class DataTableGeneratorConfig extends BaseEntity {

	private static final long serialVersionUID = -6940964534931128004L;

	@Column(name = "connection_config_id")
	private long connectionConfigId;

	@Column(name = "table_name")
	private String tableName;

	@Column(name = "domain_object_name")
	private String domainObjectName;

	@Column(name = "mapper_name")
	private String mapperName;

	@Column(name = "comment")
	private boolean comment;

	public DataTableGeneratorConfig() {
		super();
	}

	public long getConnectionConfigId() {
		return connectionConfigId;
	}

	public void setConnectionConfigId(long connectionConfigId) {
		this.connectionConfigId = connectionConfigId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDomainObjectName() {
		return domainObjectName;
	}

	public void setDomainObjectName(String domainObjectName) {
		this.domainObjectName = domainObjectName;
	}

	public String getMapperName() {
		return mapperName;
	}

	public void setMapperName(String mapperName) {
		this.mapperName = mapperName;
	}

	public boolean isComment() {
		return comment;
	}

	public void setComment(boolean comment) {
		this.comment = comment;
	}

}
