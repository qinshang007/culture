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
import com.culture.service.InstanceService;
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
	@Autowired
	private InstanceService instService;
	
	private int pageSize = 12;
	
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
	 * 获取文物列表数量
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getListCount.asmx")
	public void getListCount(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//获取文物大类，器物，织物，建筑，壁画
			String type = request.getParameter("type");
			//获取文物详细分类
			String classification = request.getParameter("classification");
			//获取朝代
			String creation_date = request.getParameter("creation_date");
			int count = clService.getListCount(type,classification,creation_date);
			outputJsonResponse(response, true,String.valueOf(count));
		}catch (RuntimeException e) {
			logger.error("获取文物列表数量请求出错！" +  ",errMsg=" + e.getMessage());
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
			//获取朝代
			String creation_date = request.getParameter("creation_date");
			//获取页码
			String pageStart = request.getParameter("pageStart");
			int start = (Integer.parseInt(pageStart)-1)*pageSize;
			List<CulturalBean> cbList = clService.getCulturalList(username,type,classification,creation_date,start,pageSize);
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

	/**
	 * 获取朝代列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getDynastyList.asmx")
	public void getDynastyList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//获取朝代列表
			List<OClass> dynastylist = instService.getCreationDateList();
			outputJsonResponse(response, dynastylist);
		}catch (RuntimeException e) {
			logger.error("获取朝代列表请求据出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	
}
