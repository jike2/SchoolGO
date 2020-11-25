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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("login")){
			this.login(request,response);
		}else if(method.equals("loginadmin")) {
			this.loginadmin(request,response);
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	private void loginadmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Worker worker = ws.login(username);
		if(worker!=null) {
			if(worker.getW_position().equals("管理员")) {
				if(worker.getW_pwd().equals(password)) {
					Tools.username=username;
					String sessionid = request.getSession().getId();
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("id", sessionid);
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Worker worker = ws.login(username);
		if(worker!=null) {
			if(worker.getW_pwd().equals(password)) {
				Tools.username=username;
				String sessionid = request.getSession().getId();
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", sessionid);
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
