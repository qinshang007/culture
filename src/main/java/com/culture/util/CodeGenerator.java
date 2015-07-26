package com.culture.util;

import java.security.MessageDigest;
import java.util.UUID;

import org.springframework.util.Assert;

public class CodeGenerator {
	/**
	 * 数英字符串
	 */
	private static String ALPHANUMERIC_STR;
	static {
		String numberStr = "0123456789";
		String aphaStr = "abcdefghijklmnopqrstuvwxyz";
		ALPHANUMERIC_STR = numberStr + aphaStr + aphaStr.toUpperCase();
	}

	/**
	 * 生成32个字符长度的UUID编码串，所有的字母转换为大写的格式。
	 */
	public static String createUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "").toUpperCase();
	}

	/**
	 * 获取srcStr的SHA-1编码（十六进制表示）
	 */
	public static String getSHADigest(String srcStr) {
		return getDigest(srcStr, "SHA-1");
	}

	/**
	 * 获取srcStr的MD5编码（十六进制表示）
	 */
	public static String getMD5Digest(String srcStr) {
		return getDigest(srcStr, "MD5");
	}

	/**
	 * 产生6位英数随机数,区分大小写
	 */
	public static String getUpdateKey() {
		return getRandomStr(6);
	}

	/**
	 * 产生一个随机英数字符串，区分大小写
	 */
	public static String getRandomStr(int length) {
		int srcStrLen = ALPHANUMERIC_STR.length();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int maxnum = (int) (Math.random() * 1000);
			int result = maxnum % srcStrLen;
			char temp = ALPHANUMERIC_STR.charAt(result);
			sb.append(temp);
		}
		return sb.toString();
	}
    /*
     * 得到消息摘要
     */
	private static String getDigest(String srcStr, String alg) {
		Assert.notNull(srcStr);
		Assert.notNull(alg);
		try {
			MessageDigest alga = MessageDigest.getInstance(alg);
			alga.update(srcStr.getBytes());
			byte[] digesta = alga.digest();
			return byte2hex(digesta);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 二进制转十六进制字符串
	 */
	private static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs.append("0");
			}
			hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

}
