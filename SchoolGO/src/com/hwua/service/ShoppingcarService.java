package com.hwua.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hwua.pojo.Shoppingcar;

public interface ShoppingcarService {

	public boolean addgoodcar(Shoppingcar shop);
	
	public List<Shoppingcar> querymycar(String userName);
	
	public boolean delcargood(int sid);
}
