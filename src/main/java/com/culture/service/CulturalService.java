package com.culture.service;

import java.util.List;
import java.util.Map;

import com.culture.model.CulturalBean;

public interface CulturalService {

	//添加文物
	public boolean addCultural(CulturalBean cb);
	//更新文物
	public boolean updateCultural(CulturalBean cb);
	//返回文物列表数量
	public int getListCount(String userName,String title,String type,String classification,String creation_date);
	//返回文物列表
	public List<CulturalBean> getCulturalList(String userName,String title,String type,String classification,String creation_date,int pageStat,int pageSize);
	//删除文物
	public boolean delCultural(String cbid);
	//返回文物详情
	public CulturalBean getCulturalById(String culId);
	/*判断文物名字是否存在*/
	public boolean isTitleExist(String title);
	//返回推荐文物列表
	public List<CulturalBean> getRecommendList(String type,String classification,String culId);
	//更新文物点击量
	public boolean updateSernum(String culId);

	
}
