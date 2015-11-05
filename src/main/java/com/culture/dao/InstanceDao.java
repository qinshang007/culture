package com.culture.dao;

import com.culture.model.Instance;

public interface InstanceDao {
	
	/*根据id查找实例*/
	public Instance getInstanceById(String identifier);
	
	/*根据标题查找实例*/
	public Instance getInstanceByTitle(String title);
	
	/*添加实例*/
	public boolean addInstance(Instance instance);
	
	/*删除实例*/
	public boolean delInstance(String identifier);
	
	/*更新实例*/
	public boolean updateInstance(Instance instance);
	
}
