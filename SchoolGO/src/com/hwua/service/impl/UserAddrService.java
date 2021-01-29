package com.hwua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwua.dao.UserAddrDao;
import com.hwua.pojo.Addr;
import com.hwua.pojo.UserAddr;
@Service("UserAddrService")
public class UserAddrService implements com.hwua.service.UserAddrService{

	@Autowired
	private UserAddrDao useraddrdao;
	@Override
	public List<UserAddr> queryUserAddrs(int userid) {
		return useraddrdao.queryUserAddrs(userid);
	}

}
