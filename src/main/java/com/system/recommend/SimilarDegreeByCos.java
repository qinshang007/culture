package com.system.recommend;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.culture.model.CulturalBean;

public class SimilarDegreeByCos{
	
	 static String sql = null;

	 static DBConnect db = null;
	 
	 static ResultSet ret = null;
		
	 static Statement st = null;
	 /*
	 * 计算两个对象之间的相似度
	 */
	 public static double getSimilarty(CulturalBean cb1,CulturalBean cb2) throws IOException{
		 return getSimilarDegree(cb1.getKeywords().toString(), cb2.getKeywords().toString());
	 }
	 
	 /*
	 * 计算两个字符串(英文字符)的相似度，简单的余弦计算，未添权重
	 */
     public static double getSimilarDegree(String str1, String str2)
     {
    	//创建向量空间模型，使用map实现，主键为词项，值为长度为2的数组，存放着对应词项在字符串中的出现次数
    	 Map<String, int[]> vectorSpace = new HashMap<String, int[]>();
    	 int[] itemCountArray = null;//为了避免频繁产生局部变量，所以将itemCountArray声明在此
    	 
    	 //以空格为分隔符，分解字符串
    	 String strArray[] = str1.split(",");
    	 for(int i=0; i<strArray.length; ++i)
    	 {    		 
    		 if(vectorSpace.containsKey(strArray[i])){
    			 ++(vectorSpace.get(strArray[i])[0]);    		 	 
    		 }    			 
    		 else
    		 {
    			 itemCountArray = new int[2];
    	         itemCountArray[0] = 1;
    	         itemCountArray[1] = 0;
    	         vectorSpace.put(strArray[i], itemCountArray);    	       
    		 }
    	 }
    	 
    	 strArray = str2.split(",");
    	 for(int i=0; i<strArray.length; ++i)
    	 {
    		 if(vectorSpace.containsKey(strArray[i]))
    			 ++(vectorSpace.get(strArray[i])[1]);
    		 else
    		 {
    			 itemCountArray = new int[2];
    	         itemCountArray[0] = 0;
    	         itemCountArray[1] = 1;
    	         vectorSpace.put(strArray[i], itemCountArray);
    		 }
    	 }
    	 
    	 //计算相似度
    	 double vector1Modulo = 0.00;//向量1的模
    	 double vector2Modulo = 0.00;//向量2的模
    	 double vectorProduct = 0.00; //向量积
    	 double d=0.0;
    	 Iterator iter = vectorSpace.entrySet().iterator();
    	 
    	 while(iter.hasNext())
    	 {
    		 Map.Entry entry = (Map.Entry)iter.next();
    		 itemCountArray = (int[])entry.getValue();
    		 
    		 vector1Modulo += itemCountArray[0]*itemCountArray[0];
    		 vector2Modulo += itemCountArray[1]*itemCountArray[1];
    		 
    		 vectorProduct += itemCountArray[0]*itemCountArray[1];
    	 }    	 
    	 vector1Modulo = Math.sqrt(vector1Modulo);
    	 vector2Modulo = Math.sqrt(vector2Modulo);
    	 d=vectorProduct/(vector1Modulo*vector2Modulo);
    	 
    	 //返回相似度
		return d;
     }
     
     public static void main(String args[]) throws IOException
     {
    	 CulturalBean cb1 = new CulturalBean();    	     	
     	 cb1.setKeywords("先出, 中州, 陶瓷工艺, 吸水率, 夏商周, 陶瓷工, 周三, 吸水, 它在, 釉结, 浸, 三代, 火候, 条件, 刷");
     	 CulturalBean cb2 = new CulturalBean();
     	 cb2.setKeywords("降魔, 北周, 敦煌, 煌, 莫高窟, 第, 石窟, 窟, 敦, 420-589, 南北朝, 北朝, 北, 南北, 图");
     	
    	 System.out.println(SimilarDegreeByCos.getSimilarty(cb1,cb2)); 
       	
     }
}
