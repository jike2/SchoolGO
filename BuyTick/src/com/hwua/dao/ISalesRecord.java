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
	
	public List<Census> censusSalespage(String date, String w_id, int page, int limit);
}
