package com.culture.dao;

import java.util.List;

import com.culture.model.UserBean;

public interface UserDao {
	
	/*判断用户是否存在*/
	public boolean checkUser(UserBean ub);
	
	/*根据用户id获取用户*/
	public UserBean getUserById(String userId);
	
	/*根据用户名称获取用户*/
	public UserBean getUserByName(String userName);
	
	/*添加用户*/
	public boolean addUser(UserBean ub);
	
	/*返回用户列表*/
	public List<UserBean> getUserList();
	
	/*更新用户信息*/
	public boolean updateUser(UserBean ub);
	
	/*删除用户*/
	public boolean deleteUser(String userId);
	
}
