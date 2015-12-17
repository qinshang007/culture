package com.culture.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.culture.dao.BaseDao;
import com.culture.dao.UserDao;
import com.culture.model.UserBean;

public class UserDaoImpl extends BaseDao implements UserDao{
	
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

	/**
	 * 验证用户
	 */
	public boolean checkUser(UserBean ub) {
		// TODO Auto-generated method stub
		int object = 0;
		try{
			object = (Integer)getSqlMapClientTemplate().queryForObject("checkUser",ub);
		}catch (Exception e) {
			logger.error("验证用户出错！" +  ",errMsg=" + e.getMessage());
		}
		if(object==1)
			return true;
		else
			return false;
	}

	/**
	 * 根据用户id获取用户
	 */
	public UserBean getUserById(String userId) {
		// TODO Auto-generated method stub
		UserBean user = null;
		try{
			user = (UserBean)getSqlMapClientTemplate().queryForObject("getUserById",userId);
		}catch (Exception e) {
			logger.error("根据用户Id获取用户出错！" +  ",errMsg=" + e.getMessage());
		}
		return user;
	}

	/**
	 * 根据用户姓名获取用户
	 */
	public UserBean getUserByName(String userName) {
		// TODO Auto-generated method stub
		UserBean user = null;
		try{
			user = (UserBean)getSqlMapClientTemplate().queryForObject("getUserByName",userName);
		}catch (Exception e) {
			logger.error("根据用户名称获取用户出错！" +  ",errMsg=" + e.getMessage());
		}
		return user;

	}

	/**
	 * 添加用户
	 */
	@Override
	public boolean addUser(UserBean ub) {
		// TODO Auto-generated method stub
		int object = -1;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().insert("addUser",ub);
		}catch (Exception e) {
			logger.error("添加用户出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != -1) {
			flag = true;
		}
		return flag;

	}

	@Override
	public List<UserBean> getUserList() {
		// TODO Auto-generated method stub
		List<UserBean> userList = null;
		try{
			userList = getSqlMapClientTemplate().queryForList("getUserList");
		}catch (Exception e) {
			logger.error("获取用户列表信息出错！" +  ",errMsg=" + e.getMessage());
		}
		return userList;

	}

	/**
	 * 更新用户信息
	 */
	@Override
	public boolean updateUser(UserBean ub) {
		// TODO Auto-generated method stub
		int object = 0;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().update("updateUser",ub);
		}catch (Exception e) {
			logger.error("更新用户出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != 0) {
			flag = true;
		}
		return flag;

	}

	/**
	 * 删除用户
	 */
	@Override
	public boolean deleteUser(String userId) {
		// TODO Auto-generated method stub
		int object = 0;
		boolean flag = false;
		try {
			object =(Integer) getSqlMapClientTemplate().update("deleteUser",userId);
		}catch (Exception e) {
			logger.error("删除用户出错！" +  ",errMsg=" + e.getMessage());
		}
		if (object != 0) {
			flag = true;
		}
		return flag;
	}

}
