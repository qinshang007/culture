package com.culture.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.culture.model.CulturalBean;
import com.culture.model.OClass;
import com.culture.model.UploadFile;
import com.culture.service.CulturalService;
import com.culture.service.InstanceService;
import com.culture.service.OClassService;
import com.culture.util.CodeGenerator;
import com.culture.util.CommonConst;
import com.culture.util.DateUtils;

/**
 * 接口服务
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/webservice")
public class InterfaceController extends BaseController{
	
	@Autowired
	private OClassService ocService;
	@Autowired
	private CulturalService clService;
	@Autowired
	private InstanceService instService;
	
	private int pageSize = 12;
	
	private static final Logger logger = Logger.getLogger(InterfaceController.class);
	
	/**
	 * 获取文物类别的详细分类
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getConcretType.asmx")
	public void getData(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String type = request.getParameter("type");
			//获取文物类别概念的子概念
			if(type.equals("建筑"))
				type = "建筑物";
			List<OClass> oclist = ocService.getChildClass(type,1);
			outputJsonResponse(response, oclist);
		}catch (RuntimeException e) {
			logger.error("获取文物类别的详细分类请求据出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	/**
	 * 获取文物列表数量
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getListCount.asmx")
	public void getListCount(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String username = "admin";
			//获取文物标题
			String title = request.getParameter("title");
			//获取文物大类，器物，织物，建筑，壁画
			String type = request.getParameter("type");
			//获取文物详细分类
			String classification = request.getParameter("classification");
			//获取朝代
			String creation_date = request.getParameter("creation_date");
			int count = clService.getListCount(username,title,type,classification,creation_date);
			outputJsonResponse(response, true,String.valueOf(count));
		}catch (RuntimeException e) {
			logger.error("获取文物列表数量请求出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}
	
	/**
	 * 获取文物列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getCultureList.asmx")
	public void getCultureList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String username = "admin";
			//获取文物名字
			String title = request.getParameter("title");
			//获取文物大类，器物，织物，建筑，壁画
			String type = request.getParameter("type");
			//获取文物详细分类
			String classification = request.getParameter("classification");
			//获取朝代
			String creation_date = request.getParameter("creation_date");
			//获取页码
			String pageStart = request.getParameter("pageStart");
			int start = (Integer.parseInt(pageStart)-1)*pageSize;
			List<CulturalBean> cbList = clService.getCulturalList(username,title,type,classification,creation_date,start,pageSize);
			outputJsonResponse(response, cbList);
		}catch (RuntimeException e) {
			logger.error("获取文物列表请求出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}
	

	/**
	 * 获取文物详情接口
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCultureInfo.asmx")
	public void getCultureInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String culId = request.getParameter("culId");
			CulturalBean cb = clService.getCulturalById(culId);
			outputJsonResponse(response,cb);
			//更新文物的点击量
			clService.updateSernum(culId);
		}catch (RuntimeException e) {
			logger.error("获取文物详情请求出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	/**
	 * 获取朝代列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getDynastyList.asmx")
	public void getDynastyList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			//获取朝代列表
			List<OClass> dynastylist = instService.getCreationDateList();
			outputJsonResponse(response, dynastylist);
		}catch (RuntimeException e) {
			logger.error("获取朝代列表请求据出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	/**
	 * 获取知识推荐列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getRecommendList.asmx")
	public void getRecommendList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String type = request.getParameter("type");
			//获取知识推荐列表
			List<CulturalBean> cblist = clService.getRecommendList(type);
			outputJsonResponse(response, cblist);
		}catch (RuntimeException e) {
			logger.error("获取知识推荐列表请求据出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	/**
	 * 添加 文物
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/saveCultural.asmx")
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
				outputJsonResponse(response, true,"添加成功！");
			else
				outputJsonResponse(response, false,"添加失败！");
		}catch (RuntimeException e) {
			logger.error("添加文物请求出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	/**
	 * 修改文物
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/updateCultural.asmx")
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
			logger.error("修改文物请求出错！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	/**
	 * 删除文物
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/delete.asmx")
	public void delInstance(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String culId = request.getParameter("culId");
			//更新数据库
			clService.delCultural(culId);
			//更新本体文件
			outputJsonResponse(response, true,"删除文物成功！");
		}catch (RuntimeException e) {
			logger.error("删除文物请求失败！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	/**
	 * 更新文物点击量
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/updateSernum.asmx")
	public void updateSernum(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String culId = request.getParameter("culId");
			//更新数据库
			clService.updateSernum(culId);
			//更新本体文件
			outputJsonResponse(response, true,"更新文物点击量成功！");
		}catch (RuntimeException e) {
			logger.error("更新文物点击量请求失败！" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	
}
