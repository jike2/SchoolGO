package com.hwua.service;

import java.util.List;

import com.hwua.pojo.Tickets;

public interface TickService {

	public List<Tickets> queryAll(int page,int limit);//获取全部门票信息
	
	public boolean saleTick(int p_id,int number,String w_id);//门票出售
	
	public Tickets queryTickById(int id);//根据id查门票
	
	public Tickets queryTickByType(String p_type);//根据门票分类查门票
	
	public boolean uptick(Tickets tk);
	
	public boolean addticknum(int p_id,int number,String w_id);//门票入库
	
	public boolean deltick(int p_id);//门票删除
	
	public boolean addtick(Tickets tk);//添加门票
}
