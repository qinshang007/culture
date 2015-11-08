package com.culture.service;

import java.util.List;

import com.culture.model.OProperty;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;

public interface OPropertyService {
	
	/*返回属性列表*/
	public List<OProperty> getPropertyList(String userName);		
	
	/*根据id返回某个属性*/
	public OProperty getPropertyById(int id);	
	
	/*根据属性名字返回属性,是否需要返回属性的值域*/
	public OProperty getPropertyByName(String pname,boolean needRange);
	
	/*添加属性*/
	public boolean addProperty(OProperty OProperty);
	
	/*删除属性*/
	public boolean delProperty(String pid,String pname);
	
	/*更改属性*/
	public boolean updateProperty(OProperty oproperty,String oldPname);
	
	/*根据概念id，获得所有与它相关的属性*/
	public List<OProperty> getPropertys(String cid);
	
	/*将OntProperty转换为OProeprty*/
	public OProperty transfer(OntProperty ontp);
	
	/*返回需要制定规则的属性列表*/
	public List<OProperty> getRulePropertys();
	
	/*验证属性名字是否存在*/
	public boolean isPropertyExist(String pname);

	
}
