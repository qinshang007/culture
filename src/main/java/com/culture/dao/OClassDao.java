package com.culture.dao;

import java.util.List;

import com.culture.model.OClass;

public interface OClassDao {
	
	/*返回概念列表*/
	public List<OClass> getClassList(OClass oclass);		
	
	/*根据id返回某个概念*/
	public OClass getClassById(String id);		
	
	/*根据name返回某个概念*/
	public OClass getClassByName(String cname);			
	
	/*添加概念*/
	public boolean addClass(OClass oclass);
	
	/*删除概念*/
	public boolean delClass(int id);
	
	/*升级概念*/
	public boolean upgradeClass(int cid);
	
	/*更新概念*/
	public boolean updateClass(OClass oclass);
	
	/*更新路径*/
	public boolean updatePath(OClass oclass);
	
	/*返回所有的子概念*/
	public List<OClass> getChildClass(int cid);

	
}
