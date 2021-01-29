package com.hwua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwua.dao.AddrDao;
import com.hwua.dao.UserAddrDao;
import com.hwua.pojo.Addr;
import com.hwua.pojo.Goodstype;
import com.hwua.pojo.UserAddr;
@Service("AddrService")
public class AddrService implements com.hwua.service.AddrService{

	@Autowired
	private AddrDao addrdao;
	@Override
	public List<Addr> queryAddr() {
		return addrdao.queryAddr();
	}
	@Override
	public List<Goodstype> querygoodtype() {
		return addrdao.querygoodtype();
	}
	@Override
	public List<Goodstype> querygoodtypebytypeOne(String typeOne) {
		List<Goodstype> list = addrdao.querygoodtypebytypeOne(typeOne);
		return list;
	}
	@Override
	public List<Addr> screeningaddr(String goodProvinces) {
		List<Addr> list = addrdao.screeningaddr(goodProvinces);
		return list;
	}
	@Override
	public List<Addr> queryschool(String addrCity) {
		List<Addr> queryschool = addrdao.queryschool(addrCity);
		return queryschool;
	}

}
