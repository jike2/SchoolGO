package com.hwua.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.hwua.pojo.Goods;
import com.hwua.pojo.User;
public interface UserService {

	public User login(String usernam,String password);
	
	public Map<String, Object> register(User user);
	
	public User queryuserbyname(String username);

	public List<User> querycanuseuser(String userState,String query);
	
	public boolean upuserState(User user);
}
