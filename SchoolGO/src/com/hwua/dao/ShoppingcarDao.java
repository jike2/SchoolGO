package com.hwua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hwua.pojo.Shoppingcar;

public interface ShoppingcarDao {

	public int addgoodcar(Shoppingcar shop);
	
	public List<Shoppingcar> querymycar(@Param("userName") String userName);
	
	public int delcargood(@Param("sid")int sid);
}
