package com.culture.dao.impl;

import java.util.List;

import com.culture.dao.BaseDao;
import com.culture.dao.ORuleDao;
import com.culture.model.OClass;
import com.culture.model.ORule;

public class ORuleDaoImpl extends BaseDao implements ORuleDao{

	/**
	 * 添加规则
	 */
	public boolean addRule(ORule rule) {
		// TODO Auto-generated method stub
		int object = 0;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().insert("addRule",rule);
		}catch (Exception e) {
			logger.error("添加规则出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 根据规则id查找规则
	 */
	public ORule getRuleById(int rid) {
		// TODO Auto-generated method stub
		ORule or = null;
		try{
			or = (ORule)getSqlMapClientTemplate().queryForObject("getRuleById",rid);
		}catch (Exception e) {
			logger.error("根据规则id获取规则出错！" +  ",errMsg=" + e.getMessage());
		}
		return or;

	}

	/**
	 * 更新规则
	 */
	public boolean updateRule(ORule rule) {
		// TODO Auto-generated method stub
		int object = 0;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().update("updateRule",rule);
		}catch (Exception e) {
			logger.error("更新规则出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != 0) {
			flag = true;
		}
		return flag;

	}

	/**
	 * 返回规则列表
	 */
	public List<ORule> getRuleList(ORule orule) {
		// TODO Auto-generated method stub
		List<ORule> orlist = null;
		try{
			orlist = getSqlMapClientTemplate().queryForList("getORuleList",orule);
		}catch (Exception e) {
			logger.error("获取规则列表出错！" +  ",errMsg=" + e.getMessage());
		}
		return orlist;

	}

	/**
	 * 删除规则
	 */
	public boolean delRule(int rid) {
		// TODO Auto-generated method stub
		int object = 0;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().update("delRule",rid);
		}catch (Exception e) {
			logger.error("删除规则出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != 0) {
			flag = true;
		}
		return flag;

	}

}
