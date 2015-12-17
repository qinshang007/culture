package com.culture.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.culture.model.Instance;
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
	private CulturalService clService;
	@Autowired
	private InstanceService instService;
	@Autowired
	private OModelFactory omodelFactory;
	
	private int pageSize = 5;

	private static final Logger logger = Logger.getLogger(InstanceController.class);  
	
	//文物等级
	List<String> levelList1 = new ArrayList<String>(){{add("一级文物");add("二级文物");add("三级文物");add("一般文物");add("未定级文物");}};
	List<String> levelList2 = new ArrayList<String>(){{add("全国重点文物保护单位");add("省级文物保护单位");add("市，县文物保护单位");add("世界文化遗产");}};
	
	/**
	 * 保存实例到数据库
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/saveCultural.do")
	public void saveCultural(HttpServletRequest request, HttpServletResponse response,CulturalBean cb) throws Exception{
		try{
			String username = getUserName(request,response);
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
			cb.setIsCheck(0);
			cb.setManager(username); 
			//将实例保存到数据库
			boolean result = clService.addCultural(cb);
			if(result)
				outputJsonResponse(response, true,cb.getIdentifier());
			else
				outputJsonResponse(response, false,"保存失败！");
		}catch (RuntimeException e) {
			logger.error("保存实例出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}
	
	/**
	 * 保存实例到本体文件
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/saveInstance.do")
	public void saveInstance(HttpServletRequest request, HttpServletResponse response,Instance cb) throws Exception{
		try{
			Map<String,String> valueMap = new HashMap<String,String>();
			valueMap.put("名称", cb.getTitle());
			valueMap.put("其他名称", cb.getUsed_title());
			valueMap.put("创作朝代", cb.getCreation_date());
			valueMap.put("创作者", cb.getCreator());
			valueMap.put("器型", cb.getShape());
			valueMap.put("纹饰", cb.getPattern());
			valueMap.put("颜色", cb.getColor());
			valueMap.put("结构", cb.getStructure());
			valueMap.put("使用情境", cb.getScene());
			valueMap.put("象征意义", cb.getSymbolic_meaning());
			valueMap.put("审美", cb.getAesthetic_desc());
			//将实例写入本体文件
			boolean result = instService.addInstance(valueMap, cb);
			if(result)
				outputJsonResponse(response, true,"保存成功！");
			else
				outputJsonResponse(response, false,"保存失败！");
		}catch (RuntimeException e) {
			logger.error("保存实例出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	
	/**
	 * 更改数据库中的实例
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/updateCultural.do")
	public void updateCultural(HttpServletRequest request, HttpServletResponse response,CulturalBean cb) throws Exception{
		try{
			String username = getUserName(request,response);
			//获取主图
			String path = CommonConst.EW_FILE_PATH;
			List<UploadFile> list = upload(request,response, path);
			if(list.size()!=0){
				String mainpic = list.get(0).getFileSrc();
				cb.setMainpic(mainpic);
			}
			//保存文物
			cb.setIsCheck(0);
			cb.setManager(username);
			//将实例保存到数据库
			boolean result = clService.updateCultural(cb);
			if(result){
				outputJsonResponse(response, true,"更改成功！");
			}else{
				outputJsonResponse(response, false,"更改失败！");
			}
		}catch (RuntimeException e) {
			logger.error("更新数据库实例出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}

	}
	
	/**
	 * 更改本体中的实例
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/updateInstance.do")
	public void updateInstance(HttpServletRequest request, HttpServletResponse response,Instance cb) throws Exception{
		try{
			Map<String,String> valueMap = new HashMap<String,String>();
			valueMap.put("名称", cb.getTitle());
			valueMap.put("其他名称", cb.getUsed_title());
			valueMap.put("创作朝代", cb.getCreation_date());
			valueMap.put("创作者", cb.getCreator());
			valueMap.put("器型", cb.getShape());
			valueMap.put("纹饰", cb.getPattern());
			valueMap.put("颜色", cb.getColor());
			valueMap.put("结构", cb.getStructure());
			valueMap.put("使用情境", cb.getScene());
			valueMap.put("象征意义", cb.getSymbolic_meaning());
			valueMap.put("审美", cb.getAesthetic_desc());
			//获取实例旧的名称
			String oldTitle = request.getParameter("oldTitle");
			valueMap.put("oldTitle", oldTitle);
			//更新本体文件
			instService.editInstance(valueMap, cb);
			outputJsonResponse(response, true,"更新成功！");
		}catch (RuntimeException e) {
			logger.error("更新本体实例出错！" +  ",errMsg=" + e.getMessage());
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
			instService.delInstance(culId,title);
			outputJsonResponse(response, true,"删除实例成功！");
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
//			List<OClass> oclist = ocService.getSubClasses(type, true);
			List<OClass> oclist = ocService.getChildClass(type,1);
			outputJsonResponse(response, oclist);
		}catch (RuntimeException e) {
			logger.error("返回二级概念数据出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}
	
	/**
	 * 获取朝代概念数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getDynasty.do")
	public void getDynasty(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//获取创作朝代列表
			OClass oclass = ocService.getClassByName("朝代");
			List<OClass> creationDateList = ocService.getClassList(oclass);
			outputJsonResponse(response, creationDateList);
		}catch (RuntimeException e) {
			logger.error("返回朝代概念数据出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	
	/**
	 * 添加实例页面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/addCultural.do")
	public ModelAndView addCultural(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try{
			//获取一级概念，器物，织物，建筑，壁画
			String type = request.getParameter("type");
			//获取二级概念，也就是细的类别，如瓷器，陶器等
			String classification = request.getParameter("classification");
			//获取创作朝代列表
			List<OClass> creationDateList = instService.getCreationDateList();
			//获取文物级别列表
			List<String> levelList = null;
			if(type.equals("建筑"))
				levelList = levelList2;
			else
				levelList = levelList1;
			//将参数存到map里头
			Map map = new HashMap();
			map.put("type", type);
			map.put("classification", classification);
			map.put("creationDateList", creationDateList);
			map.put("levelList", levelList);
			return new ModelAndView("instance/addCultural").addAllObjects(map);
		}catch (RuntimeException e) {
			logger.error("返回属性出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	/**
	 * 添加实例页面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/addInstance.do")
	public ModelAndView addInstance(HttpServletRequest request, HttpServletResponse response,CulturalBean cb) throws Exception{
		try{
			Map map = instService.getInstanceMap();
			map.put("cb",  cb);
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
//			List<OClass> oclist = ocService.getSubClasses("文物", true);
			List<OClass> oclist = ocService.getChildClass("文物",1);
			List<OClass> childlist = new ArrayList<OClass>();
			if(oclist.size()!=0){
				//取出第一个父节点
				OClass parent = oclist.get(0);
				String pcname = parent.getCname();
//				childlist = ocService.getSubClasses(pcname, true);
				childlist = ocService.getChildClass(pcname,1);
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
	 * 返回文物实例列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/culturalList.do")
	public ModelAndView getCulturalList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String username = getUserName(request,response);
			//获取名称
			String title = request.getParameter("title");
			//获取页码
			String pageStart = request.getParameter("pageStart");
			int start = (Integer.parseInt(pageStart)-1)*pageSize;
			int count = clService.getListCount(username,title,null,null,null);
			List<CulturalBean> cbList = clService.getCulturalList(username,title,null,null,null,start,pageSize);
			String url = "/culture/instance/culturalList.do?pageStart=";
			if(StringUtils.isNotEmpty(title)){
				url = "/culture/instance/culturalList.do?title="+title+"&pageStart=";
			}
			Map map = new HashMap();
			map.put("cbList", cbList);
			map.put("count", count);
			map.put("now", pageStart);
			map.put("url", url);
			return new ModelAndView("instance/culturalList").addAllObjects(map);
		}catch (RuntimeException e) {
			logger.error("返回文物实例列表出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}
	
	/**
	 * 返回本体实例列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/instanceList.do")
	public ModelAndView getInstanceList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String username = getUserName(request,response);
			//获取名称
			String title = request.getParameter("title");
			//获取页码
			String pageStart = request.getParameter("pageStart");
			int start = (Integer.parseInt(pageStart)-1)*pageSize;
			int count = instService.getInstanceCount(title);
			List<Instance> instList = instService.getInstanceList(title, start, pageSize);
			String url = "/culture/instance/instanceList.do?pageStart=";
			if(StringUtils.isNotEmpty(title)){
				url = "/culture/instance/instanceList.do?title="+title+"&pageStart=";
			}
			Map map = new HashMap();
			map.put("instList", instList);
			map.put("count", count);
			map.put("now", pageStart);
			map.put("url", url);
			return new ModelAndView("instance/instanceList").addAllObjects(map);
		}catch (RuntimeException e) {
			logger.error("返回本体实例列表出错！" +  ",errMsg=" + e.getMessage());
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
	@RequestMapping("/editCultural.do")
	public ModelAndView editCultural(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String culId = request.getParameter("culId");
			CulturalBean cb = clService.getCulturalById(culId);
			String type = cb.getType();
			String classification = cb.getClassification();
			//获取创作朝代列表
			List<OClass> creationDateList = instService.getCreationDateList();
			//文物级别
			List<String> levelList = null;
			if(type.equals("建筑"))
				levelList = levelList2;
			else
				levelList = levelList1;
			Map map = new HashMap();
			map.put("cb", cb);
			map.put("type", type);
			map.put("classification", classification);
			map.put("creationDateList", creationDateList);
			map.put("levelList", levelList);
			return new ModelAndView("instance/editCultural").addAllObjects(map);
		}catch (RuntimeException e) {
			logger.error("修改实例出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}
	
	/**
	 * 修改实例页面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/editInstance.do")
	public ModelAndView editInstance(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//获取本体Id
			String culId = request.getParameter("culId");
			//获取文物实例
			CulturalBean cb = clService.getCulturalById(culId);
			//获取instance
			Instance instacne = instService.getInstanceById(culId);
			//获取旧的名称
			String oldTitle = request.getParameter("oldTitle");
			Map map = instService.getInstanceMap();
			map.put("cb",  cb);
			map.put("oldTitle", oldTitle);
			map.put("instance", instacne);
			return new ModelAndView("instance/editInstance").addAllObjects(map);
		}catch (RuntimeException e) {
			logger.error("修改实例页面出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	/**
	 * 查看本体实例页面
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/viewInstance.do")
	public ModelAndView viewInstance(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String culId = request.getParameter("culId");
			//获取instance
			Instance instacne = instService.getInstanceById(culId);
			Map map = instService.getInstanceMap();
			map.put("instance", instacne);
			return new ModelAndView("instance/viewInstance").addAllObjects(map);
		}catch (RuntimeException e) {
			logger.error("查看实例页面出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	
	/**
	 * 查看文物实例
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/viewCultural.do")
	public ModelAndView viewCultural(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String culId = request.getParameter("culId");
			CulturalBean cb = clService.getCulturalById(culId);
			String type = cb.getType();
			String classification = cb.getClassification();
			//获取创作朝代列表
			OClass oclass = ocService.getClassByName("朝代");
			List<OClass> creationDateList = ocService.getClassList(oclass);
			//文物级别
			List<String> levelList = null;
			if(type.equals("建筑"))
				levelList = levelList2;
			else
				levelList = levelList1;
			Map map = new HashMap();
			map.put("cb", cb);
			map.put("type", type);
			map.put("classification", classification);
			map.put("creationDateList", creationDateList);
			map.put("levelList", levelList);
			return new ModelAndView("instance/viewCultural").addAllObjects(map);
		}catch (RuntimeException e) {
			logger.error("查看实例出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}

	
	/**
	 * 验证文物名字是否存在
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/isTitleExist.do")
	public void isTitleExist(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String title = request.getParameter("title");
			boolean result = clService.isTitleExist(title);
			if(result)
				outputJsonResponse(response, true, "文物名字存在！");
			else
				outputJsonResponse(response, false, "文物名字不存在！");
		}catch (Exception e) {
			logger.error("验证文物名字是否存在出错：" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}


}
