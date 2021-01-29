package com.hwua.pojo;

public class Process {

	private int propid;//id
	private long prosid;//订单编号
	private double prorefunmoney;//退款金额
	private String prorefundcause;//退款原因
	private String proimg;//相关截图
	private String Prostate;//处理状态
	private String prorefused;//处理结果
	private int proempid;//介入客服id
	private String proempcause;//介入结果
	public int getPropid() {
		return propid;
	}
	public void setPropid(int propid) {
		this.propid = propid;
	}
	public long getProsid() {
		return prosid;
	}
	public void setProsid(long prosid) {
		this.prosid = prosid;
	}
	public double getProrefunmoney() {
		return prorefunmoney;
	}
	public void setProrefunmoney(double prorefunmoney) {
		this.prorefunmoney = prorefunmoney;
	}
	public String getProrefundcause() {
		return prorefundcause;
	}
	public void setProrefundcause(String prorefundcause) {
		this.prorefundcause = prorefundcause;
	}
	public String getProimg() {
		return proimg;
	}
	public void setProimg(String proimg) {
		this.proimg = proimg;
	}
	public String getProstate() {
		return Prostate;
	}
	public void setProstate(String prostate) {
		Prostate = prostate;
	}
	public String getProrefused() {
		return prorefused;
	}
	public void setProrefused(String prorefused) {
		this.prorefused = prorefused;
	}
	public int getProempid() {
		return proempid;
	}
	public void setProempid(int proempid) {
		this.proempid = proempid;
	}
	public String getProempcause() {
		return proempcause;
	}
	public void setProempcause(String proempcause) {
		this.proempcause = proempcause;
	}
	public Process(int propid, long prosid, double prorefunmoney, String prorefundcause, String proimg,
			String prostate, String prorefused, int proempid, String proempcause) {
		super();
		this.propid = propid;
		this.prosid = prosid;
		this.prorefunmoney = prorefunmoney;
		this.prorefundcause = prorefundcause;
		this.proimg = proimg;
		Prostate = prostate;
		this.prorefused = prorefused;
		this.proempid = proempid;
		this.proempcause = proempcause;
	}
	public Process() {
		super();
	}
	@Override
	public String toString() {
		return "Processing [propid=" + propid + ", prosid=" + prosid + ", prorefunmoney=" + prorefunmoney
				+ ", prorefundcause=" + prorefundcause + ", proimg=" + proimg + ", Prostate=" + Prostate
				+ ", prorefused=" + prorefused + ", proempid=" + proempid + ", proempcause=" + proempcause + "]";
	}
}
