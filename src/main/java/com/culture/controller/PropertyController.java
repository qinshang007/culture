package com.culture.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
import com.culture.util.StringUtils;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.ModelFactory;
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
	public void saveProperty(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			OntModel model = omodelFactory.getModel();
			//属性类别,1为对象属性，2为数据属性
			int ptype = Integer.valueOf(request.getParameter("ptype"));
			//获取属性名字
			String pname = request.getParameter("pname");
			//获取父属性名字
			String pfname = request.getParameter("pfname");
			//获取父属性id
			int pfid = Integer.parseInt(request.getParameter("pfid"));
			//获取定义域
			String domain = request.getParameter("domain");
			//获取值域
			String range = request.getParameter("range");
			//是否通用属性
			int isgeneral = Integer.valueOf(request.getParameter("isgeneral"));
			
			OntProperty children = null;
			//为属性添加值域
			if(ptype==1){
				 children = model.createObjectProperty(OModelFactory.NSP+pname);
				 
				//将值域值转化为数组
				String[] rangeArray = range.split(",");
				
				//为属性添加值域
				for(int i=0;i<rangeArray.length;i++){
					OntClass oclass = model.getOntClass(OModelFactory.NSC+rangeArray[i]);
					children.addRange(oclass);
				}
				 
			}else if(ptype==2){
				 children = model.createDatatypeProperty(OModelFactory.NSP+pname);
				 
				 //添加值域
				 int index = Integer.valueOf(range);
				 //获取数据类型
				 Resource s = resource[index];
				 children.addRange(s);
			}
			
			//父属性不为空且不等于无
			if(!StringUtils.isEmpty(pfname)&&!pfname.equals("无")){
				OntProperty parent = model.getOntProperty(OModelFactory.NSP+pfname);//取出父概念
				parent.addSubProperty(children);
			}
			
			//将定义域值转化为数组
			String[] domainArray = domain.split(",");
			
			//为属性添加定义域
			for(int i=0;i<domainArray.length;i++){
				OntClass oclass = model.getOntClass(OModelFactory.NSC+domainArray[i]);
				children.addDomain(oclass);
			}

			
			//write XML FILE
			File file = new File(omodelFactory.getOwlFile());
			try{
				OutputStream out = new FileOutputStream(file);
				model.write(out);
			}catch(Exception e){
				e.printStackTrace();
			}
			//保存到数据库
			OProperty op = new OProperty();
			op.setPname(pname);
			op.setPtype(ptype);
			op.setPfid(pfid);
			op.setIsgeneral(isgeneral);
			opService.addProperty(op);
			
			outputJsonResponse(response, true, "uploadSuccess");
			                                                                                                                                                   
		}catch (RuntimeException e) {
			logger.error("保存属性出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}
	
	/**
	 * 修改属性
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/update.do")
	public void updateProperty(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			OntModel model = omodelFactory.getModel();
			//属性类别,1为对象属性，2为数据属性
			int ptype = Integer.valueOf(request.getParameter("ptype"));
			//获取属性名字
			String pname = request.getParameter("pname");
			//生成属性id
			int pid = Integer.parseInt(request.getParameter("pid"));
			//获取父属性名字
			String pfname = request.getParameter("pfname");
			//获取父属性id
			int pfid = Integer.parseInt(request.getParameter("pfid"));
			//获取定义域
			String domain = request.getParameter("domain");
			//获取值域
			String range = request.getParameter("range");
			
			OntProperty children = null;
			
			if(ptype==1){
				
				 children = model.getObjectProperty(OModelFactory.NSP+pid);
				 
				//获取属性之前的值域，并将这些值域清楚掉
				ExtendedIterator<OntResource> rangeiter = (ExtendedIterator<OntResource>)children.listRange();
				List<OntResource> rangelist = new ArrayList<OntResource>();
				while(rangeiter.hasNext()){
					OntResource rs = rangeiter.next();
					rangelist.add(rs);
				}
				for(int i=0;i<rangelist.size();i++){
					children.removeRange(rangelist.get(i));
				}
				
				//将值域值转化为数组
				String[] rangeArray = range.split(",");
				
				//为属性添加值域
				for(int i=0;i<rangeArray.length;i++){
					OntClass oclass = model.getOntClass(OModelFactory.NSC+rangeArray[i]);
					children.addRange(oclass);
				}

				
			}else if(ptype==2){
				
				 children = model.getDatatypeProperty(OModelFactory.NSP+pid);
				 
				//获得属性之前的值域，并将它清楚掉
				OntResource or = children.getRange();
				children.removeRange(or);
				
				 //添加值域
				 int index = Integer.valueOf(range);
				 //获取数据类型
				 Resource s = resource[index];
				 children.addRange(s);
			}
			
			//获取属性之前的定义域，并将这些定义域清楚掉
			ExtendedIterator<OntResource> domainiter = (ExtendedIterator<OntResource>)children.listDomain();
			List<OntResource> domainlist = new ArrayList<OntResource>();
			while(domainiter.hasNext()){
				OntResource rs = domainiter.next();
				domainlist.add(rs);
			}
			for(int i=0;i<domainlist.size();i++)
				children.removeDomain(domainlist.get(i));
			
			//将定义域值转化为数组
			String[] domainArray = domain.split(",");
			
			//为属性添加定义域
			for(int i=0;i<domainArray.length;i++){
				OntClass oclass = model.getOntClass(OModelFactory.NSC+domainArray[i]);
				children.addDomain(oclass);
			}
			
			//父概念不为空且不等于无
			if(!StringUtils.isEmpty(pfname)&&!pfname.equals("无")){
				//取出以前的父概念，然后从其子概念中删掉
				OntProperty old_parent = children.getSuperProperty();
				if(old_parent!=null)
					old_parent.removeSubProperty(children);
				//取得新的父概念
				OntProperty parent = model.getOntProperty(OModelFactory.NSP+pfname);
				parent.addSubProperty(children);//
			}

			//write XML FILE
			File file = new File(omodelFactory.getOwlFile());
			try{
				OutputStream out = new FileOutputStream(file);
				model.write(out);
			}catch(Exception e){
				e.printStackTrace();
			}
			//保存到数据库
			OProperty op = new OProperty();
			op.setPid(pid);
			op.setPname(pname);
			op.setPtype(ptype);
			op.setPfid(pfid);
			opService.updateProperty(op);
			
			outputJsonResponse(response, true, "updateSuccess");

		}catch (Exception e) {
			logger.error("修改属性出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	/**
	 * 添加对象属性界面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addObjectProperty.do")
	public ModelAndView addObjectProperty(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//获取父概念id
			String pfid = request.getParameter("pfid");
			//获取父概念名字
			String pfname = request.getParameter("pfname");
			//获取概念列表
			OClass oclass = new OClass();
			oclass.setDel(0);
			List<OClass> oclist = ocService.getClassList(oclass);
			//获取属性列表
			List<OProperty> oplist = opService.getPropertyList();
			Map map = new HashMap();
			map.put("pfid",pfid);
			map.put("pfname", pfname);
			map.put("oclist", oclist);
			map.put("oplist", oplist);
			ModelAndView view = new ModelAndView("property/addObjectProperty").addAllObjects(map);
			return view;
		}catch (RuntimeException e) {
			logger.error("添加对象属性界面出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	/**
	 * 添加数据属性界面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addDataProperty.do")
	public ModelAndView addDataProperty(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//获取父概念id
			String pfid = request.getParameter("pfid");
			//获取父概念名字
			String pfname = request.getParameter("pfname");
			//获取概念列表
			OClass oclass = new OClass();
			oclass.setDel(0);
			List<OClass> oclist = ocService.getClassList(oclass);
			//获取属性列表
			List<OProperty> oplist = opService.getPropertyList();
			Map map = new HashMap();
			map.put("pfid",pfid);
			map.put("pfname", pfname);
			map.put("oclist", oclist);
			map.put("oplist", oplist);
			ModelAndView view = new ModelAndView("property/addDataProperty").addAllObjects(map);
			return view;
		}catch (RuntimeException e) {
			logger.error("添加数据属性界面出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	/**
	 * 属性列表界面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/propertyList.do")
	public ModelAndView getPropertyList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//获取属性列表
			List<OProperty> oplist = opService.getPropertyList();
			Map map = new HashMap();
			map.put("oplist", oplist);
			ModelAndView view = new ModelAndView("property/propertyList").addAllObjects(map);
			return view;
		}catch (RuntimeException e) {
			logger.error("返回概念列表出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	/**
	 * 属性详情界面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/viewProperty.do")
	public ModelAndView viewProperty(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			OntModel model = omodelFactory.getModel();
			String pid = request.getParameter("pid");
			//获取属性
			OProperty op = opService.getPropertyById(pid);
			//获取概念列表
			OClass oclass = new OClass();
			oclass.setDel(0);
			List<OClass> domainList = ocService.getClassList(oclass);
			List<OClass> rangeList = ocService.getClassList(oclass);
			//获取属性列表
			List<OProperty> oplist = opService.getPropertyList();
			//获取父属性id
			int pfid = op.getPfid();
			//获取父属性名字
			String pfname = "无";
			for(int i=0;i<oplist.size();i++){
				if(oplist.get(i).getPid() == pfid){
					pfname = oplist.get(i).getPname();
					oplist.remove(i);
					break;
				}
			}
			//取得属性的映射
			Map<Integer,Integer> dmap = new HashMap<Integer,Integer>();
			Map<Integer,Integer> rmap = new HashMap<Integer,Integer>();
			for(int i=0; i<domainList.size();i++){
				dmap.put(domainList.get(i).getCid(), i);
				rmap.put(rangeList.get(i).getCid(), i);
			}

			if(op.getPtype()==1){	//如果是对象属性
				op.setPtype(1);
				ObjectProperty ontop = model.getObjectProperty(OModelFactory.NSP+op.getPid());
				//获取属性的定义域
				ExtendedIterator<OntResource> domainiter = (ExtendedIterator<OntResource>)ontop.listDomain();
				while(domainiter.hasNext()){
					OntResource rs = domainiter.next();
					String uri = rs.toString();
					//这里这样做的目的是因为如果cid是以数字开头的话，会被默认截短一位
					String cid = uri.substring(uri.indexOf('#')+1);
					int index = dmap.get(cid);
					domainList.get(index).setSelected(1);
				}
				op.setDomainList(domainList);
				//获取属性的值域
				ExtendedIterator<OntResource> rangeiter = (ExtendedIterator<OntResource>)ontop.listRange();
				while(rangeiter.hasNext()){
					OntResource rs = rangeiter.next();
					String uri = rs.toString();
					String cid = uri.substring(uri.indexOf('#')+1);
					int index = rmap.get(cid);
					rangeList.get(index).setSelected(1);
				}
				op.setRangeList(rangeList);
			}else if(op.getPtype()==2){ //如果是数据属性
				op.setPtype(2);
				DatatypeProperty dp = model.getDatatypeProperty(OModelFactory.NSP+op.getPid());
				//获取属性的定义域
				ExtendedIterator<OntResource> domainiter = (ExtendedIterator<OntResource>)dp.listDomain();
				while(domainiter.hasNext()){
					OntResource rs = domainiter.next();
					String namespace = rs.toString();
					String cid = namespace.substring(namespace.indexOf('#')+1);
					int index = dmap.get(cid);
					domainList.get(index).setSelected(1);
				}
				op.setDomainList(domainList);
				//获得属性的值域
				OntResource or = dp.getRange();
				for(int i=0;i<resource.length;i++){
					if(or.equals(resource[i]))
						op.setRange(""+i);
				}
			}
			Map map = new HashMap();
			map.put("op", op);
			map.put("oplist", oplist);
			map.put("pfid", pfid);
			map.put("pfname", pfname);
			return new ModelAndView("property/viewProperty").addAllObjects(map);
		}catch (RuntimeException e) {
			logger.error("查看概念详情出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}
	
	
}
