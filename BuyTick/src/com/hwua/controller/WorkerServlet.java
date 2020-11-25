package com.hwua.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hwua.Db.Tools;
import com.hwua.pojo.SalesRecord;
import com.hwua.pojo.Worker;
import com.hwua.service.impl.WorkerServiceImpl;

@WebServlet("/WorkerServlet.do")
public class WorkerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WorkerServiceImpl wsi = new WorkerServiceImpl();
	public WorkerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		System.out.println(method);
		if(method.equals("finMyPerInfo")) {
			this.finMyPerInfo(request,response);
		}else if(method.equals("upMyPwd")) {
			this.upMyPwd(request,response);
		}else if(method.equals("querworkerAll")) {
			this.querworkerAll(request,response);
		}else if(method.equals("findWorkByID")) {
			this.findWorkByID(request,response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		doGet(request, response);
	}

	private void finMyPerInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Worker worker = wsi.login(Tools.username);
		String jsonString = JSON.toJSONString(worker);
		response.getWriter().write(jsonString);
	}
	


	private void upMyPwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String lapwd = request.getParameter("lapwd");
		String pwd = request.getParameter("newpwd");
		Worker worker = wsi.login(Tools.username);
		if(!lapwd.equals(worker.getW_pwd())) {
			Map<String,Boolean> map = new HashMap<String, Boolean>();
			System.out.println("密码错误");
			map.put("pwdflag", false);
			String jsonString = JSON.toJSONString(map);
			response.getWriter().write(jsonString);
		}else if(wsi.upMyPwd(pwd)){
			Map<String,Boolean> map = new HashMap<String, Boolean>();
			map.put("flags", true);
			String jsonString = JSON.toJSONString(map);
			response.getWriter().write(jsonString);
		}
	}
	
	//查询全部员工
	private void querworkerAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String page = request.getParameter("page");
		int pages = Integer.parseInt(page);
		int limit = 10;
		Map<String,Object> map = new HashMap<String, Object>();
		List<Worker> listall = wsi.quertWorkerAll();//查询全部数据
		int count =0;
		if(listall.size()%limit==0) {
			count = listall.size()/limit;
		}else {
			count = listall.size()/limit+1;
		}
		List<Worker> list = wsi.quertWorkerpage(pages, limit);//根据分页查询数据
		map.put("data", list);
		map.put("count", count);
		String jsonString = JSON.toJSONString(map);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
		
	}

	//根据工号或姓名查询
	private void findWorkByID(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String page = request.getParameter("page");
		int pages = Integer.parseInt(page);
		String finds = request.getParameter("findById");
		String find = new String(finds.getBytes("ISO-8859-1"),"UTF-8");
		int limit = 10;
		Map<String,Object> map = new HashMap<String, Object>();
		List<Worker> listall = wsi.findWorkByID(find);//查询全部数据
		int count =0;
		if(listall.size()%limit==0) {
			count = listall.size()/limit;
		}else {
			count = listall.size()/limit+1;
		}
		List<Worker> list = wsi.findWorkByIDpage(find,pages,limit);//根据分页查询数据
		map.put("data", list);
		map.put("count", count);
		String jsonString = JSON.toJSONString(map);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
	}
}
