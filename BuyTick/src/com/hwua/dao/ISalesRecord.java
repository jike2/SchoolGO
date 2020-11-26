package com.hwua.dao;

import java.util.Date;
import java.util.List;

import com.hwua.pojo.Census;
import com.hwua.pojo.SalesRecord;

public interface ISalesRecord {

	public boolean add(SalesRecord sr);//增加记录

	public List<SalesRecord> querySalesAll();//查询全部订单

	public List<SalesRecord> querySalespage(int page,int limit);//分页查询全部订单

	public SalesRecord quertSalesById(long id);//根据订单编号查询订单

	public boolean SaleRecord(SalesRecord sr);//撤单
	
	public List<SalesRecord> quertMySalesAll();//查询全部订单

	public List<SalesRecord> quertMySalespage(int page,int limit);//分页查询全部订单
	
	public List<Census> quertSalespageByDay(String w_id,int page,int limit);//根据账号统计订单
	
	public List<Census> quertSalespageByDayAll(String w_id);//根据账号统计订单
	
	public List<Census> censusSalespageAll(String date,String w_id);//根据日期统计订单
	
	public List<Census> censusSalespage(String date, String w_id, int page, int limit);//根据日期统计订单(分页)
	
	public List<SalesRecord> adminfindSale(String find);//根据工号、订单号、门票分类查询
	
	public List<SalesRecord> adminfindSalepage(String find, int page, int limit);//根据工号、订单号、门票分类查询(分页)
	
	
	public List<SalesRecord> Putinstorage();//查询入库订单
	
	public List<SalesRecord> Putinstoragepage(int page, int limit);//查询入库订单(分页)
	
	public List<SalesRecord> adminquerySaleAll();//查询全部订单（入库+出售）
	
	public List<SalesRecord> adminquerySaleAllpage(int page, int limit);//查询全部订单（入库+出售）分页
	
	public List<Census> admincensusAll();//统计全部订单
	
	public List<Census> admincensusAllpage(int page, int limit);//统计全部订单（分页）
	
	public List<Census> censusMsalesAllByMonth();//统计全部订单（按月）
	
	public List<Census> censusMsalesAllByMonthpage(int page, int limit);//统计全部订单（按月）（分页）
	
	public List<Census> admincensusBydate(String date);//统计输入日期的订单
	
	public List<Census> admincensusBydatepage(String date,int page, int limit);//统计输入日期的订单（分页

	
	public List<Census> censalesByWorkermonth(String w_id);//根据月份统计订单
	
	public List<Census> censalesByWorkermonthpage(String w_id,int page, int limit);//根据月份统计订单（分页）
}
