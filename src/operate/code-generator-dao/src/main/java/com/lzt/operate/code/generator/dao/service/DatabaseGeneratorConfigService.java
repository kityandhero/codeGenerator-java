package com.lzt.operate.code.generator.dao.service;

import com.lzt.operate.code.generator.common.config.mybatis.generator.MybatisGeneratorGlobalConfig;
import com.lzt.operate.code.generator.common.config.mybatisplus.generator.MybatisPlusGeneratorGlobalConfig;
import com.lzt.operate.code.generator.common.enums.DatabaseType;
import com.lzt.operate.code.generator.common.enums.mybatis.GeneratorType;
import com.lzt.operate.code.generator.dao.repositories.DatabaseGeneratorConfigRepository;
import com.lzt.operate.code.generator.dao.service.bases.BaseService;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.code.generator.entities.DatabaseGeneratorConfig;
import com.lzt.operate.utility.assists.EnumAssist;
import com.lzt.operate.utility.assists.ReflectAssist;
import com.lzt.operate.utility.general.ConstantCollection;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author luzhitao
 */
public interface DatabaseGeneratorConfigService extends BaseService<DatabaseGeneratorConfigRepository, DatabaseGeneratorConfig> {

	/**
	 * findByConnectionConfigId
	 *
	 * @param connectionConfigId connectionConfigId
	 * @return Optional<DataColumn>
	 */
	default Optional<DatabaseGeneratorConfig> findByConnectionConfigId(long connectionConfigId) {
		Specification<DatabaseGeneratorConfig> specification = new Specification<DatabaseGeneratorConfig>() {

			private static final long serialVersionUID = -3508744018398462569L;

			@Override
			public Predicate toPredicate(@NonNull Root<DatabaseGeneratorConfig> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<>();

				list.add(criteriaBuilder.equal(root.get(ReflectAssist.getFieldName(DatabaseGeneratorConfig::getConnectionConfigId)), connectionConfigId));

				Predicate[] p = new Predicate[list.size()];

				return criteriaBuilder.and(list.toArray(p));
			}
		};

		return this.findFirst(specification);
	}

	/**
	 * findByConnectionConfigId
	 *
	 * @param connectionConfig connectionConfig
	 */
	default void changeConnectorJarPathByConnectionConfig(@NotNull ConnectionConfig connectionConfig) {
		if (ConstantCollection.ZERO_LONG.equals(connectionConfig.getId())) {
			return;
		}

		Optional<DatabaseType> optionalDatabaseType = EnumAssist.getTargetValue(DatabaseType.valuesToList(), DatabaseType::getFlag, connectionConfig
				.getDatabaseType());

		if (optionalDatabaseType.isPresent()) {
			DatabaseType databaseType = optionalDatabaseType.get();

			Optional<DatabaseGeneratorConfig> optional = this.findByConnectionConfigId(connectionConfig.getId());

			if (optional.isPresent()) {
				DatabaseGeneratorConfig databaseGeneratorConfig = optional.get();

				if (GeneratorType.MybatisGenerator.getFlag().equals(databaseGeneratorConfig.getGeneratorType())) {
					MybatisGeneratorGlobalConfig mybatisGeneratorGlobalConfig = databaseGeneratorConfig.BuildMybatisGeneratorGlobalConfig();

					mybatisGeneratorGlobalConfig.setConnectorJarFile(databaseType.getConnectorJarFile());

					databaseGeneratorConfig.fillInMybatisGeneratorGlobalConfig(mybatisGeneratorGlobalConfig);

					this.save(databaseGeneratorConfig);
				}

				if (GeneratorType.MybatisPlusGenerator.getFlag().equals(databaseGeneratorConfig.getGeneratorType())) {
					MybatisPlusGeneratorGlobalConfig mybatisPlusGeneratorGlobalConfig = databaseGeneratorConfig.BuildMybatisPlusGeneratorGlobalConfig();

					mybatisPlusGeneratorGlobalConfig.setConnectorJarFile(databaseType.getConnectorJarFile());

					databaseGeneratorConfig.fillInMybatisPlusGeneratorGlobalConfig(mybatisPlusGeneratorGlobalConfig);

					this.save(databaseGeneratorConfig);
				}

			}
		}
	}

}
