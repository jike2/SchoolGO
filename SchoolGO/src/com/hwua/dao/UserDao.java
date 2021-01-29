package com.hwua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hwua.pojo.Goods;
import com.hwua.pojo.User;

public interface UserDao {
	public User login(@Param("username") String username,@Param("password") String password);
	
	public int register(User user);
	
	public List<User> queryAlluser();
	
	public int checkregister(User user);
	
	public User querymysell(@Param("userID")int userID);
	
	public User queryuserbyname(@Param("userName") String username);
	
	public List<User> querycanuseuser(@Param("userState")String userState,@Param("query")String query);
	
	public int upuserState(User user);
}
