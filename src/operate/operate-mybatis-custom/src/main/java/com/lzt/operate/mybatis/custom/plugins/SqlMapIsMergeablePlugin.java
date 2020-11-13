package com.lzt.operate.mybatis.custom.plugins;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;

public class SqlMapIsMergeablePlugin extends ClientDaoPlugin {

	@Override
	public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
		//重新生成代码,xml内容覆盖
		sqlMap.setMergeable(false);
		return super.sqlMapGenerated(sqlMap, introspectedTable);
	}
}