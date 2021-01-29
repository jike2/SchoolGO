package com.hwua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hwua.pojo.Addr;
import com.hwua.pojo.Goodstype;

public interface AddrDao {

	public List<Addr> queryAddr();
	public List<Goodstype> querygoodtype();
	public List<Goodstype> querygoodtypebytypeOne(@Param("typeOne") String typeOne);
	
	public List<Addr> screeningaddr(@Param("goodProvinces") String goodProvinces);
	
	public List<Addr> queryschool(@Param("addrCity") String addrCity);
}
