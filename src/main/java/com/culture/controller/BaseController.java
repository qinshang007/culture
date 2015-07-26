package com.culture.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.culture.model.UploadFile;
import com.culture.util.CodeGenerator;
import com.culture.util.CommonConst;
import com.culture.util.StringUtils;
import com.culture.util.SystemConfig;

public class BaseController {
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	/*
	 * 多文件上传
	 * path:上传服务器的路径，例如path = "/upload/project"
	 * isRelative:true：相对路径 false：绝对路径
	 * return:List<UploadModel>
	 */
	protected List<UploadFile> upload(HttpServletRequest request, HttpServletResponse response, String path) throws Exception {
		try {
			List<UploadFile> list = new ArrayList<UploadFile>();
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator it = multipartRequest.getFileNames();
			while (it.hasNext()) {
				String key = (String) it.next();
				MultipartFile file = multipartRequest.getFile(key);
				if (file.getOriginalFilename().length() > 0) {
					String fileName = file.getOriginalFilename();
					String extName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
					String fileId = CodeGenerator.createUUID();
					String newFileName = fileId + extName;
					String fullPath = "";
					String rootPath = CommonConst.UPLOAD_ROOT_PATH;
					fullPath = rootPath + path;
					//如果目录不存在创建目录
					File dirFile = new File(fullPath);
					if (!dirFile.exists())
						dirFile.mkdirs();
					//创建文件
					fullPath += "/" + newFileName;
					File newFile = new File(fullPath);
					file.transferTo(newFile);
					UploadFile obj = new UploadFile();
					obj.setFileId(fileId);
					obj.setFileName(fileName);
					obj.setFileSrc(path + "/" + newFileName);
					obj.setFileSize(newFile.length());
					list.add(obj);
				}
			}
			return list;
		} catch (Exception e) {
			//log.error(e.toString());
			return new ArrayList();
		}
	}

	/**
	 * Method to flush a String as response.
	 * @param response
	 * @param responseContent
	 * @throws IOException
	 */
	protected void flushResponse(HttpServletResponse response, String responseContent) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		try {
			PrintWriter out = response.getWriter();
			out.write(responseContent);
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}


	/**
	 *  输出响应
	 */
	public void outputHtmlResponse(HttpServletResponse response, String str) {
		try {
			StringBuffer htmlText = new StringBuffer();
			htmlText.append("<script type=\"text/javascript\">\n");
			htmlText.append(str);
			htmlText.append("</script>\n");
			this.flushResponse(response, htmlText.toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	
	/**
	 * 输出Json响应：{"result":true}
	 */
	protected void outputJsonResponse(HttpServletResponse response, boolean result) {
		JSONObject json = new JSONObject();
		json.put("result", result);
		String content = json.toString();
		this.flushResponse(response, content);
	}

	/**
	 * 输出Json响应：{"result":true,"msg":"abc"}
	 */
	protected void outputJsonResponse(HttpServletResponse response, boolean result, String message) {
		JSONObject json = new JSONObject();
		json.put("result", result);
		json.put("message", message);
		String content = json.toString();
		this.flushResponse(response, content);
	}
	
	/**
	 * 输出Json响应：{"msg":"abc"}
	 */
	protected void outputJsonResponse(HttpServletResponse response,String message) {
		this.flushResponse(response, message);
	}

	
	protected void outputJsonResponse(HttpServletResponse response, boolean result, String message,Object obj) {
		JSONObject json = new JSONObject();
		json.put("result", result);
		json.put("message", message);
		if (obj != null ){
			JSONArray jsonArray = new JSONArray(obj);   //将article数据转为json对象  
			String data = jsonArray.toString();         //将json对象转为字符串  
			json.put("data", data);
		}
		String content = json.toString();
		this.flushResponse(response, content);
	}

	protected void outputJsonResponse(HttpServletResponse response,Object obj) {
		String content = "";
		if (obj != null ){
			if(obj.getClass().isArray()){
				JSONArray jsonArray = new JSONArray(obj);
				content = jsonArray.toString();  
			}else{
				JSONObject json = new JSONObject(obj);
				content = json.toString();
			}
		}
		this.flushResponse(response, content);
	}


	protected void outputJsonResponse(HttpServletResponse response, Map dataMap) {
		this.flushResponse(response, toJsonString(dataMap));
	}

	/*
	 * {"result":true,"data":{"key1":"value1","key2":"value2"}}
	 */
	protected void outputJsonResponse(HttpServletResponse response, boolean result, Map customData) {
		JSONObject json = new JSONObject();
		json.put("result", result);
		if (customData != null && customData.size() > 0)
			json.put("data", customData);
		String content = json.toString();
		this.flushResponse(response, content);
	}
	

	
	protected void outputJsonResponse(HttpServletResponse response, boolean result, String message, Map customData) {
		JSONObject json = new JSONObject();
		json.put("result", result);
		json.put("message", message);
		if (customData != null && customData.size() > 0)
			json.put("data", customData);
		String content = json.toString();
		this.flushResponse(response, content);
	}

	/*
	 * 输出json数据
	 * 输出格式为：{result:true,data:[{"name":"name1","id":"id1"},{"name":"name2","id":"id2"}]}
	 */
	protected void outputJsonResponse(HttpServletResponse response, boolean result, Collection coll) {
		JSONObject json = new JSONObject();
		json.put("result", result);
		if (coll != null && coll.size() > 0) {
			JSONArray jsonArray = new JSONArray(coll);
			json.put("data", jsonArray);
		}
		String content = json.toString();
		this.flushResponse(response, content);
	}
	
	/*
	 * 输出json数据
	 * 输出格式为：{data:[{"name":"name1","id":"id1"},{"name":"name2","id":"id2"}]}
	 */
	protected void outputJsonResponse(HttpServletResponse response,Collection coll) {
		JSONArray jsonArray = null;
		if (coll != null && coll.size() > 0) {
			jsonArray = new JSONArray(coll);
		}
		String content ="{}";
		if(jsonArray!=null)
			content = jsonArray.toString();
		this.flushResponse(response, content);
	}

	
	protected void outputJsonResponse(HttpServletResponse response, boolean result, String message, Collection coll) {
		JSONObject json = new JSONObject();
		json.put("result", result);
		json.put("message", message);
		if (coll != null && coll.size() > 0) {
			JSONArray jsonArray = new JSONArray(coll);
			json.put("data", jsonArray);
		}
		String content = json.toString();
		this.flushResponse(response, content);
	}

	/**
	 * 将java对象转换成json字符串
	 *{"name":"name1","id":"id1"}
	 */
	public static String toJsonString(Object object) {
		String res = "";
		if (object != null ){
			JSONArray jsonArray = new JSONArray(object);   //将article数据转为json对象  
			res = jsonArray.toString();         //将json对象转为字符串  
		}
		return res;
	}

	/*
	 * 将java对象转化为json数组字符串
	 * [{"name":"name1","id":"id1"},{"name":"name2","id":"id2"}]
	 */
	public static String toJsonArrayString(Collection<Object> coll) {
		String res = "";
		if (coll != null && coll.size() > 0) {
			JSONArray jsonArray = new JSONArray(coll);
			res = jsonArray.toString();
		}
		return res;
	}
	
}
