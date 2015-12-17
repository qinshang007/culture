package com.culture.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.culture.model.UserBean;
import com.culture.service.UserService;
import com.culture.util.CodeGenerator;


@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	/**
	 * 保存用户信息
	 * @param request
	 * @param response
	 * @param user
	 * @throws Exception
	 */
	@RequestMapping("/save.do")
	public void saveUser(HttpServletRequest request, HttpServletResponse response,UserBean user) throws Exception {
		try{
			user.setUserId(CodeGenerator.createUUID());
			boolean result = userService.addUser(user);
			if(result)
				outputJsonResponse(response, true, "添加成功！");
			else
				outputJsonResponse(response, false, "添加失败！");
		}catch(RuntimeException e) {
			outputJsonResponse(response, false,e.getMessage());
			logger.error("添加用户出错："+e.getMessage());
		}
	}

	/**
	 * 更新用户信息
	 * @param request
	 * @param response
	 * @param user
	 * @throws Exception
	 */
	@RequestMapping("/update.do")
	public void updateUser(HttpServletRequest request, HttpServletResponse response,UserBean user) throws Exception {
		try{
			boolean result = userService.updateUser(user);
			if(result)
				outputJsonResponse(response, true, "更新成功！");
			else
				outputJsonResponse(response, true, "更新失败！");
		}catch(RuntimeException e) {
			outputJsonResponse(response, false,e.getMessage());
			logger.error("更新用户信息出错："+e.getMessage());
		}
	}

	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @param user
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/login.do")
	public void userLogin(HttpServletRequest request, HttpServletResponse response,UserBean user) throws ServletException, IOException{
		try{
			HttpSession hs = request.getSession();
			String message = "";
			if(user!=null && hs.getAttribute("userName")!=null){
				if(hs.getAttribute("userName").equals(user.getUserName()))
					outputJsonResponse(response, true, message);
			}
			else{
				boolean flag = userService.login(user);
				if(flag){
					user = userService.getUserByName(user.getUserName());
					hs.setAttribute("userName",user.getUserName());
					hs.setAttribute("permission", user.getLevel());
					outputJsonResponse(response, true, "登录成功！");
				}else{
					outputJsonResponse(response, false, "用户名或密码错误！");
				}
			}
		}catch (RuntimeException e) {
			logger.error(e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

	/**
	 * 用户注销
	 * @param request
	 * @param response
	 * @param user
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/logout.do")
	public ModelAndView logOut(HttpServletRequest request, HttpServletResponse response, UserBean user) throws ServletException, IOException{
		try{
			HttpSession hs = request.getSession();
			hs.setAttribute("userName", null);
			return new ModelAndView("redirect:/login.jsp");
		}catch (RuntimeException e) {
			logger.error(e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
			return null;
		}
	}
	
	/**
	 * 添加用户
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/addUser.do")
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			return new ModelAndView("user/userAdd");
		}catch(RuntimeException e) {
			outputJsonResponse(response, false,e.getMessage());
			logger.error("添加用户界面出错："+e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取用户列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getUserList.do")
	public ModelAndView getBoxList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			List<UserBean> userList = userService.getUserList();
			return new ModelAndView("user/userList").addObject("userList", userList);
		}catch(RuntimeException e) {
			outputJsonResponse(response, false,e.getMessage());
			logger.error("获取用户列表出错："+e.getMessage());
			return null;
		}
	}

	/**
	 * 查看用户详情
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/viewUser.do")
	public ModelAndView viewUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String userId = request.getParameter("userId");
			UserBean user =  userService.getUserById(userId);
			return new ModelAndView("user/userView").addObject("user", user);
		}catch(RuntimeException e) {
			outputJsonResponse(response, false,e.getMessage());
			logger.error("查看用户详情出错："+e.getMessage());
			return null;
		}
	}
	
	/**
	 * 删除用户
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/deleteUser.do")
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String userId = request.getParameter("userId");
			boolean result = userService.deleteUser(userId);
			if(result)
				outputJsonResponse(response, true, "删除成功！");
			else
				outputJsonResponse(response, false, "删除失败！");
		}catch (Exception e) {
			logger.error("删除用户出错：" +  ",errMsg=" + e.getMessage());
			outputJsonResponse(response, false, e.getMessage());
		}
	}

}
