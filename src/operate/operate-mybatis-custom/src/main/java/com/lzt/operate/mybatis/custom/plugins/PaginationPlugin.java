package com.lzt.operate.mybatis.custom.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * PaginationPlugin
 *
 * @author luzhitao
 */
public class PaginationPlugin extends SqlMapIsMergeablePlugin {
	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		FullyQualifiedJavaType baseExampleType = FullyQualifiedJavaTypeProxyFactory.getBaseExampleInstance();
		topLevelClass.setSuperClass(baseExampleType);

		topLevelClass.addImportedType(baseExampleType);
		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element,
																  IntrospectedTable introspectedTable) {

		XmlElement isNotNullElement1 = new XmlElement("if");
		isNotNullElement1.addAttribute(new Attribute("test", "groupByClause != null"));
		isNotNullElement1.addElement(new TextElement("group by ${groupByClause}"));
		element.addElement(5, isNotNullElement1);
		XmlElement isNotNullElement = new XmlElement("if");
		isNotNullElement.addAttribute(new Attribute("test", "pageInfo != null"));
		isNotNullElement.addElement(new TextElement("limit #{pageInfo.pageBegin} , #{pageInfo.pageSize}"));
		element.addElement(isNotNullElement);

		return super.sqlMapUpdateByExampleWithBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
																	 IntrospectedTable introspectedTable) {

		XmlElement isNotNullElement1 = new XmlElement("if");
		isNotNullElement1.addAttribute(new Attribute("test", "groupByClause != null"));
		isNotNullElement1.addElement(new TextElement("group by ${groupByClause}"));
		element.addElement(5, isNotNullElement1);

		XmlElement isNotNullElement = new XmlElement("if");
		isNotNullElement.addAttribute(new Attribute("test", "pageInfo != null"));
		isNotNullElement.addElement(new TextElement("limit #{pageInfo.pageBegin} , #{pageInfo.pageSize}"));
		element.addElement(isNotNullElement);

		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		XmlElement answer = new XmlElement("select");

		String fqjt = introspectedTable.getExampleType();

		answer.addAttribute(new Attribute("id", introspectedTable.getCountByExampleStatementId()));
		answer.addAttribute(new Attribute("parameterType", fqjt));
		answer.addAttribute(new Attribute("resultType", "java.lang.Integer"));

		context.getCommentGenerator().addComment(answer);

		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) from ");
		sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());

		XmlElement ifElement = new XmlElement("if");
		ifElement.addAttribute(new Attribute("test", "_parameter != null"));
		XmlElement includeElement = new XmlElement("include");
		includeElement.addAttribute(new Attribute("refid", introspectedTable.getExampleWhereClauseId()));
		ifElement.addElement(includeElement);

		element.getElements().clear();
		element.getElements().add(new TextElement(sb.toString()));
		element.getElements().add(ifElement);
		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}
}