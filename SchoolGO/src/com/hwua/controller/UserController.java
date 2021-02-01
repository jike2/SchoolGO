package com.hwua.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.hwua.pojo.Employee;
import com.hwua.pojo.Goods;
import com.hwua.pojo.Sellrecords;
import com.hwua.pojo.Shoppingcar;
import com.hwua.pojo.User;
import com.hwua.service.EmployeeService;
import com.hwua.service.SellrecordsService;
import com.hwua.service.ShoppingcarService;
import com.hwua.service.UserService;
import com.mysql.fabric.Response;
import com.sun.jmx.snmp.Timestamp;


@CrossOrigin
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private SellrecordsService sellrecordsService;
	@Autowired
	private ShoppingcarService shopcar;
	@Autowired
	private EmployeeService employeeservice;

	@RequestMapping(value="/login" ,method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String login(@RequestBody User user) {
		User login = userService.login(user.getUserName(), user.getUserPassword());
		Map<String,Object> map = new HashMap<String, Object>();
		if(login==null) {
			map.put("flag",false);
			map.put("msg","用户名或密码错误");
		}else {
			List<Shoppingcar> list = shopcar.querymycar(login.getUserName());
			int goodnum=0;
			double goodcarprivice=0;
			for(int i=0;i<list.size();i++){
				goodcarprivice=goodcarprivice+list.get(i).getShopNumber()*list.get(i).getGood().getGoodPrice();
			}
			map.put("goodnum", list.size());
			map.put("goodcarprivice", goodcarprivice);
			map.put("flag",true);
			map.put("user", login);
		}
		return JSON.toJSONString(map);
	}

	@RequestMapping(value="/register" ,method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String register(@RequestBody User user) {
		System.out.println(user);
		Map<String, Object> map = userService.register(user);
		return JSON.toJSONString(map);
	}
	@RequestMapping(value="/querymysell" ,method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String querymysell(String userName) {
		System.out.println("返回来的数据："+userName);
		List<Sellrecords> querymysell = sellrecordsService.querymysell(userName);
		return JSON.toJSONString(querymysell);
	}

	
	@RequestMapping(value = "/querycanuseuser",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String querycanuseuser(int page,int limit,String userSate)
			throws IOException {
		Map<String,Object> map = new HashMap<String, Object>();
		List<User> list = userService.querycanuseuser(userSate, null);
		List<User> subList = new ArrayList<User>();
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
	@RequestMapping(value = "/querycanuseuserbyname",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String querycanuseuserbyname(int page,int limit,String userSate,String query)
			throws IOException {
		System.out.println(page+"======="+limit+"========="+userSate+"=============="+query);
		Map<String,Object> map = new HashMap<String, Object>();
		List<User> list = userService.querycanuseuser(userSate, query);
		List<User> subList = new ArrayList<User>();
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
	@RequestMapping(value = "/upuserState",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String upuserState(int userID,String userState,String Cause,long empTell)
			throws IOException {
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println(userID+"==========="+userState+"========="+Cause+"========"+empTell);
		if(Cause==null || Cause=="") {
			map.put("flag", false);
			map.put("msg", "原因不能为空");
		}else {
			User user = new User();
			user.setUserID(userID);
			user.setUserState(userState);
			user.setUserCause(Cause);
			Employee employee = employeeservice.querymyemployee(empTell);
			user.setUserEmpID(employee.getEmpID());
			boolean b = userService.upuserState(user);
			if(b) {
				map.put("flag", true);
				if(userState.equals("可使用")) {
					map.put("msg", "启用成功");
				}else {
					map.put("msg", "注销成功");}
			}else {
				map.put("flag", false);
				if(userState.equals("可使用")) {
					map.put("msg", "启用失败");
				}else {
					map.put("msg", "注销失败");}
			}
		}
		return JSON.toJSONString(map);
	}
	
	@RequestMapping(value = "/upmyself",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String upmyself(@RequestParam("file") MultipartFile[] file,User user,HttpServletRequest request)
			throws IOException {
		System.out.println("进来了");
		Map<String,Object> map = new HashMap<String, Object>();
		System.out.println("这个file文件："+file);
		System.out.println(user+file.toString());
		return map.toString();
	}
}
