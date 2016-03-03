package com.culture.controller;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.culture.model.AsoRule;
import com.culture.model.OProperty;
import com.culture.model.ORule;
import com.culture.service.OPropertyService;
import com.culture.service.ORuleService;
import com.culture.util.DateUtils;

@Controller
@RequestMapping(value="/rule")
public class RuleController extends BaseController{

	@Autowired
	private ORuleService orService;
	@Autowired
	private OPropertyService opService;

	private final String CONTENT_IF = "如果文物A的";
	private final String CONTENT_THEN = "那么文物A的";
	private final String CONTENT_IS = "是";
	private int pageSize = 5;
	private static final Logger logger = Logger.getLogger(RuleController.class);
	
	/**
	 * 保存规则
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/save.do")
	public void save(HttpServletRequest request, HttpServletResponse response,ORule orule) throws Exception{
		try{
			//规则类型，1表示一元规则，2表示二元规则
			String rcontent = "";
			String opBodyType1 = request.getParameter("opBodyType1");	//条件属性1的类型
			String opBodyType2 = request.getParameter("opBodyType2");	//条件属性2的类型
			String opHeadType = request.getParameter("opHeadType");		//结论属性类型
			if(orule.getType() == 1){	//一元规则
				 rcontent = CONTENT_IF+orule.getOpBodyName1()+CONTENT_IS+orule.getOpBodyValue1()+","+CONTENT_THEN+orule.getOpHeadName()+CONTENT_IS+orule.getOpHeadValue();
			}else if(orule.getType() == 2){ //二元规则
				 rcontent = CONTENT_IF+orule.getOpBodyName1()+CONTENT_IS+orule.getOpBodyValue1()+","+orule.getOpBodyName2()+CONTENT_IS+orule.getOpBodyValue2()+","+CONTENT_THEN+orule.getOpHeadName()+CONTENT_IS+orule.getOpHeadValue();
			}
			orule.setRcontent(rcontent);
			orule.setTime(Timestamp.valueOf(DateUtils.getCurrDateTimeStr()));
			boolean result = orService.addRule(orule, opBodyType1, opBodyType2, opHeadType);
			if(result)
				outputJsonResponse(response, true, "添加成功！");
			else
				outputJsonResponse(response, false, "添加失败！");
		}catch (RuntimeException e) {
			logger.error("添加规则出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}
	
	/**
	 * 添加规则页面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/addRule.do")
	public ModelAndView addRule(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String type = request.getParameter("type");
			List<OProperty> opList = opService.getRulePropertys();
			OProperty first  = opList.get(0);
			Map map = new HashMap();
			map.put("opList", opList);
			map.put("first", first);
			if(type.equals("1"))	//添加一元规则
				return new ModelAndView("rule/addSingleRule").addAllObjects(map);
			else if(type.equals("2"))	//添加二元规则
				return new ModelAndView("rule/addDoubleRule").addAllObjects(map);
			else
				return null;
		}catch (RuntimeException e) {
			logger.error("返回规则列表出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	/**
	 * 删除规则
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/delRule.do")
	public void delRule(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String rid = request.getParameter("rid");
			boolean result = orService.delRule(Integer.valueOf(rid));
			if(result)
				outputJsonResponse(response, true, "删除成功！");
			else
				outputJsonResponse(response, false, "删除失败！");
		}catch (RuntimeException e) {
			logger.error("返回规则列表出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	
	/**
	 * 添加规则页面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/ruleList.do")
	public ModelAndView getRuleList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//获取页码
			String pageStart = request.getParameter("pageStart");
			int start = (Integer.parseInt(pageStart)-1)*pageSize;
			int count = orService.getListCount();
			String url = "/culture/rule/ruleList.do?pageStart=";
			List<ORule> orList = orService.getRuleList(0,start,pageSize);
			Map map = new HashMap();
			map.put("orList", orList);
			map.put("count", count);
			map.put("now", pageStart);
			map.put("url", url);
			return new ModelAndView("rule/ruleList").addAllObjects(map);
		}catch (RuntimeException e) {
			logger.error("返回规则列表出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	/**
	 * 生成本体属性文件
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/genRule.do")
	public ModelAndView genRule(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			List<AsoRule> arList = new ArrayList<AsoRule>();
			Map<String,Integer> result = orService.genRules();
	        DecimalFormat    df   = new DecimalFormat("######0.00");   
	        Iterator iter = result.entrySet().iterator();
	        String str = "";
	        while(iter.hasNext()){
	        	Entry entry = (Entry)iter.next();
	        	String key = (String)entry.getKey();
	        	int val = (Integer)entry.getValue();
	        	String[] keys = key.split(",");
	        	if(keys.length == 2){
	        		String start = keys[0];	
	        		int startValue = result.get(start);
	        		double confidence = (double)val/startValue;
	        		confidence *= 100;
	        		str = "{"+keys[0]+"}-->{"+keys[1]+"}";
	        		AsoRule ar = new AsoRule();
	        		ar.setCondition(keys[0]);
	        		ar.setConclusion(keys[1]);
	        		ar.setRule(str);
	        		ar.setConfidence(df.format(confidence)+"%");
	        		arList.add(ar);
	        	}
	        }
	        return new ModelAndView("analyze/asoRuleList").addObject("arList", arList);
		}catch (Exception e) {
			logger.error("生成频繁项集出错：" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	
}
