package com.culture.dao;

import java.util.List;

import com.culture.model.OClass;

public interface OClassDao {
	
	/*���ظ����б�*/
	public List<OClass> getClassList(OClass oclass);		
	
	/*����id����ĳ������*/
	public OClass getClassById(String id);		
	
	/*����name����ĳ������*/
	public OClass getClassByName(String cname);			
	
	/*��Ӹ���*/
	public boolean addClass(OClass oclass);
	
	/*ɾ������*/
	public boolean delClass(String id);
	
	/*���¸���*/
	public boolean updateClass(OClass oclass);
	
}
