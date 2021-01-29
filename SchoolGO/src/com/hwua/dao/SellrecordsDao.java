package com.hwua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hwua.pojo.Sellrecords;

public interface SellrecordsDao {

	public int deletesell(@Param("sellID") long sellid);
	
	public List<Sellrecords> querymysell(Sellrecords sell);
	
	public int upsell(Sellrecords sell);
	
	public int paygood(List<Sellrecords> list);
	
	public List<Sellrecords> querymysellbysellstate(@Param("username") String username,@Param("State") String State);

	
	public Sellrecords querymysellbysellid(@Param("sellid") long sellid);
	
	public List<Sellrecords> querymyshopsellbysellstate(@Param("username") String username,@Param("State") String State);
	
	public List<Sellrecords> queryAllsellrecord();
	
	public List<Sellrecords> queryallsellrecordbyname(@Param("queryname")String queryname);
}
