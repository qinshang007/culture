package com.culture.service;

import java.util.List;
import java.util.Map;

import com.culture.model.Instance;
import com.culture.model.OClass;

public interface InstanceService {
	
	/*保存实例*/
	public boolean addInstance(Map<String,String> valueMap,Instance cb);
	
	/*删除实例*/
	public boolean delInstance(String culId,String title);
	
	/*编辑实例*/
	public boolean editInstance(Map<String,String> valueMap,Instance cb);
	
	/*根据标识符查找实例*/
	public Instance getInstanceById(String identifier);
	
	/*更新本体实例*/
	public boolean updateInstance(Instance instance);
	
	/*获取朝代列表*/
	public List<OClass> getCreationDateList();
	
	/*获取文物实例页面属性map*/
	public Map getInstanceMap();
	
}
