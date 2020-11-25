package com.hwua.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.hwua.Db.Tools;
import com.hwua.dao.ISalesRecord;
import com.hwua.dao.impl.SalesRecordImpl;
import com.hwua.pojo.Census;
import com.hwua.pojo.SalesRecord;
import com.hwua.service.SalesRecordService;

public class SalesRecordServiceImpl implements SalesRecordService{
	ISalesRecord isr = new SalesRecordImpl();
	//查询全部订单
	@Override
	public List<SalesRecord> quertSalesAll() {
		List<SalesRecord> list = isr.querySalesAll();
		return list;
	}
	//分页查询订单
	@Override
	public List<SalesRecord> quertSalespage(int page, int limit) {
		List<SalesRecord> list = isr.querySalespage(page, limit);
		return list;
	}
	//根据订单编号查询数据
	@Override
	public SalesRecord quertSalesById(long id) {
		SalesRecord record = isr.quertSalesById(id);
		return record;
	}
	//撤单
	@Override
	public boolean saleRecordById(long id) {
		boolean flag = false;
		SalesRecord record = isr.quertSalesById(id);
		record.setS_worker(Tools.username);
		SalesRecord sr = new SalesRecord();
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		record.setS_date(timeStamp);
		record.setS_state("已撤单");
		flag = isr.SaleRecord(record);
		System.out.println(flag);
		return flag;
	}
	//查询当前账号全部订单
	@Override
	public List<SalesRecord> quertMySalesAll() {
		List<SalesRecord> list = isr.quertMySalesAll();
		return list;
	}
	//查询当前账号全部订单（分页）
	@Override
	public List<SalesRecord> quertMySalespage(int page, int limit) {
		List<SalesRecord> list = isr.quertMySalespage(page, limit);
		return list;
	}
	//根据日期统计订单
	@Override
	public List<Census> quertSalespageByDay(String w_id, int page, int limit) {
		List<Census> list = isr.quertSalespageByDay(w_id,page, limit);
		return list;
	}
	//统计某人订单
	@Override
	public List<Census> quertSalespageByDayAll(String w_id) {
		List<Census> list = isr.quertSalespageByDayAll(w_id);
		return list;
	}
	//统计某日某人的订单
	@Override
	public List<Census> censusSalespageAll(String date, String w_id) {
		List<Census> list = isr.censusSalespageAll(date, w_id);
		return list;
	}
	@Override
	public List<Census> censusSalespage(String date, String w_id, int page, int limit) {
		List<Census> list = isr.censusSalespage(date, w_id,page,limit);
		return list;
	}

}
