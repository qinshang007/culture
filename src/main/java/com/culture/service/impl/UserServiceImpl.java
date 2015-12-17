package com.culture.service.impl;

import java.util.List;

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

	/**
	 * 添加用户
	 */
	@Override
	public boolean addUser(UserBean ub) {
		// TODO Auto-generated method stub
		return getUserDao().addUser(ub);
	}

	/**
	 * 返回用户列表
	 */
	@Override
	public List<UserBean> getUserList() {
		// TODO Auto-generated method stub
		return getUserDao().getUserList();
	}

	/**
	 * 更新用户信息
	 */
	@Override
	public boolean updateUser(UserBean ub) {
		// TODO Auto-generated method stub
		return getUserDao().updateUser(ub);
	}

	/**
	 * 根据用户Id返回用户
	 */
	@Override
	public UserBean getUserById(String userId) {
		// TODO Auto-generated method stub
		return getUserDao().getUserById(userId);
	}

	/**
	 * 删除用户
	 */
	@Override
	public boolean deleteUser(String userId) {
		// TODO Auto-generated method stub
		return getUserDao().deleteUser(userId);
	}

}
