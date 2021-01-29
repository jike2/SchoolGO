package com.hwua.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hwua.dao.GoodsDao;
import com.hwua.dao.UserAddrDao;
import com.hwua.dao.UserDao;
import com.hwua.pojo.Addr;
import com.hwua.pojo.Goods;
import com.hwua.pojo.Sellrecords;
import com.hwua.pojo.Shoppingcar;
import com.hwua.pojo.User;
import com.hwua.service.GoodsService;
import com.hwua.service.SellrecordsService;
import com.hwua.service.ShoppingcarService;

@CrossOrigin
@Controller
public class SellrecordsController {
	@Autowired
	private UserDao userdao;
	@Autowired
	private GoodsService goodsService;

	@Autowired
	private ShoppingcarService shopcar;

	@Autowired
	private SellrecordsService sellrecords;
	
	@RequestMapping(value="/confirmgood" ,method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String confirmgood(Long sellID,String userName,String userPassword,int goodID) {
		//		List<Goods> list = goodsService.queryindex(addr.getAddrCity());
		User user = userdao.login(userName, userPassword);
		Map<String,Object> map = new HashMap<String, Object>();
		if(user != null) {
			Sellrecords sell = new Sellrecords();
			sell.setSellID(sellID);
			sell.setSellState("已完成");
			boolean upsell = sellrecords.upsell(sell);
			if(upsell) {
				Goods goods = goodsService.querybyidAll(goodID);
				goods.setGoodState("下架");
				boolean upgood = goodsService.upgood(goods);
				map.put("msg", "收货成功");
				map.put("flag", true);
			}else {
				map.put("msg", "收货失败，请联系客服");
				map.put("flag", false);
			}
		}else {
			map.put("msg", "密码输入错误");
			map.put("flag", false);
		}
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/querymysellbyalipay" ,method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String querymysellbyalipay(long alipayid) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<Sellrecords> list = sellrecords.querymysellbyalipayid(alipayid);
		System.out.println("用户名"+list.get(0));
		List<Shoppingcar> carlist = shopcar.querymycar(list.get(0).getSellBuyer());
		System.out.println("购物车数量："+carlist.size());
		double goodcarprivice=0;
		for(int i=0;i<carlist.size();i++){
			goodcarprivice=goodcarprivice+carlist.get(i).getShopNumber()*carlist.get(i).getGood().getGoodPrice();
		}
		map.put("goodnum", list.size());
		map.put("goodcarprivice", goodcarprivice);
		map.put("list", list);
		return JSON.toJSONString(map);
	}
	@CrossOrigin
	@RequestMapping(value="/getmysell" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getmysell(String userName) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<Sellrecords> allsell = sellrecords.querymysellbysellstate(userName, "");
		map.put("allsell", allsell.size());//全部订单
		List<Sellrecords> payment = sellrecords.querymysellbysellstate(userName, "待付款");
		map.put("payment", payment.size());//待付款订单
		List<Sellrecords> sendgood = sellrecords.querymysellbysellstate(userName, "待发货");
		map.put("sendgood", sendgood.size());//待发货订单
		List<Sellrecords> forgood = sellrecords.querymysellbysellstate(userName, "待收货");
		map.put("forgood", forgood.size());//待收货订单
		List<Sellrecords> refund = sellrecords.querymysellbysellstate(userName, "退款");
		map.put("refund", refund.size());//退款中订单
		if(allsell.size()>=3) {
			map.put("data", allsell.subList(0,3));
		}else {
			map.put("data", allsell);
		}
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/querymysellbytype" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String querymysellbytype(String userName,String selltype) {
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println(selltype);
		List<Sellrecords> refund = sellrecords.querymysellbysellstate(userName, selltype);
		map.put("data", refund);
		return JSON.toJSONString(map);
	}

	@RequestMapping(value="/getmysellbysellid" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getmysellbysellid(Long sellid) {
		System.out.println("订单号为"+sellid);
		Sellrecords sellrecords2 = sellrecords.querymysellbysellid(sellid);
		return JSON.toJSONString(sellrecords2);
	}
	@RequestMapping(value="/Confirmthegoods" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String Confirmthegoods(String userName,String userpassword, Long sellid) {
		Map<String,Object> map = new HashMap<String, Object>();
		User login = userdao.login(userName, userpassword);
		if(login !=null) {
			Sellrecords sell = new Sellrecords();
			sell.setSellID(sellid);
			sell.setSellState("已完成");
			boolean b = sellrecords.upsell(sell);
			if(b) {
				map.put("flag", true);
				map.put("msg", "收货成功");
			}else {
				map.put("flag", false);
				map.put("msg", "收货失败");
			}
		}else {
			map.put("flag", false);
			map.put("msg", "密码输入错误");
		}
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/querymysend" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String querymysend(String userName,String selltype) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<Sellrecords> list = sellrecords.querymysellbysellstate(userName, selltype);
		map.put("data", list);
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/querymyshopsend" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String querymyshopsend(String userName,String selltype) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<Sellrecords> list = sellrecords.querymyshopsellbysellstate(userName, selltype);
		map.put("data", list);
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/sendgoods" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String sendgoods(long sellid,String SellCouriernumber) {
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println(sellid+"==========="+SellCouriernumber);
		Sellrecords sell = new Sellrecords();
		sell.setSellID(sellid);
		sell.setSellState("待收货");
		sell.setSellCouriernumber(SellCouriernumber);
		boolean b = sellrecords.upsell(sell);
		if(b) {
			map.put("flag", true);
			map.put("msg", "发货成功");
		}else {
			map.put("flag", false);
			map.put("msg", "发货失败");
		}
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/queryAllsellrecord" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String queryAllsellrecord(int page,int limit) {
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println(page+"============"+limit);
		List<Sellrecords> list = sellrecords.queryAllsellrecord();
		List<Sellrecords> subList = new ArrayList<Sellrecords>();
		if(list.size()<limit) {
			subList = list;
		}else {
			if((list.size()-(page-1)*limit)>limit) {
				subList = list.subList((page-1)*limit,(page-1)*limit+limit);
				System.out.println("第一层判断："+subList.size());
			}else {
				subList = list.subList((page-1)*limit,list.size());
				System.out.println("第二层判断："+subList.size());
			}
		}
		map.put("total", list.size());
		map.put("data", subList);
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/queryallsellrecordbyname" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String queryallsellrecordbyname(int page,int limit,String query) {
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println(page+"============"+limit+"=========="+query);
		List<Sellrecords> list = sellrecords.queryallsellrecordbyname(query);
		List<Sellrecords> subList = new ArrayList<Sellrecords>();
		if(list.size()<limit) {
			subList = list;
		}else {
			if((list.size()-(page-1)*limit)>limit) {
				subList = list.subList((page-1)*limit,(page-1)*limit+limit);
				System.out.println("第一层判断："+subList.size());
			}else {
				subList = list.subList((page-1)*limit,list.size());
				System.out.println("第二层判断："+subList.size());
			}
		}
		map.put("total", list.size());
		map.put("data", subList);
		return JSON.toJSONString(map);
	}
}
