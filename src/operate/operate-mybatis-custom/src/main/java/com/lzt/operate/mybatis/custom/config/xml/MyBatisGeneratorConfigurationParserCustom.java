package com.lzt.operate.mybatis.custom.config.xml;

import com.lzt.operate.mybatis.custom.config.ContextCustom;
import com.lzt.operate.mybatis.custom.config.JavaServiceGeneratorConfiguration;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.config.xml.MyBatisGeneratorConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.util.StringUtility;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Properties;

public class MyBatisGeneratorConfigurationParserCustom extends MyBatisGeneratorConfigurationParser {

	public MyBatisGeneratorConfigurationParserCustom(Properties extraProperties) {
		super(extraProperties);
	}

	private void parseJavaServiceGenerator(Context context, Node node) {

		ContextCustom contextOverride = (ContextCustom) context; ////替换Context

		JavaServiceGeneratorConfiguration serviceGeneratorConfiguration = new JavaServiceGeneratorConfiguration();

		contextOverride.setJavaServiceGeneratorConfiguration(serviceGeneratorConfiguration);
		Properties attributes = parseAttributes(node);

		String targetPackage = attributes.getProperty("targetPackage");
		String targetProject = attributes.getProperty("targetProject");
		String implementationPackage = attributes.getProperty("implementationPackage");

		serviceGeneratorConfiguration.setTargetPackage(targetPackage);
		serviceGeneratorConfiguration.setTargetProject(targetProject);
		serviceGeneratorConfiguration.setImplementationPackage(implementationPackage);

		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node childNode = nodeList.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE && "property".equals(childNode.getNodeName())) {
				parseProperty(serviceGeneratorConfiguration, childNode);
			}
		}

	}

	@Override
	public Configuration parseConfiguration(Element rootNode) throws XMLParserException {
		Configuration configuration = new Configuration();

		NodeList nodeList = rootNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); ++i) {
			Node childNode = nodeList.item(i);

			if (childNode.getNodeType() != 1) {
				continue;
			}

			if ("properties".equals(childNode.getNodeName())) {
				parseProperties(configuration, childNode);
			} else if ("classPathEntry".equals(childNode.getNodeName())) {
				parseClassPathEntry(configuration, childNode);
			} else if ("context".equals(childNode.getNodeName())) {
				parseContext(configuration, childNode);
			}
		}

		return configuration;
	}

	private void parseContext(Configuration configuration, Node node) {
		Properties attributes = parseAttributes(node);
		String defaultModelType = attributes.getProperty("defaultModelType");
		String targetRuntime = attributes.getProperty("targetRuntime");
		String introspectedColumnImpl = attributes.getProperty("introspectedColumnImpl");
		String id = attributes.getProperty("id");
		ModelType mt = defaultModelType != null ? ModelType.getModelType(defaultModelType) : null;
		Context context = new ContextCustom(mt);
		context.setId(id);
		if (StringUtility.stringHasValue(introspectedColumnImpl)) {
			context.setIntrospectedColumnImpl(introspectedColumnImpl);
		}
		if (StringUtility.stringHasValue(targetRuntime)) {
			context.setTargetRuntime(targetRuntime);
		}
		configuration.addContext(context);
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node childNode = nodeList.item(i);
			if (childNode.getNodeType() != 1) {
				continue;
			}

			if ("property".equals(childNode.getNodeName())) {
				parseProperty(context, childNode);
				continue;
			}
			if ("plugin".equals(childNode.getNodeName())) {
				parsePlugin(context, childNode);
				continue;
			}
			if ("commentGenerator".equals(childNode.getNodeName())) {
				parseCommentGenerator(context, childNode);
				continue;
			}
			if ("jdbcConnection".equals(childNode.getNodeName())) {
				parseJdbcConnection(context, childNode);
				continue;
			}
			if ("connectionFactory".equals(childNode.getNodeName())) {
				parseConnectionFactory(context, childNode);
				continue;
			}
			if ("javaModelGenerator".equals(childNode.getNodeName())) {
				parseJavaModelGenerator(context, childNode);
				continue;
			}
			if ("javaTypeResolver".equals(childNode.getNodeName())) {
				parseJavaTypeResolver(context, childNode);
				continue;
			}
			if ("sqlMapGenerator".equals(childNode.getNodeName())) {
				parseSqlMapGenerator(context, childNode);
				continue;
			}
			if ("javaClientGenerator".equals(childNode.getNodeName())) {
				parseJavaClientGenerator(context, childNode);
				continue;
			}
			if ("javaServiceGenerator".equals(childNode.getNodeName())) {
				parseJavaServiceGenerator(context, childNode);
				continue;
			}
			if ("table".equals(childNode.getNodeName())) {
				parseTable(context, childNode);
			}
		}
	}

	private void parsePlugin(Context context, Node node) {
		PluginConfiguration pluginConfiguration = new PluginConfiguration();
		context.addPluginConfiguration(pluginConfiguration);
		Properties attributes = parseAttributes(node);
		String type = attributes.getProperty("type");
		pluginConfiguration.setConfigurationType(type);
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node childNode = nodeList.item(i);
			if (childNode.getNodeType() == 1 && "property".equals(childNode.getNodeName())) {
				parseProperty(pluginConfiguration, childNode);
			}
		}

	}

	private void parseJavaClientGenerator(Context context, Node node) {
		JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
		context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
		Properties attributes = parseAttributes(node);
		String type = attributes.getProperty("type");
		String targetPackage = attributes.getProperty("targetPackage");
		String targetProject = attributes.getProperty("targetProject");
		String implementationPackage = attributes.getProperty("implementationPackage");
		javaClientGeneratorConfiguration.setConfigurationType(type);
		javaClientGeneratorConfiguration.setTargetPackage(targetPackage);
		javaClientGeneratorConfiguration.setTargetProject(targetProject);
		javaClientGeneratorConfiguration.setImplementationPackage(implementationPackage);
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node childNode = nodeList.item(i);
			if (childNode.getNodeType() == 1 && "property".equals(childNode.getNodeName())) {
				parseProperty(javaClientGeneratorConfiguration, childNode);
			}
		}

	}
}