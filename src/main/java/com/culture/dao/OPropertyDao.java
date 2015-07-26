package com.culture.dao;

import java.util.List;

import com.culture.model.OClass;
import com.culture.model.OProperty;

public interface OPropertyDao {
	
	/*���������б�*/
	public List<OProperty> getPropertyList();		
	
	/*����id����ĳ������*/
	public OProperty getPropertyById(String id);			
	
	/*�������*/
	public boolean addProperty(OProperty oproperty);
	
	/*ɾ������*/
	public boolean delProperty(String id);
	
	/*��������*/
	public boolean updateProperty(OProperty oproperty);
	
}
