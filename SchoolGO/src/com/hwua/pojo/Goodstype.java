package com.hwua.pojo;

public class Goodstype {

	private int typeOneID;//一级分类id
	private String typeOne;//一级分类
	private int typeTwoID;//二级分类id
	private String typeTwo;//二级分类
	public int getTypeOneID() {
		return typeOneID;
	}
	public void setTypeOneID(int typeOneID) {
		this.typeOneID = typeOneID;
	}
	public String getTypeOne() {
		return typeOne;
	}
	public void setTypeOne(String typeOne) {
		this.typeOne = typeOne;
	}
	public int getTypeTwoID() {
		return typeTwoID;
	}
	public void setTypeTwoID(int typeTwoID) {
		this.typeTwoID = typeTwoID;
	}
	public String getTypeTwo() {
		return typeTwo;
	}
	public void setTypeTwo(String typeTwo) {
		this.typeTwo = typeTwo;
	}
	public Goodstype(int typeOneID, String typeOne, int typeTwoID, String typeTwo) {
		super();
		this.typeOneID = typeOneID;
		this.typeOne = typeOne;
		this.typeTwoID = typeTwoID;
		this.typeTwo = typeTwo;
	}
	public Goodstype() {
		super();
	}
	@Override
	public String toString() {
		return "Goodstype [typeOneID=" + typeOneID + ", typeOne=" + typeOne + ", typeTwoID=" + typeTwoID + ", typeTwo="
				+ typeTwo + "]";
	}
	
}
