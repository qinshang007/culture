package com.culture.service;

import java.util.Map;

public interface AnalyzeService {

	/**
	 * 统计某个类别属性的完整度
	 * @param property
	 * @return
	 */
	public Map<String,Object> getIntegrity(String type,String classification);
	
	/**
	 * 统计某个朝代下文物的数量
	 * @param creation_date
	 * @param type
	 * @param classification
	 * @return
	 */
	public Map<String,Object> getNumByDynasty(String creation_date,String type,String classification);

}
