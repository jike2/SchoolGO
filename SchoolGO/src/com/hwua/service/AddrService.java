package com.hwua.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hwua.pojo.Addr;
import com.hwua.pojo.Goodstype;

public interface AddrService {

	public List<Addr> queryAddr();
	
	public List<Goodstype> querygoodtype();
	
	public List<Goodstype> querygoodtypebytypeOne(String typeOne);
	
	public List<Addr> screeningaddr(String goodProvinces);
	
	public List<Addr> queryschool(String addrCity);
}
