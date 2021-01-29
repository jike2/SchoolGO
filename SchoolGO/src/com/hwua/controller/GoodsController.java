package com.hwua.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.hwua.dao.RefunsedGoodDao;
import com.hwua.dao.SellrecordsDao;
import com.hwua.pojo.Addr;
import com.hwua.pojo.Employee;
import com.hwua.pojo.Goods;
import com.hwua.pojo.Listsell;
import com.hwua.pojo.RefunsedGood;
import com.hwua.pojo.Sellrecords;
import com.hwua.pojo.Shoppingcar;
import com.hwua.pojo.User;
import com.hwua.pojo.UserAddr;
import com.hwua.service.AddrService;
import com.hwua.service.EmployeeService;
import com.hwua.service.GoodsService;
import com.hwua.service.ShoppingcarService;
import com.hwua.service.UserAddrService;
import com.hwua.service.UserService;
import com.hwua.util.AlipayConfig;
import com.mysql.fabric.xmlrpc.base.Data;

import sun.misc.BASE64Decoder;

@CrossOrigin
@Controller
public class GoodsController {
	@Autowired
	private GoodsService goodsService;

	@Autowired
	private SellrecordsDao selldao;

	@Autowired
	private UserAddrService userAddrService;

	@Autowired
	private ShoppingcarService shoppingcarService;

	@Autowired
	private AddrService addrService;

	@Autowired
	private UserService userservice;
	
	@Autowired
	private RefunsedGoodDao refunsed;
	@Autowired
	private EmployeeService employeeservice;
	//进入首页查询对应模块的数据
	@RequestMapping(value="/queryindex.do" ,method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String queryindex() {
		Map<String,Object> map = new HashMap<String, Object>();
		List<Goods> list = goodsService.queryindex("服饰");
		List<Goods> list2 = goodsService.queryindex("学生用品");
		map.put("dress", list);
		map.put("stusupply", list2);
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/querygoodsbyid.do" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String querygoodsbyid(int goodID) {
		//根据传回来的商品id查找商品信息
		Goods goods = goodsService.querybyid(goodID);
		return JSON.toJSONString(goods);
	}
	@RequestMapping(value="/paymentbyid" ,method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String paymentbyid(@RequestBody Listsell roleIds) {
		List<Goods> goodid = roleIds.getGoodid();
		Map<String,Object> map = new HashMap<String, Object>();
		//获取商品信息
		List<Goods> list = new ArrayList<Goods>();
		for(int i=0;i<goodid.size();i++) {
			Goods goods = goodsService.querybyid(goodid.get(i).getGoodID());
			list.add(goods);
		}
		map.put("good", list);
		//获取收货地址
		List<UserAddr> queryUserAddrs = userAddrService.queryUserAddrs(Integer.parseInt(goodid.get(0).getGooduserName()));
		map.put("addr", queryUserAddrs);
		//获取当前用户的收货地址
		return JSON.toJSONString(map);
	}

	@RequestMapping(value="/paymoney" ,method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String paymoney(@RequestBody Listsell sell) throws AlipayApiException {
		List<Sellrecords> list = sell.getSell();
		double money=0;
		Date date = new Date();
		String sellIDs=null;
		//		为传回来的订单添加订单号（订单号通过获取当前时间转为long类型）
		for(int i=0;i<list.size();i++) {
			Sellrecords sellgood = list.get(i);
			System.out.println(sell);
			Date date1 = new Date();
			long time = date1.getTime()+i;
			System.out.println(time);
			sellgood.setSellID(time);
			sellgood.setSellOrdertime(date);
			sellgood.setSellalipayid(date.getTime());
			money = money+sellgood.getSellMoney();
		}
		//将订单信息插入订单表
		Map<String, Object> map = goodsService.paygood(list);
		//如果是购物车购买，在购买完成后删除购物车中对应商品
			for(int i=0;i<list.size();i++) {
				String shopid=String.valueOf(list.get(i).getSellgoodID());
				List<Shoppingcar> carlist = shoppingcarService.querymycar(list.get(i).getSellBuyer());
				for(int j=0;j<carlist.size();j++) {
					String carshopid = String.valueOf(carlist.get(j).getShopID());
					if(shopid.equals(carshopid)) {
						shoppingcarService.delcargood(carlist.get(j).getSid());
					}
				}

			}
		String sellName = (String) map.get("sellName");
		if(map.get("flag").equals(true)) {
			System.out.println("插入成功，开始跳转支付宝界面");
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);
			//设置请求参数
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl(AlipayConfig.return_url);
			alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

			//商户订单号，商户网站订单系统中唯一订单号，必填
			//	        String out_trade_no = request.getParameter("Order");
			String out_trade_no = String.valueOf(date.getTime());
			//付款金额，必填  ShopName
			String total_amount = String.valueOf(money);
			//订单名称，必填
			String subject = sellName;
			//商品描述，可空
			String body = "购物测试";
			// 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
			String timeout_express = "30m";
			alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
					+ "\"total_amount\":\"" + total_amount + "\","
					+ "\"subject\":\"" + subject + "\","
					+ "\"body\":\"" + body + "\","
					+ "\"timeout_express\":\""+ timeout_express +"\","
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			//请求
			String url = "";
			try {
				url = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
			} catch (AlipayApiException e) {
				e.printStackTrace();
			}
			System.out.println("结果"+url);
			map.put("url", url);
		}

		return JSON.toJSONString(map);
	}

	//添加商品
	@RequestMapping(value = "/addgood",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String updateUserPostsImage(@RequestParam("file") MultipartFile[] file,Goods good,HttpServletRequest request)
			throws IOException {
		Map<String,Object> map = new HashMap<String, Object>();
//		User user = userservice.queryuserbyname(good.getGooduserName());
		String img=null;
		//循环下载图片
		for(int i=0;i<file.length;i++) {
			Date date = new Date();
			long time = date.getTime();
			String imgs=time+".jpg";
			if(i==0) {
				img=imgs;
			}else {
				img=img+","+imgs;
			}
			//			String newFileName = UUID.randomUUID() + file[i].getOriginalFilename();
			file[i].transferTo(new File("E:\\2020年实训\\毕业设计\\校园GO电子商城\\web\\images",imgs));
		}
		good.setGoodImg(img);
		//开始上架商品
		boolean addgood = goodsService.addgood(good);
		map.put("flag", addgood);
		if(addgood) {
			map.put("flag", true);
			map.put("msg", "上架成功");
		}else {
			map.put("flag", false);
			map.put("msg", "上架失败，请联系管理员");
		}
		return JSON.toJSONString(map);
	}


	//修改商品
	@RequestMapping(value = "/upmygood",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String upmygood(@RequestParam("file") MultipartFile[] file,Goods good,HttpServletRequest request)
			throws IOException {
		Map<String,Object> map = new HashMap<String, Object>();
		String img=null;
		//循环下载图片
		for(int i=0;i<file.length;i++) {
			Date date = new Date();
			long time = date.getTime();
			String imgs=time+".jpg";
			if(i==0) {
				img=imgs;
			}else {
				img=img+","+imgs;
			}
			//			String newFileName = UUID.randomUUID() + file[i].getOriginalFilename();
			file[i].transferTo(new File("E:\\2020年实训\\毕业设计\\校园GO电子商城\\web\\images",imgs));
		}
		good.setGoodImg(img);
		Date date = new Date();
		good.setGoodSubtime(date);
		good.setGoodState("待审核");
		if(file.length==0) {
			Goods goods = goodsService.querybyidAll(good.getGoodID());
			good.setGoodImg(goods.getGoodImg());
		}
		//开始上架商品
		boolean addgood = goodsService.upgood(good);
		if(addgood) {
			map.put("flag", true);
			map.put("msg", "提交成功，等待管理员审核");
		}else {
			map.put("flag", true);
			map.put("msg", "提交成功，等待管理员审核");
		}
		return JSON.toJSONString(map);
	}
	/**
	 * 同步跳转
	 *
	 * @param request
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/returnUrl.do")
	public void returnUrl(HttpServletRequest request,HttpServletResponse resp) throws Exception {
		// 获取支付宝GET过来反馈信息（官方固定代码）
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		Sellrecords sell = new Sellrecords();
		long sellid =0;
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
			System.out.println(name+"==="+valueStr);
			if(name.equals("out_trade_no")){
				sellid = Long.parseLong(valueStr);
				sell.setSellalipayid(sellid);
				sell.setSellState("待发货");
			}
		}
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.sign_type); 
		// 调用SDK验证签名
		// 返回界面
		if (signVerified) {
			System.out.println("前往支付成功页面");
			selldao.upsell(sell);
			//			mav.setViewName("http://127.0.0.1:5500/paysuccess.html");
			resp.sendRedirect("http://127.0.0.1:5500/paysuccess.html?sellid="+sellid);//已成功
		} else {
			System.out.println("前往支付失败页面");
			//            mav.setViewName("failReturn");
			resp.sendRedirect("http://127.0.0.1:5500/payerror.html?sellid="+sellid);
		}
	}
	/**
	 * 支付宝服务器异步通知
	 *
	 * @param request
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/notifyUrl.do")
	public void notifyUrl(HttpServletRequest request) throws Exception {
		// 获取支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.sign_type); // 调用SDK验证签名

		if (signVerified) { 
			// 验证成功 更新订单信息
			System.out.println("异步通知成功");
			// 商户订单号
			String out_trade_no = request.getParameter("out_trade_no");
			Sellrecords sell = new Sellrecords();
			long sellid = Long.parseLong(out_trade_no);
			sell.setSellID(sellid);
			sell.setSellState("已付款");
			selldao.upsell(sell);
			// 交易状态
			String trade_status = request.getParameter("trade_status");
			// 修改数据库
		} else {
			System.out.println("异步通知失败");
		}
	}
	//搜索框模糊查询
	@RequestMapping(value="/querygoodbyname.do" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String querygoodbyname(String queryname,Integer page,Integer limit,Addr addr) {
		System.out.println("关键字："+queryname);
		System.out.println("当前页码："+page);
		System.out.println("每页数据条数："+limit);
		System.out.println("筛选字段："+addr);
		Map<String,Object> map = new HashMap<String, Object>();
		int i = goodsService.querybynamecount(queryname, addr);
		List<Addr> addrlist=null;
		List<Goods> querybyname = goodsService.querybyname(queryname, addr,(page-1)*limit,limit);
		System.out.println(querybyname);
		if(addr.getAddrProvinces().equals("")) {
			addrlist = addrService.queryAddr();
		}else if(addr.getAddrCity().equals("") && !addr.getAddrProvinces().equals("")) {
			addrlist = addrService.screeningaddr(addr.getAddrProvinces());
		}else if(!addr.getAddrProvinces().equals("") && !addr.getAddrCity().equals("")){
			addrlist = addrService.queryschool(addr.getAddrCity());
		}
		map.put("goodnumber", i);
		map.put("list", querybyname);
		map.put("addr", addrlist);
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/getmygood" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getmygood(String userName) {
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println(userName);
		List<Goods> shangjialist = goodsService.querymygoodbygoodstate(userName, "上架");
		map.put("shangjialist", shangjialist.size());
		List<Goods> xiajialiist = goodsService.querymygoodbygoodstate(userName, "下架");
		map.put("xiajialiist", xiajialiist.size());
		List<Goods> shenheliist = goodsService.querymygoodbygoodstate(userName, "待审核");
		map.put("shenheliist", shenheliist.size());
		List<Sellrecords> daifahuoliist = selldao.querymyshopsellbysellstate(userName, "待发货");
		map.put("daifahuoliist", daifahuoliist.size());
		List<Sellrecords> yifahuoliist = selldao.querymyshopsellbysellstate(userName, "待收货");
		map.put("yifahuoliist", yifahuoliist.size());
		List<Sellrecords> tuikuanlist = selldao.querymyshopsellbysellstate(userName, "退款中");
		map.put("tuikuanlist", tuikuanlist.size());
		List<Sellrecords> yiwanchenglist = selldao.querymyshopsellbysellstate(userName, "已完成");
		map.put("yiwanchenglist", yiwanchenglist.size());
		map.put("data", shangjialist);
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/downmygood" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String downmygood(int goodid) {
		Map<String,Object> map = new HashMap<String, Object>();
		Goods goods = goodsService.querybyidAll(goodid);
		goods.setGoodState("下架");
		Date date = new Date();
		goods.setGoodSubtime(date);
		boolean b = goodsService.upgood(goods);
		if(b) {
			map.put("flag", true);
			map.put("msg", "下架成功");
		}else {
			map.put("flag", false);
			map.put("msg", "下架失败");
		}
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/getmygoodbytype" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getmygoodbytype(String userName,String goodtype) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<Goods> list = goodsService.querymygoodbygoodstate(userName, goodtype);
		map.put("data", list);
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/remomygood" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String remomygood(int goodid) {
		Map<String,Object> map = new HashMap<String, Object>();
		boolean b = goodsService.delmygood(goodid);
		if(b) {
			map.put("msg", "取消成功");
			map.put("flag", true);
		}else {
			map.put("msg", "取消失败");
			map.put("flag", false);
		}
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/queryordergoods" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String queryordergoods(int page,int limit, long empID) {
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println(page+"=========="+limit+"======="+empID);
		Employee employee = employeeservice.querymyemployee(empID);
		List<Goods> goodlist = new ArrayList<Goods>();
		switch (employee.getEmpGmlevel()) {
		case 1:
			goodlist = goodsService.queryautogood("待审核", 0, 1000);
			break;
		case 2:
			goodlist = goodsService.queryautogood("待审核", 1001, 2000);
			break;
		case 3:
			goodlist = goodsService.queryautogood("待审核", 2001, 3000);
			break;
		case 4:
			goodlist = goodsService.queryautogood("待审核", 3001, 4000);
			break;
		case 5:
			goodlist = goodsService.queryautogood("待审核", 4001, 5000);
			break;
		case 6:
			goodlist = goodsService.queryautogood("待审核", 5001, 6000);
			break;
		case 7:
			goodlist = goodsService.queryautogood("待审核", 6001, 7000);
			break;
		case 8:
			goodlist = goodsService.queryautogood("待审核", 7001, 8000);
			break;
		case 9:
			goodlist = goodsService.queryautogood("待审核", 8001, 9000);
			break;
		case 10:
			goodlist = goodsService.queryautogood("待审核", 9001, 10000);
			break;
		}
		int count=0;
		if(goodlist.size()%limit==0) {
			count=goodlist.size()/limit;
		}else {
			count=goodlist.size()/limit+1;
		}
		List<Goods> subList=new ArrayList<Goods>();
		if(goodlist.size()<limit) {
			subList = goodlist;
		}else {
			if((goodlist.size()-(page-1)*limit)>limit) {
				subList = goodlist.subList((page-1)*limit,(page-1)*limit+limit);
			}else {
				subList = goodlist.subList((page-1)*limit,goodlist.size());
			}
		}
		map.put("total", count);
		map.put("data", subList);
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/queryordergoodsbyname" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String queryordergoodsbyname(int page,int limit, long empID,String query) {
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println(page+"=========="+limit+"======="+empID+"==========="+query);
		Employee employee = employeeservice.querymyemployee(empID);
		List<Goods> goodlist = new ArrayList<Goods>();
		switch (employee.getEmpGmlevel()) {
		case 1:
			goodlist = goodsService.queryautogoodbyname("待审核", 0, 1000, query);
			break;
		case 2:
			goodlist = goodsService.queryautogoodbyname("待审核", 1001, 2000, query);
			break;
		case 3:
			goodlist = goodsService.queryautogoodbyname("待审核", 2001, 3000, query);
			break;
		case 4:
			goodlist = goodsService.queryautogoodbyname("待审核", 3001, 4000, query);
			break;
		case 5:
			goodlist = goodsService.queryautogoodbyname("待审核", 4001, 5000, query);
			break;
		case 6:
			goodlist = goodsService.queryautogoodbyname("待审核", 5001, 6000, query);
			break;
		case 7:
			goodlist = goodsService.queryautogoodbyname("待审核", 6001, 7000, query);
			break;
		case 8:
			goodlist = goodsService.queryautogoodbyname("待审核", 7001, 8000, query);
			break;
		case 9:
			goodlist = goodsService.queryautogoodbyname("待审核", 8001, 9000, query);
			break;
		case 10:
			goodlist = goodsService.queryautogoodbyname("待审核", 9001, 10000, query);
			break;
		}
		int count=0;
		if(goodlist.size()%limit==0) {
			count=goodlist.size()/limit;
		}else {
			count=goodlist.size()/limit+1;
		}
		List<Goods> subList=new ArrayList<Goods>();
		if(goodlist.size()<limit) {
			subList = goodlist;
		}else {
			if((goodlist.size()-(page-1)*limit)>limit) {
				subList = goodlist.subList((page-1)*limit,(page-1)*limit+limit);
			}else {
				subList = goodlist.subList((page-1)*limit,goodlist.size());
			}
		}
		
		map.put("total", count);
		map.put("data", subList);
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/queryallupgood" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String queryallupgood(int page,int limit,String query) {
		Map<String,Object> map = new HashMap<String, Object>();
//		goodsService.queryallgoodbystate("")
		System.out.println(page+"=========="+limit+"=========="+query);
		List<Goods> list = goodsService.queryallgoodbystate(query);
		List<Goods> subList = new ArrayList<Goods>();
		if(list.size()<limit) {
			subList = list;
		}else {
			if((list.size()-(page-1)*limit)>limit) {
				subList = list.subList((page-1)*limit,(page-1)*limit+limit);
			}else {
				subList = list.subList((page-1)*limit,list.size());
			}
		}
		int count=0;
		if(list.size()%limit==0) {
			count=list.size()/limit;
		}else {
			count=list.size()/limit+1;
		}
		map.put("total", count);
		map.put("data", subList);
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/empdowngood" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String empdowngood(int goodID,long empTell) {
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println(goodID+"==========="+empTell);
		Goods good = goodsService.querybyid(goodID);
		Employee employee = employeeservice.querymyemployee(empTell);
		good.setgoodEmpID(employee.getEmpID());
		good.setGoodState("下架");
		Date date = new Date();
		good.setGoodShelftime(date);
		boolean upgood=false;
		switch (employee.getEmpGmlevel()) {
		case 1:
			if(good.getGoodPrice()<=1000) {
				upgood = goodsService.upgood(good);
			}
			break;
		case 2:
			if(good.getGoodPrice()<=2000) {
				upgood = goodsService.upgood(good);
			}
			break;
		case 3:
			if(good.getGoodPrice()<=3000) {
				upgood = goodsService.upgood(good);
			}
			break;
		case 4:
			if(good.getGoodPrice()<=4000) {
				upgood = goodsService.upgood(good);
			}
			break;
		case 5:
			if(good.getGoodPrice()<=5000) {
				upgood = goodsService.upgood(good);
			}
			break;
		case 6:
			if(good.getGoodPrice()<=6000) {
				upgood = goodsService.upgood(good);
			}
			break;
		case 7:
			if(good.getGoodPrice()<=7000) {
				upgood = goodsService.upgood(good);
			}
			break;
		case 8:
			if(good.getGoodPrice()<=8000) {
				upgood = goodsService.upgood(good);
			}
			break;
		case 9:
			if(good.getGoodPrice()<=9000) {
				upgood = goodsService.upgood(good);
			}
			break;
		case 10:
			if(good.getGoodPrice()<=10000) {
				upgood = goodsService.upgood(good);
			}
			break;
		}
		if(upgood) {
			map.put("flag",true);
			map.put("msg","下架成功");
		}else {
			map.put("flag",false);
			map.put("msg","权限不足");
		}
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/queryordergoodsbynameAll" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String queryordergoodsbynameAll(int page,int limit,String query) {
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println(page+"=============="+limit+"=============="+query);
		
		List<Goods> list = goodsService.queryordergoodsbynameAll(query, "上架");
		List<Goods> subList = new ArrayList<Goods>();
		if(list.size()<limit) {
			subList = list;
		}else {
			if((list.size()-(page-1)*limit)>limit) {
				subList = list.subList((page-1)*limit,(page-1)*limit+limit);
			}else {
				subList = list.subList((page-1)*limit,list.size());
			}
		}
		int count=0;
		if(list.size()%limit==0) {
			count=list.size()/limit;
		}else {
			count=list.size()/limit+1;
		}
		map.put("total", count);
		map.put("data", subList);
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/adminauditgood" ,method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String adminauditgood(int goodID,int goodState,String Cause,long empTell) {
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println(goodID+"====="+goodState+"======="+Cause+"======="+empTell);
		Goods goods = goodsService.querybyidAll(goodID);
		Employee emp = employeeservice.querymyemployee(empTell);
		if(goodState==2) {
			goods.setGoodState("上架");
			Date date = new Date();
			goods.setgoodEmpID(emp.getEmpID());
			goods.setGoodShelftime(date);
			boolean upgood = goodsService.upgood(goods);
			if(upgood) {
				map.put("flag", true);
				map.put("msg", "商品已上架");
			}else{
				map.put("flag", false);
				map.put("msg", "系统异常，审核失败");
			}
		}else {
			goods.setGoodState("未通过审核");
			Date date = new Date();
			goods.setgoodEmpID(emp.getEmpID());
			goods.setGoodShelftime(date);
			boolean upgood = goodsService.upgood(goods);
			RefunsedGood refgood = new RefunsedGood();
			refgood.setRefgoodID(goodID);
			refgood.setRefgoodCause(Cause);
			refgood.setReftime(date);
			refgood.setRefempID(emp.getEmpID());
			refunsed.addrefGood(refgood);
			if(upgood) {
				map.put("flag", true);
				map.put("msg", "操作已完成");
			}
		}
		return JSON.toJSONString(map);
	}
}
