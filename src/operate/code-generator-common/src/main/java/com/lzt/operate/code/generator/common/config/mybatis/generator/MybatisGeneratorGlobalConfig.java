package com.lzt.operate.code.generator.common.config.mybatis.generator;

import com.lzt.operate.code.generator.common.config.base.generator.BaseGlobalConfig;
import com.lzt.operate.utility.assists.ConvertAssist;

import java.util.Optional;

/**
 * MybatisGeneratorGlobalConfig
 *
 * @author luzhitao
 */
public class MybatisGeneratorGlobalConfig extends BaseGlobalConfig {

	private static final long serialVersionUID = -1208551375087585488L;

	public MybatisGeneratorGlobalConfig() {
		super();
	}

	public static MybatisGeneratorGlobalConfig Deserialize(String json) {
		try {
			MybatisGeneratorGlobalConfig result = ConvertAssist.deserialize(json, MybatisGeneratorGlobalConfig.class);

			if (Optional.ofNullable(result).isPresent()) {
				return result;
			}

			return new MybatisGeneratorGlobalConfig();
		} catch (Exception e) {
			e.printStackTrace();

			return new MybatisGeneratorGlobalConfig();
		}
	}
}
