package com.culture.dao;

import com.culture.model.UserBean;

public interface UserDao {
	
	/*�ж��û��Ƿ����*/
	public boolean checkUser(UserBean ub);
	
	/*�����û�id��ȡ�û�*/
	public UserBean getUserById(String userId);
	
	/*�����û����ƻ�ȡ�û�*/
	public UserBean getUserByName(String userName);
	
}
