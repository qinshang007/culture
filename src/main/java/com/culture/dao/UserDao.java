package com.culture.dao;

import java.util.List;

import com.culture.model.UserBean;

public interface UserDao {
	
	/*�ж��û��Ƿ����*/
	public boolean checkUser(UserBean ub);
	
	/*�����û�id��ȡ�û�*/
	public UserBean getUserById(String userId);
	
	/*�����û����ƻ�ȡ�û�*/
	public UserBean getUserByName(String userName);
	
	/*����û�*/
	public boolean addUser(UserBean ub);
	
	/*�����û��б�*/
	public List<UserBean> getUserList();
	
	/*�����û���Ϣ*/
	public boolean updateUser(UserBean ub);
	
	/*ɾ���û�*/
	public boolean deleteUser(String userId);
	
}
