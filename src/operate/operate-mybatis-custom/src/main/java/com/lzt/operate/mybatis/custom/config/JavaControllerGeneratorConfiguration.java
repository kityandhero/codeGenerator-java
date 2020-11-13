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
public class JavaControllerGeneratorConfiguration extends PropertyHolder {

	private String targetPackage;
	private String targetProject;

	/**
	 *
	 */
	public JavaControllerGeneratorConfiguration() {
		super();
	}

	public String getTargetPackage() {
		return this.targetPackage;
	}

	public void setTargetPackage(String targetPackage) {
		this.targetPackage = targetPackage;
	}

	public String getTargetProject() {
		return this.targetProject;
	}

	public void setTargetProject(String targetProject) {
		this.targetProject = targetProject;
	}

	public XmlElement toXmlElement() {
		XmlElement answer = new XmlElement("javaControllerGenerator");

		if (this.targetPackage != null) {
			answer.addAttribute(new Attribute("targetPackage", this.targetPackage));
		}

		if (this.targetProject != null) {
			answer.addAttribute(new Attribute("targetProject", this.targetProject));
		}

		this.addPropertyXmlElements(answer);

		return answer;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	public void validate(List errors, String contextId) {
		if (!StringUtility.stringHasValue(this.getTargetProject())) {
			errors.add(Messages.getString("ValidationError.102", contextId));
		}

		if (!StringUtility.stringHasValue(this.getTargetPackage())) {
			errors.add(Messages.getString("ValidationError.112", "ControllerGenerator", contextId));
		}
	}
}
