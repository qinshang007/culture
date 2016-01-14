package com.culture.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.culture.model.CulturalBean;
import com.culture.model.OClass;
import com.culture.service.CulturalService;
import com.culture.service.InstanceService;
import com.culture.service.OClassService;

@Controller
@RequestMapping(value="/utils")
public class UtilsController extends BaseController{
	
	@Autowired
	private OClassService ocService;
	@Autowired
	private InstanceService instService;
	@Autowired
	private CulturalService cbService;
	
	private static final Logger logger = Logger.getLogger(UtilsController.class);
	
	/**
	 * 知识获取工具
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/obtainUtil.do")
	public ModelAndView obtains(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String type = request.getParameter("type");
			String classification = request.getParameter("classification");
			Map<String,Object> map = new HashMap<String,Object>();
			//获取文物概念的子概念
			List<OClass> oclist = ocService.getChildClass("文物",1);
			List<CulturalBean> instList = cbService.getRecommendList(type, classification, null);
			map.put("oclist", oclist);
			map.put("type", type);
			map.put("instList", instList);
			return new ModelAndView("utils/obtainUtil").addAllObjects(map);
		}catch (RuntimeException e) {
			logger.error("知识获取工具！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}
	
	/**
	 * 知识分析工具
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/analyze.do")
	public ModelAndView analyze(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String type = request.getParameter("type");
			String classification = request.getParameter("classification");
			Map<String,Object> map = new HashMap<String,Object>();
			//获取文物概念的子概念
			List<OClass> oclist = ocService.getChildClass("文物",1);
			List<CulturalBean> instList = cbService.getRecommendList(type, classification, null);
			map.put("oclist", oclist);
			map.put("type", type);
			map.put("classification", classification);
			map.put("instList", instList);
			if(StringUtils.isNotEmpty(type)){	//如果类别不为空
				List<OClass> childlist = ocService.getChildClass(type,1);
				map.put("childlist", childlist);
			}
			return new ModelAndView("utils/analyze").addAllObjects(map);
		}catch (RuntimeException e) {
			logger.error("知识获取工具！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

}
