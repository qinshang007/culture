package com.culture.dao.impl;

import org.apache.log4j.Logger;

import com.culture.dao.BaseDao;
import com.culture.dao.InstanceDao;
import com.culture.model.Instance;

public class InstanceDaoImpl extends BaseDao implements InstanceDao{
	
	private static final Logger logger = Logger.getLogger(InstanceDaoImpl.class);

	/**
	 * 根据标识符获取实例
	 */
	public Instance getInstanceById(String identifier) {
		// TODO Auto-generated method stub
		Instance instance = null;
		try{
			instance = (Instance)getSqlMapClientTemplate().queryForObject("getInstanceById", identifier);
		}catch (Exception e) {
			logger.error("根据idntifier获取实例信息出错！" +  ",errMsg=" + e.getMessage());
		}
		return instance;
	}

	/**
	 * 根据标题获取实例
	 */
	public Instance getInstanceByTitle(String title) {
		// TODO Auto-generated method stub
		Instance instance = null;
		try{
			instance = (Instance)getSqlMapClientTemplate().queryForObject("getInstanceByTitle", title);
		}catch (Exception e) {
			logger.error("根据title获取实例信息出错！" +  ",errMsg=" + e.getMessage());
		}
		return instance;

	}

	/**
	 * 添加实例
	 */
	public boolean addInstance(Instance instance) {
		// TODO Auto-generated method stub
		int object = 0;
		boolean flag = false;
		try{
			object =(Integer) getSqlMapClientTemplate().insert("addInstance",instance);
		}catch (Exception e) {
			logger.error("添加实例信息出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除实例
	 */
	public boolean delInstance(String identifier) {
		// TODO Auto-generated method stub
		int object = 0;
		boolean flag = false;
		try{
			object =(Integer) getSqlMapClientTemplate().delete("delInstance", identifier);
		}catch (Exception e) {
			logger.error("删除实例信息出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != 0) {
			flag = true;
		}
		return flag;

	}

	/**
	 * 更新实例
	 */
	public boolean updateInstance(Instance instance) {
		// TODO Auto-generated method stub
		int object = 0;
		boolean flag = false;
		try{
			object =(Integer) getSqlMapClientTemplate().update("updateInstance", instance);
		}catch (Exception e) {
			logger.error("更新实例信息出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != 0) {
			flag = true;
		}
		return flag;

	}

}
