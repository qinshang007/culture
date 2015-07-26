package com.culture.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.culture.dao.OClassDao;
import com.culture.model.OClass;
import com.culture.dao.BaseDao;

@Repository
public class OClassDaoImpl extends BaseDao implements OClassDao{

	private static final Logger logger = Logger.getLogger(OClassDaoImpl.class);  
	
	@SuppressWarnings("unchecked")
	public List<OClass> getClassList(OClass oclass) {
		// TODO Auto-generated method stub
		List<OClass> oclist = null;
		try{
			oclist = getSqlMapClientTemplate().queryForList("getOclassList",oclass);
		}catch (Exception e) {
			logger.error("获取概念列表信息出错！" +  ",errMsg=" + e.getMessage());
		}
		return oclist;
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
		String object = null;
		boolean flag = false;
		try {
			object =(String) getSqlMapClientTemplate().insert("addClass",oclass);
		}catch (Exception e) {
			logger.error("添加概念信息出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != null) {
			flag = true;
		}
		return flag;
	}

	public boolean delClass(String id) {
		// TODO Auto-generated method stub
		Object object = null;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().update("delClass",id);
		}catch (Exception e) {
			logger.error("删除概念信息出错！" +  ",errMsg=" + e.getMessage());
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

}
