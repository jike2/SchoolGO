package com.hwua.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hwua.Db.Tools;
import com.hwua.pojo.Tickets;
import com.hwua.pojo.Worker;
import com.hwua.service.TickService;
import com.hwua.service.WorkerService;
import com.hwua.service.impl.TickServiceImpl;
import com.hwua.service.impl.WorkerServiceImpl;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	WorkerService ws = new WorkerServiceImpl();

	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTTIONS,DELETE");
		response.setHeader("Access-Control-Allow-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Authentication,x-request-with,Content-Type,Accept,userid");
		response.setHeader("Access-Control-Allow-Credentials", "true");//是否支持cookie
		response.setHeader("Access-Control-Expose-Headers", "userid");
		response.setStatus(202);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("login")){
			this.login(request,response);
		}else if(method.equals("loginadmin")) {
			this.loginadmin(request,response);
		}
		request.getSession().setAttribute("list", 111);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	//管理员登录
	private void loginadmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTTIONS,DELETE");
		response.setHeader("Access-Control-Allow-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Authentication,x-request-with,Content-Type,Accept,userid");
		response.setHeader("Access-Control-Allow-Credentials", "true");//是否支持cookie
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Worker worker = ws.login(username);
		if(worker!=null) {
			if(worker.getW_position().equals("管理员") || worker.getW_position().equals("超级管理员")) {
				if(worker.getW_pwd().equals(password)) {
					String sessionid = request.getSession().getId();
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("id", username+"&"+sessionid);
					response.getWriter().write(JSON.toJSONString(map));
				}else {
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("id", 0);//0是登录失败
					response.getWriter().write(JSON.toJSONString(map));
				}
			}else {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", 0);//0是登录失败
				response.getWriter().write(JSON.toJSONString(map));
			}
		}else {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", 0);//0是登录失败
			response.getWriter().write(JSON.toJSONString(map));
		}

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTTIONS,DELETE");
		response.setHeader("Access-Control-Allow-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Authentication,x-request-with,Content-Type,Accept,userid");
		response.setHeader("Access-Control-Allow-Credentials", "true");//是否支持cookie
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username+"========"+password);
		Worker worker = ws.login(username);
		if(worker!=null) {
			if(worker.getW_pwd().equals(password)) {
				String sessionid = request.getSession().getId();
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", username+"&"+sessionid);
				response.getWriter().write(JSON.toJSONString(map));
			}else {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", 0);//0是登录失败
				response.getWriter().write(JSON.toJSONString(map));
			}
		}else {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", 0);//0是登录失败
			response.getWriter().write(JSON.toJSONString(map));
		}

	}

}
