package com.lzt.operate.secret;

import com.lzt.operate.extensions.StringEx;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * DES加密解密工具
 *
 * @author lzt
 */
public class DesAssist {
    private static final String DEFAULT_KEY = "ui678934uio0kw";

    /**
     * 默认秘钥加密
     *
     * @param content 需要加密的内容
     * @return 默认秘钥加密后的内容
     */
    public static String encryptWithCBC(String content) {
        return encryptWithCBC(content, DEFAULT_KEY);
    }

    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @param key     加密秘钥
     * @return 加密后的内容
     */
    static String encryptWithCBC(String content, String key) {
        if (StringEx.isNullOrEmpty(content)) {
            return "";
        }

        byte[] resultEncrypt = encryptWithCBC(content.getBytes(), key.getBytes());

        if (resultEncrypt == null) {
            return "";
        }

        return byteToHexString(resultEncrypt);
    }

    /**
     * 加密
     *
     * @param contentBytes 需要加密的内容
     * @param keyBytes     加密秘钥
     * @return 加密后的内容
     */
    private static byte[] encryptWithCBC(byte[] contentBytes, byte[] keyBytes) {

        try {
            DESKeySpec keySpec = new DESKeySpec(keyBytes);
            //指定使什么样的算法
            String algorithm = "DES";
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
            SecretKey key = keyFactory.generateSecret(keySpec);

            //用什么样的转型方式
            String transformation = "DES/CBC/PKCS5Padding";
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(keySpec.getKey()));

            return cipher.doFinal(contentBytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 默认秘钥解密
     *
     * @param content 需要解密的内容
     * @return 使用默认秘钥解密后的内容
     */
    public static String decryptWithCBC(String content) {

        return decryptWithCBC(content, DEFAULT_KEY);
    }

    /**
     * 解密
     *
     * @param content 需要解密的内容
     * @param key     解密秘钥
     * @return 解密后的内容
     */
    private static String decryptWithCBC(String content, String key) {
        if (StringEx.isNullOrEmpty(content)) {
            return "";
        }

        byte[] resultEncrypt = decryptWithCBC(content.getBytes(), key.getBytes());

        if (resultEncrypt == null) {
            return "";
        }

        return byteToHexString(resultEncrypt);
    }

    /**
     * 解密
     *
     * @param contentBytes 需要解密的内容
     * @param keyBytes     解密秘钥
     * @return 解密后的内容
     */
    private static byte[] decryptWithCBC(byte[] contentBytes, byte[] keyBytes) {

        try {
            DESKeySpec keySpec = new DESKeySpec(keyBytes);

            String algorithm = "DES";
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
            SecretKey key = keyFactory.generateSecret(keySpec);

            String transformation = "DES/CBC/PKCS5Padding";
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(keyBytes));

            return cipher.doFinal(contentBytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 二进制转16进制
     *
     * @param bytes 需要转换的二进制
     * @return 转换后的文本
     */
    private static String byteToHexString(byte[] bytes) {

        StringBuilder stringBuilder = new StringBuilder();
        String sTemp;

        for (byte aByte : bytes) {

            sTemp = Integer.toHexString(0xFF & aByte);
            if (sTemp.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(sTemp.toUpperCase());

        }

        return stringBuilder.toString();
    }
}
