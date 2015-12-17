package com.culture.service;

import java.util.List;

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
	
	/**
	 * 根据userId返回用户
	 * @param userId
	 * @return
	 */
	public UserBean getUserById(String userId);
	
	/**
	 * 添加用户
	 * @param ub
	 * @return
	 */
	public boolean addUser(UserBean ub);
	
	/**
	 * 返回用户列表
	 * @return
	 */
	public List<UserBean> getUserList();

	/**
	 * 更新用户信息
	 * @param ub
	 * @return
	 */
	public boolean updateUser(UserBean ub);

	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	public boolean deleteUser(String userId);
}
