package com.culture.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.culture.model.CulturalBean;
import com.culture.model.OClass;
import com.culture.model.OModelFactory;
import com.culture.model.UploadFile;
import com.culture.service.CulturalService;
import com.culture.service.InstanceService;
import com.culture.service.OClassService;
import com.culture.service.OPropertyService;
import com.culture.util.CodeGenerator;
import com.culture.util.CommonConst;
import com.culture.util.DateUtils;


@Controller
@RequestMapping(value="/instance")
public class InstanceController extends BaseController{
	
	@Autowired
	private OClassService ocService;
	@Autowired
	private OPropertyService opService;
	@Autowired
	private CulturalService clService;
	@Autowired
	private InstanceService instService;
	@Autowired
	private OModelFactory omodelFactory;

	private static final Logger logger = Logger.getLogger(InstanceController.class);  
	
	/**
	 * 保存实例
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/save.do")
	public void saveInstance(HttpServletRequest request, HttpServletResponse response,CulturalBean cb) throws Exception{
		try{
			//获取主图
			String path = CommonConst.EW_FILE_PATH;
			List<UploadFile> list = upload(request,response, path);
			if(list.size()!=0){
				String mainpic = list.get(0).getFileSrc();
				cb.setMainpic(mainpic);
			}
			//设置创建时间
			Timestamp crtime = Timestamp.valueOf(DateUtils.getCurrDateTimeStr());
			cb.setCrtime(crtime);
			//创建文物id
			String identifier = CodeGenerator.createUUID();
			cb.setIdentifier(identifier);
			//保存文物
			cb.setComplete(0);
			cb.setManager("lyp");
			//将实例保存到数据库
			clService.addCultural(cb);
			//将实例写入本体文件
			instService.addInstance(request, cb);
			outputJsonResponse(response, true,"uploadSuccess");
		}catch (RuntimeException e) {
			logger.error("保存实例出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}
	
	/**
	 * 保存实例
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/edit.do")
	public void editInstance(HttpServletRequest request, HttpServletResponse response,CulturalBean cb) throws Exception{
		try{
			//获取主图
			String path = CommonConst.EW_FILE_PATH;
			List<UploadFile> list = upload(request,response, path);
			if(list.size()!=0){
				String mainpic = list.get(0).getFileSrc();
				cb.setMainpic(mainpic);
			}
			//保存文物
			cb.setComplete(0);
			cb.setManager("lyp");
			//将实例保存到数据库
			clService.updateCultural(cb);
			//获取实例旧的名称
			String oldTitle = request.getParameter("oldTitle");
			//更新本体文件
			instService.editInstance(request, cb, oldTitle);
			outputJsonResponse(response, true,"updateSuccess");
		}catch (RuntimeException e) {
			logger.error("保存实例出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}

	}

	/**
	 * 删除实例
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/del.do")
	public void delInstance(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String culId = request.getParameter("culId");
			String title = request.getParameter("title");
			//更新数据库
			clService.delCultural(culId);
			//更新本体文件
			instService.delInstance(title);
			outputJsonResponse(response, true,"deleteSuccess");
		}catch (RuntimeException e) {
			logger.error("删除实例出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}
	
	/**
	 * 获取二级概念数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getData.do")
	public void getData(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String type = request.getParameter("type");
			//获取文物概念的子概念
			List<OClass> oclist = ocService.getSubClasses(type, true);
			outputJsonResponse(response, oclist);
		}catch (RuntimeException e) {
			logger.error("返回二级概念数据出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}
	
	/**
	 * 返回概念的属性
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/addInstance.do")
	public ModelAndView addInstance(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try{
			//获取一级概念，器物，织物，建筑，壁画
			String type = request.getParameter("type");
			//获取二级概念，也就是细的类别，如瓷器，陶器等
			String classification = request.getParameter("classification");
			//获取创作朝代列表
			OClass oclass = ocService.getClassByName("朝代");
			List<OClass> creationDateList = ocService.getClassList(oclass);
			//将参数存到map里头
			Map map = new HashMap();
			map.put("type", type);
			map.put("classification", classification);
			map.put("creationDateList", creationDateList);
			return new ModelAndView("instance/addInstance").addAllObjects(map);
		}catch (RuntimeException e) {
			logger.error("返回属性出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	/**
	 * 选择概念
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/chooseClass.do")
	public ModelAndView chooseClass(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//获取文物概念的子概念
			List<OClass> oclist = ocService.getSubClasses("文物", true);
			List<OClass> childlist = new ArrayList<OClass>();
			if(oclist.size()!=0){
				//取出第一个父节点
				OClass parent = oclist.get(0);
				String pcname = parent.getCname();
				childlist = ocService.getSubClasses(pcname, true);
			}
			Map map = new HashMap();
			map.put("oclist", oclist);
			map.put("childlist", childlist);
			return new ModelAndView("instance/chooseClass").addAllObjects(map);
			
		}catch (RuntimeException e) {
			logger.error("返回属性出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}
	
	/**
	 * 返回实例列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/instanceList.do")
	public ModelAndView getInstanceList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			List<CulturalBean> cbList = clService.getCulturalList();
			return new ModelAndView("instance/instanceList").addObject("cbList",cbList);
		}catch (RuntimeException e) {
			logger.error("返回实例列表出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}
	
	/**
	 * 修改实例
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editInstance.do")
	public ModelAndView editInstance(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String culId = request.getParameter("culId");
			CulturalBean cb = clService.getCulturalById(culId);
			String type = cb.getType();
			String classification = cb.getClassification();
			//获取创作朝代列表
			OClass oclass = ocService.getClassByName("朝代");
			List<OClass> creationDateList = ocService.getClassList(oclass);
			Map map = new HashMap();
			map.put("cb", cb);
			map.put("type", type);
			map.put("classification", classification);
			map.put("creationDateList", creationDateList);
			return new ModelAndView("instance/editInstance").addAllObjects(map);
		}catch (RuntimeException e) {
			logger.error("修改实例出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}	
}
