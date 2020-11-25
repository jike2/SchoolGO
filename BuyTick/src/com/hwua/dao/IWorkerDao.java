package com.hwua.dao;

import java.util.List;

import com.hwua.pojo.Worker;

public interface IWorkerDao {

	public Worker login(String username);
	
	public boolean upMyPwd(String pwd);
	
	public List<Worker> quertWorkerAll();//查询全部员工
	
	public List<Worker> quertWorkerpage(int page,int limit);//查询全部员工(分页)
	
	public List<Worker> findWorkByID(String find);//根据工号或者姓名查询员工
	
	public List<Worker> findWorkByIDpage(String find,int page,int limit);//根据工号或者姓名查询员工(分页)
}
