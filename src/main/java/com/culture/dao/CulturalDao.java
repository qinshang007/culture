package com.culture.dao;

import java.util.List;
import java.util.Map;

import com.culture.model.CulturalBean;

public interface CulturalDao {
	
	//添加文物
	public boolean addCultural(CulturalBean cb);
	//更新文物
	public boolean updateCultural(CulturalBean cb);
	//返回文物列表数量
	public int getListCount(Map map);
	//返回文物列表
	public List<CulturalBean> getCulturalList(Map map);
	//删除文物
	public boolean delCultural(String cbid);
	//返回文物详情
	public CulturalBean getCulturalById(String culId);
	/*根据文物名字返回文物*/
	public CulturalBean getCulturalByName(String title);
	/*判断文物名字是否存在*/
	public boolean isTitleExist(String title);
	
}
