package com.culture.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.culture.model.OClass;
import com.culture.service.OClassService;

/**
 * 接口服务
 * @author Administrator
 *
 */
@Controller("webservice")
public class InterfaceController extends BaseController{
	
	@Autowired
	private OClassService ocService;
	
	private static final Logger logger = Logger.getLogger(InterfaceController.class);
	
	/**
	 * 获取二级概念数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getData.asmx")
	public void getData(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String type = request.getParameter("type");
			//获取文物概念的子概念
			List<OClass> oclist = ocService.getChildClass(type,1);
			outputJsonResponse(response, oclist);
		}catch (RuntimeException e) {
			logger.error("返回二级概念数据出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	
}
