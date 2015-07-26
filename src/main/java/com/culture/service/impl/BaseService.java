package com.culture.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.culture.dao.CulturalDao;
import com.culture.dao.OClassDao;
import com.culture.dao.OPropertyDao;

public class BaseService {
	
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"classpath:/applicationContext.xml");

	private OClassDao ocDao = (OClassDao) context.getBean("ocDao");
	
	private OPropertyDao opDao = (OPropertyDao) context.getBean("opDao");
	
	private CulturalDao clDao = (CulturalDao) context.getBean("clDao");
	
	public  OClassDao getOClassDao(){
		return ocDao;
	}
	
	public OPropertyDao getOPropertyDao(){
		return opDao;
	}
	
	public CulturalDao getCulturalDao(){
		return clDao;
	}
	
}
