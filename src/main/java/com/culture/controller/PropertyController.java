package com.culture.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.culture.model.OClass;
import com.culture.model.OModelFactory;
import com.culture.model.OProperty;
import com.culture.service.OClassService;
import com.culture.service.OPropertyService;
import com.culture.util.DateUtils;
import com.culture.util.StringUtils;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.XSD;

@Controller
@RequestMapping(value="/property")
public class PropertyController extends BaseController{
	
	@Autowired
	private OClassService ocService;

	@Autowired
	private OPropertyService opService;
	
	@Autowired
	private OModelFactory omodelFactory;

	Resource[] resource = new Resource[]{XSD.xstring,XSD.xint,XSD.xfloat,XSD.date};
	
	private static final Logger logger = Logger.getLogger(PropertyController.class);  
	
	/**
	 * 保存属性
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/save.do")
	public void saveProperty(HttpServletRequest request, HttpServletResponse response,OProperty op) throws Exception{
		try{
			String username = getUserName(request,response);
			op.setManager(username);
			boolean flag = opService.addProperty(op);
			if(flag)
				outputJsonResponse(response, true, "保存成功");
			else
				outputJsonResponse(response, false, "保存失败");                                                                                                                                             
		}catch (RuntimeException e) {
			logger.error("保存属性出错：" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}
	
	/**
	 * 更新属性
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/update.do")
	public void updateProperty(HttpServletRequest request, HttpServletResponse response,OProperty op) throws Exception{
		try{
			String username = getUserName(request,response);
			op.setManager(username);
			//属性旧的名字
			String oldPname = request.getParameter("oldPname");
			boolean result = opService.updateProperty(op, oldPname);
			if(result)
				outputJsonResponse(response, true, "更新成功！");
			else
				outputJsonResponse(response, false, "更新失败！");

		}catch (Exception e) {
			logger.error("更新属性出错：" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	/**
	 * 删除属性
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/delProperty.do")
	public void delProperty(HttpServletRequest request, HttpServletResponse response,OProperty op) throws Exception{
		try{
			String pid = request.getParameter("pid");
			String pname = request.getParameter("pname");
			boolean result = opService.delProperty(pid, pname);
			if(result)
				outputJsonResponse(response, true, "删除成功！");
			else
				outputJsonResponse(response, false, "删除失败！");
		}catch (Exception e) {
			logger.error("删除属性出错：" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}
	
	/**
	 * 添加对象属性
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addObjectProperty.do")
	public ModelAndView addObjectProperty(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//获取父属性id
			String pfid = request.getParameter("pfid");
			//获取父属性名字
			String pfname = request.getParameter("pfname");
			//获取概念列表
			OClass oclass = new OClass();
			oclass.setDel(0);
			List<OClass> oclist = ocService.getClassList(oclass);
			//获取属性列表
			List<OProperty> oplist = opService.getPropertyList("");
			Map map = new HashMap();
			map.put("pfid",pfid);
			map.put("pfname", pfname);
			map.put("oclist", oclist);
			map.put("oplist", oplist);
			ModelAndView view = new ModelAndView("property/addObjectProperty").addAllObjects(map);
			return view;
		}catch (RuntimeException e) {
			logger.error("添加对象属性出错：" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	/**
	 * 添加数据属性页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addDataProperty.do")
	public ModelAndView addDataProperty(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//获取父属性id
			String pfid = request.getParameter("pfid");
			//获取父属性名字
			String pfname = request.getParameter("pfname");
			//获取概念列表
			OClass oclass = new OClass();
			oclass.setDel(0);
			List<OClass> oclist = ocService.getClassList(oclass);
			//获取属性列表
			List<OProperty> oplist = opService.getPropertyList("");
			Map map = new HashMap();
			map.put("pfid",pfid);
			map.put("pfname", pfname);
			map.put("oclist", oclist);
			map.put("oplist", oplist);
			ModelAndView view = new ModelAndView("property/addDataProperty").addAllObjects(map);
			return view;
		}catch (RuntimeException e) {
			logger.error("添加数据属性页面出错：" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	/**
	 * 获取属性列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/propertyList.do")
	public ModelAndView getPropertyList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String userName = getUserName(request, response);
			//获取属性列表
			List<OProperty> oplist = opService.getPropertyList(userName);
			Map map = new HashMap();
			map.put("oplist", oplist);
			ModelAndView view = new ModelAndView("property/propertyList").addAllObjects(map);
			return view;
		}catch (RuntimeException e) {
			logger.error("获取属性列表界面出错：" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	/**
	 * 查看属性详情
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/viewProperty.do")
	public ModelAndView viewProperty(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String pid = request.getParameter("pid");
			//根据属性id获取属性
			OProperty op = opService.getPropertyById(Integer.valueOf(pid));
			//获取概念列表
			OClass oclass = new OClass();
			oclass.setDel(0);
			List<OClass> oclist = ocService.getClassList(oclass);
			//获取属性列表
			List<OProperty> oplist = opService.getPropertyList("");

			Map map = new HashMap();
			map.put("op", op);
			map.put("oplist", oplist);
			map.put("oclist", oclist);
			return new ModelAndView("property/viewProperty").addAllObjects(map);
		}catch (RuntimeException e) {
			logger.error("查看属性详情出错：" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}
	
	/**
	 * 根据属性名字获取属性信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getPropertyByName.do")
	public void getPropertyByName(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String pname = request.getParameter("pname");			
			//获取文物概念的子概念
			OProperty op = opService.getPropertyByName(pname,true);
			outputJsonResponse(response, op);
		}catch (RuntimeException e) {
			logger.error("返回二级概念数据出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}
	
	/**
	 * 验证属性名字是否存在
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/isPnameExist.do")
	public void isPnameExist(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String pname = request.getParameter("pname");
			boolean result = opService.isPropertyExist(pname);
			if(result)
				outputJsonResponse(response, true, "属性名字存在！");
			else
				outputJsonResponse(response, false, "属性名字不存在！");
		}catch (Exception e) {
			logger.error("验证属性名字是否存在出错：" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	
}
