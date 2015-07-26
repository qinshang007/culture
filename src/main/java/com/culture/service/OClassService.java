package com.culture.service;

import java.util.List;

import com.culture.model.OClass;

public interface OClassService {
	
	/*���ظ����б�*/
	public List<OClass> getClassList(OClass oclass);		
	
	/*����id����ĳ������*/
	public OClass getClassById(String id);	
	
	/*����name����ĳ������*/
	public OClass getClassByName(String cname);			
	
	/*��Ӹ���*/
	public boolean addClass(OClass oclass);
	
	/*ɾ������*/
	public boolean delClass(String id);
	
	/*���¸���*/
	public boolean updateClass(OClass oclass);
	
	/*���ݸ���id����directΪtrueʱ��������ֱϵ�Ӹ����directΪFalseʱ�������������к������*/
	public List<OClass> getSubClasses(String cid,boolean direct);
	
	/*���ݸ���id���ظ��������*/
	public String getNameById(String id);

}
