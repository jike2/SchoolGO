package com.hwua.pojo;

import java.sql.Date;
import java.sql.Timestamp;

public class SalesRecord {
	private long s_id;//�������
	private String price_id;//��Ʊ���
	private double price_price;//��Ʊ�۸�
	private int s_number;//��Ʊ����
	private int s_addnumber;//��Ʊ��������
	private double s_money;//��Ʊ�ܼ�
	private String s_worker;//������
	private String s_usestate;
	private String s_state;//����״̬���Ѵ������������
	private Timestamp s_date;//�۳�ʱ��
	public String getS_usestate() {
		return s_usestate;
	}
	public void setS_usestate(String s_usestate) {
		this.s_usestate = s_usestate;
	}
	
	public long getS_id() {
		return s_id;
	}
	public void setS_id(long s_id) {
		this.s_id = s_id;
	}
	public double getPrice_price() {
		return price_price;
	}
	public void setPrice_price(double price_price) {
		this.price_price = price_price;
	}
	public int getS_number() {
		return s_number;
	}
	public void setS_number(int s_number) {
		this.s_number = s_number;
	}
	public int getS_addnumber() {
		return s_addnumber;
	}
	public void setS_addnumber(int s_addnumber) {
		this.s_addnumber = s_addnumber;
	}
	public double getS_money() {
		return s_money;
	}
	public void setS_money(double s_money) {
		this.s_money = s_money;
	}
	public String getS_worker() {
		return s_worker;
	}
	public void setS_worker(String s_worker) {
		this.s_worker = s_worker;
	}
	public String getS_state() {
		return s_state;
	}
	public void setS_state(String s_state) {
		this.s_state = s_state;
	}
	public Timestamp getS_date() {
		return s_date;
	}
	public void setS_date(Timestamp s_date) {
		this.s_date = s_date;
	}
	public String getPrice_id() {
		return price_id;
	}
	public void setPrice_id(String price_id) {
		this.price_id = price_id;
	}
	public SalesRecord(long s_id, String price_id, double price_price, int s_number, int s_addnumber, double s_money,
			String s_worker, String s_usestate, String s_state, Timestamp s_date) {
		super();
		this.s_id = s_id;
		this.price_id = price_id;
		this.price_price = price_price;
		this.s_number = s_number;
		this.s_addnumber = s_addnumber;
		this.s_money = s_money;
		this.s_worker = s_worker;
		this.s_usestate = s_usestate;
		this.s_state = s_state;
		this.s_date = s_date;
	}
	public SalesRecord() {
		super();
	}
	@Override
	public String toString() {
		return "SalesRecord [s_id=" + s_id + ", price_id=" + price_id + ", price_price=" + price_price + ", s_number="
				+ s_number + ", s_addnumber=" + s_addnumber + ", s_money=" + s_money + ", s_worker=" + s_worker
				+ ", s_usestate=" + s_usestate + ", s_state=" + s_state + ", s_date=" + s_date + "]";
	}
	

}
