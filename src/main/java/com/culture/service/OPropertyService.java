package com.culture.service;

import java.util.List;

import com.culture.model.OProperty;

public interface OPropertyService {
	
	/*���������б�*/
	public List<OProperty> getPropertyList();		
	
	/*����id����ĳ������*/
	public OProperty getPropertyById(String id);			
	
	/*�������*/
	public boolean addProperty(OProperty OProperty);
	
	/*ɾ������*/
	public boolean delProperty(String id);
	
	/*��������*/
	public boolean updateProperty(OProperty oproperty);
	
	/*���ݸ���id���������������ص�����*/
	public List<OProperty> getPropertys(String cid);

	
}
