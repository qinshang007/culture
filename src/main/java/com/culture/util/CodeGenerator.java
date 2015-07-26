package com.culture.util;

import java.security.MessageDigest;
import java.util.UUID;

import org.springframework.util.Assert;

public class CodeGenerator {
	/**
	 * ��Ӣ�ַ���
	 */
	private static String ALPHANUMERIC_STR;
	static {
		String numberStr = "0123456789";
		String aphaStr = "abcdefghijklmnopqrstuvwxyz";
		ALPHANUMERIC_STR = numberStr + aphaStr + aphaStr.toUpperCase();
	}

	/**
	 * ����32���ַ����ȵ�UUID���봮�����е���ĸת��Ϊ��д�ĸ�ʽ��
	 */
	public static String createUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "").toUpperCase();
	}

	/**
	 * ��ȡsrcStr��SHA-1���루ʮ�����Ʊ�ʾ��
	 */
	public static String getSHADigest(String srcStr) {
		return getDigest(srcStr, "SHA-1");
	}

	/**
	 * ��ȡsrcStr��MD5���루ʮ�����Ʊ�ʾ��
	 */
	public static String getMD5Digest(String srcStr) {
		return getDigest(srcStr, "MD5");
	}

	/**
	 * ����6λӢ�������,���ִ�Сд
	 */
	public static String getUpdateKey() {
		return getRandomStr(6);
	}

	/**
	 * ����һ�����Ӣ���ַ��������ִ�Сд
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
     * �õ���ϢժҪ
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
	 * ������תʮ�������ַ���
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
