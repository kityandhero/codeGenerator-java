package com.lzt.operate.mybatis.custom.config;

import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.ModelType;

import java.util.List;

public class ContextCustom extends Context {
	//添加ServiceGeneratorConfiguration
	private JavaServiceGeneratorConfiguration javaServiceGeneratorConfiguration;

	private JavaControllerGeneratorConfiguration javaControllerGeneratorConfiguration;

	public ContextCustom(ModelType defaultModelType) {
		super(defaultModelType);
	}

	public JavaServiceGeneratorConfiguration getJavaServiceGeneratorConfiguration() {
		return this.javaServiceGeneratorConfiguration;
	}

	public void setJavaServiceGeneratorConfiguration(JavaServiceGeneratorConfiguration javaServiceGeneratorConfiguration) {
		this.javaServiceGeneratorConfiguration = javaServiceGeneratorConfiguration;
	}

	public JavaControllerGeneratorConfiguration getJavaControllerGeneratorConfiguration() {
		return this.javaControllerGeneratorConfiguration;
	}

	public void setJavaControllerGeneratorConfiguration(JavaControllerGeneratorConfiguration javaControllerGeneratorConfiguration) {
		this.javaControllerGeneratorConfiguration = javaControllerGeneratorConfiguration;
	}

	@Override
	public void validate(List<String> errors) {
		if (this.javaServiceGeneratorConfiguration != null) {
			this.javaServiceGeneratorConfiguration.validate(errors, this.getId());
		}

		if (this.javaControllerGeneratorConfiguration != null) {
			this.javaControllerGeneratorConfiguration.validate(errors, this.getId());
		}

		super.validate(errors);
	}

	@Override
	public XmlElement toXmlElement() {

		XmlElement xmlElement = super.toXmlElement();

		if (this.javaServiceGeneratorConfiguration != null) {
			xmlElement.addElement(this.javaServiceGeneratorConfiguration.toXmlElement());
		}

		if (this.javaControllerGeneratorConfiguration != null) {
			xmlElement.addElement(this.javaControllerGeneratorConfiguration.toXmlElement());
		}
		
		return xmlElement;
	}
}