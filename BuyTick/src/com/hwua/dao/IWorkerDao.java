package com.hwua.dao;

import java.util.List;

import com.hwua.pojo.Worker;

public interface IWorkerDao {

	public Worker login(String username);
	
	public boolean upWorker(Worker worker);
	
	public List<Worker> quertWorkerAll(int permissions);//查询全部员工
	
	public List<Worker> quertWorkerpage(int permissions,int page,int limit);//查询全部员工(分页)
	
	public List<Worker> findWorkByID(String find);//根据工号或者姓名查询员工
	
	public List<Worker> findWorkByIDpage(String find,int page,int limit);//根据工号或者姓名查询员工(分页)
	
	public Worker findWorkOne(String find);//根据工号查询员工
	
	public boolean delWorker(String id);//删除员工
	
	public boolean addWorker(Worker worker);//添加员工
}
