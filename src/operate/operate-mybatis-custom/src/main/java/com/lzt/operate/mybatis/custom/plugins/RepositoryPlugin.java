package com.lzt.operate.mybatis.custom.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * Project: mybatis-generator-gui
 *
 * @author slankka on 2017/12/13.
 */
public class RepositoryPlugin extends PluginAdapter {

	private FullyQualifiedJavaType annotationRepository;
	private String annotation = "@Repository";

	public RepositoryPlugin() {
		//$NON-NLS-1$
		annotationRepository = new FullyQualifiedJavaType("org.springframework.stereotype.Repository");
	}

	@Override
	public boolean validate(List<String> list) {
		return true;
	}

	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		interfaze.addImportedType(annotationRepository);
		interfaze.addAnnotation(annotation);
		return true;
	}
}
