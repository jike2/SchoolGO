package com.hwua.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hwua.Db.Tools;
import com.hwua.dao.ISalesRecord;
import com.hwua.dao.ITickDao;
import com.hwua.dao.impl.SalesRecordImpl;
import com.hwua.dao.impl.TickDaoImpl;
import com.hwua.pojo.SalesRecord;
import com.hwua.pojo.Tickets;
import com.hwua.service.TickService;

public class TickServiceImpl implements TickService{
	ITickDao itd = new TickDaoImpl();
	ISalesRecord isr = new SalesRecordImpl();
	//查询全部门票信息
	@Override
	public List<Tickets> queryAll(int page,int limit) {
		List<Tickets> list = itd.queryAll(page,limit);
		return list;
	}
	//门票出售
	@Override
	public boolean saleTick(int p_id, int number,String w_id) {
		boolean flag = false;
		Tickets tk = itd.query(p_id);
		int num = tk.getP_number()-number;
		if(itd.saleTick(p_id,num)) {
			SalesRecord sr = new SalesRecord();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			sr.setS_id(Long.parseLong(sdf.format(date)));
			sr.setPrice_id(tk.getP_type());
			sr.setPrice_price(tk.getP_price());
			sr.setS_number(number);
			sr.setS_addnumber(0);
			sr.setS_money(tk.getP_price()*number);
			sr.setS_worker(w_id);
			sr.setS_usestate("未使用");
			sr.setS_state("已处理");
			Timestamp timeStamp = new Timestamp(date.getTime());
			sr.setS_date(timeStamp);
			if(isr.add(sr)) {
				flag=true;
			}
		}
		return flag;
	}
	//根据id查门票
	@Override
	public Tickets queryTickById(int id) {
		Tickets tk = itd.query(id);
		return tk;
	}
	//根据门票分类查门票
	@Override
	public Tickets queryTickByType(String p_type) {
		Tickets tk = itd.queryByName(p_type);
		return tk;
	}
	//修改门票信息
	@Override
	public boolean uptick(Tickets tk) {
		boolean b = itd.uptick(tk);
		return b;
	}
	//门票入库
	@Override
	public boolean addticknum(int p_id, int number,String w_id) {
		boolean flag = false;
		Tickets tk = itd.query(p_id);
		int num = tk.getP_number()+number;
		if(itd.saleTick(p_id,num)) {
			SalesRecord sr = new SalesRecord();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			sr.setS_id(Long.parseLong(sdf.format(date)));
			sr.setPrice_id(tk.getP_type());
			sr.setPrice_price(tk.getP_price());
			sr.setS_number(0);
			sr.setS_addnumber(number);
			sr.setS_money(0);
			sr.setS_worker(w_id);
			sr.setS_usestate("未使用");
			sr.setS_state("已处理");
			Timestamp timeStamp = new Timestamp(date.getTime());
			sr.setS_date(timeStamp);
			if(isr.add(sr)) {
				flag=true;
			}
		}
		return flag;
	}
	//删除门票
	@Override
	public boolean deltick(int p_id) {
		boolean deltick = itd.deltick(p_id);
		return deltick;
	}
	//添加门票
	@Override
	public boolean addtick(Tickets tk) {
		boolean addtick = itd.addtick(tk);
		return addtick;
	}


}
