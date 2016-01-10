package com.culture.service.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.culture.service.AnalyzeService;

@Service
public class AnalyzeServiceImpl extends BaseService implements AnalyzeService{
	
	String[] propertys = new String[]{"title","used_title","c_level","creation_date","creation_time","place_of_origin","creator","measurement","excavation_date","excavation_place","current_location","exhibition_history","location","shape","pattern","color","structure","decoration","scene","c_usage","symbolic_meaning","aesthetic_desc","social_history_info","material","technique","history_info","folklore","relation","source","rights","mainpic"};

	DecimalFormat df = new DecimalFormat("0.00");//格式化小数   
	
	/**
	 * 统计某个类别属性的完整度
	 */
	@Override
	public Map<String,Object> getIntegrity(String type,String classification) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", type);
		map.put("classification", classification);
		int base = getCulturalDao().getListCount(map);
		Map<String,Object> resMap = new HashMap<String,Object>();
		for(int i=0;i<propertys.length;i++){
			map.put("property", propertys[i]);
			int count = getAnalyzeDao().getIntegrity(map);
			int integrity = (int)(((base-count)/(float)base)*100);
			resMap.put(propertys[i], integrity);
		}
		return resMap;
	}

}
