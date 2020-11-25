package com.hwua.pojo;

public class Census {
	private String date;
	private String type;
	private int num;
	private double money;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Census(String date, String type, int num, double money) {
		super();
		this.date = date;
		this.type = type;
		this.num = num;
		this.money = money;
	}
	public Census() {
		super();
	}
	@Override
	public String toString() {
		return "Census [date=" + date + ", type=" + type + ", num=" + num + ", money=" + money + "]";
	}
	
}
