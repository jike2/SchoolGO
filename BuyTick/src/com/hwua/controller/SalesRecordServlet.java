package com.hwua.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.hwua.pojo.Census;
import com.hwua.pojo.SalesRecord;
import com.hwua.pojo.Tickets;
import com.hwua.service.SalesRecordService;
import com.hwua.service.TickService;
import com.hwua.service.impl.SalesRecordServiceImpl;
import com.hwua.service.impl.TickServiceImpl;

@WebServlet("/SalesRecordServlet.do")
public class SalesRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SalesRecordService srs = new SalesRecordServiceImpl(); 
	TickService ts = new TickServiceImpl();
	public SalesRecordServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("querySaleAll")) {
			this.querySaleAll(request,response);
		}else if(method.equals("querySaleByID")){
			this.querySaleByID(request,response);
		}else if(method.equals("salesRecordByID")) {
			this.salesRecordByID(request,response);
		}else if(method.equals("mysales")) {
			this.mysales(request,response);
		}else if(method.equals("censusMsalesAll")) {
			this.censusMsalesAll(request,response);
		}else if(method.equals("cenMySalesByDate")) {
			this.cenMySalesByDate(request,response);
		}else if(method.equals("adminfindSale")) {
			this.adminfindSale(request,response);
		}else if(method.equals("Putinstorage")) {
			this.Putinstorage(request,response);
		}else if(method.equals("adminquerySaleAll")) {
			this.adminquerySaleAll(request,response);
		}else if(method.equals("admincensusAll")) {
			this.admincensusAll(request,response);
		}else if(method.equals("censusMsalesAllByMonth")) {
			this.censusMsalesAllByMonth(request,response);
		}else if(method.equals("admincensusBydate")) {
			this.admincensusBydate(request,response);
		}else if(method.equals("censalesByWorkerdate")) {
			this.censalesByWorkerdate(request,response);
		}else if(method.equals("censalesByWorkermonth")) {
			this.censalesByWorkermonth(request,response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		doGet(request, response);
	}
	//查询全部订单
	private void querySaleAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String page = request.getParameter("page");
		int pages = Integer.parseInt(page);
		int limit = 10;
		Map<String,Object> map = new HashMap<String, Object>();
		List<SalesRecord> listall = srs.quertSalesAll();//查询全部数据
		int count =0;
		if(listall.size()%limit==0) {
			count = listall.size()/limit;
		}else {
			count = listall.size()/limit+1;
		}
		List<SalesRecord> list = srs.quertSalespage(pages, limit);//根据分页查询数据
		
		map.put("data", list);
		map.put("count", count);
		String jsonString = JSON.toJSONString(map);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
	}
	//根据订单编号查询订单
	private void querySaleByID(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("findById");
		Long ids=Long.parseLong(id);
		SalesRecord record = srs.quertSalesById(ids);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("data", record);
		map.put("count", 1);
		String jsonString = JSON.toJSONString(map);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
	}
	//根据id撤单
	private void salesRecordByID(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("salesID");
		Long ids=Long.parseLong(id);
		String userid = request.getHeader("userid");
		String[] split = userid.split("&");
		boolean b = false;
		if(srs.saleRecordById(ids,split[0])) {
			SalesRecord record = srs.quertSalesById(ids);
			Tickets tickets = ts.queryTickByType(record.getPrice_id());
			tickets.setP_number(tickets.getP_number()+record.getS_number());
			if(ts.uptick(tickets)) {
				b=true;
			}
		}
		String jsonString = JSON.toJSONString(b);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
	}
	

	//查询当前登录账号处理的订单
	private void mysales(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String page = request.getParameter("page");
		int pages = Integer.parseInt(page);
		int limit = 10;
		String userid = request.getHeader("userid");
		String[] split = userid.split("&");
		Map<String,Object> map = new HashMap<String, Object>();
		List<SalesRecord> listall = srs.quertMySalesAll(split[0]);//查询全部数据
		int count =0;
		if(listall.size()%limit==0) {
			count = listall.size()/limit;
		}else {
			count = listall.size()/limit+1;
		}
		List<SalesRecord> list = srs.quertMySalespage(split[0],pages, limit);//根据分页查询数据
		
		map.put("data", list);
		map.put("count", count);
		String jsonString = JSON.toJSONString(map);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
	}
	//根据日期统计本人订单
	private void censusMsalesAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String page = request.getParameter("page");
		String userid = request.getHeader("userid");
		String[] split = userid.split("&");
		int pages = Integer.parseInt(page);
		int limit = 10;
		Map<String,Object> map = new HashMap<String, Object>();
		List<Census> listall = srs.quertSalespageByDayAll(split[0]);
		int count =0;
		if(listall.size()%limit==0) {
			count = listall.size()/limit;
		}else {
			count = listall.size()/limit+1;
		}
		
		List<Census> list = srs.quertSalespageByDay(split[0], pages, limit);//根据分页查询数据
		map.put("data", list);
		map.put("count", count);
		String jsonString = JSON.toJSONString(map);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
//		
	}
	//统计输入的日期的订单
	private void cenMySalesByDate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> map = new HashMap<String, Object>();
		String page = request.getParameter("page");
		int pages = Integer.parseInt(page);
		String userid = request.getHeader("userid");
		String[] split = userid.split("&");
		String date = request.getParameter("date");
		List<Census> listall = srs.censusSalespageAll(date, split[0]);
		int limit = 10;
		int count =0;
		if(listall.size()%limit==0) {
			count = listall.size()/limit;
		}else {
			count = listall.size()/limit+1;
		}
		List<Census> list = srs.censusSalespage(date, split[0],pages,limit);
		map.put("data", list);
		map.put("count", count);
		String jsonString = JSON.toJSONString(map);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
	}
	
	//根据工号、订单号、门票分类查询
	private void adminfindSale(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String page = request.getParameter("page");
		String finds = request.getParameter("find");
		int pages = Integer.parseInt(page);
		int limit = 10;
		Map<String,Object> map = new HashMap<String, Object>();
		String find = new String(finds.getBytes("ISO-8859-1"),"UTF-8");
		List<SalesRecord> listall = srs.adminfindSale(find);//查询全部数据
		int count =0;
		if(listall.size()%limit==0) {
			count = listall.size()/limit;
		}else {
			count = listall.size()/limit+1;
		}
		List<SalesRecord> list = srs.adminfindSalepage(find,pages, limit);//根据分页查询数据
		
		map.put("data", list);
		map.put("count", count);
		String jsonString = JSON.toJSONString(map);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
		
	}
	//入库记录
	private void Putinstorage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String page = request.getParameter("page");
		int pages = Integer.parseInt(page);
		int limit = 10;
		Map<String,Object> map = new HashMap<String, Object>();
		List<SalesRecord> listall = srs.Putinstorage();//查询全部数据
		int count =0;
		if(listall.size()%limit==0) {
			count = listall.size()/limit;
		}else {
			count = listall.size()/limit+1;
		}
		List<SalesRecord> list = srs.Putinstoragepage(pages, limit);//根据分页查询数据
		
		map.put("data", list);
		map.put("count", count);
		String jsonString = JSON.toJSONString(map);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
		
	}
	//查询全部订单（入库+出售）
	private void adminquerySaleAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String page = request.getParameter("page");
		int pages = Integer.parseInt(page);
		int limit = 10;
		Map<String,Object> map = new HashMap<String, Object>();
		List<SalesRecord> listall = srs.adminquerySaleAll();//查询全部数据
		int count =0;
		if(listall.size()%limit==0) {
			count = listall.size()/limit;
		}else {
			count = listall.size()/limit+1;
		}
		List<SalesRecord> list = srs.adminquerySaleAllpage(pages, limit);//根据分页查询数据
		
		map.put("data", list);
		map.put("count", count);
		String jsonString = JSON.toJSONString(map);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
	}
	
	//统计全部订单（按日）
	private void admincensusAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String page = request.getParameter("page");
		int pages = Integer.parseInt(page);
		int limit = 10;
		Map<String,Object> map = new HashMap<String, Object>();
		
		List<Census> listall = srs.admincensusAll();
		int count =0;
		if(listall.size()%limit==0) {
			count = listall.size()/limit;
		}else {
			count = listall.size()/limit+1;
		}
		List<Census> list = srs.admincensusAllpage(pages, limit);//根据分页查询数据
		map.put("data", list);
		map.put("count", count);
		String jsonString = JSON.toJSONString(map);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
		
	}

	//统计全部订单（按月）
	private void censusMsalesAllByMonth(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String page = request.getParameter("page");
		int pages = Integer.parseInt(page);
		int limit = 10;
		Map<String,Object> map = new HashMap<String, Object>();
		
		List<Census> listall = srs.censusMsalesAllByMonth();
		int count =0;
		if(listall.size()%limit==0) {
			count = listall.size()/limit;
		}else {
			count = listall.size()/limit+1;
		}
		List<Census> list = srs.censusMsalesAllByMonthpage(pages, limit);//根据分页查询数据
		map.put("data", list);
		map.put("count", count);
		String jsonString = JSON.toJSONString(map);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
		
	}
	//根据日期查询订单
	private void admincensusBydate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> map = new HashMap<String, Object>();
		String page = request.getParameter("page");
		int pages = Integer.parseInt(page);
		
		String date = request.getParameter("date");
		List<Census> listall = srs.admincensusBydate(date);
		int limit = 10;
		int count =0;
		if(listall.size()%limit==0) {
			count = listall.size()/limit;
		}else {
			count = listall.size()/limit+1;
		}
		List<Census> list = srs.admincensusBydatepage(date,pages,limit);
		map.put("data", list);
		map.put("count", count);
		String jsonString = JSON.toJSONString(map);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
	}
	

	//按日统计员工的订单
	private void censalesByWorkerdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String page = request.getParameter("page");
		String find = request.getParameter("find");
		int pages = Integer.parseInt(page);
		int limit = 10;
		Map<String,Object> map = new HashMap<String, Object>();
		
		List<Census> listall = srs.quertSalespageByDayAll(find);
		int count =0;
		if(listall.size()%limit==0) {
			count = listall.size()/limit;
		}else {
			count = listall.size()/limit+1;
		}
		
		List<Census> list = srs.quertSalespageByDay(find, pages, limit);//根据分页查询数据
		map.put("data", list);
		map.put("count", count);
		String jsonString = JSON.toJSONString(map);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
		
	}
	

	//按月统计员工的订单
	private void censalesByWorkermonth(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String page = request.getParameter("page");
		String find = request.getParameter("find");
		int pages = Integer.parseInt(page);
		int limit = 10;
		Map<String,Object> map = new HashMap<String, Object>();
		
		List<Census> listall = srs.censalesByWorkermonth(find);
		int count =0;
		if(listall.size()%limit==0) {
			count = listall.size()/limit;
		}else {
			count = listall.size()/limit+1;
		}
		
		List<Census> list = srs.censalesByWorkermonthpage(find, pages, limit);//根据分页查询数据
		map.put("data", list);
		map.put("count", count);
		String jsonString = JSON.toJSONString(map);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
		
	}
}
