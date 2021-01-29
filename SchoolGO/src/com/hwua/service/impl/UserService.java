package com.hwua.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwua.dao.UserDao;
import com.hwua.pojo.User;
import com.mysql.fabric.xmlrpc.base.Data;
import com.sun.jmx.snmp.Timestamp;
@Service("UserService")
public class UserService implements com.hwua.service.UserService{

	@Autowired
	private UserDao userdao;
	//登录方法
	@Override
	public User login(String usernam, String password) {
		User user = userdao.login(usernam, password);
		return user;
	}
	//注册方法
	@Override
	public Map<String, Object> register(User user) {
		HashMap<String, Object> map = new HashMap<String, Object>();
//		List<User> list = userdao.queryAlluser();
		User u1 = new User();
		u1.setUserName(user.getUserName());
		System.out.println("用户名验证"+userdao.checkregister(u1));
		if(userdao.checkregister(u1)>0){
			map.put("flag", false);
			map.put("msg", "用户名已存在");
			return map;
		}
		u1.setUserEmail(user.getUserEmail());
		System.out.println("邮箱验证"+userdao.checkregister(u1));
		if(userdao.checkregister(u1)>0) {
			map.put("flag", false);
			map.put("msg", "该邮箱已注册");
			return map;
		}
		u1.setUserTell(user.getUserTell());
		System.out.println("手机号验证"+userdao.checkregister(u1));
		if(userdao.checkregister(u1)>0) {
			map.put("flag", false);
			map.put("msg", "该手机号码已注册");
			return map;
		}
		Date date = new Date();
		user.setUserRegistertime(date);
		System.out.println(user);
		user.setUserState("可使用");
		int i = userdao.register(user);
		if(i>0) {
			map.put("flag", true);
			map.put("msg", "注册成功");
			return map;
		}else {
			map.put("flag", false);
			map.put("msg", "注册失败");
			return map;
		}
	}
	@Override
	public User queryuserbyname(String username) {
		User user = userdao.queryuserbyname(username);
		return user;
	}
	@Override
	public List<User> querycanuseuser(String userState, String query) {
		List<User> querycanuseuser = userdao.querycanuseuser(userState, query);
		return querycanuseuser;
	}
	@Override
	public boolean upuserState(User user) {
		int i = userdao.upuserState(user);
		return i>0;
	}
	

}
