package com.lzt.operate.code.generator.app.util;

import com.alibaba.fastjson.JSON;
import com.lzt.operate.code.generator.common.enums.DatabaseType;
import com.lzt.operate.code.generator.entities.ConnectionConfig;
import com.lzt.operate.code.generator.entities.DataTableGeneratorConfig;
import com.lzt.operate.code.generator.custommessagequeue.generallog.GeneralLogAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.general.ConstantCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * XML based config file help class
 * <p>
 *
 * @author luzhitao
 * @date 6/16/16
 */
public class ConfigHelper {

	private static final Logger _LOG = LoggerFactory.getLogger(ConfigHelper.class);
	private static final String BASE_DIR = "config";
	private static final String CONFIG_FILE = "/sqlite3.db";

	public static void createEmptyFiles() throws Exception {
		File file = new File(ConfigHelper.BASE_DIR);
		if (!file.exists()) {
			boolean mkdir = file.mkdir();
			if (!mkdir) {
				throw new Exception("创建文件夹" + ConfigHelper.BASE_DIR + "失败！");
			}
		}

		File uiConfigFile = new File(ConfigHelper.BASE_DIR + ConfigHelper.CONFIG_FILE);
		if (!uiConfigFile.exists()) {
			ConfigHelper.createEmptyXMLFile(uiConfigFile);
		}
	}

	private static void createEmptyXMLFile(File uiConfigFile) throws IOException {
		try (InputStream fis = Thread.currentThread()
									 .getContextClassLoader()
									 .getResourceAsStream("sqlite3.db"); FileOutputStream fos = new FileOutputStream(uiConfigFile)) {
			byte[] buffer = new byte[1024];
			int byteread;
			assert fis != null;
			while ((byteread = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, byteread);
			}
		}

	}

	public static List<ConnectionConfig> loadConnectionConfig() throws Exception {
		try (Connection conn = ConnectionManager.getConnection(); Statement stat = conn.createStatement(); ResultSet rs = stat
				.executeQuery("SELECT * FROM dbs")) {
			List<ConnectionConfig> configs = new ArrayList<>();
			while (rs.next()) {
				String id = rs.getString("id");
				String value = rs.getString("value");
				ConnectionConfig connectionConfig = JSON.parseObject(value, ConnectionConfig.class);
				connectionConfig.setId(Long.parseLong(id));
				configs.add(connectionConfig);
			}

			return configs;
		}
	}

	public static void saveConnectionConfig(boolean isUpdate, Integer primaryKey, ConnectionConfig dbConfig) throws Exception {
		String configName = dbConfig.getName();
		// ResultSet rs = null;
		try (Connection conn = ConnectionManager.getConnection(); Statement stat = conn.createStatement()) {
			if (!isUpdate) {
				ResultSet rs1 = stat.executeQuery("SELECT * from dbs where name = '" + configName + "'");
				if (rs1.next()) {
					throw new RuntimeException("配置已经存在, 请使用其它名字");
				}
			}
			String jsonStr = JSON.toJSONString(dbConfig);
			String sql;
			if (isUpdate) {
				sql = String.format("UPDATE dbs SET name = '%s', value = '%s' where id = %d", configName, jsonStr, primaryKey);
			} else {
				sql = String.format("INSERT INTO dbs (name, value) values('%s', '%s')", configName, jsonStr);
			}
			stat.executeUpdate(sql);
		}
		// if (rs != null) {
		//     rs.close();
		// }
	}

	public static void deleteConnectionConfig(ConnectionConfig connectionConfig) throws Exception {
		// ResultSet rs = null;
		try (Connection conn = ConnectionManager.getConnection(); Statement stat = conn.createStatement()) {
			String sql = String.format("delete from dbs where id=%d", connectionConfig.getId());
			stat.executeUpdate(sql);
			// } finally {
			//     rs.close();
		}
	}

	public static DataTableGeneratorConfig loadGeneratorConfig(String name) throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			stat = conn.createStatement();
			String sql = String.format("SELECT * FROM generator_config where name='%s'", name);
			ConfigHelper._LOG.info("sql: {}", sql);
			rs = stat.executeQuery(sql);
			DataTableGeneratorConfig generatorConfig = null;
			if (rs.next()) {
				String value = rs.getString("value");
				generatorConfig = JSON.parseObject(value, DataTableGeneratorConfig.class);
			}
			return generatorConfig;
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (stat != null) {
				stat.close();
			}

			if (conn != null) {
				conn.close();
			}
		}
	}

	public static List<DataTableGeneratorConfig> loadGeneratorConfigs() throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			stat = conn.createStatement();
			String sql = "SELECT * FROM generator_config";
			ConfigHelper._LOG.info("sql: {}", sql);
			rs = stat.executeQuery(sql);
			List<DataTableGeneratorConfig> configs = new ArrayList<>();
			while (rs.next()) {
				String value = rs.getString("value");
				configs.add(JSON.parseObject(value, DataTableGeneratorConfig.class));
			}
			return configs;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public static int deleteGeneratorConfig(String name) throws Exception {
		try (Connection conn = ConnectionManager.getConnection(); Statement stat = conn.createStatement()) {
			String sql = String.format("DELETE FROM generator_config where name='%s'", name);
			ConfigHelper._LOG.info("sql: {}", sql);
			return stat.executeUpdate(sql);
		}
	}

	public static String findConnectorLibPath(String dbType) {
		DatabaseType type = DatabaseType.valueOf(dbType);
		URL resource = Thread.currentThread().getContextClassLoader().getResource("logback.xml");
		ConfigHelper._LOG.info("jar resource: {}", resource);

		if (resource != null) {
			String path = "";

			try {
				path = resource.toURI().getRawPath() + "/../lib/" + type.getConnectorJarFile();

				File file = new File(path);

				return URLDecoder.decode(file.getCanonicalPath(), StandardCharsets.UTF_8.displayName());
			} catch (Exception e) {
				GeneralLogAssist.quickRecord(StringAssist.merge("lib路径(findConnectorLibPath)：", path));

				throw new RuntimeException("找不到驱动文件，请联系开发者");
			}
		} else {
			throw new RuntimeException("lib can't find");
		}
	}

	public static List<String> getAllJDBCDriverJarPaths() {
		URL resourcePath = Thread.currentThread().getContextClassLoader().getResource("lib/");
		List<String> jarFilePathList = new ArrayList<>();

		String path = "";

		try {
			path = resourcePath.getPath();

			File file = new File(path);

			File[] jarFiles = file.listFiles();

			Integer fileSize = ConstantCollection.ZERO_INT;

			if (Optional.ofNullable(jarFiles).isPresent()) {
				fileSize = jarFiles.length;
			}

			if (fileSize.equals(ConstantCollection.ZERO_INT)) {
				path = StringAssist.merge(file.getParentFile()
											  .getParentFile()
											  .getParentFile()
											  .getParentFile()
											  .getPath(), "/classes/lib/");

				URL url = new URL(path);

				URI uri = url.toURI();

				String rootPath = uri.getRawPath();

				DatabaseType[] databaseTypes = DatabaseType.values();

				for (DatabaseType databaseType : databaseTypes) {
					String jarPath = StringAssist.merge(rootPath, databaseType.getConnectorJarFile());

					jarFilePathList.add(jarPath);
				}
			} else {
				for (File jarFile : jarFiles) {
					ConfigHelper._LOG.info("jar file: {}", jarFile.getAbsolutePath());

					if (jarFile.isFile() && jarFile.getAbsolutePath().endsWith(".jar")) {
						jarFilePathList.add(jarFile.getAbsolutePath());
					}
				}
			}

			if (fileSize.equals(ConstantCollection.ZERO_INT)) {
				GeneralLogAssist.quickRecord(StringAssist.merge("path：", file.getPath()));
			}

		} catch (Exception e) {
			GeneralLogAssist.quickRecord(StringAssist.merge("lib路径(getAllJDBCDriverJarPaths)：", path));

			throw new RuntimeException("找不到驱动文件，请联系开发者");
		}

		return jarFilePathList;
	}

}
