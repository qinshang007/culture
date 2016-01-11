package com.culture.dao.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.culture.dao.AnalyzeDao;
import com.culture.dao.BaseDao;

public class AnalyzeDaoImpl extends BaseDao implements AnalyzeDao{

	private static final Logger logger = Logger.getLogger(AnalyzeDaoImpl.class);
	
	/**
	 * 统计某个属性的完整度
	 */
	@Override
	public int getIntegrity(Map map) {
		// TODO Auto-generated method stub
		int object = 0;
		try {
			object =(Integer) getSqlMapClientTemplate().queryForObject("getIntegrity", map);
		}catch (Exception e) {
			logger.error("统计属性的完整度出错！" +  ",errMsg=" + e.getMessage());
		}
		return object;
	}

	/**
	 * 统计某个朝代的文物数量
	 */
	@Override
	public int getNumByDynasty(Map map) {
		// TODO Auto-generated method stub
		int object = 0;
		try {
			object =(Integer) getSqlMapClientTemplate().queryForObject("getNumByDynasty", map);
		}catch (Exception e) {
			logger.error("统计某个朝代的文物数量出错！" +  ",errMsg=" + e.getMessage());
		}
		return object;
	}

}
