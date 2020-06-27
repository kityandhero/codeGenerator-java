package com.lzt.operate.mybatis.custom.plugins.controllergenerate;

import com.lzt.operate.mybatis.custom.config.ContextCustom;
import com.lzt.operate.mybatis.custom.config.JavaControllerGeneratorConfiguration;
import com.lzt.operate.mybatis.custom.plugins.FullyQualifiedJavaTypeProxyFactory;
import com.lzt.operate.mybatis.custom.plugins.PaginationPlugin;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ControllerLayerPlugin
 *
 * @author luzhitao
 */
public class ControllerLayerPlugin extends PaginationPlugin {
	/**
	 * 生成额外java文件
	 */
	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {

		ContextCustom context = (ContextCustom) introspectedTable.getContext();

		JavaControllerGeneratorConfiguration javaControllerGeneratorConfiguration;

		if ((javaControllerGeneratorConfiguration = context.getJavaControllerGeneratorConfiguration()) == null) {
			return null;
		}

		String targetPackage = javaControllerGeneratorConfiguration.getTargetPackage();
		String targetProject = javaControllerGeneratorConfiguration.getTargetProject();

		CompilationUnit addControllerClazz = this.addControllerClazz(introspectedTable, targetPackage);

		GeneratedJavaFile gjfControllerClazz = new GeneratedJavaFile(addControllerClazz, targetProject,
				this.context.getProperty("javaFileEncoding"), this.context.getJavaFormatter());

		List<GeneratedJavaFile> list = new ArrayList<>();

		list.add(gjfControllerClazz);

		return list;
	}

	protected CompilationUnit addControllerClazz(IntrospectedTable introspectedTable, String targetPackage) {

		String domainObjectName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();

		StringBuilder builder = new StringBuilder();

		FullyQualifiedJavaType superClazzType = new FullyQualifiedJavaType(
				builder.append("BaseCustomController")
					   .toString()
		);

		TopLevelClass controllerClazz = new TopLevelClass(
				builder.delete(0, builder.length())
					   .append(targetPackage)
					   .append(".")
					   .append(domainObjectName)
					   .append("Controller")
					   .toString()
		);

		controllerClazz.setSuperClass(superClazzType);
		controllerClazz.setVisibility(JavaVisibility.PUBLIC);

		FullyQualifiedJavaType baseControllerClazz = FullyQualifiedJavaTypeProxyFactory.getBaseControllerClazz();
		controllerClazz
				.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
		controllerClazz.addImportedType(baseControllerClazz);

		FullyQualifiedJavaType logType = new FullyQualifiedJavaType("org.slf4j.Logger");
		FullyQualifiedJavaType logFactoryType = new FullyQualifiedJavaType("org.slf4j.LoggerFactory");
		Field logField = new Field();
		logField.setVisibility(JavaVisibility.PRIVATE);
		logField.setStatic(true);
		logField.setFinal(true);
		logField.setType(logType);
		logField.setName("logger");
		logField.setInitializationString(
				builder.delete(0, builder.length())
					   .append("LoggerFactory.getLogger(")
					   .append(domainObjectName)
					   .append("Controller.class)")
					   .toString()
		);

		logField.addAnnotation("");
		logField.addAnnotation("@SuppressWarnings(\"unused\")");
		controllerClazz.addField(logField);
		controllerClazz.addImportedType(logType);
		controllerClazz.addImportedType(logFactoryType);

		String mapperName = builder.delete(0, builder.length())
								   .append(Character.toLowerCase(domainObjectName.charAt(0)))
								   .append(domainObjectName.substring(1))
								   .append("Mapper")
								   .toString();

		controllerClazz.addFileCommentLine("/*** copyright (c) 2020 luzhitao  ***/");

		controllerClazz
				.addImportedType(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));

		this.additionalControllerMethods(introspectedTable, controllerClazz, mapperName);

		return controllerClazz;
	}

	protected void additionalControllerMethods(IntrospectedTable introspectedTable, TopLevelClass clazz,
											   String mapperName) {

		if (this.notHasBLOBColumns(introspectedTable)) {
			return;
		}

		introspectedTable.getGeneratedJavaFiles()
						 .stream()
						 .filter(file -> file.getCompilationUnit().isJavaInterface()
								 && file.getCompilationUnit().getType().getShortName().endsWith("Mapper"))
						 .map(GeneratedJavaFile::getCompilationUnit)
						 .forEach(
								 compilationUnit -> ((Interface) compilationUnit).getMethods().forEach(m -> {

									 Method serviceImplMethod = this.additionalControllerLayerMethod(clazz, m);
									 serviceImplMethod.addAnnotation("@Override");
									 serviceImplMethod.addBodyLine(this.generateBodyForControllerMethod(mapperName, m));

									 clazz.addMethod(serviceImplMethod);
								 }));
	}

	private boolean notHasBLOBColumns(IntrospectedTable introspectedTable) {
		return !introspectedTable.hasBLOBColumns();
	}

	private Method additionalControllerLayerMethod(CompilationUnit compilation, Method m) {

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName(m.getName());

		List<Parameter> parameters = m.getParameters();

		method.getParameters()
			  .addAll(parameters.stream().peek(param -> param.getAnnotations().clear()).collect(Collectors.toList()));
		method.setReturnType(m.getReturnType());
		compilation.addImportedType(
				new FullyQualifiedJavaType(m.getReturnType().getFullyQualifiedNameWithoutTypeParameters()));

		return method;
	}

	private String generateBodyForControllerMethod(String mapperName, Method m) {
		StringBuilder sbf = new StringBuilder("return ");
		sbf.append(mapperName).append(".").append(m.getName()).append("(");

		boolean singleParam = true;
		for (Parameter parameter : m.getParameters()) {

			if (singleParam) {
				singleParam = false;
			} else {
				sbf.append(", ");
			}
			sbf.append(parameter.getName());

		}

		sbf.append(");");
		return sbf.toString();
	}
}
