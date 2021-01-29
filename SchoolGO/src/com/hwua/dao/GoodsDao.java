package com.hwua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hwua.pojo.Addr;
import com.hwua.pojo.Goods;
import com.hwua.pojo.Sellrecords;

public interface GoodsDao {
	public List<Goods> queryindex(@Param("goodname") String goodname);
	public Goods querybyid(@Param("goodsID")int goodID);
	public int upgood(Goods good);
	public int addgood(Goods good);
	public Goods querybyidAll(@Param("goodsID")int goodID);
	public int querybynamecount(@Param("querygoodname") String querygoodname,@Param("addr") Addr addr);
	public List<Goods> querybyname(@Param("querygoodname") String querygoodname,@Param("addr") Addr addr,@Param("page")int page,@Param("limit")int limit);
	public List<Goods> querymygoodbygoodstate(@Param("userName") String userName,@Param("goodstate") String goodstate);
	public int delmygood(@Param("delgoodID")int goodID);
	public List<Goods> queryautogood(@Param("goodState")String goodState,@Param("goodPricestart") double goodPricestart,@Param("goodPricefinish") double goodPricefinish);
	public List<Goods> queryautogoodbyname(@Param("goodState")String goodState,@Param("goodPricestart") double goodPricestart,@Param("goodPricefinish") double goodPricefinish,@Param("queryname")String queryname);
	public List<Goods> queryallgoodbystate(@Param("goodState") String goodstate);
	public List<Goods> queryordergoodsbynameAll(@Param("querygoodname") String querygoodname,@Param("goodStete") String goodStete);

}
