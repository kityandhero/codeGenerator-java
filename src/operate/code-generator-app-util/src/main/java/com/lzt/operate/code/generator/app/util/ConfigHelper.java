package com.lzt.operate.code.generator.app.util;

import com.lzt.operate.code.generator.common.enums.DatabaseType;
import com.lzt.operate.code.generator.custommessagequeue.generallog.GeneralLogAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.general.ConstantCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URI;
import java.net.URL;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigHelper.class);

	public static String findConnectorLibPath(String dbType) {
		DatabaseType type = DatabaseType.valueOf(dbType);

		List<String> list = getAllJDBCDriverJarPaths();

		for (String item : list) {
			if (StringAssist.contains(item, type.getConnectorJarFile())) {
				return item;
			}
		}

		throw new RuntimeException("找不到驱动文件，请联系开发者");
	}

	/**
	 * getAllJDBCDriverJarPaths
	 *
	 * @return List<String>
	 */
	public static List<String> getAllJDBCDriverJarPaths() {
		URL resourcePath = Thread.currentThread().getContextClassLoader().getResource("lib/");
		List<String> jarFilePathList = new ArrayList<>();

		String path = "";

		if (Optional.ofNullable(resourcePath).isPresent()) {
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

					GeneralLogAssist.quickRecord(path);
					
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
						ConfigHelper.LOGGER.info("jar file: {}", jarFile.getAbsolutePath());

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

		throw new RuntimeException("lib路径查找失败，请联系开发者");
	}

}
