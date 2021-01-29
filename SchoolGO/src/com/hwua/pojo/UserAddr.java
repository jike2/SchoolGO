package com.hwua.pojo;

public class UserAddr {

	private int useradID;//收货地址id
	private String useradProvinces;//收货省份
	private String useradCity;//收货城市
	private String useradSchool;//收货学校
	private String useradDetailed;//详细地址
	private String useradName;//收货人姓名
	private long useradTell;//收货人联系电话
	private int useradUserID;//绑定的账号
	public int getUseradID() {
		return useradID;
	}
	public void setUseradID(int useradID) {
		this.useradID = useradID;
	}
	public String getUseradProvinces() {
		return useradProvinces;
	}
	public void setUseradProvinces(String useradProvinces) {
		this.useradProvinces = useradProvinces;
	}
	public String getUseradCity() {
		return useradCity;
	}
	public void setUseradCity(String useradCity) {
		this.useradCity = useradCity;
	}
	public String getUseradSchool() {
		return useradSchool;
	}
	public void setUseradSchool(String useradSchool) {
		this.useradSchool = useradSchool;
	}
	public String getUseradDetailed() {
		return useradDetailed;
	}
	public void setUseradDetailed(String useradDetailed) {
		this.useradDetailed = useradDetailed;
	}
	public int getUseradUserID() {
		return useradUserID;
	}
	public void setUseradUserID(int useradUserID) {
		this.useradUserID = useradUserID;
	}
	public String getUseradName() {
		return useradName;
	}
	public void setUseradName(String useradName) {
		this.useradName = useradName;
	}
	public long getUseradTell() {
		return useradTell;
	}
	public void setUseradTell(long useradTell) {
		this.useradTell = useradTell;
	}
	public UserAddr() {
		super();
	}
	@Override
	public String toString() {
		return "UserAddr [useradID=" + useradID + ", useradProvinces=" + useradProvinces + ", useradCity=" + useradCity
				+ ", useradSchool=" + useradSchool + ", useradDetailed=" + useradDetailed + ", useradName=" + useradName
				+ ", useradTell=" + useradTell + ", useradUserID=" + useradUserID + "]";
	}
	public UserAddr(int useradID, String useradProvinces, String useradCity, String useradSchool, String useradDetailed,
			String useradName, long useradTell, int useradUserID) {
		super();
		this.useradID = useradID;
		this.useradProvinces = useradProvinces;
		this.useradCity = useradCity;
		this.useradSchool = useradSchool;
		this.useradDetailed = useradDetailed;
		this.useradName = useradName;
		this.useradTell = useradTell;
		this.useradUserID = useradUserID;
	}
	
	

}
