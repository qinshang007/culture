package com.culture.service;

import java.util.List;

import com.culture.model.OProperty;

public interface OPropertyService {
	
	/*返回属性列表*/
	public List<OProperty> getPropertyList();		
	
	/*根据id返回某个属性*/
	public OProperty getPropertyById(String id);			
	
	/*添加属性*/
	public boolean addProperty(OProperty OProperty);
	
	/*删除属性*/
	public boolean delProperty(String id);
	
	/*更改属性*/
	public boolean updateProperty(OProperty oproperty);
	
	/*根据概念id，获得所有与它相关的属性*/
	public List<OProperty> getPropertys(String cid);

	
}
