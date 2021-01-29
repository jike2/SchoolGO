package com.hwua.pojo;

import java.util.Date;


public class Goods {

	private int goodID;//商品id
	private String goodName;//商品名称
	private double goodPrice;//商品价格
	private int goodNumber;//商品库存
	private String goodImg;//商品照片
	private String goodAbout;//商品简介
	private String goodType;//商品分类（一级）
	private String goodRecleaner;//商品分类（二级）goodRecleaner
	private String goodProvinces;//所在区
	private String goodCity;//所在城市
	private String goodSchool;//所在学校
	private String gooduserName;//商家姓名
	private String goodState;//商品状态
	private Date goodSubtime;//提交时间
	private int goodEmpID;//处理人
	private Date goodShelftime;//上架时间
	public int getGoodID() {
		return goodID;
	}
	public void setGoodID(int goodID) {
		this.goodID = goodID;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public double getGoodPrice() {
		return goodPrice;
	}
	public void setGoodPrice(double goodPrice) {
		this.goodPrice = goodPrice;
	}
	public int getGoodNumber() {
		return goodNumber;
	}
	public void setGoodNumber(int goodNumber) {
		this.goodNumber = goodNumber;
	}
	public String getGoodImg() {
		return goodImg;
	}
	public void setGoodImg(String goodImg) {
		this.goodImg = goodImg;
	}
	public String getGoodAbout() {
		return goodAbout;
	}
	public void setGoodAbout(String goodAbout) {
		this.goodAbout = goodAbout;
	}
	public String getGoodType() {
		return goodType;
	}
	public void setGoodType(String goodType) {
		this.goodType = goodType;
	}
	public String getGoodRecleaner() {
		return goodRecleaner;
	}
	public void setGoodRecleaner(String goodRecleaner) {
		this.goodRecleaner = goodRecleaner;
	}
	public String getGoodProvinces() {
		return goodProvinces;
	}
	public void setGoodProvinces(String goodProvinces) {
		this.goodProvinces = goodProvinces;
	}
	public String getGoodCity() {
		return goodCity;
	}
	public void setGoodCity(String goodCity) {
		this.goodCity = goodCity;
	}
	public String getGoodSchool() {
		return goodSchool;
	}
	public void setGoodSchool(String goodSchool) {
		this.goodSchool = goodSchool;
	}
	public String getGooduserName() {
		return gooduserName;
	}
	public void setGooduserName(String gooduserName) {
		this.gooduserName = gooduserName;
	}
	public String getGoodState() {
		return goodState;
	}
	public void setGoodState(String goodState) {
		this.goodState = goodState;
	}
	public Date getGoodSubtime() {
		return goodSubtime;
	}
	public void setGoodSubtime(Date goodSubtime) {
		this.goodSubtime = goodSubtime;
	}
	public int getgoodEmpID() {
		return goodEmpID;
	}
	public void setgoodEmpID(int goodEmpID) {
		this.goodEmpID = goodEmpID;
	}
	public Date getGoodShelftime() {
		return goodShelftime;
	}
	public void setGoodShelftime(Date goodShelftime) {
		this.goodShelftime = goodShelftime;
	}
	public Goods(int goodID, String goodName, double goodPrice, int goodNumber, String goodImg, String goodAbout,
			String goodType, String goodRecleaner, String goodProvinces, String goodCity, String goodSchool,
			String gooduserName, String goodState, Date goodSubtime, int goodEmpID, Date goodShelftime) {
		super();
		this.goodID = goodID;
		this.goodName = goodName;
		this.goodPrice = goodPrice;
		this.goodNumber = goodNumber;
		this.goodImg = goodImg;
		this.goodAbout = goodAbout;
		this.goodType = goodType;
		this.goodRecleaner = goodRecleaner;
		this.goodProvinces = goodProvinces;
		this.goodCity = goodCity;
		this.goodSchool = goodSchool;
		this.gooduserName = gooduserName;
		this.goodState = goodState;
		this.goodSubtime = goodSubtime;
		this.goodEmpID = goodEmpID;
		this.goodShelftime = goodShelftime;
	}
	public Goods() {
		super();
	}
	@Override
	public String toString() {
		return "Goods [goodID=" + goodID + ", goodName=" + goodName + ", goodPrice=" + goodPrice + ", goodNumber="
				+ goodNumber + ", goodImg=" + goodImg + ", goodAbout=" + goodAbout + ", goodType=" + goodType
				+ ", goodRecleaner=" + goodRecleaner + ", goodProvinces=" + goodProvinces + ", goodCity=" + goodCity
				+ ", goodSchool=" + goodSchool + ", gooduserName=" + gooduserName + ", goodState=" + goodState
				+ ", goodSubtime=" + goodSubtime + ", goodEmpID=" + goodEmpID + ", goodShelftime=" + goodShelftime
				+ "]";
	}
	
	
}
