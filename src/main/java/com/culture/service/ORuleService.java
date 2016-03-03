package com.culture.service;

import java.util.List;
import java.util.Map;

import com.culture.model.ORule;

public interface ORuleService {

	/*添加规则*/
	public boolean addRule(ORule orule,String opBodyType1,String opBodyType2,String opHeadType);
	
	/*根据规则id查找规则*/
	public ORule getRuleById(int rid);
	
	/*更改规则*/
	public boolean updateRule(ORule rule);
	
	/*返回规则列表*/
	public List<ORule> getRuleList(int del,int pageStart,int pageSize);
	
	/*返回规则数量*/
	public int getListCount();
	
	/*删除规则*/
	public boolean delRule(int rid);
	
	/*自动生成规则*/
	public Map<String,Integer> genRules();

	
}
