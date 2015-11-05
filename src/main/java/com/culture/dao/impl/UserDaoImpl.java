package com.culture.dao.impl;

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

}
