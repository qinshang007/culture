package com.culture.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.culture.dao.impl.OClassDaoImpl;
import com.culture.model.OClass;
import com.culture.model.OModelFactory;
import com.culture.model.OProperty;
import com.culture.service.OClassService;
import com.culture.service.OPropertyService;
import com.culture.util.CodeGenerator;
import com.culture.util.JsonUtils;
import com.culture.util.StringUtils;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;


@Controller
@RequestMapping(value="/class")
public class ClassController extends BaseController{

	@Autowired
	private OClassService ocService;
	@Autowired
	private OModelFactory omodelFactory;

	private static final Logger logger = Logger.getLogger(ClassController.class);  
	
	/**
	 * 保存概念
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/save.do")
	public void saveClass(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			OntModel model = omodelFactory.getModel();
			//获取概念名字
			String cname = request.getParameter("cname");
			//生成概念id
			String cid = CodeGenerator.createUUID();
			//获取父概念名字
			String cfname = request.getParameter("cfname");
			//获取父概念id
			String cfid = request.getParameter("cfid");
			
			OntClass children = model.createClass(OModelFactory.NSC+cname);
			children.setLabel(cname, "zh");
			
			//父概念不为空且不等于无
			if(!StringUtils.isEmpty(cfname)&&!cfname.equals("无")){
				OntClass parent = model.getOntClass(OModelFactory.NSC+cfid);//取出父概念
				parent.addSubClass(children);//
			}
			
			//写到owl文件
			File file = new File(omodelFactory.getOwlFile());
			try{
				OutputStream out = new FileOutputStream(file);
				model.write(out);
			}catch(Exception e){
				e.printStackTrace();
			}
			//保存到数据库
			OClass c = new OClass();
			c.setCname(cname);
			c.setCid(cid);
			c.setCfid(cfid);
			ocService.addClass(c);
			
			outputJsonResponse(response, true, "uploadSuccess");
			                                                                                                                                                   
		}catch (RuntimeException e) {
			logger.error("保存概念出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	/**
	 * 保存概念
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/update.do")
	public void updateClass(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			OntModel model = omodelFactory.getModel();
			//获取概念id
			String cid = request.getParameter("cid");
			//获取概念名字
			String cname = request.getParameter("cname");
			//获取父概念名字
			String cfname = request.getParameter("cfname");
			//获取父概念id
			String cfid = request.getParameter("cfid");
			
			//更改概念label
			OntClass children = model.getOntClass(OModelFactory.NSC+cid);
			children.setLabel(cname, "zh");
			
			//父概念不为空且不等于无
			if(!StringUtils.isEmpty(cfname)&&!cfname.equals("无")){
				//取出以前的父概念，然后从其子概念中删掉
				OntClass old_parent = children.getSuperClass();
				if(old_parent!=null)
					old_parent.removeSubClass(children);
				//取得新的父概念
				OntClass parent = model.getOntClass(OModelFactory.NSC+cfid);
				parent.addSubClass(children);//
			}
			
			//写到owl文件
			File file = new File(omodelFactory.getOwlFile());
			try{
				OutputStream out = new FileOutputStream(file);
				model.write(out);
			}catch(Exception e){
				e.printStackTrace();
			}
			//更新到数据库
			OClass c = new OClass();
			c.setCname(cname);
			c.setCid(cid);
			c.setCfid(cfid);
			ocService.updateClass(c);
			
			outputJsonResponse(response, true, "updateSuccess");
			                                                                                                                                                   
		}catch (RuntimeException e) {
			logger.error("更新概念出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	
	/**
	 * 添加概念界面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addClass.do")
	public ModelAndView addClass(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//获取父概念id
			String cfid = request.getParameter("cfid");
			//获取父概念名字
			String cfname = request.getParameter("cfname");
			//获取概念列表
			OClass oclass = new OClass();
			oclass.setDel(0);
			List<OClass> oclist = ocService.getClassList(oclass);
			for(int i=0;i<oclist.size();i++){
				if(oclist.get(i).getCid().equals(cfid)){
					cfname = oclist.get(i).getCname();
					oclist.remove(i);
					break;
				}
			}
			Map map = new HashMap();
			map.put("cfid",cfid);
			map.put("cfname", cfname);
			map.put("oclist", oclist);
			ModelAndView view = new ModelAndView("class/addClass").addAllObjects(map);
			return view;
		}catch (RuntimeException e) {
			logger.error("添加概念页面出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}
	
	/**
	 * 概念列表界面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/classList.do")
	public ModelAndView getClassList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//获取概念列表
			OClass oclass = new OClass();
			oclass.setDel(0);
			List<OClass> oclist = ocService.getClassList(oclass);
			Map map = new HashMap();
			map.put("oclist", oclist);
			ModelAndView view = new ModelAndView("class/classList").addAllObjects(map);
			return view;
		}catch (RuntimeException e) {
			logger.error("返回概念列表出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	/**
	 * 更改概念界面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/viewClass.do")
	public ModelAndView viewClass(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String cid = request.getParameter("cid");
			//获取概念
			OClass oc = ocService.getClassById(cid);
			//获取概念列表
			OClass oclass = new OClass();
			oclass.setDel(0);
			List<OClass> oclist = ocService.getClassList(oclass);
			//获取父概念
			String cfid = oc.getCfid();
			String cfname = "无";
			for(int i=0;i<oclist.size();i++){
				if(oclist.get(i).getCid().equals(cfid)){
					cfname = oclist.get(i).getCname();
					oclist.remove(i);
					break;
				}
			}
			Map map = new HashMap();
			map.put("oc", oc);
			map.put("oclist", oclist);
			map.put("cfid", cfid);
			map.put("cfname", cfname);
			ModelAndView view = new ModelAndView("class/viewClass").addAllObjects(map);
			return view;
		}catch (RuntimeException e) {
			logger.error("更改概念界面出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}
	
	
	@RequestMapping("/testlog.do")
	public void testlog(HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("info级别测试");
		log.error("测试log4j日志配置！");
	}
	
}
