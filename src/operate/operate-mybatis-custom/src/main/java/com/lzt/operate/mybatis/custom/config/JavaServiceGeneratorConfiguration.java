package com.lzt.operate.mybatis.custom.config;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.PropertyHolder;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;

import java.util.List;

/**
 * @author luzhitao
 */
public class JavaServiceGeneratorConfiguration extends PropertyHolder {

	private String targetPackage;
	private String implementationPackage;
	private String targetProject;

	/**
	 *
	 */
	public JavaServiceGeneratorConfiguration() {
		super();
	}

	public String getTargetPackage() {
		return targetPackage;
	}

	public void setTargetPackage(String targetPackage) {
		this.targetPackage = targetPackage;
	}

	public String getImplementationPackage() {
		return implementationPackage;
	}

	public void setImplementationPackage(String implementationPackage) {
		this.implementationPackage = implementationPackage;
	}

	public String getTargetProject() {
		return targetProject;
	}

	public void setTargetProject(String targetProject) {
		this.targetProject = targetProject;
	}

	public XmlElement toXmlElement() {
		XmlElement answer = new XmlElement("javaServiceGenerator");

		if (targetPackage != null) {
			answer.addAttribute(new Attribute("targetPackage", targetPackage));
		}

		if (implementationPackage != null) {
			answer.addAttribute(new Attribute("implementationPackage", targetPackage));
		}
		if (targetProject != null) {
			answer.addAttribute(new Attribute("targetProject", targetProject));
		}

		addPropertyXmlElements(answer);

		return answer;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	public void validate(List errors, String contextId) {
		if (!StringUtility.stringHasValue(getTargetProject())) {
			errors.add(Messages.getString("ValidationError.102", contextId));
		}
		if (!StringUtility.stringHasValue(getTargetPackage())) {
			errors.add(Messages.getString("ValidationError.112", "ServiceGenerator", contextId));
		}
		if (!StringUtility.stringHasValue(getImplementationPackage())) {
			errors.add(Messages.getString("ValidationError.120", contextId));
		}
	}

}