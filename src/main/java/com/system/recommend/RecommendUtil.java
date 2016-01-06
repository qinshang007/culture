package com.system.recommend;

/**
 * 
 */

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import com.culture.model.CulturalBean;

/**
 * @author Riching
 * 
 * @date 2013-8-10
 */
public class RecommendUtil {
	
	/**
	 * 根据关键词描述，获取两个实例的相似度
	 * @param inst1
	 * @param inst2
	 * @return
	 */
	public static double getSimilarty(CulturalBean cb1,CulturalBean cb2) throws IOException{
		return getSimilarty(cb1.getKeywords(), cb2.getKeywords());
	}

    /**
     * 获取两个字符串的相似度
     * @param args
     * @throws IOException
     */
    public static double getSimilarty(String str1,String str2) throws IOException {
        Map<String, Integer> tf1 = getTF(str1);
        Map<String, Integer> tf2 = getTF(str2);
        Map<String, MutablePair<Integer, Integer>> tfs = new HashMap<String, MutablePair<Integer, Integer>>();
        for (String key : tf1.keySet()) {
            MutablePair<Integer, Integer> pair = new MutablePair<Integer, Integer>(tf1.get(key), 0);
            tfs.put(key, pair);
        }
        for (String key : tf2.keySet()) {
            MutablePair<Integer, Integer> pair = tfs.get(key);
            if (null == pair) {
                pair = new MutablePair<Integer, Integer>(0, tf2.get(key));
            } else {
                pair.setRight(tf2.get(key));
            }
        }
        double d = caclIDF(tfs);
        return d;
    }

    public static Map<String, Integer> getTF(String str) throws IOException {
        Map<String, Integer> map = new HashMap<String, Integer>();
        IKSegmenter ikSegmenter = new IKSegmenter(new StringReader(str), true);
        Lexeme lexeme = null;
        while ((lexeme = ikSegmenter.next()) != null) {
            String key = lexeme.getLexemeText();
            Integer count = map.get(key);
            if (null == count) {
                count = 1;
            } else {
                count = count + 1;
            }
            map.put(key, count);
        }
        return map;
    }

    public static double caclIDF(Map<String, MutablePair<Integer, Integer>> tf) {
        double d = 0;
        if (MapUtils.isEmpty(tf)) {
            return d;
        }
        double denominator = 0;
        double sqdoc1 = 0;
        double sqdoc2 = 0;
        Pair<Integer, Integer> count = null;
        for (String key : tf.keySet()) {
            count = tf.get(key);
            denominator += count.getLeft() * count.getRight();
            sqdoc1 += count.getLeft() * count.getLeft();
            sqdoc2 += count.getRight() * count.getRight();
        }
        d = denominator / (Math.sqrt(sqdoc1) * Math.sqrt(sqdoc2));
        return d;
    }
    
    public static void main(String[]args) throws IOException{
    	CulturalBean cb1 = new CulturalBean();
    	cb1.setKeywords("器物，瓷器，青花瓷");
    	CulturalBean cb2 = new CulturalBean();
    	cb2.setKeywords("器物，瓷器，青花瓷");
    	System.out.println(RecommendUtil.getSimilarty(cb1, cb2));
    }
}


