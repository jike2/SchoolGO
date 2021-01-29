package com.hwua.pojo;

import java.util.Date;


public class Sellrecords {
	private long sellID;//订单编号
	private int sellgoodID;//商品id
	private int sellNumber;//商品数量
	private double sellMoney;//总价
	private String selluserName;//商家
	private String sellBuyer;//购买人
	private String sellState;//订单状态
	private long sellalipayid;//支付单号
	private String sellCouriernumber;//快递单号
	private Date sellOrdertime;//下单时间
	private Date sellFinishtime;//订单完成时间
	private int selladdr;
	private Goods goods;
	private UserAddr useraddr;
	public long getSellID() {
		return sellID;
	}
	public void setSellID(long sellID) {
		this.sellID = sellID;
	}
	public int getSellgoodID() {
		return sellgoodID;
	}
	public void setSellgoodID(int sellgoodID) {
		this.sellgoodID = sellgoodID;
	}
	public int getSellNumber() {
		return sellNumber;
	}
	public void setSellNumber(int sellNumber) {
		this.sellNumber = sellNumber;
	}
	public double getSellMoney() {
		return sellMoney;
	}
	public void setSellMoney(double sellMoney) {
		this.sellMoney = sellMoney;
	}
	public String getSelluserName() {
		return selluserName;
	}
	public void setSelluserName(String selluserName) {
		this.selluserName = selluserName;
	}
	public String getSellBuyer() {
		return sellBuyer;
	}
	public void setSellBuyer(String sellBuyer) {
		this.sellBuyer = sellBuyer;
	}
	public String getSellState() {
		return sellState;
	}
	public void setSellState(String sellState) {
		this.sellState = sellState;
	}
	public long getSellalipayid() {
		return sellalipayid;
	}
	public void setSellalipayid(long sellalipayid) {
		this.sellalipayid = sellalipayid;
	}
	public String getSellCouriernumber() {
		return sellCouriernumber;
	}
	public void setSellCouriernumber(String sellCouriernumber) {
		this.sellCouriernumber = sellCouriernumber;
	}
	public Date getSellOrdertime() {
		return sellOrdertime;
	}
	public void setSellOrdertime(Date sellOrdertime) {
		this.sellOrdertime = sellOrdertime;
	}
	public Date getSellFinishtime() {
		return sellFinishtime;
	}
	public void setSellFinishtime(Date sellFinishtime) {
		this.sellFinishtime = sellFinishtime;
	}
	public int getSelladdr() {
		return selladdr;
	}
	public void setSelladdr(int selladdr) {
		this.selladdr = selladdr;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public UserAddr getUseraddr() {
		return useraddr;
	}
	public void setUseraddr(UserAddr useraddr) {
		this.useraddr = useraddr;
	}
	public Sellrecords(long sellID, int sellgoodID, int sellNumber, double sellMoney, String selluserName,
			String sellBuyer, String sellState, long sellalipayid, String sellCouriernumber, Date sellOrdertime,
			Date sellFinishtime, int selladdr, Goods goods, UserAddr useraddr) {
		super();
		this.sellID = sellID;
		this.sellgoodID = sellgoodID;
		this.sellNumber = sellNumber;
		this.sellMoney = sellMoney;
		this.selluserName = selluserName;
		this.sellBuyer = sellBuyer;
		this.sellState = sellState;
		this.sellalipayid = sellalipayid;
		this.sellCouriernumber = sellCouriernumber;
		this.sellOrdertime = sellOrdertime;
		this.sellFinishtime = sellFinishtime;
		this.selladdr = selladdr;
		this.goods = goods;
		this.useraddr = useraddr;
	}
	public Sellrecords() {
		super();
	}
	@Override
	public String toString() {
		return "Sellrecords [sellID=" + sellID + ", sellgoodID=" + sellgoodID + ", sellNumber=" + sellNumber
				+ ", sellMoney=" + sellMoney + ", selluserName=" + selluserName + ", sellBuyer=" + sellBuyer
				+ ", sellState=" + sellState + ", sellalipayid=" + sellalipayid + ", sellCouriernumber="
				+ sellCouriernumber + ", sellOrdertime=" + sellOrdertime + ", sellFinishtime=" + sellFinishtime
				+ ", selladdr=" + selladdr + ", goods=" + goods + ", useraddr=" + useraddr + "]";
	}
	
}
