package com.culture.service;

import java.util.List;
import java.util.Map;

import com.culture.model.CulturalBean;

public interface CulturalService {

	//�������
	public boolean addCultural(CulturalBean cb);
	//��������
	public boolean updateCultural(CulturalBean cb);
	//���������б�
	public List<CulturalBean> getCulturalList();
	//ɾ������
	public boolean delCultural(String cbid);
	
}
