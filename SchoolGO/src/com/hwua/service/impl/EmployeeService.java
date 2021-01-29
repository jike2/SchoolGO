package com.hwua.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwua.dao.EmployeeDao;
import com.hwua.pojo.Employee;

@Service("EmployeeService")
public class EmployeeService implements com.hwua.service.EmployeeService{


	@Autowired
	private EmployeeDao employeedao;

	@Override
	public Employee adminlogin(Employee emp) {
		Employee adminlogin = employeedao.adminlogin(emp);
		return adminlogin;
	}

	@Override
	public Employee querymyemployee(long empTell) {
		Employee employee = employeedao.querymyemployee(empTell);
		return employee;
	}

}
