package com.lzt.operate.mybatis.custom.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Date;
import java.util.Optional;
import java.util.Properties;

/**
 * @author luzhitao
 */
public class CommentGeneratorCustom extends DefaultCommentGenerator {

	private final Properties properties;
	private boolean suppressDate;
	private boolean suppressAllComments;

	public CommentGeneratorCustom() {
		properties = new Properties();
		suppressDate = false;
		suppressAllComments = false;
	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		compilationUnit.addFileCommentLine("/*** copyright (c) 2020 codeGenerator ***/");
	}

	/**
	 * XML file Comment
	 */
	@Override
	public void addComment(XmlElement xmlElement) {
		if (suppressAllComments) {
			return;
		}

	}

	@Override
	public void addRootComment(XmlElement rootElement) {
	}

	@Override
	public void addConfigurationProperties(Properties properties) {
		this.properties.putAll(properties);

		suppressDate = StringUtility.isTrue(properties.getProperty("suppressDate"));

		suppressAllComments = StringUtility.isTrue(properties.getProperty("suppressAllComments"));
	}

	@Override
	protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
		StringBuilder sb = new StringBuilder();

		sb.append(" * ");
		sb.append("@date");

		String s = getDateString();

		if (s != null) {
			sb.append(' ');
			sb.append(s);
		}

		javaElement.addJavaDocLine(sb.toString());
	}

	@Override
	protected String getDateString() {
		if (suppressDate) {
			return null;
		}
		return new Date().toString();
	}

	/**
	 * Comment of Example inner class(GeneratedCriteria ,Criterion)
	 */
	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}

		innerClass.addJavaDocLine("/**");
		innerClass.addJavaDocLine(" * " + introspectedTable.getFullyQualifiedTable()
														   .getDomainObjectName() + "(DomainObjectName)<p/>");
		innerClass.addJavaDocLine(" * " + introspectedTable.getFullyQualifiedTable().toString());
		addJavadocTag(innerClass, false);
		innerClass.addJavaDocLine(" */");
	}

	@Override
	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder stringBuilder = new StringBuilder();

		innerEnum.addJavaDocLine("/**");
		innerEnum.addJavaDocLine(" * " + introspectedTable.getFullyQualifiedTable().getAlias() + "<p/>");
		innerEnum.addJavaDocLine(" * " + introspectedTable.getFullyQualifiedTable());
		innerEnum.addJavaDocLine(stringBuilder.toString());

		addJavadocTag(innerEnum, false);

		innerEnum.addJavaDocLine(" */");
	}

	/**
	 * entity filed Comment
	 */
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
								IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}

		String remark = introspectedColumn.getRemarks();

		if (Optional.ofNullable(remark)
					.isPresent()) {
			field.addJavaDocLine("/**");
			field.addJavaDocLine(" * " + remark);
			field.addJavaDocLine(" */");
		}
	}

	/**
	 * Comment of Example filed
	 */
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}
		field.addJavaDocLine("/**");
		addJavadocTag(field, false);
		field.addJavaDocLine(" */");
	}

	/**
	 * Comment of Example method
	 */
	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}
	}

	/**
	 * entity Getter Comment
	 */
	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
								 IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}

		String remark = introspectedColumn.getRemarks();

		method.addJavaDocLine("/**");

		if (Optional.ofNullable(remark)
					.isPresent()) {
			method.addJavaDocLine(" * @return " + introspectedTable.getFullyQualifiedTable()
																   .getAlias() + " : " + introspectedColumn.getRemarks());
		} else {
			method.addJavaDocLine(" * @return " + introspectedTable.getFullyQualifiedTable()
																   .getAlias() + " : " + introspectedColumn.getFullyQualifiedJavaType()
																										   .getShortName());
		}

		method.addJavaDocLine(" */");
	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
								 IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		method.addJavaDocLine("/**");

		Parameter parameter = method.getParameters().get(0);
		String remark = introspectedColumn.getRemarks();

		sb.append(" * @param ");
		sb.append(parameter.getName());
		sb.append(" : ");
		if (Optional.ofNullable(remark)
					.isPresent()) {
			sb.append(remark);
		} else {
			sb.append(parameter.getName());
		}

		method.addJavaDocLine(sb.toString());
		method.addJavaDocLine(" */");
	}

	/**
	 * Comment of Example inner class(Criteria)
	 */
	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		if (suppressAllComments) {
			return;
		}

		innerClass.addJavaDocLine("/**");
		innerClass.addJavaDocLine(" * " + introspectedTable.getFullyQualifiedTable().getAlias() + "(Alias)<p/>");
		innerClass.addJavaDocLine(" * " + introspectedTable.getFullyQualifiedTable().toString());
		addJavadocTag(innerClass, markAsDoNotDelete);

		innerClass.addJavaDocLine(" */");
	}

}
