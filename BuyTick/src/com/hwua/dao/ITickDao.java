package com.hwua.dao;

import java.util.List;

import com.hwua.pojo.Tickets;

public interface ITickDao {

	public List<Tickets> queryAll(int page,int limit);//查询全部门票信息
	
	public boolean saleTick(int p_id,int number);//门票出售后更新门票库存
	
	public Tickets query(int p_id);//根据id查询门票数据
	
	public boolean uptick(Tickets tk);//修改门票信息
	
	public Tickets queryByName(String p_type);//根据门票分类查询门票信息
	
	public boolean deltick(int p_id);//门票删除
	
	public boolean addtick(Tickets tk);//添加门票
}
