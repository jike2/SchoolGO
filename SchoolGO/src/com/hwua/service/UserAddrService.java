package com.hwua.service;

import java.util.List;

import com.hwua.pojo.Addr;
import com.hwua.pojo.UserAddr;

public interface UserAddrService {

	public List<UserAddr> queryUserAddrs(int userid);
	
	public boolean addrnewuseraddr(UserAddr addr);
	
	public boolean upuseraddr(UserAddr addr);
}
