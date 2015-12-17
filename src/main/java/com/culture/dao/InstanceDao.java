package com.culture.dao;

import java.util.List;
import java.util.Map;

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
	
	/*获取本体列表*/
	public List<Instance> getInstanceList(Map map);
	
	//返回文物列表数量
	public int getInstanceCount(Map map);

	
}
