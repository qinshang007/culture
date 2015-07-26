package com.culture.service;

import javax.servlet.http.HttpServletRequest;

import com.culture.model.CulturalBean;

public interface InstanceService {
	
	public void addInstance(HttpServletRequest request,CulturalBean cb,String classifcationId);
	
}
