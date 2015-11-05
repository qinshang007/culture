package com.culture.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.culture.model.UserBean;
import com.culture.service.UserService;


@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
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
					hs.setAttribute("userName",user.getUserName());
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


}
