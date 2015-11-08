package com.culture.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.culture.dao.BaseDao;
import com.culture.dao.OPropertyDao;
import com.culture.model.OProperty;

@Repository
public class OPropertyDaoImpl extends BaseDao implements OPropertyDao{

	private static final Logger logger = Logger.getLogger(OPropertyDaoImpl.class);  
	
	@SuppressWarnings("unchecked")
	public List<OProperty> getPropertyList(Map map) {
		// TODO Auto-generated method stub
		List<OProperty> oclist = null;
		try{
			oclist = getSqlMapClientTemplate().queryForList("getPropertyList",map);
		}catch (Exception e) {
			logger.error("获取属性列表信息出错！" +  ",errMsg=" + e.getMessage());
		}
		return oclist;
	}

	public OProperty getPropertyById(int id) {
		// TODO Auto-generated method stub
		OProperty oc = null;
		try{
			oc = (OProperty)getSqlMapClientTemplate().queryForObject("getByPropertyId",id);
		}catch (Exception e) {
			logger.error("根据属性id获取属性信息出错！" +  ",errMsg=" + e.getMessage());
		}
		return oc;
	}
	
	/**
	 * 根据属性名字返回属性
	 */
	public OProperty getPropertyByName(String pname) {
		// TODO Auto-generated method stub
		OProperty oc = null;
		try{
			oc = (OProperty)getSqlMapClientTemplate().queryForObject("getPropertyByName",pname);
		}catch (Exception e) {
			logger.error("根据属性名字获取属性信息出错！" +  ",errMsg=" + e.getMessage());
		}
		return oc;

	}


	public boolean addProperty(OProperty OProperty) {
		// TODO Auto-generated method stub
		int object = 0;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().insert("addProperty",OProperty);
		}catch (Exception e) {
			logger.error("添加属性信息出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != 0) {
			flag = true;
		}
		return flag;
	}

	public boolean delProperty(int pid) {
		// TODO Auto-generated method stub
		Object object = null;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().delete("delProperty",pid);
		}catch (Exception e) {
			logger.error("删除属性信息出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != null) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 升级属性
	 */
	public boolean upgradeProperty(int pid) {
		// TODO Auto-generated method stub
		Object object = null;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().update("upgradeProperty",pid);
		}catch (Exception e) {
			logger.error("升级属性信息出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != null) {
			flag = true;
		}
		return flag;

	}

	public boolean updateProperty(OProperty oproperty) {
		// TODO Auto-generated method stub
		Object object = null;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().update("updateProperty",oproperty);
		}catch (Exception e) {
			logger.error("更新属性信息出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != null) {
			flag = true;
		}
		return flag;

	}


}
