package com.hwua.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.hwua.pojo.SalesRecord;
import com.hwua.pojo.Tickets;
import com.hwua.service.SalesRecordService;
import com.hwua.service.TickService;
import com.hwua.service.impl.SalesRecordServiceImpl;
import com.hwua.service.impl.TickServiceImpl;

@WebServlet("/TicketServlet.do")
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TickService ts = new TickServiceImpl();
   
    
    public TicketServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("saleTick")) {
			this.saleTick(request, response);
		}else if(method.equals("queryTickAll")) {
			this.queryTickAll(request, response);
		}else if(method.equals("queryTickByID")) {
			this.queryTickByID(request, response);
		}else if(method.equals("addticknum")) {
			this.addticknum(request, response);
		}else if(method.equals("deltick")) {
			this.deltick(request, response);
		}else if(method.equals("queryTickByName")) {
			this.queryTickByName(request, response);
		}else if(method.equals("uptick")) {
			this.uptick(request, response);
		}else if(method.equals("addTick")) {
			this.addTick(request, response);
		}
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		doGet(request, response);
	}
	//查找全部门票
	private void queryTickAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> map = new HashMap<String, Object>();
		String page = request.getParameter("page");//页码数
		String limit = request.getParameter("limit");//每页数据数量
		int pages = 0;
		int limits=10;
		if(page != null)pages = Integer.parseInt(page);
		if(limit != null)limits = Integer.parseInt(limit);

		List<Tickets> list = ts.queryAll(pages,limits);
		if(list != null)map.put("data", list);
		map.put("code", 0);
		map.put("count", 1000);
		String jsonString = JSON.toJSONString(map);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);

	}
	//售票
	private void saleTick(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String p_id = request.getParameter("p_id");
		String p_num = request.getParameter("p_num");
		String userid = request.getHeader("userid");
		String[] split = userid.split("&");
		int id = Integer.parseInt(p_id);
		int number = Integer.parseInt(p_num);
		Map<String,String> map = new HashMap<String, String>();
		if(ts.saleTick(id, number,split[0])) {
			map.put("flags", "true");
		}else {
			map.put("flags", "true");
		}
		String jsonString = JSON.toJSONString(map);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
	}

	//根据id查找门票
	private void queryTickByID(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("findById");
		Tickets tick = ts.queryTickById(Integer.parseInt(id));
		String jsonString = JSON.toJSONString(tick);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
		
	}
	
	//增加库存
	private void addticknum(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("p_id");
		String num = request.getParameter("p_num");
		int p_id = Integer.parseInt(id);
		int p_num = Integer.parseInt(num);
		String userid = request.getHeader("userid");
		String[] split = userid.split("&");
		boolean addticknum = ts.addticknum(p_id, p_num,split[0]);
		String jsonString = JSON.toJSONString(addticknum);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
	}
	

	//删除门票
	private void deltick(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("p_id");
		int p_id = Integer.parseInt(id);
		boolean deltick = ts.deltick(p_id);
		String jsonString = JSON.toJSONString(deltick);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
	}
	

	//根据门票分类查询门票
	private void queryTickByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String p_type = request.getParameter("p_type");
		Tickets tickets = ts.queryTickByType(new String(p_type.getBytes("ISO-8859-1"),"UTF-8"));
		if(tickets.getP_type() == null || tickets.getP_type().equals("")) {
			String jsonString = JSON.toJSONString(true);
			PrintWriter writer = response.getWriter();
			writer.write(jsonString);
		}else {
			String jsonString = JSON.toJSONString(false);
			PrintWriter writer = response.getWriter();
			writer.write(jsonString);
		}
		
	}
	
	//修改门票信息
	private void uptick(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String p_id = request.getParameter("p_id");
		int id = Integer.parseInt(p_id);
		String p_type = request.getParameter("p_type");
		String p_price = request.getParameter("p_price");
		double price = Double.parseDouble(p_price);
		Tickets tk = ts.queryTickById(id);
		tk.setP_type(new String(p_type.getBytes("ISO-8859-1"),"UTF-8"));
		tk.setP_price(price);
		boolean uptick = ts.uptick(tk);
		String jsonString = JSON.toJSONString(uptick);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
	}

	//添加门票
		private void addTick(HttpServletRequest request, HttpServletResponse response) throws IOException {
			
			String p_type = request.getParameter("p_type");
			String p_price = request.getParameter("p_price");
			double price = Double.parseDouble(p_price);
			String p_num = request.getParameter("p_num");
			int num = Integer.parseInt(p_num);
			Tickets tk = new Tickets();
			tk.setP_type(new String(p_type.getBytes("ISO-8859-1"),"UTF-8"));
			tk.setP_price(price);
			tk.setP_number(num);
			tk.setP_id(0);
			boolean addtick = ts.addtick(tk);
			String jsonString = JSON.toJSONString(addtick);
			PrintWriter writer = response.getWriter();
			writer.write(jsonString);
		}
	
}
