package com.culture.dao;

import java.util.List;
import java.util.Map;

import com.culture.model.OProperty;

public interface OPropertyDao {
	
	/*返回属性列表*/
	public List<OProperty> getPropertyList(Map map);		
	
	/*根据id返回某个属性*/
	public OProperty getPropertyById(int id);		
	
	/*根据名字返回某个属性*/
	public OProperty getPropertyByName(String pname);
	
	/*添加属性*/
	public boolean addProperty(OProperty oproperty);
	
	/*删除属性*/
	public boolean delProperty(int pid);
	
	/*升级属性*/
	public boolean upgradeProperty(int pid);
	
	/*更改属性*/
	public boolean updateProperty(OProperty oproperty);
	
}
