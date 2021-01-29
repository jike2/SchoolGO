package com.hwua.pojo;

public class Shoppingcar {
	private int sid;
	private int shopID;//商品编号
	private int shopNumber;//商品数量
	private double shopMoney;//商品总价
	private String shopUserName;//用户
	private Goods goods;
	public Goods getGood() {
		return goods;
	}
	public void setGood(Goods goods) {
		this.goods = goods;
	}
	public int getShopID() {
		return shopID;
	}
	public void setShopID(int shopID) {
		this.shopID = shopID;
	}
	public int getShopNumber() {
		return shopNumber;
	}
	public void setShopNumber(int shopNumber) {
		this.shopNumber = shopNumber;
	}
	public double getShopMoney() {
		return shopMoney;
	}
	public void setShopMoney(double shopMoney) {
		this.shopMoney = shopMoney;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public Shoppingcar() {
		super();
	}
	public String getShopUserName() {
		return shopUserName;
	}
	public void setShopUserName(String shopUserName) {
		this.shopUserName = shopUserName;
	}
	public Shoppingcar(int sid, int shopID, int shopNumber, double shopMoney, String shopUserName, Goods goods) {
		super();
		this.sid = sid;
		this.shopID = shopID;
		this.shopNumber = shopNumber;
		this.shopMoney = shopMoney;
		this.shopUserName = shopUserName;
		this.goods = goods;
	}
	@Override
	public String toString() {
		return "Shoppingcar [sid=" + sid + ", shopID=" + shopID + ", shopNumber=" + shopNumber + ", shopMoney="
				+ shopMoney + ", shopUserName=" + shopUserName + ", good=" + goods + "]";
	}

	
}
