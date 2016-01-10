package com.culture.controller;

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

import com.culture.model.OClass;
import com.culture.service.AnalyzeService;
import com.culture.service.OClassService;

@Controller
@RequestMapping(value="/analyze")
public class AnalyzeController extends BaseController{
	
	@Autowired
	private AnalyzeService analyzeService;
	
	@Autowired
	private OClassService ocService;
	
	private static final Logger logger = Logger.getLogger(AnalyzeController.class);
	
	/**
	 * 统计属性的完整度
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getIntegrity.do")
	public ModelAndView getIntegrity(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String type = request.getParameter("type");
			String classification = request.getParameter("classification");
			Map<String,Object> map = analyzeService.getIntegrity(type,classification);
			//获取文物概念的子概念
			List<OClass> oclist = ocService.getChildClass("文物",1);
			map.put("oclist", oclist);
			map.put("type", type);
			map.put("classification", classification);
			if(StringUtils.isNotEmpty(type)){	//如果类别不为空
				List<OClass> childlist = ocService.getChildClass(type,1);
				map.put("childlist", childlist);
			}
			return new ModelAndView("analyze/integrity").addAllObjects(map);
		}catch (RuntimeException e) {
			logger.error("统计某个属性的完整性出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

}
