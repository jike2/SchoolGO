package com.hwua.controller;

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
import com.hwua.pojo.Shoppingcar;
import com.hwua.service.ShoppingcarService;

@CrossOrigin
@Controller
public class ShoppingcarController {

	@Autowired
	private ShoppingcarService shoppcarservice;
	
	@RequestMapping(value="/addgoodforcart" ,method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String addgoodforcart(@RequestBody Shoppingcar shop) {
		Map<String,Object> map = new HashMap<String, Object>();
		boolean addgoodcar = shoppcarservice.addgoodcar(shop);
		if(addgoodcar) {
			map.put("flag", true);
			map.put("msg", "添加成功");
		}else {
			map.put("flag", false);
			map.put("msg", "添加失败");
		}
		return JSON.toJSONString(map);
	}
	
	@RequestMapping(value="/querymycar" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String querymycar(String userName) {
		System.out.println(userName);
		List<Shoppingcar> list = shoppcarservice.querymycar(userName);
		System.out.println(list.size());
		System.out.println(list);
//		Goods goods = goodsService.querybyid(goodID);
		return JSON.toJSONString(list);
	}
	@RequestMapping(value="/delcargood" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String delcargood(int cargoodid) {
		System.out.println(cargoodid);
		boolean delcargood = shoppcarservice.delcargood(cargoodid);
		return JSON.toJSONString(delcargood);
	}
}
