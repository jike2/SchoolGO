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
import com.hwua.pojo.Addr;
import com.hwua.pojo.Goods;
import com.hwua.pojo.Goodstype;
import com.hwua.pojo.UserAddr;
import com.hwua.service.AddrService;
import com.hwua.service.GoodsService;
import com.hwua.service.UserAddrService;

@CrossOrigin
@Controller
public class AddrController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private UserAddrService userAddrService;
	@Autowired
	private AddrService addrService;
	//获取用户收货地址
	@RequestMapping(value="/queryaddrandtype" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String queryaddr() {
		Map<String,Object> map = new HashMap<String, Object>();
		//获取省-市-高校信息
		List<Addr> addrlist = addrService.queryAddr();
		map.put("goodaddr", addrlist);
		List<Goodstype> querygoodtype = addrService.querygoodtype();
		map.put("goodtype", querygoodtype);
		//获取当前用户的收货地址
		return JSON.toJSONString(map);
	}
	//获取用户收货地址
	@RequestMapping(value="/querymygoodtext" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String querymygoodtext(int goodID) {
		Map<String,Object> map = new HashMap<String, Object>();
		Goods goods = goodsService.querybyidAll(goodID);
		map.put("goods", goods);
		//获取省-市-高校信息
		List<Addr> addrlist = addrService.queryAddr();
		map.put("goodaddr", addrlist);
		List<Goodstype> querygoodtype = addrService.querygoodtype();
		map.put("goodtype", querygoodtype);
		//获取当前用户的收货地址
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/querygoodtype" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String querygoodtype(String typeOne) {
		List<Goodstype> list = addrService.querygoodtypebytypeOne(typeOne);
		return JSON.toJSONString(list);
	}
	@RequestMapping(value="/screeningaddr" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String screeningaddr(String goodProvinces) {
		System.out.println(goodProvinces);
		List<Addr> list = addrService.screeningaddr(goodProvinces);
		return JSON.toJSONString(list);
	}
	@RequestMapping(value="/queryschool" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String queryschool(String addrCity) {
		List<Addr> list = addrService.queryschool(addrCity);
		return JSON.toJSONString(list);
	}
	@RequestMapping(value="/queryaddnewaddr" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String queryaddnewaddr(Addr addr) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<Addr> list = new ArrayList<Addr>();
		if(addr.getAddrProvinces().equals("") || addr.getAddrProvinces().equals(null)) {
			list = addrService.queryAddr();
		}else if(addr.getAddrCity().equals("") || addr.getAddrCity().equals(null)) {
			list = addrService.screeningaddr(addr.getAddrProvinces());
		}else {
			list = addrService.queryschool(addr.getAddrCity());
		}
		map.put("data", list);
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/addnewuseraddr" ,method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String addnewuseraddr(@RequestBody UserAddr addr) {
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println("传回来的数据"+addr);
		if(addr.getUseradProvinces().equals("") || addr.getUseradCity().equals("") || addr.getUseradSchool().equals("") || addr.getUseradDetailed().equals("") || addr.getUseradName().equals("") || addr.getUseradTell()==0) {
			map.put("flag", false);
			map.put("msg", "信息填写不完整");
		}else {
			boolean b = userAddrService.addrnewuseraddr(addr);
			if(b) {
				map.put("flag", true);
			}else {
				map.put("flag", false);
				map.put("msg", "添加失败");
			}
		}
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/upuseraddr" ,method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String upuseraddr(@RequestBody UserAddr addr) {
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println(addr);
		if(addr.getUseradProvinces().equals("") || addr.getUseradCity().equals("") || addr.getUseradSchool().equals("") || addr.getUseradDetailed().equals("") || addr.getUseradName().equals("") || addr.getUseradTell()==0) {
			map.put("flag", false);
			map.put("msg", "信息填写不完整");
		}else {
			boolean b = userAddrService.upuseraddr(addr);
			if(b) {
				map.put("flag", true);
			}else {
				map.put("flag", false);
				map.put("msg", "修改失败");
			}
		}
		return JSON.toJSONString(map);
	}
}
