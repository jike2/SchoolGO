package com.hwua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hwua.pojo.Addr;
import com.hwua.pojo.UserAddr;

public interface UserAddrDao {

	public List<UserAddr> queryUserAddrs(@Param("userid")int userid);
	
	
}
