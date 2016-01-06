package com.system.recommend;


import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.dic.Dictionary;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class Test2 {
	public static void main(String[] args) throws IOException {
		// 检索内容
		String text = "塔为砖石结构，金刚宝座式，由塔座和座上群塔两部分组成。塔座呈八边形，八角设圆倚柱，上饰石雕螭首，下奠石柱础。东南、西北、西南、东北四面各设石砌券门。各面外壁均嵌石雕佛龛和佛像。塔座上端叠涩浅檐。座内正中有一单层八角小塔，类似塔心柱。东北向的门道内构石阶，上通塔座顶部方形罩亭。座顶立五塔，中央为喇嘛塔，高9．54米。四隅均为六角形三檐小塔高6．65米。塔身嵌石龛、佛像。";
		List<String> list = new ArrayList<String>();
		list.add("test中文");

		// 尚未初始化，因为第一次执行分词的时候才会初始化，为了在执行分词前手动添加额外的字典，需要先手动的初始化一下
		Dictionary.initial(DefaultConfig.getInstance());
		Dictionary.getSingleton().addWords(list);

		//创建分词对象  
		Analyzer analyzer = new IKAnalyzer(true);       
		StringReader reader = new StringReader(text);  

		TokenStream ts = analyzer.tokenStream("", reader);  
		CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);  
		//遍历分词数据  
		try {
		    while(ts.incrementToken()){  
		        System.out.print(term.toString()+"|");  
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		} finally{
		    reader.close();
		}
	}

}
