package com.culture.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.culture.dao.BaseDao;
import com.culture.dao.CulturalDao;
import com.culture.model.CulturalBean;

public class CulturalDaoImpl extends BaseDao implements CulturalDao{

	private static final Logger logger = Logger.getLogger(CulturalDaoImpl.class);
	
	public boolean addCultural(CulturalBean cb) {
		// TODO Auto-generated method stub
		int object = 0;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().insert("addCultural",cb);
		}catch (Exception e) {
			logger.error("添加文物出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != 0) {
			flag = true;
		}
		return flag;

	}

	public boolean updateCultural(CulturalBean cb) {
		// TODO Auto-generated method stub
		Object object = null;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().update("updateCultural",cb);
		}catch (Exception e) {
			logger.error("更新文物出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != null) {
			flag = true;
		}
		return flag;

	}

	public List<CulturalBean> getCulturalList() {
		// TODO Auto-generated method stub
		List<CulturalBean> cblist = null;
		try{
			cblist = getSqlMapClientTemplate().queryForList("getCulturalList");
		}catch (Exception e) {
			logger.error("获取文物列表出错！" +  ",errMsg=" + e.getMessage());
		}
		return cblist;
	}

	public boolean delCultural(String identifier) {
		// TODO Auto-generated method stub
		Object object = null;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().update("delCultural",identifier);
		}catch (Exception e) {
			logger.error("删除文物出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != null) {
			flag = true;
		}
		return flag;

	}

	public CulturalBean getCulturalById(String culId) {
		// TODO Auto-generated method stub
		CulturalBean cb = null;
		try{
			cb = (CulturalBean)getSqlMapClientTemplate().queryForObject("getCulturalById", culId);
		}catch (Exception e) {
			logger.error("获取文物详情出错！" +  ",errMsg=" + e.getMessage());
		}
		return cb;
	}

}
