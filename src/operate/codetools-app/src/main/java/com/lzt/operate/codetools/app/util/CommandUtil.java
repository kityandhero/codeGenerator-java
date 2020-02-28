package com.lzt.operate.codetools.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luzhitao
 */
public class CommandUtil {

	/**
	 * LOG日志
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandUtil.class);

	/**
	 * 打开浏览器
	 *
	 * @param uri uri地址
	 */
	public static void browse(URI uri) {
		if (openSystemSpecific(uri.toString())) {
			return;
		}

		browseDesktop(uri);
	}

	/**
	 * 打开文件
	 *
	 * @param file 文件
	 * @return 是否成功
	 */
	public static boolean open(File file) {
		if (openSystemSpecific(file.getPath())) {
			return true;
		}

		return openDesktop(file);
	}

	/**
	 * 编辑指令
	 *
	 * @param file 文件
	 * @return 是否成功
	 */
	public static boolean edit(File file) {
		// you can try something like
		// runCommand("gimp", "%s", file.getPath())
		// based on user preferences.

		if (openSystemSpecific(file.getPath())) {
			return true;
		}

		return editDesktop(file);
	}

	/**
	 * 打开系统特殊的指令
	 *
	 * @param what 指令
	 * @return 是否成功
	 */
	private static boolean openSystemSpecific(String what) {
		EnumOS os = getOs();

		if (os.isLinux()) {
			if (runCommand("kde-open", "%s", what)) {
				return true;
			}
			if (runCommand("gnome-open", "%s", what)) {
				return true;
			}
			if (runCommand("xdg-open", "%s", what)) {
				return true;
			}
		}

		if (os.isMac()) {
			if (runCommand("open", "%s", what)) {
				return true;
			}
		}

		if (os.isWindows()) {
			return runCommand("explorer", "%s", what);
		}

		return false;
	}

	/**
	 * @param uri URI
	 */
	private static void browseDesktop(URI uri) {
		LOGGER.info("Trying to use Desktop.getDesktop().browse() with " + uri.toString());
		try {
			if (!Desktop.isDesktopSupported()) {
				LOGGER.error("Platform is not supported.");
				return;
			}

			if (!Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				LOGGER.error("BROWSE is not supported.");
				return;
			}

			Desktop.getDesktop().browse(uri);

		} catch (Throwable t) {
			LOGGER.error("Error using desktop browse.", t);
		}
	}

	/**
	 * @param file File
	 * @return boolean
	 */
	private static boolean openDesktop(File file) {

		LOGGER.info("Trying to use Desktop.getDesktop().open() with " + file.toString());
		try {
			if (!Desktop.isDesktopSupported()) {
				LOGGER.error("Platform is not supported.");
				return false;
			}

			if (!Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
				LOGGER.error("OPEN is not supported.");
				return false;
			}

			Desktop.getDesktop().open(file);

			return true;
		} catch (Throwable t) {
			LOGGER.error("Error using desktop open.", t);
			return false;
		}
	}

	/**
	 * 调用jdk工具类
	 *
	 * @param file File
	 * @return boolean
	 */
	private static boolean editDesktop(File file) {
		LOGGER.info("Trying to use Desktop.getDesktop().edit() with " + file);
		try {
			if (!Desktop.isDesktopSupported()) {
				LOGGER.error("Platform is not supported.");
				return false;
			}

			if (!Desktop.getDesktop().isSupported(Desktop.Action.EDIT)) {
				LOGGER.error("EDIT is not supported.");
				return false;
			}
			Desktop.getDesktop().edit(file);

			return true;
		} catch (Throwable t) {
			LOGGER.error("Error using desktop edit.", t);
			return false;
		}
	}

	/**
	 * 运行命令
	 *
	 * @param command 指令
	 * @param args    参数
	 * @param file    文件
	 * @return 是否
	 */
	private static boolean runCommand(String command, String args, String file) {
		LOGGER.info("Trying to exec:\n   cmd = {}\n   args = {}\n   {} = ", command, args, file);
		String[] parts = prepareCommand(command, args, file);

		try {
			Process p = Runtime.getRuntime().exec(parts);
			if (p == null) {
				return false;
			}

			try {
				int value = p.exitValue();

				if (value == 0) {
					LOGGER.error("Process ended immediately.");
					return false;
				}
				LOGGER.error("Process crashed.");
				return false;
			} catch (IllegalThreadStateException itse) {
				LOGGER.error("Process is running.");
				return true;
			}
		} catch (IOException e) {
			LOGGER.error("Error running command.", e);
			return false;
		}
	}

	/**
	 * 准备命令
	 *
	 * @param command 指令
	 * @param args    参数
	 * @param file    文件
	 * @return string array
	 */
	private static String[] prepareCommand(String command, String args, String file) {
		List<String> parts = new ArrayList<>();
		parts.add(command);

		if (args != null) {
			for (String s : args.split(" ")) {
				// put in the filename thing
				s = String.format(s, file);

				parts.add(s.trim());
			}
		}

		return parts.toArray(new String[0]);
	}

	/**
	 * 操作系统
	 *
	 * @author 许畅
	 * @version 2018年9月14日 许畅 新建
	 * @since JDK1.7
	 */
	public enum EnumOS {
		/**
		 * linux系统
		 */
		linux,

		/**
		 * macos系统
		 */
		macos,

		/**
		 * solaris系统
		 */
		solaris,

		/**
		 * unknown系统
		 */
		unknown,

		/**
		 * windows系统
		 */
		windows;

		/**
		 * @return 是否linux
		 */
		public boolean isLinux() {
			return this == linux || this == solaris;
		}

		/**
		 * @return 是否mac
		 */
		public boolean isMac() {
			return this == macos;
		}

		/**
		 * @return 是否windows
		 */
		public boolean isWindows() {
			return this == windows;
		}
	}

	/**
	 * @return EnumOS
	 */
	public static EnumOS getOs() {
		String s = System.getProperty("os.name").toLowerCase();

		if (s.contains("win")) {
			return EnumOS.windows;
		}

		if (s.contains("mac")) {
			return EnumOS.macos;
		}

		if (s.contains("solaris")) {
			return EnumOS.solaris;
		}

		if (s.contains("sunos")) {
			return EnumOS.solaris;
		}

		if (s.contains("linux")) {
			return EnumOS.linux;
		}

		if (s.contains("unix")) {
			return EnumOS.linux;
		}
		return EnumOS.unknown;
	}

}
