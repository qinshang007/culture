package com.culture.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.culture.model.CulturalBean;
import com.culture.model.OClass;
import com.culture.service.CulturalService;
import com.culture.service.OClassService;

/**
 * 接口服务
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/webservice")
public class InterfaceController extends BaseController{
	
	@Autowired
	private OClassService ocService;
	@Autowired
	private CulturalService clService;
	
	
	private static final Logger logger = Logger.getLogger(InterfaceController.class);
	
	/**
	 * 获取文物类别的详细分类
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getConcretType.asmx")
	public void getData(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String type = request.getParameter("type");
			//获取文物类别概念的子概念
			List<OClass> oclist = ocService.getChildClass(type,1);
			outputJsonResponse(response, oclist);
		}catch (RuntimeException e) {
			logger.error("获取文物类别的详细分类请求据出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}
	
	/**
	 * 获取文物列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getCultureList.asmx")
	public void getCultureList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String username = "admin";
			//获取文物大类，器物，织物，建筑，壁画
			String type = request.getParameter("type");
			//获取文物详细分类
			String classification = request.getParameter("classification");
			List<CulturalBean> cbList = clService.getCulturalList(username,type,classification);
			outputJsonResponse(response, cbList);
		}catch (RuntimeException e) {
			logger.error("获取文物列表请求出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}
	

	/**
	 * 获取文物详情接口
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCultureInfo.asmx")
	public void getCultureInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String culId = request.getParameter("culId");
			CulturalBean cb = clService.getCulturalById(culId);
			outputJsonResponse(response,cb);
		}catch (RuntimeException e) {
			logger.error("获取文物详情请求出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	
}
