package com.culture.dao;

import java.util.Map;

public interface AnalyzeDao {
	
	/**
	 * 统计某个属性的完整度
	 * @param property
	 * @return
	 */
	public int getIntegrity(Map map);
	
	/**
	 * 统计某个朝代文物数量
	 * @param map
	 * @return
	 */
	public int getNumByDynasty(Map map);
}
