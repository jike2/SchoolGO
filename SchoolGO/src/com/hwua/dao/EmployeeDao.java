package com.hwua.dao;

import org.apache.ibatis.annotations.Param;

import com.hwua.pojo.Employee;

public interface EmployeeDao {

	public Employee adminlogin(Employee emp);
	
	public Employee querymyemployee(@Param("empTell") long empTell);
}
