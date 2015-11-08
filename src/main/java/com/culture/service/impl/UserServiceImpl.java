package com.culture.service.impl;

import org.springframework.stereotype.Service;

import com.culture.model.UserBean;
import com.culture.service.UserService;

@Service
public class UserServiceImpl extends BaseService implements UserService{

	/**
	 * 用户登录
	 */
	public boolean login(UserBean user) {
		// TODO Auto-generated method stub
		boolean flag = true;
		flag = getUserDao().checkUser(user);
		return flag;
	}

	/**
	 * 根据用户名获取用户名称
	 */
	public UserBean getUserByName(String userName) {
		// TODO Auto-generated method stub
		return getUserDao().getUserByName(userName);
	}

}
