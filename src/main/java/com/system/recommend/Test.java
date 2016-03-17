package com.system.recommend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Test {
	
    public static LinkedHashMap<String, Float> sort(HashMap<String, Float> map){

    	LinkedHashMap<String,Float> resMap = new LinkedHashMap<String,Float>();
		List<Map.Entry<String, Float>> infoIds = new ArrayList<Map.Entry<String, Float>>(map.entrySet());
			Collections.sort(infoIds, new Comparator<Map.Entry<String, Float>>() {
				public int compare(Map.Entry<String, Float> o1,
						Map.Entry<String, Float> o2) {
					
					if(o1.getValue()==o2.getValue()&&o1.getKey().equals(o2.getKey()))
						return 0;
					return o1.getValue()>o2.getValue()?-1:1;
				}
			});
			
		for (int i = 0; i < infoIds.size(); i++) {
			Entry<String,Float> ent=infoIds.get(i);
			resMap.put(ent.getKey(), ent.getValue());
		}
		return resMap;
    }

    public static void main(String[]args){
    	HashMap<String,Float> map = new HashMap<String,Float>();
    	map.put("天", (float) 0.002079002);
    	map.put("上", (float) 0.002079002);
    	map.put("人", (float) 0.002079002);
    	map.put("间", (float) 0.002079002);
    	LinkedHashMap<String,Float> sortedMap = sort(map);
    	for(int i=0;i<sortedMap.size();i++)
    		System.out.println(sortedMap.get(i));
    }
	
}
