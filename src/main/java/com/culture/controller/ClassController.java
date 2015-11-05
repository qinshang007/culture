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
			//获取父概念名字
			String cfname = request.getParameter("cfname");
			//获取父概念id
			String cfid = request.getParameter("cfid");
			
			OntClass children = model.createClass(OModelFactory.NSC+cname);
			
			//如果父概念名字不为空且不为无
			if(!StringUtils.isEmpty(cfname)&&!cfname.equals("无")){
				OntClass parent = model.getOntClass(OModelFactory.NSC+cfname);//取得父概念
				parent.addSubClass(children);//
			}
			
			//写入owl文件
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
			c.setCfid(Integer.valueOf(cfid));
			ocService.addClass(c);
			
			outputJsonResponse(response, true, "uploadSuccess");
			                                                                                                                                                   
		}catch (RuntimeException e) {
			logger.error("保存概念出错：" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	/**
	 * 更新概念
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
			
			//获取该概念的子概念
			OntClass children = model.getOntClass(OModelFactory.NSC+cname);
			
			//如果概念名字不为空且不为无
			if(!StringUtils.isEmpty(cfname)&&!cfname.equals("无")){
				//获取原来的父概念
				OntClass old_parent = children.getSuperClass();
				if(old_parent!=null)
					old_parent.removeSubClass(children);
				//获取新的父概念
				OntClass parent = model.getOntClass(OModelFactory.NSC+cfname);
				parent.addSubClass(children);//
			}
			
			//写入到owl文件
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
			c.setCid(Integer.valueOf(cid));
			c.setCfid(Integer.valueOf(cfid));
			ocService.updateClass(c);
			
			outputJsonResponse(response, true, "updateSuccess");
			                                                                                                                                                   
		}catch (RuntimeException e) {
			logger.error("更新概念出错：" +  ",errMsg=" + e.getMessage());
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
			//如果父概念id不为空
			int cfid = 0;
			if(request.getParameter("cfid")!=null)
				cfid = Integer.parseInt(request.getParameter("cfid"));
			//获取父概念名字
			String cfname = request.getParameter("cfname");
			//获取概念列表
			OClass oclass = new OClass();
			oclass.setDel(0);
			List<OClass> oclist = ocService.getClassList(oclass);
			for(int i=0;i<oclist.size();i++){
				if(oclist.get(i).getCid() == cfid){
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
			logger.error("添加概念界面出错：" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取概念列表
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
			logger.error("获取概念列表出错：" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	/**
	 * 查看概念详情
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/viewClass.do")
	public ModelAndView viewClass(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String cid = request.getParameter("cid");
			//根据概念id获取概念
			OClass oc = ocService.getClassById(cid);
			//获取概念列表
			OClass oclass = new OClass();
			oclass.setDel(0);
			List<OClass> oclist = ocService.getClassList(oclass);
			//获取概念列表
			int cfid = oc.getCfid();
			String cfname = "无";
			for(int i=0;i<oclist.size();i++){
				if(oclist.get(i).getCid() == cfid){
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
			logger.error("查看概念详情出错：" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}
	

	/**
	 * 删除属性
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/delClass.do")
	public void delClass(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String cid = request.getParameter("cid");
			String cname = request.getParameter("cname");
			boolean result = ocService.delClass(cid, cname);
			if(result)
				outputJsonResponse(response, true, "删除成功！");
			else
				outputJsonResponse(response, false, "删除失败！");
		}catch (Exception e) {
			logger.error("删除概念出错：" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	/**
	 * 验证概念名字是否存在
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/isCnameExist.do")
	public void isCnameExist(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String cname = request.getParameter("cname");
			boolean result = ocService.isClassExist(cname);
			if(result)
				outputJsonResponse(response, true, "概念名字存在！");
			else
				outputJsonResponse(response, false, "概念名字不存在！");
		}catch (Exception e) {
			logger.error("验证概念名字是否存在出错：" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

}
