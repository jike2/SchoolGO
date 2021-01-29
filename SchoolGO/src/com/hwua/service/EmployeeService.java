package com.hwua.service;

import org.apache.ibatis.annotations.Param;

import com.hwua.pojo.Employee;

public interface EmployeeService {

	
	public Employee adminlogin(Employee emp);
	public Employee querymyemployee(long empTell);
}
