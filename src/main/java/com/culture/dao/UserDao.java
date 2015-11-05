package com.culture.dao;

import com.culture.model.UserBean;

public interface UserDao {
	
	/*判断用户是否存在*/
	public boolean checkUser(UserBean ub);
	
	/*根据用户id获取用户*/
	public UserBean getUserById(String userId);
	
	/*根据用户名称获取用户*/
	public UserBean getUserByName(String userName);
	
}
