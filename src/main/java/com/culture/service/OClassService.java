package com.culture.service;

import java.util.List;

import com.culture.model.OClass;

public interface OClassService {
	
	/*返回概念列表*/
	public List<OClass> getClassList(OClass oclass);		
	
	/*根据id返回某个概念*/
	public OClass getClassById(String id);	
	
	/*根据name返回某个概念*/
	public OClass getClassByName(String cname);			
	
	/*添加概念*/
	public boolean addClass(OClass oclass);
	
	/*删除概念*/
	public boolean delClass(String cid,String cname);
	
	/*更新概念*/
	public boolean updateClass(OClass oclass);
	
	/*更新路径*/
	public boolean updatePath(OClass oclass);
	
	/*根据概念id，当direct为true时返回它的直系子概念，当direct为False时，返回它的所有后代概念*/
	public List<OClass> getSubClasses(String cname,boolean direct);
	
	/*查询数据库，返回某一感念的所有子概念*/
	public List<OClass> getChildClass(String cname,int direct);
	
	/*根据概念id返回概念的名字*/
	public String getNameById(String id);
	
	/*验证概念名字是否存在*/
	public boolean isClassExist(String cname);
	
}
