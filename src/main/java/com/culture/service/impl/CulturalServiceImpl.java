package com.culture.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culture.model.CulturalBean;
import com.culture.model.UserBean;
import com.culture.service.CulturalService;
import com.culture.service.UserService;

@Service
public class CulturalServiceImpl extends BaseService implements CulturalService{
	
	private static final Logger logger = Logger.getLogger(CulturalServiceImpl.class);
	
	@Autowired
	private UserService userService;

	public boolean addCultural(CulturalBean cb) {
		// TODO Auto-generated method stub
		return getCulturalDao().addCultural(cb);
	}

	public boolean updateCultural(CulturalBean cb) {
		// TODO Auto-generated method stub
		return getCulturalDao().updateCultural(cb);
	}

	public List<CulturalBean> getCulturalList(String userName) {
		// TODO Auto-generated method stub
		//获取user
		UserBean user = userService.getUserByName(userName);
		int level = user.getLevel();
		Map map = new HashMap();
		map.put("level", level);
		map.put("manager", userName);
		return getCulturalDao().getCulturalList(map);
	}

	public boolean delCultural(String cbid) {
		// TODO Auto-generated method stub
		return getCulturalDao().delCultural(cbid);
	}

	public CulturalBean getCulturalById(String culId) {
		// TODO Auto-generated method stub
		return getCulturalDao().getCulturalById(culId);
	}

	/**
	 * 判断文物名字是否存在
	 */
	public boolean isTitleExist(String title) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try{
			CulturalBean cb = getCulturalDao().getCulturalByName(title);
			if(cb != null)
				flag = true;
		}catch(Exception e){
			logger.error("验证文物名字是否存在出错："+e.getMessage());
		}
		return flag;

	}

}
