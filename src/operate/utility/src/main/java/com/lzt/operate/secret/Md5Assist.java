package com.lzt.operate.secret;

import com.lzt.operate.extensions.StringEx;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * Md5相关类，支持校验跨平台字符串
 *
 * @author luzhitao
 */
public class Md5Assist {
	/**
	 * 获取对应的 MD5 字符串
	 *
	 * @param target 源字符串
	 * @return md5 结果
	 */
	public static String toMd5(StringEx target) throws NoSuchAlgorithmException {
		return toMd5(target.toString(), "");
	}

	/**
	 * 获取对应的 MD5 字符串
	 *
	 * @param target 源字符串
	 * @return md5 结果
	 */
	public static String toMd5(StringEx target, String salt) throws NoSuchAlgorithmException {
		return toMd5(target.toString(), salt);
	}

	/**
	 * 获取对应的 MD5 字符串
	 *
	 * @param target 源字符串
	 * @return md5 结果
	 */
	public static String toMd5(String target) throws NoSuchAlgorithmException {
		String v = Optional.of(target).orElse("");

		return toMd5(target, "");
	}

	/**
	 * 获取对应的 MD5 字符串
	 *
	 * @param target 源字符串
	 * @param salt   混淆字符串
	 * @return md5 结果
	 */
	public static String toMd5(String target, String salt) throws NoSuchAlgorithmException {
		String v = Optional.of(target).orElse("");

		v += Optional.of(salt).orElse("");

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(v.getBytes(StandardCharsets.UTF_8));
		byte[] result = md.digest();
		StringBuilder builder = new StringBuilder();

		for (byte b : result) {
			int val = b & 0xff;
			builder.append(Integer.toHexString(val));
		}

		return builder.toString();
	}

	/**
	 * 校验对应的 MD5 字符串
	 *
	 * @param md5    对比的Md5源字符串
	 * @param target 目标字符串
	 * @param salt   混淆字符串
	 * @return boolean 对比结果
	 */
	public static boolean verifyMd5(String md5, String target, String salt) throws NoSuchAlgorithmException {

		String v = Optional.of(target).orElse("");

		String s = Optional.of(salt).orElse("");

		String targetMd5 = toMd5(v, s);

		return targetMd5.equals(md5);
	}

	/**
	 * 校验对应的 MD5 字符串
	 *
	 * @param md5    对比的Md5源字符串
	 * @param target 目标字符串
	 * @return boolean 对比结果
	 */
	public static boolean verifyMd5(String md5, String target) throws NoSuchAlgorithmException {
		return verifyMd5(md5, target, "");
	}
}
