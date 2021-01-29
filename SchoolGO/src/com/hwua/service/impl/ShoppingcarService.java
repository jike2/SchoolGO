package com.hwua.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwua.dao.ShoppingcarDao;
import com.hwua.pojo.Shoppingcar;

@Service("ShoppingcarService")
public class ShoppingcarService implements com.hwua.service.ShoppingcarService{

	@Autowired
	private ShoppingcarDao shoppingcarDao;
	
	@Override
	public boolean addgoodcar(Shoppingcar shop) {
		int i = shoppingcarDao.addgoodcar(shop);
		return i>0;
	}

	@Override
	public List<Shoppingcar> querymycar(String userName) {
		List<Shoppingcar> list = shoppingcarDao.querymycar(userName);
		System.out.println(list.size());
		System.out.println(list);
		return list;
	}

	@Override
	public boolean delcargood(int sid) {
		int delcargood = shoppingcarDao.delcargood(sid);
		return delcargood>0;
	}

}
