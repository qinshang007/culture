package com.culture.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.culture.dao.AnalyzeDao;
import com.culture.dao.CulturalDao;
import com.culture.dao.InstanceDao;
import com.culture.dao.OClassDao;
import com.culture.dao.OPropertyDao;
import com.culture.dao.ORuleDao;
import com.culture.dao.UserDao;

public class BaseService {
	
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"classpath:/applicationContext.xml");

	private OClassDao ocDao = (OClassDao) context.getBean("ocDao");
	
	private OPropertyDao opDao = (OPropertyDao) context.getBean("opDao");
	
	private CulturalDao clDao = (CulturalDao) context.getBean("clDao");
	
	private ORuleDao orDao = (ORuleDao) context.getBean("orDao");
	
	private UserDao userDao = (UserDao)context.getBean("userDao");
	
	private InstanceDao instanceDao = (InstanceDao)context.getBean("instanceDao");
	
	private AnalyzeDao analyzeDao = (AnalyzeDao)context.getBean("analyzeDao");
	
	public  OClassDao getOClassDao(){
		return ocDao;
	}
	
	public OPropertyDao getOPropertyDao(){
		return opDao;
	}
	
	public CulturalDao getCulturalDao(){
		return clDao;
	}
	
	public ORuleDao getORuleDao(){
		return orDao;
	}
	
	public UserDao getUserDao(){
		return userDao;
	}
	
	public InstanceDao getInstanceDao(){
		return instanceDao;
	}
	
	public AnalyzeDao getAnalyzeDao(){
		return analyzeDao;
	}
}
