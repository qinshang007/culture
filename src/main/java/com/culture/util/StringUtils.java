package com.culture.util;

import java.text.DecimalFormat;
import java.util.*;

public class StringUtils {
    /*
     * 字符串列表转化为字符串
     */
	public static String toString(List<String> strList) {
		StringBuffer sb = new StringBuffer();
		if (strList == null || strList.size() == 0)
			return sb.toString();
		for (int i=0;i<strList.size();i++){
			String str = (String)strList.get(i);
			sb.append("'").append(str).append("'").append(",");
		}
		return sb.substring(0, sb.length() - 1);
	}
    /*
     * 转化为整数
     */
	public static int toInt(String srcStr, int defaultValue) {
		if (isEmpty(srcStr)) return defaultValue;
		int result = defaultValue;
		try {
			result = new Integer(srcStr).intValue();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static int toInt(String srcStr) {
		return toInt(srcStr, 0);
	}
    /*
     * 是否是数字
     */
	public static boolean isNumber(String str) {
		if (isEmpty(str)) return false;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!Character.isDigit(ch))
				return false;
		}
		return true;
	}
    /*
     * 转化为double
     */
	public static double toDouble(String srcStr, double defaultValue) {
		if (isEmpty(srcStr))
			return defaultValue;
		double result = defaultValue;
		try {
			result = new Double(srcStr).doubleValue();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static double toDouble(String srcStr) {
		return toDouble(srcStr, 0.0D);
	}
    /*
     * 转化为long
     */
	public static long toLong(String srcStr, long defaultValue) {
		if (isEmpty(srcStr))
			return defaultValue;
		long result = defaultValue;
		try {
			result = new Long(srcStr).longValue();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static long toLong(String srcStr) {
		return toLong(srcStr, 0L);
	}
    /*
     * 转化为boolean
     */
	public static boolean toBoolean(String srcStr, boolean defaultValue) {
		if (isEmpty(srcStr)) return defaultValue;
		boolean result = defaultValue;
		if ("true".equalsIgnoreCase(srcStr.trim()) || "1".equals(srcStr.trim())){
			result = true;
		}else{
			if ("false".equalsIgnoreCase(srcStr.trim()) || "0".equals(srcStr.trim())){
				result = false;
			}else{
				
			}
		}
		return result;
	}

	public static boolean toBoolean(String srcStr) {
		return toBoolean(srcStr, false);
	}
    /*
     * 格式化数�?
     */
	public static String formatNumber(double db, String fmt) {
		String result = "";
		if (null == fmt || "".equals(fmt.trim()))
			return Double.toString(db);
		try {
			DecimalFormat decimalformat = new DecimalFormat(fmt);
			result = decimalformat.format(db);
			decimalformat = null;
		} catch (IllegalArgumentException iaex) {
			result = Double.toString(db);
		}
		return result;
	}

	public static String formatNumber(double db) {
		return formatNumber(db, "0.00");
	}

	public static String formatNumber(String numStr, String fmt) {
		double db = toDouble(numStr, 0.0D);
		return formatNumber(db, fmt);
	}

	public static String formatNumber(String numStr) {
		return formatNumber(numStr, "0.00");
	}

	public static String htmlEncode(String str) {
		String result = null;
		if (null == str || "".equals(str.trim())) {
			result = str;
		} else {
			result = result.replace("&", "&amp;");
			result = result.replace("<", "&lt;");
			result = result.replace(">", "&gt;");
			result = result.replace("\r\n", "\n");
			result = result.replace("\n", "<br>");
			result = result.replace("\t", "    ");
			result = result.replace(" ", "&nbsp;");
			result = result.replace("\"", "&quot;");
		}
		return result;
	}
    /*
     * 是否是空�?
     */
	public static boolean isEmpty(String str) {
		return null == str || "".equals(str.trim());
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static String toVisualHtmlString(String srcStr) {
		if (srcStr == null || srcStr.equals("")){
			return "&nbsp;";
		}else{
			return srcStr;
		}
	}
    
	public static String toVisualString(String srcStr) {
		if (srcStr == null || srcStr.equals(""))
			return "";
		else
			return srcStr;
	}
	
	public static List<String> split(String str,String split){
		ArrayList<String> list=new ArrayList<String>();
		String[] strs=str.split(split);
		for (String val:strs){
			list.add(val);
		}
		return list;
	}
}
