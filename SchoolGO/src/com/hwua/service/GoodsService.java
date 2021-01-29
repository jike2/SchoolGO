package com.hwua.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.hwua.pojo.Addr;
import com.hwua.pojo.Goods;
import com.hwua.pojo.Sellrecords;
import com.hwua.pojo.Shoppingcar;
public interface GoodsService {
	
	public List<Goods> queryindex(String goodname);
	
	public Goods querybyid(int goodID);
	
	public Map<String, Object> paygood(List<Sellrecords> sell);
	
	public boolean upgood(Goods good);
	
	public boolean addgood(Goods good);
	
	public Goods querybyidAll(int goodID);
	
	public int querybynamecount(String querygoodname,Addr addr);
	public List<Goods> querybyname(String querygoodname, Addr addr,int page,int limit);
	
	public List<Goods> querymygoodbygoodstate(String userName,String goodstate);

	public boolean delmygood(int goodID);
	
	public List<Goods> queryautogood(String goodState,double goodPricestart, double goodPricefinish);
	public List<Goods> queryautogoodbyname(String goodState,double goodPricestart, double goodPricefinish,String queryname);
	public List<Goods> queryallgoodbystate(String goodstate);
	
	public List<Goods> queryordergoodsbynameAll(String querygoodname,String goodStete);
}
