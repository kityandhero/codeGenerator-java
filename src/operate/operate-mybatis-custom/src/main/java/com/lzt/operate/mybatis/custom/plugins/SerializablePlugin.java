package com.lzt.operate.mybatis.custom.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.Properties;

public class SerializablePlugin extends PluginAdapter {
	private FullyQualifiedJavaType serializable;
	private FullyQualifiedJavaType gwtSerializable;
	private boolean addGWTInterface;
	private boolean suppressJavaInterface;

	public SerializablePlugin() {
		this.serializable = new FullyQualifiedJavaType("java.io.Serializable");
		this.gwtSerializable = new FullyQualifiedJavaType("com.google.gwt.user.client.rpc.IsSerializable");
	}

	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);
		this.addGWTInterface = Boolean.valueOf(properties.getProperty("addGWTInterface")).booleanValue();
		this.suppressJavaInterface = Boolean.valueOf(properties.getProperty("suppressJavaInterface")).booleanValue();
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		makeSerializable(topLevelClass, introspectedTable);
		return true;
	}

	@Override
	public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		makeSerializable(topLevelClass, introspectedTable);
		return true;
	}

	@Override
	public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass,
													  IntrospectedTable introspectedTable) {
		makeSerializable(topLevelClass, introspectedTable);
		return true;
	}

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		makeSerializable(topLevelClass, introspectedTable);
		return true;
	}

	protected void makeSerializable(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		if (this.addGWTInterface) {
			topLevelClass.addImportedType(this.gwtSerializable);
			topLevelClass.addSuperInterface(this.gwtSerializable);
		}

		if (!(this.suppressJavaInterface)) {
			topLevelClass.addImportedType(this.serializable);
			topLevelClass.addSuperInterface(this.serializable);
			topLevelClass.addAnnotation("@SuppressWarnings(\"serial\")");

		}
	}

	/**
	 * This plugin is always valid - no properties are required
	 */
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}
}