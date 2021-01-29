package com.hwua.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwua.dao.GoodsDao;
import com.hwua.dao.SellrecordsDao;
import com.hwua.pojo.Goods;
import com.hwua.pojo.Sellrecords;
import com.mysql.fabric.xmlrpc.base.Data;
@Service("SellrecordsService")
public class SellrecordsService implements com.hwua.service.SellrecordsService{

	@Autowired
	private SellrecordsDao sellrecordsdao;
	
	@Override
	public boolean deletesell(long sellid) {
		int i = sellrecordsdao.deletesell(sellid);
		return i>0;
	}

	@Override
	public List<Sellrecords> querymysell(String userName) {
		Sellrecords sell = new Sellrecords();
		sell.setSellBuyer(userName);
		List<Sellrecords> list = sellrecordsdao.querymysell(sell);
		System.out.println(list);
		return list;
	}

	@Override
	public boolean upsell(Sellrecords sell) {
		int i = sellrecordsdao.upsell(sell);
		return i>0;
	}

	@Override
	public List<Sellrecords> querymysellbyalipayid(long alipayid) {
		Sellrecords sell = new Sellrecords();
		sell.setSellalipayid(alipayid);
		List<Sellrecords> list = sellrecordsdao.querymysell(sell);
		return list;
	}

	@Override
	public List<Sellrecords> querymysellbysellstate(String username, String State) {
		List<Sellrecords> list = sellrecordsdao.querymysellbysellstate(username, State);
		System.out.println(username+"============"+State);
		return list;
	}

	@Override
	public Sellrecords querymysellbysellid(long sellid) {
		Sellrecords sellrecords = sellrecordsdao.querymysellbysellid(sellid);
		return sellrecords;
	}

	@Override
	public List<Sellrecords> querymyshopsellbysellstate(String username, String State) {
		List<Sellrecords> list = sellrecordsdao.querymyshopsellbysellstate(username, State);
		return list;
	}

	@Override
	public List<Sellrecords> queryAllsellrecord() {
		List<Sellrecords> allsellrecord = sellrecordsdao.queryAllsellrecord();
		return allsellrecord;
	}

	@Override
	public List<Sellrecords> queryallsellrecordbyname(String queryname) {
		List<Sellrecords> list = sellrecordsdao.queryallsellrecordbyname(queryname);
		return list;
	}


}
