/*
 *  Copyright 2008 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.lzt.operate.mybatis.custom.plugins;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;
import java.util.Set;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

/**
 * 此插件使用数据库表中列的注释来生成Java Model中属性的注释
 *
 * @author Owen Zou
 */
public class DbRemarksCommentGenerator implements CommentGenerator {

	private final Properties properties;
	private boolean columnRemarks;
	private boolean isAnnotations;

	public DbRemarksCommentGenerator() {
		super();
		properties = new Properties();
	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		// add no file level comments by default
		if (isAnnotations) {
			compilationUnit.addImportedType(new FullyQualifiedJavaType("javax.persistence.Table"));
			compilationUnit.addImportedType(new FullyQualifiedJavaType("javax.persistence.Id"));
			compilationUnit.addImportedType(new FullyQualifiedJavaType("javax.persistence.Column"));
			compilationUnit.addImportedType(new FullyQualifiedJavaType("javax.persistence.GeneratedValue"));
			compilationUnit.addImportedType(new FullyQualifiedJavaType("org.hibernate.validator.constraints.NotEmpty"));
		}
	}

	/**
	 * Adds a suitable comment to warn users that the element was generated, and
	 * when it was generated.
	 */
	@Override
	public void addComment(XmlElement xmlElement) {
	}

	@Override
	public void addRootComment(XmlElement rootElement) {
		// add no document level comments by default
	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {

	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {

	}

	@Override
	public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

	}

	@Override
	public void addConfigurationProperties(Properties properties) {
		this.properties.putAll(properties);
		columnRemarks = isTrue(properties
				.getProperty("columnRemarks"));
		isAnnotations = isTrue(properties
				.getProperty("annotations"));
	}

	@Override
	public void addClassComment(InnerClass innerClass,
								IntrospectedTable introspectedTable) {
	}

	@Override
	public void addModelClassComment(TopLevelClass topLevelClass,
									 IntrospectedTable introspectedTable) {
		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine(" * " + introspectedTable.getFullyQualifiedTable().getIntrospectedTableName());
		topLevelClass.addJavaDocLine(" * @author ");
		topLevelClass.addJavaDocLine(" */");
		if (isAnnotations) {
			topLevelClass.addAnnotation("@Table(name=\"" + introspectedTable.getFullyQualifiedTableNameAtRuntime() + "\")");
		}
	}

	@Override
	public void addEnumComment(InnerEnum innerEnum,
							   IntrospectedTable introspectedTable) {
	}

	@Override
	public void addFieldComment(Field field,
								IntrospectedTable introspectedTable,
								IntrospectedColumn introspectedColumn) {
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			field.addJavaDocLine("/**");
			String sb = " * " + introspectedColumn.getRemarks();
			field.addJavaDocLine(sb);
			field.addJavaDocLine(" */");
		}

		if (isAnnotations) {
			boolean isId = false;
			for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
				if (introspectedColumn == column) {
					isId = true;
					field.addAnnotation("@Id");
					field.addAnnotation("@GeneratedValue");
					break;
				}
			}
			if (!introspectedColumn.isNullable() && !isId) {
				field.addAnnotation("@NotEmpty");
			}
			if (introspectedColumn.isIdentity()) {
				final String jdbc = "JDBC";

				if (jdbc.equals(introspectedTable.getTableConfiguration()
												 .getGeneratedKey()
												 .getRuntimeSqlStatement())) {
					field.addAnnotation("@GeneratedValue(generator = \"JDBC\")");
				} else {
					field.addAnnotation("@GeneratedValue(strategy = GenerationType.IDENTITY)");
				}
			} else if (introspectedColumn.isSequenceColumn()) {
				field.addAnnotation("@SequenceGenerator(name=\"\",sequenceName=\"" + introspectedTable.getTableConfiguration()
																									  .getGeneratedKey()
																									  .getRuntimeSqlStatement() + "\")");
			}
		}
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
	}

	@Override
	public void addGeneralMethodComment(Method method,
										IntrospectedTable introspectedTable) {
	}

	@Override
	public void addGetterComment(Method method,
								 IntrospectedTable introspectedTable,
								 IntrospectedColumn introspectedColumn) {
	}

	@Override
	public void addSetterComment(Method method,
								 IntrospectedTable introspectedTable,
								 IntrospectedColumn introspectedColumn) {
	}

	@Override
	public void addClassComment(InnerClass innerClass,
								IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		innerClass.addJavaDocLine("/**"); //$NON-NLS-1$
		innerClass.addJavaDocLine(" */"); //$NON-NLS-1$
	}
}
