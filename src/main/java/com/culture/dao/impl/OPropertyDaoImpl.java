package com.culture.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.culture.dao.OPropertyDao;
import com.culture.model.OProperty;
import com.culture.dao.BaseDao;

@Repository
public class OPropertyDaoImpl extends BaseDao implements OPropertyDao{

	private static final Logger logger = Logger.getLogger(OPropertyDaoImpl.class);  
	
	@SuppressWarnings("unchecked")
	public List<OProperty> getPropertyList() {
		// TODO Auto-generated method stub
		List<OProperty> oclist = null;
		try{
			oclist = getSqlMapClientTemplate().queryForList("getPropertyList",0);
		}catch (Exception e) {
			logger.error("��ȡ�����б���Ϣ����" +  ",errMsg=" + e.getMessage());
		}
		return oclist;
	}

	public OProperty getPropertyById(String id) {
		// TODO Auto-generated method stub
		OProperty oc = null;
		try{
			oc = (OProperty)getSqlMapClientTemplate().queryForObject("getByPropertyId",id);
		}catch (Exception e) {
			logger.error("��������id��ȡ������Ϣ����" +  ",errMsg=" + e.getMessage());
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
			logger.error("���������Ϣ����" +  ",errMsg=" + e.getMessage());
		}
		if (object != 0) {
			flag = true;
		}
		return flag;
	}

	public boolean delProperty(String id) {
		// TODO Auto-generated method stub
		Object object = null;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().update("delProperty",id);
		}catch (Exception e) {
			logger.error("ɾ��������Ϣ����" +  ",errMsg=" + e.getMessage());
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
			logger.error("����������Ϣ����" +  ",errMsg=" + e.getMessage());
		}
		if (object != null) {
			flag = true;
		}
		return flag;

	}

}
