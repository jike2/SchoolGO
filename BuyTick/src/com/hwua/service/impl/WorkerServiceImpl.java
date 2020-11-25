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
	public boolean upMyPwd(String pwd) {
		
		return wd.upMyPwd(pwd);
	}
	//查询全部员工
	@Override
	public List<Worker> quertWorkerAll() {
		List<Worker> list = wd.quertWorkerAll();
		return list;
	}
	//查询全部员工（分页）
	@Override
	public List<Worker> quertWorkerpage(int page, int limit) {
		List<Worker> list = wd.quertWorkerpage(page,limit);
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

}
