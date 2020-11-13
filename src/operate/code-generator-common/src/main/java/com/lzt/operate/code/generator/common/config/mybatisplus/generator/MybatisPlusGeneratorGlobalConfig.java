package com.lzt.operate.code.generator.common.config.mybatisplus.generator;

import com.lzt.operate.code.generator.common.config.base.generator.BaseGlobalConfig;
import com.lzt.operate.utility.assists.ConvertAssist;

import java.util.Optional;

/**
 * MybatisPlusGeneratorGlobalConfig
 */
public class MybatisPlusGeneratorGlobalConfig extends BaseGlobalConfig {

	private static final long serialVersionUID = -1208551375087585489L;

	public MybatisPlusGeneratorGlobalConfig() {
		super();
	}

	public static MybatisPlusGeneratorGlobalConfig Deserialize(String json) {
		try {
			MybatisPlusGeneratorGlobalConfig result = ConvertAssist.deserialize(json, MybatisPlusGeneratorGlobalConfig.class);

			if (Optional.ofNullable(result).isPresent()) {
				return result;
			}

			return new MybatisPlusGeneratorGlobalConfig();
		} catch (Exception e) {
			e.printStackTrace();

			return new MybatisPlusGeneratorGlobalConfig();
		}
	}
}
