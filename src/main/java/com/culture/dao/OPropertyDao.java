package com.culture.dao;

import java.util.List;

import com.culture.model.OClass;
import com.culture.model.OProperty;

public interface OPropertyDao {
	
	/*返回属性列表*/
	public List<OProperty> getPropertyList();		
	
	/*根据id返回某个属性*/
	public OProperty getPropertyById(String id);			
	
	/*添加属性*/
	public boolean addProperty(OProperty oproperty);
	
	/*删除属性*/
	public boolean delProperty(String id);
	
	/*更改属性*/
	public boolean updateProperty(OProperty oproperty);
	
}
