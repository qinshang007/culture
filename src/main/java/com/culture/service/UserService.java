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
	
}
