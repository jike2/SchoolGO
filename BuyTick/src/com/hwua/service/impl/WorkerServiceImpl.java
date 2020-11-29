package com.hwua.service.impl;

import java.util.List;

import com.hwua.dao.IWorkerDao;
import com.hwua.dao.impl.WorkerDaoImpl;
import com.hwua.pojo.Worker;
import com.hwua.service.WorkerService;

public class WorkerServiceImpl implements WorkerService{
	IWorkerDao wd = new WorkerDaoImpl();
	//登录
	@Override
	public Worker login(String username) {
		Worker worker = wd.login(username);
		return worker;
	}
	//修改自己的密码
	@Override
	public boolean upWorker(Worker worker) {
		return wd.upWorker(worker);
	}
	//查询全部员工
	@Override
	public List<Worker> quertWorkerAll(int permissions) {
		List<Worker> list = wd.quertWorkerAll(permissions);
		return list;
	}
	//查询全部员工（分页）
	@Override
	public List<Worker> quertWorkerpage(int permissions,int page, int limit) {
		List<Worker> list = wd.quertWorkerpage(permissions,page,limit);
		return list;
	}
	@Override
	public List<Worker> findWorkByID(String find) {
		List<Worker> list = wd.findWorkByID(find);
		return list;
	}
	@Override
	public List<Worker> findWorkByIDpage(String find, int page, int limit) {
		List<Worker> list = wd.findWorkByIDpage(find,page,limit);
		return list;
	}
	//根据工号查询员工
	@Override
	public Worker findWorkOne(String find) {
		Worker worker=wd.findWorkOne(find);//根据工号查询员工
		return worker;
	}
	@Override
	public boolean delWorker(String id) {
		boolean b=wd.delWorker(id);//删除员工
		return b;
	}
	@Override
	public boolean addWorker(Worker worker) {
		boolean b=wd.addWorker(worker);
		return b;
	}

}
