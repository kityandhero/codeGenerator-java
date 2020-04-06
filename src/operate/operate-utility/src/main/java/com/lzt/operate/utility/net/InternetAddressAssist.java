package com.lzt.operate.utility.net;

import java.net.InetAddress;

/**
 * @author luzhitao
 */
public class InternetAddressAssist {

	public static String getLocalHost() {
		String result;

		try {
			//获取本地主机地址对象
			InetAddress ip = InetAddress.getLocalHost();

			result = ip.getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();

			result = "";
		}

		return result;
	}

}
