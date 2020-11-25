package com.hwua.pojo;

public class Tickets {
	private int p_id;//���
	private String p_type;//��Ʊ����
	private double p_price;//�۸�
	private int p_number;//���
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_type() {
		return p_type;
	}
	public void setP_type(String p_type) {
		this.p_type = p_type;
	}
	public double getP_price() {
		return p_price;
	}
	public void setP_price(double p_price) {
		this.p_price = p_price;
	}
	public int getP_number() {
		return p_number;
	}
	public void setP_number(int p_number) {
		this.p_number = p_number;
	}
	public Tickets() {
		super();
	}
	public Tickets(int p_id, String p_type, double p_price, int p_number) {
		super();
		this.p_id = p_id;
		this.p_type = p_type;
		this.p_price = p_price;
		this.p_number = p_number;
	}
	@Override
	public String toString() {
		return "Tickets [p_id=" + p_id + ", p_type=" + p_type + ", p_price=" + p_price + ", p_number=" + p_number + "]";
	}
	
	
	
}
