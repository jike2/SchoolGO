package com.hwua.pojo;

import java.util.Date;

import com.sun.jmx.snmp.Timestamp;

public class User {
	private int userID;//用户编号
	private String userName;//用户名（昵称）
	private Date userBirthday;//用户出生日期
	private String userGender;//性别
	private long userTell;//电话号码
	private String userEmail;//邮箱
	private String userPassword;//用户密码
	private String userAddr;//家庭住址
	private String userHeadimg;//头像
	private Date userRegistertime;//注册时间
	private String userState;//用户账号状态
	private int userEmpID;//处理人（销户时使用）
	private String userCause;//处理原因
	private String userRealname;//真实姓名
	private String userIdnumber;//身份证号
	private String alipaynumber;//支付宝账号
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public long getUserTell() {
		return userTell;
	}
	public void setUserTell(long userTell) {
		this.userTell = userTell;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String getUserHeadimg() {
		return userHeadimg;
	}
	public void setUserHeadimg(String userHeadimg) {
		this.userHeadimg = userHeadimg;
	}
	public Date getUserRegistertime() {
		return userRegistertime;
	}
	public void setUserRegistertime(Date userRegistertime) {
		this.userRegistertime = userRegistertime;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public int getUserEmpID() {
		return userEmpID;
	}
	public void setUserEmpID(int userEmpID) {
		this.userEmpID = userEmpID;
	}
	public String getUserCause() {
		return userCause;
	}
	public void setUserCause(String userCause) {
		this.userCause = userCause;
	}
	public String getUserRealname() {
		return userRealname;
	}
	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}
	public String getUserIdnumber() {
		return userIdnumber;
	}
	public void setUserIdnumber(String userIdnumber) {
		this.userIdnumber = userIdnumber;
	}
	public String getAlipaynumber() {
		return alipaynumber;
	}
	public void setAlipaynumber(String alipaynumber) {
		this.alipaynumber = alipaynumber;
	}
	public User(int userID, String userName, Date userBirthday, String userGender, long userTell, String userEmail,
			String userPassword, String userAddr, String userHeadimg, Date userRegistertime, String userState,
			int userEmpID, String userCause, String userRealname, String userIdnumber, String alipaynumber) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userBirthday = userBirthday;
		this.userGender = userGender;
		this.userTell = userTell;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userAddr = userAddr;
		this.userHeadimg = userHeadimg;
		this.userRegistertime = userRegistertime;
		this.userState = userState;
		this.userEmpID = userEmpID;
		this.userCause = userCause;
		this.userRealname = userRealname;
		this.userIdnumber = userIdnumber;
		this.alipaynumber = alipaynumber;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", userBirthday=" + userBirthday + ", userGender="
				+ userGender + ", userTell=" + userTell + ", userEmail=" + userEmail + ", userPassword=" + userPassword
				+ ", userAddr=" + userAddr + ", userHeadimg=" + userHeadimg + ", userRegistertime=" + userRegistertime
				+ ", userState=" + userState + ", userEmpID=" + userEmpID + ", userCause=" + userCause
				+ ", userRealname=" + userRealname + ", userIdnumber=" + userIdnumber + ", alipaynumber=" + alipaynumber
				+ "]";
	}

}
