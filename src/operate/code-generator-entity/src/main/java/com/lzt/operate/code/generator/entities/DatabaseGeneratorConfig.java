package com.lzt.operate.code.generator.entities;

import com.lzt.operate.code.generator.common.config.mybatis.generator.MybatisGeneratorGlobalConfig;
import com.lzt.operate.code.generator.common.config.mybatisplus.generator.MybatisPlusGeneratorGlobalConfig;
import com.lzt.operate.code.generator.common.enums.mybatis.GeneratorType;
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

	@Column(name = "generator_type", nullable = false)
	private int generatorType;

	@Lob
	@Column(name = "mybatis_generator_global_config", nullable = false, unique = true)
	private String mybatisGeneratorGlobalConfig;

	@Lob
	@Column(name = "mybatis_plus_generator_global_config", nullable = false, unique = true)
	private String mybatisPlusGeneratorGlobalConfig;

	public DatabaseGeneratorConfig() {
		this.connectionConfigId = 0;
		this.generatorType = GeneratorType.MybatisGenerator.getFlag();
		this.mybatisGeneratorGlobalConfig = "";
		this.mybatisPlusGeneratorGlobalConfig = "";
	}

	public long getConnectionConfigId() {
		return this.connectionConfigId;
	}

	public void setConnectionConfigId(long connectionConfigId) {
		this.connectionConfigId = connectionConfigId;
	}

	public int getGeneratorType() {
		return this.generatorType;
	}

	public void setGeneratorType(int generatorType) {
		this.generatorType = generatorType;
	}

	public String getMybatisGeneratorGlobalConfig() {
		return this.mybatisGeneratorGlobalConfig;
	}

	public void setMybatisGeneratorGlobalConfig(String mybatisGeneratorGlobalConfig) {
		this.mybatisGeneratorGlobalConfig = mybatisGeneratorGlobalConfig;
	}

	public String getMybatisPlusGeneratorGlobalConfig() {
		return this.mybatisPlusGeneratorGlobalConfig;
	}

	public void setMybatisPlusGeneratorGlobalConfig(String mybatisPlusGeneratorGlobalConfig) {
		this.mybatisPlusGeneratorGlobalConfig = mybatisPlusGeneratorGlobalConfig;
	}

	//region  mybatisGeneratorGlobalConfig

	public MybatisGeneratorGlobalConfig BuildMybatisGeneratorGlobalConfig() {
		MybatisGeneratorGlobalConfig mybatisGeneratorGlobalConfig = new MybatisGeneratorGlobalConfig();

		if (!StringAssist.isNullOrEmpty(this.getMybatisGeneratorGlobalConfig())) {
			mybatisGeneratorGlobalConfig = MybatisGeneratorGlobalConfig.Deserialize(this.getMybatisGeneratorGlobalConfig());
		}

		return mybatisGeneratorGlobalConfig;
	}

	public DatabaseGeneratorConfig fillInMybatisGeneratorGlobalConfig(@NotNull MybatisGeneratorGlobalConfig mybatisGeneratorGlobalConfig) {
		try {
			this.setMybatisGeneratorGlobalConfig(ConvertAssist.serialize(mybatisGeneratorGlobalConfig));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	//endregion

	//region  mybatisPlusGeneratorGlobalConfig

	public MybatisPlusGeneratorGlobalConfig BuildMybatisPlusGeneratorGlobalConfig() {
		MybatisPlusGeneratorGlobalConfig mybatisPlusGeneratorGlobalConfig = new MybatisPlusGeneratorGlobalConfig();

		if (!StringAssist.isNullOrEmpty(this.getMybatisPlusGeneratorGlobalConfig())) {
			mybatisPlusGeneratorGlobalConfig = MybatisPlusGeneratorGlobalConfig.Deserialize(this.getMybatisPlusGeneratorGlobalConfig());
		}

		return mybatisPlusGeneratorGlobalConfig;
	}

	public DatabaseGeneratorConfig fillInMybatisPlusGeneratorGlobalConfig(@NotNull MybatisPlusGeneratorGlobalConfig mybatisPlusGeneratorGlobalConfig) {
		try {
			this.setMybatisPlusGeneratorGlobalConfig(ConvertAssist.serialize(mybatisPlusGeneratorGlobalConfig));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	//endregion
}
