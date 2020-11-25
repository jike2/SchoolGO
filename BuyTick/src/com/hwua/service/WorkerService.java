package com.hwua.service;

import java.util.List;

import com.hwua.pojo.Worker;

public interface WorkerService {
	public Worker login(String username);//根据用户名查找数据
	public boolean upMyPwd(String pwd);//修改自己的密码
	public List<Worker> quertWorkerAll();//查询全部员工
	public List<Worker> quertWorkerpage(int page,int limit);//查询全部员工(分页)
	public List<Worker> findWorkByID(String find);//根据工号或姓名查询员工
	
	public List<Worker> findWorkByIDpage(String find,int page,int limit);//根据工号或者姓名查询员工(分页)
}
