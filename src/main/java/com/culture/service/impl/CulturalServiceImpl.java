package com.culture.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culture.model.CulturalBean;
import com.culture.model.TopSimilar;
import com.culture.model.UserBean;
import com.culture.service.CulturalService;
import com.culture.service.UserService;
import com.system.import_data.ReadExcel;

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
	public int getListCount(String userName,String title,String type, String classification, String creation_date) {
		// TODO Auto-generated method stub
		UserBean user = userService.getUserByName(userName);
		int level = user.getLevel();
		Map map = new HashMap();
		map.put("level", level);
		map.put("manager", userName);
		map.put("type", type);
		map.put("classification", classification);
		map.put("creation_date", creation_date);
		map.put("title", title);
		return getCulturalDao().getListCount(map);
	}
	
	public List<CulturalBean> getCulturalList(String userName,String title,String type,String classification,String creation_date,int pageStart,int pageSize) {
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
		map.put("title", title);
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

	/**
	 * 返回知识推荐列表
	 */
	@Override
	public List<CulturalBean> getRecommendList(String type,String classification,String culId) {
		// TODO Auto-generated method stub
		List<CulturalBean> cblist = null;
		Map map = new HashMap();
		map.put("type", type);
		map.put("classification", classification);
		cblist =  getCulturalDao().getRecommendList(map);
		if(culId != null && cblist != null){
		}
		return cblist;
	}

	/**
	 * 更新文物点击量
	 */
	@Override
	public boolean updateSernum(String culId) {
		// TODO Auto-generated method stub
		return getCulturalDao().updateSernum(culId);
	}

	/**
	 * 批量导入文物数据
	 */
	@Override
	public List<CulturalBean> addCultualList(String xmlPath, String type, String username) {
		// TODO Auto-generated method stub
		List<CulturalBean> cblist = null;
		try {
			cblist = new ReadExcel().readExcel(xmlPath,type,username);
			if(cblist != null)
				getCulturalDao().addCulturalList(cblist);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cblist;
	}

	/**
	 * 根据文物id获取最相似的十件文物
	 */
	@Override
	public List<CulturalBean> getTopSimilar(String identifier) {
		// TODO Auto-generated method stub
		List<CulturalBean> topSimilar = new ArrayList<CulturalBean>();
		TopSimilar top = getCulturalDao().getTopSimilar(identifier);
		if(top != null){
			String topsimilar = top.getTopSimilar();
			String[] tops = topsimilar.split(",");
			HashMap<String,String> map = new HashMap<String,String>();
			for(int i=0;i<tops.length;i++){
				String id = tops[i];
				CulturalBean cb = getCulturalDao().getCulturalById(id);
				String title = cb.getTitle();
				if(cb != null && map.get(title) == null){
					map.put(title, "exist");
					topSimilar.add(cb);
				}
			}
		}
		return topSimilar;
	}


}
