package com.culture.service;

import java.util.Map;

public interface AnalyzeService {

	/**
	 * 统计某个类别属性的完整度
	 * @param property
	 * @return
	 */
	public Map<String,Object> getIntegrity(String type,String classification);

}
