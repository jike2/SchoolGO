package com.hwua.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwua.dao.GoodsDao;
import com.hwua.dao.SellrecordsDao;
import com.hwua.pojo.Addr;
import com.hwua.pojo.Goods;
import com.hwua.pojo.Sellrecords;
import com.hwua.pojo.Shoppingcar;
import com.mysql.fabric.xmlrpc.base.Data;
@Service("GoodsService")
public class GoodsService implements com.hwua.service.GoodsService{

	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private SellrecordsDao sellrecordsdao;
	@Override
	public List<Goods> queryindex(String goodname) {
		List<Goods> list = goodsDao.queryindex(goodname);
		return list;
	}
	@Override
	public Goods querybyid(int goodID) {
		Goods goods = goodsDao.querybyid(goodID);
		System.out.println(goods);
		return goods;
	}
	@Override
	public Map<String, Object> paygood(List<Sellrecords> sell) {
		Map map =new HashMap<String, Object>();
		List<Integer> nullgood=new ArrayList<Integer>();
		List<Goods> goodlist=new ArrayList<Goods>();
		String sellName=null;
		for(int i=0;i<sell.size();i++) {
			sell.get(i).setSellState("待付款");
			Goods goods = querybyid(sell.get(i).getSellgoodID());
			int num=goods.getGoodNumber()-sell.get(i).getSellNumber();
			if(num<0) {
				map.put("msg","商品数量不足或已下架");
				map.put("goodName", goods.getGoodName());
				map.put("flag", false);
				return map;
			}else {
				goods.setGoodNumber(num);
			}
			goodlist.add(goods);
			if(goods==null) {
				nullgood.add(sell.get(i).getSellgoodID());
			}
			if(i==0) {
				sellName=goods.getGoodName();
			}else {
				sellName += " "+goods.getGoodName();
			}
		}
		if(nullgood.size()>0) {
			map.put("msg","已售出或下架");
			map.put("goodid", nullgood);
			map.put("flag", false);
		}else {
			int paygood = sellrecordsdao.paygood(sell);
			if(paygood>0) {
				for(int i=0;i<goodlist.size();i++) {
					boolean b = upgood(goodlist.get(i));
					map.put("sellName", sellName);
					map.put("flag", true);
				}
			}else {
				for(int i=0;i<sell.size();i++) {
					sell.get(i).setSellState("购买失败");
					sellrecordsdao.upsell(sell.get(i));
				}
				map.put("msg","购买失败");
				map.put("flag", false);
			}
		}
		return map;
	}
	@Override
	public boolean upgood(Goods good) {
		int i = goodsDao.upgood(good);
		return i>0;
	}
	@Override
	public boolean addgood(Goods good) {
		Date date = new Date();
		good.setGoodSubtime(date);
		good.setGoodState("待审核");
		int i = goodsDao.addgood(good);
		return i>0;
	}
	@Override
	public Goods querybyidAll(int goodID) {
		Goods goods = goodsDao.querybyidAll(goodID);
		System.out.println(goods);
		return goods;
	}
	@Override
	public int querybynamecount(String querygoodname, Addr addr) {
		int i = goodsDao.querybynamecount(querygoodname, addr);
		return i;
	}
	@Override
	public List<Goods> querybyname(String querygoodname, Addr addr,int page,int limit) {
		List<Goods> list = goodsDao.querybyname(querygoodname, addr,page,limit);
		return list;
	}
	@Override
	public List<Goods> querymygoodbygoodstate(String userName, String goodstate) {
		List<Goods> list = goodsDao.querymygoodbygoodstate(userName, goodstate);
		return list;
	}
	
	@Override
	public boolean delmygood(int goodID) {
		int i = goodsDao.delmygood(goodID);
		return i>0;
	}
	@Override
	public List<Goods> queryautogood(String goodState, double goodPricestart,double goodPricefinish) {
		List<Goods> list = goodsDao.queryautogood(goodState, goodPricestart, goodPricefinish);
		return list;
	}
	@Override
	public List<Goods> queryautogoodbyname(String goodState, double goodPricestart, double goodPricefinish,
			String queryname) {
		List<Goods> list = goodsDao.queryautogoodbyname(goodState, goodPricestart, goodPricefinish, queryname);
		return list;
	}
	@Override
	public List<Goods> queryallgoodbystate(String goodstate) {
		List<Goods> list = goodsDao.queryallgoodbystate(goodstate);
		return list;
	}
	@Override
	public List<Goods> queryordergoodsbynameAll(String querygoodname, String goodStete) {
		List<Goods> list = goodsDao.queryordergoodsbynameAll(querygoodname, goodStete);
		return list;
	}

}
