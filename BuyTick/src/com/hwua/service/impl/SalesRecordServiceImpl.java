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
	public boolean saleRecordById(long id,String w_id) {
		boolean flag = false;
		SalesRecord record = isr.quertSalesById(id);
		record.setS_worker(w_id);
		SalesRecord sr = new SalesRecord();
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		record.setS_date(timeStamp);
		record.setS_state("已撤单");
		flag = isr.SaleRecord(record);
		return flag;
	}
	//查询当前账号全部订单
	@Override
	public List<SalesRecord> quertMySalesAll(String w_id) {
		List<SalesRecord> list = isr.quertMySalesAll(w_id);
		return list;
	}
	//查询当前账号全部订单（分页）
	@Override
	public List<SalesRecord> quertMySalespage(String w_id,int page, int limit) {
		List<SalesRecord> list = isr.quertMySalespage(w_id,page, limit);
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
	//根据工号、订单号、门票分类查询
	@Override
	public List<SalesRecord> adminfindSale(String find) {
		List<SalesRecord> list = isr.adminfindSale(find);
		return list;
	}
	//根据工号、订单号、门票分类查询(分页)
	@Override
	public List<SalesRecord> adminfindSalepage(String find, int page, int limit) {
		List<SalesRecord> list = isr.adminfindSalepage(find,page,limit);
		return list;
	}
	//查询入库订单
	@Override
	public List<SalesRecord> Putinstorage() {
		List<SalesRecord> list = isr.Putinstorage();
		return list;
	}
	//查询入库订单(分页)
	@Override
	public List<SalesRecord> Putinstoragepage(int page, int limit) {
		List<SalesRecord> list = isr.Putinstoragepage(page,limit);
		return list;
	}
	//查询全部订单（入库+出售）
	@Override
	public List<SalesRecord> adminquerySaleAll() {
		List<SalesRecord> list = isr.adminquerySaleAll();
		return list;
	}
	//查询全部订单（入库+出售）分页
	@Override
	public List<SalesRecord> adminquerySaleAllpage(int page, int limit) {
		List<SalesRecord> list = isr.adminquerySaleAllpage(page,limit);
		return list;
	}
	//统计全部订单
	@Override
	public List<Census> admincensusAll() {
		List<Census> list = isr.admincensusAll();
		return list;
	}
	//统计全部订单（分页）
	@Override
	public List<Census> admincensusAllpage(int page, int limit) {
		List<Census> list = isr.admincensusAllpage(page,limit);
		return list;
	}
	//统计全部订单（按月）
	@Override
	public List<Census> censusMsalesAllByMonth() {
		List<Census> list = isr.censusMsalesAllByMonth();
		return list;
	}
	//统计全部订单（按月）（分页）
	@Override
	public List<Census> censusMsalesAllByMonthpage(int page, int limit) {
		List<Census> list = isr.censusMsalesAllByMonthpage(page,limit);
		return list;
	}
	//统计输入日期的订单
	@Override
	public List<Census> admincensusBydate(String date) {
		List<Census> list = isr.admincensusBydate(date);
		return list;
	}
	//统计输入日期的订单(分页)
	@Override
	public List<Census> admincensusBydatepage(String date, int page, int limit) {
		List<Census> list = isr.admincensusBydatepage(date,page,limit);
		return list;
	}
	//按月份统计账号订单
	@Override
	public List<Census> censalesByWorkermonth(String w_id) {
		List<Census> list = isr.censalesByWorkermonth(w_id);
		return list;
	}
	//按月份统计账号订单（分页）
	@Override
	public List<Census> censalesByWorkermonthpage(String w_id, int page, int limit) {
		List<Census> list = isr.censalesByWorkermonthpage(w_id,page,limit);
		return list;
	}

}
