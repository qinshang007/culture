package com.culture.service;

import com.culture.model.UserBean;

public interface UserService {
	
	/**
	 * 用户登录
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean login(UserBean user);
	
	/**
	 * 根据用户名称获取用户
	 * @param userName
	 * @return
	 */
	public UserBean getUserByName(String userName);
	
}
