package com.lzt.operate.mybatis.custom.plugins;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.JDBCConnectionFactory;
import org.mybatis.generator.internal.util.StringUtility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

/**
 * @author luzhitao
 */
public class EntityCommentPlugin extends PluginAdapter {

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		addModelClassComment(topLevelClass, introspectedTable);
		return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
	}

	@Override
	public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass,
													  IntrospectedTable introspectedTable) {

		addModelClassComment(topLevelClass, introspectedTable);
		return super.modelRecordWithBLOBsClassGenerated(topLevelClass, introspectedTable);
	}

	protected void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		String tableComment = getTableComment(table);

		topLevelClass.addJavaDocLine("/**");
		if (StringUtility.stringHasValue(tableComment)) {
			topLevelClass.addJavaDocLine(" * " + tableComment + "<p/>");
		}
		topLevelClass.addJavaDocLine(" * " + table.toString() + "<p/>");
		topLevelClass.addJavaDocLine(" * @date " + new Date().toString());
		topLevelClass.addJavaDocLine(" *");
		topLevelClass.addJavaDocLine(" */");
	}

	/**
	 * @param table table
	 * @author Marvis
	 * @date Jul 13, 2017 4:39:52 PM
	 */
	private String getTableComment(FullyQualifiedTable table) {
		String tableComment = "";
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			JDBCConnectionFactory jdbc = new JDBCConnectionFactory(context.getJdbcConnectionConfiguration());
			connection = jdbc.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery("SHOW CREATE TABLE " + table.getIntrospectedTableName());

			if (rs != null && rs.next()) {
				String createDDL = rs.getString(2);
				int index = createDDL.indexOf("COMMENT='");

				if (index < 0) {
					tableComment = "";
				} else {
					tableComment = createDDL.substring(index + 9);
					tableComment = tableComment.substring(0, tableComment.length() - 1);
				}
			}

		} catch (SQLException e) {

		} finally {
			closeConnection(connection, statement, rs);
		}
		return tableComment;
	}

	/**
	 * @param connection connection
	 * @param statement  statement
	 * @param rs         rs
	 * @author Marvis
	 * @date Jul 13, 2017 4:45:26 PM
	 */
	private void closeConnection(Connection connection, Statement statement, ResultSet rs) {
		try {
			if (null != rs) {
				rs.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			} finally {

				try {
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
