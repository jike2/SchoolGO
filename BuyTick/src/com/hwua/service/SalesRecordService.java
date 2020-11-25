package com.hwua.service;

import java.util.List;

import com.hwua.pojo.Census;
import com.hwua.pojo.SalesRecord;

public interface SalesRecordService {

	public List<SalesRecord> quertSalesAll();//查询全部订单
	public List<SalesRecord> quertSalespage(int page,int limit);//查询全部订单（分页）
	public SalesRecord quertSalesById(long id);//根据订单编号查询订单
	public boolean saleRecordById(long id);//根据订单编号撤单
	
	public List<SalesRecord> quertMySalesAll();//查询当前账号全部订单
	
	public List<SalesRecord> quertMySalespage(int page,int limit);//查询当前账号全部订单（分页）
	
	public List<Census> quertSalespageByDayAll(String w_id);//根据日期统计订单
	
	public List<Census> quertSalespageByDay(String w_id,int page,int limit);//根据日期统计订单
	
	public List<Census> censusSalespageAll(String date,String w_id);//统计某日某人的订单
	
	public List<Census> censusSalespage(String date,String w_id,int page,int limit);//统计某日某人的订单
}
