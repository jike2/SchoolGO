package com.hwua.pojo;

import java.util.Date;

public class Employee {

	private int empID;//员工id
	private String empName;//员工名字
	private int empAge;//员工年龄
	private String empGender;//员工性别
	private String empAddr;//家庭住址
	private long empTell;//联系电话
	private String empPassword;//登录密码
	private int empGmlevel;//权限等级
	private String empDepartment;//所属部门
	private String empPosition;//员工职务
	private Date empTntrytime;//入职时间
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getEmpAge() {
		return empAge;
	}
	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}
	public String getEmpGender() {
		return empGender;
	}
	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}
	public String getEmpAddr() {
		return empAddr;
	}
	public void setEmpAddr(String empAddr) {
		this.empAddr = empAddr;
	}
	public long getEmpTell() {
		return empTell;
	}
	public void setEmpTell(long empTell) {
		this.empTell = empTell;
	}
	public String getEmpPassword() {
		return empPassword;
	}
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	public int getEmpGmlevel() {
		return empGmlevel;
	}
	public void setEmpGmlevel(int empGmlevel) {
		this.empGmlevel = empGmlevel;
	}
	public String getEmpDepartment() {
		return empDepartment;
	}
	public void setEmpDepartment(String empDepartment) {
		this.empDepartment = empDepartment;
	}
	public String getEmpPosition() {
		return empPosition;
	}
	public void setEmpPosition(String empPosition) {
		this.empPosition = empPosition;
	}
	public Date getEmpTntrytime() {
		return empTntrytime;
	}
	public void setEmpTntrytime(Date empTntrytime) {
		this.empTntrytime = empTntrytime;
	}
	public Employee(int empID, String empName, int empAge, String empGender, String empAddr, long empTell,
			String empPassword, int empGmlevel, String empDepartment, String empPosition, Date empTntrytime) {
		super();
		this.empID = empID;
		this.empName = empName;
		this.empAge = empAge;
		this.empGender = empGender;
		this.empAddr = empAddr;
		this.empTell = empTell;
		this.empPassword = empPassword;
		this.empGmlevel = empGmlevel;
		this.empDepartment = empDepartment;
		this.empPosition = empPosition;
		this.empTntrytime = empTntrytime;
	}
	public Employee() {
		super();
	}
	@Override
	public String toString() {
		return "Employee [empID=" + empID + ", empName=" + empName + ", empAge=" + empAge + ", empGender=" + empGender
				+ ", empAddr=" + empAddr + ", empTell=" + empTell + ", empPassword=" + empPassword + ", empGmlevel="
				+ empGmlevel + ", empDepartment=" + empDepartment + ", empPosition=" + empPosition + ", empTntrytime="
				+ empTntrytime + "]";
	}
	
}
