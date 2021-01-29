package com.hwua.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.hwua.pojo.Goods;
import com.hwua.pojo.Sellrecords;
public interface SellrecordsService {
	
	public boolean deletesell(long sellid);
	
	public List<Sellrecords> querymysell(String userName);
	
	public boolean upsell(Sellrecords sell);
	
	public List<Sellrecords> querymysellbyalipayid(long alipayid);
	
	public List<Sellrecords> querymysellbysellstate(String username,String State);
	
	public Sellrecords querymysellbysellid(long sellid);
	
	public List<Sellrecords> querymyshopsellbysellstate(String username,String State);

	public List<Sellrecords> queryAllsellrecord();
	
	public List<Sellrecords> queryallsellrecordbyname(String queryname);
}
