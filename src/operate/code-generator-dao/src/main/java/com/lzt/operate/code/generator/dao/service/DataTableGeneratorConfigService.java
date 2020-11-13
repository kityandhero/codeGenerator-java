package com.lzt.operate.code.generator.dao.service;

import com.lzt.operate.code.generator.dao.repositories.DataTableGeneratorConfigRepository;
import com.lzt.operate.code.generator.dao.service.bases.BaseService;
import com.lzt.operate.code.generator.entities.DataTableGeneratorConfig;
import com.lzt.operate.utility.assists.StringAssist;

import java.util.Optional;

/**
 * @author luzhitao
 */
public interface DataTableGeneratorConfigService extends BaseService<DataTableGeneratorConfigRepository, DataTableGeneratorConfig> {

	default boolean checkHasGenerateContent(DataTableGeneratorConfig dataTableGeneratorConfig) {
		if (Optional.ofNullable(dataTableGeneratorConfig).isPresent()) {
			boolean allContentIsEmpty = StringAssist.isNullOrEmpty(dataTableGeneratorConfig.getModelContent()) && StringAssist
					.isNullOrEmpty(dataTableGeneratorConfig.getExampleContent()) && StringAssist.isNullOrEmpty(dataTableGeneratorConfig
					.getMapperContent()) && StringAssist.isNullOrEmpty(dataTableGeneratorConfig.getMappingXmlContent());

			return !allContentIsEmpty;
		}

		return false;
	}

}
