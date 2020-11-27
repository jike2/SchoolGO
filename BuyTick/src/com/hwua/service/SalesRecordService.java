package com.hwua.service;

import java.util.List;

import com.hwua.pojo.Census;
import com.hwua.pojo.SalesRecord;

public interface SalesRecordService {

	public List<SalesRecord> quertSalesAll();//查询全部订单
	
	public List<SalesRecord> quertSalespage(int page,int limit);//查询全部订单（分页）
	
	public SalesRecord quertSalesById(long id);//根据订单编号查询订单
	
	public boolean saleRecordById(long id,String w_id);//根据订单编号撤单
	
	public List<SalesRecord> quertMySalesAll(String w_id);//查询当前账号全部订单
	
	public List<SalesRecord> quertMySalespage(String w_id,int page,int limit);//查询当前账号全部订单（分页）
	
	public List<Census> quertSalespageByDayAll(String w_id);//根据日期统计订单
	
	public List<Census> quertSalespageByDay(String w_id,int page,int limit);//根据日期统计订单
	
	public List<Census> censusSalespageAll(String date,String w_id);//统计某日某人的订单
	
	public List<Census> censusSalespage(String date,String w_id,int page,int limit);//统计某日某人的订单
	
	public List<SalesRecord> adminfindSale(String find);//根据工号、订单号、门票分类查询
	
	public List<SalesRecord> adminfindSalepage(String find, int page, int limit);//根据工号、订单号、门票分类查询(分页)

	public List<SalesRecord> Putinstorage();//查询入库记录全部订单
	
	public List<SalesRecord> Putinstoragepage(int page,int limit);//查询入库记录全部订单（分页）
	
	public List<SalesRecord> adminquerySaleAll();//查询全部订单（入库+出售）
	
	public List<SalesRecord> adminquerySaleAllpage(int page, int limit);//查询全部订单（入库+出售）分页
	
	public List<Census> admincensusAll();//统计全部订单
	
	public List<Census> admincensusAllpage(int page, int limit);//统计全部订单（分页）
	
	public List<Census> censusMsalesAllByMonth();//统计全部订单（按月）
	
	public List<Census> censusMsalesAllByMonthpage(int page, int limit);//统计全部订单（按月）（分页）
	
	public List<Census> admincensusBydate(String date);//统计输入日期的订单
	
	public List<Census> admincensusBydatepage(String date,int page, int limit);//统计输入日期的订单（分页）
	
	public List<Census> censalesByWorkermonth(String w_id);//根据月份统计订单
	
	public List<Census> censalesByWorkermonthpage(String w_id,int page, int limit);//根据月份统计订单（分页）
	
}
