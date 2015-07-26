package com.culture.dao;

import java.util.List;
import java.util.Map;

import com.culture.model.CulturalBean;

public interface CulturalDao {
	
	//�������
	public boolean addCultural(CulturalBean cb);
	//��������
	public boolean updateCultural(CulturalBean cb);
	//���������б�
	public List<CulturalBean> getCulturalList();
	//ɾ������
	public boolean delCultural(String cbid);
	
}
