package com.lzt.operate.secret;

import com.lzt.operate.utility.StringAssist;
import lombok.NonNull;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.util.Base64;

/**
 * DES加密解密工具
 *
 * @author lzt
 */
public class DesAssist {
	private final static int MIN_LENGTH = 8;

	/**
	 * 偏移变量，固定占8位字节
	 */
	private final static String IV_PARAMETER = "87453216";

	/**
	 * 密钥算法
	 */
	private static final String ALGORITHM = "DES";

	/**
	 * 加密/解密算法-工作模式-填充模式
	 */
	private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";

	/**
	 * 默认编码
	 */
	private static final String CHARSET = "utf-8";

	/**
	 * 默认密钥
	 */
	private static final String DEFAULT_KEY = "ui678934uio0kwe435qt5ec45326df53";

	/**
	 * 生成key
	 *
	 * @param key 文本密钥
	 * @return Key 密钥
	 * @throws Exception Exception
	 */
	private static Key generateKey(@NonNull String key) throws Exception {
		DESKeySpec dks = new DESKeySpec(key.getBytes(CHARSET));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		return keyFactory.generateSecret(dks);
	}

	/**
	 * 默认秘钥加密
	 *
	 * @param content 需要加密的内容
	 * @return 默认秘钥加密后的内容
	 */
	public static String encrypt(@NonNull String content) {
		return encrypt(DEFAULT_KEY, content);
	}

	/**
	 * 加密
	 *
	 * @param content 需要加密的内容
	 * @param key     加密秘钥
	 * @return 加密后的内容
	 */
	static String encrypt(@NonNull String key, @NonNull String content) {
		if (StringAssist.isNullOrEmpty(content)) {
			return "";
		}

		if (key.length() < MIN_LENGTH) {
			throw new RuntimeException("加密失败，key不能小于8位");
		}

		try {
			Key secretKey = generateKey(key);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
			byte[] bytes = cipher.doFinal(content.getBytes(CHARSET));

			//JDK1.8及以上可直接使用Base64，JDK1.7及以下可以使用BASE64Encoder
			//Android平台可以使用android.util.Base64

			return new String(Base64.getEncoder().encode(bytes));
		} catch (Exception e) {
			e.printStackTrace();

			return "";
		}

	}

	/**
	 * 默认秘钥解密
	 *
	 * @param content 需要解密的内容
	 * @return 使用默认秘钥解密后的内容
	 */
	public static String decrypt(@NonNull String content) {

		return decrypt(DEFAULT_KEY, content);
	}

	/**
	 * 解密
	 *
	 * @param content 需要解密的内容
	 * @param key     解密秘钥
	 * @return 解密后的内容
	 */
	static String decrypt(@NonNull String key, @NonNull String content) {
		if (StringAssist.isNullOrEmpty(content)) {
			return "";
		}

		if (key.length() < MIN_LENGTH) {
			throw new RuntimeException("加密失败，key不能小于8位");
		}

		try {
			Key secretKey = generateKey(key);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
			cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
			return new String(cipher.doFinal(Base64.getDecoder().decode(content.getBytes(CHARSET))), CHARSET);
		} catch (Exception e) {
			e.printStackTrace();

			return "";
		}
	}

	/**
	 * DES加密文件
	 *
	 * @param srcFile  待加密的文件
	 * @param destFile 加密后存放的文件路径
	 * @return 加密后的文件路径
	 */
	public static String encryptFile(@NonNull String key, String srcFile, String destFile) {

		if (key.length() < MIN_LENGTH) {
			throw new RuntimeException("加密失败，key不能小于8位");
		}

		try {
			IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, generateKey(key), iv);
			InputStream is = new FileInputStream(srcFile);
			OutputStream out = new FileOutputStream(destFile);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			byte[] buffer = new byte[1024];
			int r;
			while ((r = cis.read(buffer)) > 0) {
				out.write(buffer, 0, r);
			}
			cis.close();
			is.close();
			out.close();
			return destFile;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * DES解密文件
	 *
	 * @param srcFile  已加密的文件
	 * @param destFile 解密后存放的文件路径
	 * @return 解密后的文件路径
	 */
	public static String decryptFile(@NonNull String key, String srcFile, String destFile) {
		if (key.length() < MIN_LENGTH) {
			throw new RuntimeException("加密失败，key不能小于8位");
		}
		try {
			File file = new File(destFile);

			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}

			IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, generateKey(key), iv);
			InputStream is = new FileInputStream(srcFile);
			OutputStream out = new FileOutputStream(destFile);
			CipherOutputStream cos = new CipherOutputStream(out, cipher);
			byte[] buffer = new byte[1024];
			int r;

			while ((r = is.read(buffer)) >= 0) {
				cos.write(buffer, 0, r);
			}

			cos.close();
			is.close();
			out.close();
			return destFile;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
