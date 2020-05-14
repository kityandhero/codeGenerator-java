package com.lzt.operate.code.generator.entities;

import com.lzt.operate.code.generator.common.config.mybatis.generator.GlobalConfig;
import com.lzt.operate.code.generator.entities.bases.BaseEntity;
import com.lzt.operate.utility.assists.ConvertAssist;
import com.lzt.operate.utility.assists.StringAssist;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author luzhitao
 */
@Entity
@Table(name = "database_generator_config")
@EqualsAndHashCode(callSuper = true)
public class DatabaseGeneratorConfig extends BaseEntity {
	private static final long serialVersionUID = 4482665423484753190L;

	@Column(name = "connection_config_id", nullable = false, unique = true)
	private long connectionConfigId;

	@Lob
	@Column(name = "mybatis_generator_global_config", nullable = false, unique = true)
	private String mybatisGeneratorGlobalConfig;

	public DatabaseGeneratorConfig() {
		connectionConfigId = 0;
		mybatisGeneratorGlobalConfig = "";
	}

	public long getConnectionConfigId() {
		return connectionConfigId;
	}

	public void setConnectionConfigId(long connectionConfigId) {
		this.connectionConfigId = connectionConfigId;
	}

	public String getMybatisGeneratorGlobalConfig() {
		return mybatisGeneratorGlobalConfig;
	}

	public void setMybatisGeneratorGlobalConfig(String mybatisGeneratorGlobalConfig) {
		this.mybatisGeneratorGlobalConfig = mybatisGeneratorGlobalConfig;
	}

	//region  mybatisGeneratorGlobalConfig

	public GlobalConfig BuildMyybatisGeneratorGlobalConfig() {
		GlobalConfig mybatisGeneratorGlobalConfig = new GlobalConfig();

		if (!StringAssist.isNullOrEmpty(getMybatisGeneratorGlobalConfig())) {
			mybatisGeneratorGlobalConfig = GlobalConfig.Deserialize(getMybatisGeneratorGlobalConfig());
		}

		return mybatisGeneratorGlobalConfig;
	}

	public DatabaseGeneratorConfig fillInMyybatisGeneratorGlobalConfig(@NotNull GlobalConfig mybatisGeneratorGlobalConfig) {
		try {
			setMybatisGeneratorGlobalConfig(ConvertAssist.serialize(mybatisGeneratorGlobalConfig));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	//endregion
}
