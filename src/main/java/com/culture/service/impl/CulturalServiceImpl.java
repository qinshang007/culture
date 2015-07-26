package com.culture.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.culture.model.CulturalBean;
import com.culture.service.CulturalService;

@Service
public class CulturalServiceImpl extends BaseService implements CulturalService{

	public boolean addCultural(CulturalBean cb) {
		// TODO Auto-generated method stub
		return getCulturalDao().addCultural(cb);
	}

	public boolean updateCultural(CulturalBean cb) {
		// TODO Auto-generated method stub
		return getCulturalDao().updateCultural(cb);
	}

	public List<CulturalBean> getCulturalList() {
		// TODO Auto-generated method stub
		return getCulturalDao().getCulturalList();
	}

	public boolean delCultural(String cbid) {
		// TODO Auto-generated method stub
		return getCulturalDao().delCultural(cbid);
	}

}
