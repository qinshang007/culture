package com.culture.dao;

import java.util.List;

import com.culture.model.ORule;

public interface ORuleDao {
	
	/*添加规则*/
	public boolean addRule(ORule rule);
	
	/*根据规则id查找规则*/
	public ORule getRuleById(int rid);
	
	/*更改规则*/
	public boolean updateRule(ORule rule);
	
	/*返回规则列表*/
	public List<ORule> getRuleList(ORule orule);
	
	/*删除规则*/
	public boolean delRule(int rid);
	
}
