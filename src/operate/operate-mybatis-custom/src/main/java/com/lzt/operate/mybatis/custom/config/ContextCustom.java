package com.lzt.operate.mybatis.custom.config;

import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.ModelType;

import java.util.List;

public class ContextCustom extends Context {
	//添加ServiceGeneratorConfiguration
	private JavaServiceGeneratorConfiguration javaServiceGeneratorConfiguration;

	public ContextCustom(ModelType defaultModelType) {
		super(defaultModelType);
	}

	public JavaServiceGeneratorConfiguration getJavaServiceGeneratorConfiguration() {
		return javaServiceGeneratorConfiguration;
	}

	public void setJavaServiceGeneratorConfiguration(JavaServiceGeneratorConfiguration javaServiceGeneratorConfiguration) {
		this.javaServiceGeneratorConfiguration = javaServiceGeneratorConfiguration;
	}

	@Override
	public void validate(List<String> errors) {
		if (javaServiceGeneratorConfiguration != null) {
			javaServiceGeneratorConfiguration.validate(errors, this.getId());
		}

		super.validate(errors);
	}

	@Override
	public XmlElement toXmlElement() {

		XmlElement xmlElement = super.toXmlElement();
		if (javaServiceGeneratorConfiguration != null) {
			xmlElement.addElement(javaServiceGeneratorConfiguration.toXmlElement());
		}
		return xmlElement;
	}
}