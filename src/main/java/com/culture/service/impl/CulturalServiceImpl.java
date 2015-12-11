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

	/**
	 * 返回文物列表数量
	 */
	@Override
	public int getListCount(String type, String classification, String creation_date) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("type", type);
		map.put("classification", classification);
		map.put("creation_date", creation_date);
		return getCulturalDao().getListCount(map);
	}
	
	public List<CulturalBean> getCulturalList(String userName,String type,String classification,String creation_date,int pageStart,int pageSize) {
		// TODO Auto-generated method stub
		//获取user
		UserBean user = userService.getUserByName(userName);
		int level = user.getLevel();
		Map map = new HashMap();
		map.put("level", level);
		map.put("manager", userName);
		map.put("type", type);
		map.put("classification", classification);
		map.put("creation_date", creation_date);
		map.put("_start", pageStart);
		map.put("_size", pageSize);
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
		return getCulturalDao().isTitleExist(title);
	}


}
