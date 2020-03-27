package com.lzt.operate.codetools.dao.service;

import com.lzt.operate.codetools.common.enums.CustomConfigCategory;
import com.lzt.operate.codetools.common.enums.CustomConfigCollection;
import com.lzt.operate.codetools.dao.repositories.CustomConfigRepository;
import com.lzt.operate.codetools.dao.service.bases.BaseService;
import com.lzt.operate.codetools.entities.CustomConfig;
import com.lzt.operate.utility.assists.EnumAssist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author luzhitao
 */
public interface CustomConfigService extends BaseService<CustomConfigRepository, CustomConfig> {

	/**
	 * 通过uuid取信息
	 *
	 * @param uuid uuid
	 * @return Optional<CustomConfig>
	 */
	Optional<CustomConfig> findByUuid(String uuid);

	/**
	 * 通过uuid取信息
	 *
	 * @param category category
	 * @return List<CustomConfig>
	 */
	default List<CustomConfig> listSource(int category) {
		Optional<CustomConfigCategory> optional = EnumAssist.getTargetValue(Arrays.asList(CustomConfigCategory.values()), CustomConfigCategory::getFlag, category);

		if (optional.isPresent()) {
			return listSource(optional.get());
		}

		return new ArrayList<>();
	}

	/**
	 * 通过uuid取信息
	 *
	 * @param customConfigCategory CustomConfigCategory
	 * @return List<CustomConfig>
	 */
	default List<CustomConfig> listSource(CustomConfigCategory customConfigCategory) {
		Integer flag = customConfigCategory.getFlag();

		return Arrays.stream(CustomConfigCollection.values())
					 .filter(o -> flag.equals(o.getCategory().getFlag()))
					 .map(o -> {
						 CustomConfig customConfig = new CustomConfig();

						 customConfig.setName(o.getName());
						 customConfig.setValue(o.getDefaultValue());
						 customConfig.setDescription(o.getDescription());
						 customConfig.setUuid(o.getUuid());

						 return customConfig;
					 })
					 .collect(Collectors.toList());
	}

}
