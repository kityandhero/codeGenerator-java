package com.lzt.operate.utility.io.support;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.util.Properties;

/**
 * CorsConfig yml加载
 *
 * @author luzhitao
 */
public class YamlConfigFactory extends DefaultPropertySourceFactory {

	private static final String YML = ".yml";

	private static final String YAML = ".yaml";

	@Override
	@NonNull
	public PropertySource<?> createPropertySource(String name, @NonNull EncodedResource resource) throws IOException {
		String sourceName = name != null ? name : resource.getResource().getFilename();

		assert sourceName != null;

		if (!resource.getResource().exists()) {
			return new PropertiesPropertySource(sourceName, new Properties());
		} else {
			if (sourceName.endsWith(YML) || sourceName.endsWith(YAML)) {
				Properties propertiesFromYaml = loadYml(resource);

				return new PropertiesPropertySource(sourceName, propertiesFromYaml);
			} else {
				return super.createPropertySource(name, resource);
			}
		}
	}

	private Properties loadYml(EncodedResource resource) {
		YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();

		factory.setResources(resource.getResource());
		factory.afterPropertiesSet();

		return factory.getObject();
	}
}
