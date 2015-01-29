package com.bresume.core.common.utils.security;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Encrypt {
	private static final Log log = LogFactory.getLog(Encrypt.class);

	public static final String decryptSSOPlain(String plaintext, String key) {
		String temp = decryptSSO(plaintext, key);
		if (temp.substring(0, 32).equals(
				new MD5(temp.substring(32, temp.length())).compute()))
			temp = temp.substring(32, temp.length());
		else {
			return "";
		}
		return temp;
	}

	public static final String encryptSSOPlain(String plaintext, String key) {
		try {
			System.out.println(getEncoding(plaintext));
			plaintext = new String(plaintext.getBytes("UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		plaintext = new MD5(plaintext).compute() + plaintext;
		return encryptSSO(plaintext, key);
	}

	public static final String decryptSSO(String message, String key) {
		try {
			return new String(decrypt(message, odd(md5Hash(key)), key)
					.getBytes("UTF-8"), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("解密失败", e);
		}
		return "";
	}

	public static final String encryptSSO(String message, String key) {
		try {
			return encrypt(message, odd(md5Hash(key)), key);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("加密失败", e);
		}
		return "";
	}

	private static String decrypt(String message, String key, String ivKey)
			throws Exception {
		BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[] bytesrc = base64Decoder.decodeBuffer(message);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(ivKey.getBytes("UTF-8"));
		cipher.init(2, secretKey, iv);
		byte[] retByte = cipher.doFinal(bytesrc);
		return new String(retByte, "UTF-8");
	}

	private static String encrypt(String message, String key, String ivKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(ivKey.getBytes("UTF-8"));
		cipher.init(1, secretKey, iv);
		byte[] encryptbyte = cipher.doFinal(message.getBytes("UTF-8"));
		BASE64Encoder base64Encoder = new BASE64Encoder();
		return base64Encoder.encode(encryptbyte);
	}

	private static String md5Hash(String plaintext) {
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		m.reset();
		m.update(plaintext.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		String hashtext = bigInt.toString(16);
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
		return hashtext.substring(8, 24);
	}

	private static String odd(String md5Key) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < md5Key.length(); i += 2) {
			buff.append(md5Key.substring(i, i + 1));
		}
		return buff.toString();
	}

	private static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception localException3) {
			encode = "ISO-8859-1";
			try {
				if (str.equals(new String(str.getBytes(encode), encode))) {
					String s1 = encode;
					return s1;
				}
			} catch (Exception localException4) {
				encode = "UTF-8";
				try {
					if (str.equals(new String(str.getBytes(encode), encode))) {
						String s2 = encode;
						return s2;
					}
				} catch (Exception localException5) {
					encode = "GBK";
					try {
						if (str.equals(new String(str.getBytes(encode), encode))) {
							String s3 = encode;
							return s3;
						}
					} catch (Exception localException6) {
					}
				}
			}
		}
		return "";
	}
}
