package com.culture.dao;

import java.util.List;
import java.util.Map;

import com.culture.model.CulturalBean;

public interface CulturalDao {
	
	//添加文物
	public boolean addCultural(CulturalBean cb);
	//更新文物
	public boolean updateCultural(CulturalBean cb);
	//返回文物列表
	public List<CulturalBean> getCulturalList();
	//删除文物
	public boolean delCultural(String cbid);
	
}
