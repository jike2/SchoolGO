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
import com.hwua.pojo.Employee;
import com.hwua.pojo.Shoppingcar;
import com.hwua.pojo.User;
import com.hwua.service.EmployeeService;

@CrossOrigin
@Controller
public class EmployeeController {
	
	
	@Autowired
	private EmployeeService employeeservice;
	
	@RequestMapping(value="/adminlogin" ,method=RequestMethod.POST)
	public @ResponseBody String login(@RequestBody Employee emp) {
		Map<String,Object> map = new HashMap<String, Object>();
		Employee adminlogin = employeeservice.adminlogin(emp);
		if(adminlogin != null) {
			map.put("flag", true);
			map.put("userId", adminlogin);
		}else {
			map.put("flag", false);
			map.put("msg", "用户名或密码错误");
		}
		return JSON.toJSONString(map);
	}
}
