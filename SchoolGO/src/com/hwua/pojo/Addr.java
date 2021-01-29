package com.hwua.pojo;

public class Addr {

	private String addrProvinces;//省份
	private String addrCity;//城市
	private String addrSchool;//学校
	public String getAddrProvinces() {
		return addrProvinces;
	}
	public void setAddrProvinces(String addrProvinces) {
		this.addrProvinces = addrProvinces;
	}
	public String getAddrCity() {
		return addrCity;
	}
	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}
	public String getAddrSchool() {
		return addrSchool;
	}
	public void setAddrSchool(String addrSchool) {
		this.addrSchool = addrSchool;
	}
	public Addr(String addrProvinces, String addrCity, String addrSchool) {
		super();
		this.addrProvinces = addrProvinces;
		this.addrCity = addrCity;
		this.addrSchool = addrSchool;
	}
	public Addr() {
		super();
	}
	@Override
	public String toString() {
		return "Addr [addrProvinces=" + addrProvinces + ", addrCity=" + addrCity + ", addrSchool=" + addrSchool + "]";
	}
	

}
