package com.culture.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.culture.dao.BaseDao;
import com.culture.dao.OClassDao;
import com.culture.model.OClass;

@Repository
public class OClassDaoImpl extends BaseDao implements OClassDao{

	private static final Logger logger = Logger.getLogger(OClassDaoImpl.class);  
	
	@SuppressWarnings("unchecked")
	public List<OClass> getClassList(Map map) {
		// TODO Auto-generated method stub
		List<OClass> oclist = null;
		try{
			oclist = getSqlMapClientTemplate().queryForList("getOclassList",map);
		}catch (Exception e) {
			logger.error("获取概念列表信息出错！" +  ",errMsg=" + e.getMessage());
		}
		return oclist;
	}
	
	@Override
	public int getListCount(Map map) {
		// TODO Auto-generated method stub
		int object = 0;
		try{
			object = (Integer)getSqlMapClientTemplate().queryForObject("getClassCount",map);
		}catch (Exception e) {
			logger.error("获取概念列表数量出错！" +  ",errMsg=" + e.getMessage());
		}
		return object;
	}

	public OClass getClassById(String id) {
		// TODO Auto-generated method stub
		OClass oc = null;
		try{
			oc = (OClass)getSqlMapClientTemplate().queryForObject("getByClassId",id);
		}catch (Exception e) {
			logger.error("根据概念id获取概念信息出错！" +  ",errMsg=" + e.getMessage());
		}
		return oc;
	}

	public OClass getClassByName(String cname) {
		// TODO Auto-generated method stub
		OClass oc = null;
		try{
			oc = (OClass)getSqlMapClientTemplate().queryForObject("getByClassName",cname);
		}catch (Exception e) {
			logger.error("根据概念名字获取概念信息出错！" +  ",errMsg=" + e.getMessage());
		}
		return oc;
	}

	
	public boolean addClass(OClass oclass) {
		// TODO Auto-generated method stub
		int object = 0;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().insert("addClass",oclass);
		}catch (Exception e) {
			logger.error("添加概念信息出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != 0) {
			flag = true;
		}
		return flag;
	}

	public boolean delClass(int cid) {
		// TODO Auto-generated method stub
		Object object = null;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().delete("delClass",cid);
		}catch (Exception e) {
			logger.error("删除概念信息出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != null) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 升级概念
	 */
	public boolean upgradeClass(int cid) {
		// TODO Auto-generated method stub
		Object object = null;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().update("upgradeClass",cid);
		}catch (Exception e) {
			logger.error("升级概念信息出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != null) {
			flag = true;
		}
		return flag;

	}


	public boolean updateClass(OClass oclass) {
		// TODO Auto-generated method stub
		Object object = null;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().update("updateClass",oclass);
		}catch (Exception e) {
			logger.error("更新概念信息出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != null) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 更新路径
	 */
	public boolean updatePath(OClass oclass) {
		// TODO Auto-generated method stub
		Object object = null;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().update("updatePath",oclass);
		}catch (Exception e) {
			logger.error("更新概念路径信息出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != null) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 返回某一概念的所有子概念
	 */
	public List<OClass> getChildClass(Map map) {
		// TODO Auto-generated method stub
		List<OClass> oclist = null;
		try{
			oclist = getSqlMapClientTemplate().queryForList("getChildClass",map);
		}catch (Exception e) {
			logger.error("获取子概念信息出错！" +  ",errMsg=" + e.getMessage());
		}
		return oclist;
	}


}
