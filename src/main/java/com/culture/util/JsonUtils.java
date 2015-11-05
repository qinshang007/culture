package com.culture.util;


import org.json.JSONArray;

public class JsonUtils {
	
	/** 
	 * 从json解析出java字符串数组
	 * jsonArrayStr:["1","2","3"]
	 */
	public static String[] toStringArray(String jsonArrayStr) {
		JSONArray jsonArray = new JSONArray(jsonArrayStr);
		String[] stringArray = new String[jsonArray.length()];
		for (int i = 0; i < jsonArray.length(); i++) {
			stringArray[i] = jsonArray.getString(i);
		}
		return stringArray;
	}

}
