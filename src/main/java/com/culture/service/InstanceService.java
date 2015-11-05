package com.culture.service;

import java.util.Map;

import com.culture.model.Instance;

public interface InstanceService {
	
	/*保存实例*/
	public boolean addInstance(Map<String,String> valueMap,Instance cb);
	
	/*删除实例*/
	public void delInstance(String title);
	
	/*编辑实例*/
	public boolean editInstance(Map<String,String> valueMap,Instance cb);
	
	/*根据标识符查找实例*/
	public Instance getInstanceById(String identifier);
	
	/*更新本体实例*/
	public boolean updateInstance(Instance instance);
	
}
