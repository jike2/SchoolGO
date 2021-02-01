package com.hwua.pojo;

import java.util.Date;

public class RefunsedGood {

	private int RefID;//处理编号
	private int RefgoodID;//商品编号
	private String RefgoodCause;//未通过原因
	private int RefempID;//处理人id
	private Date Reftime;//处理时间
	public int getRefID() {
		return RefID;
	}
	public void setRefID(int refID) {
		RefID = refID;
	}
	public int getRefgoodID() {
		return RefgoodID;
	}
	public void setRefgoodID(int refgoodID) {
		RefgoodID = refgoodID;
	}
	public String getRefgoodCause() {
		return RefgoodCause;
	}
	public void setRefgoodCause(String refgoodCause) {
		RefgoodCause = refgoodCause;
	}
	public int getRefempID() {
		return RefempID;
	}
	public void setRefempID(int refempID) {
		RefempID = refempID;
	}
	public Date getReftime() {
		return Reftime;
	}
	public void setReftime(Date reftime) {
		Reftime = reftime;
	}
	public RefunsedGood(int refID, int refgoodID, String refgoodCause, int refempID, Date reftime) {
		super();
		RefID = refID;
		RefgoodID = refgoodID;
		RefgoodCause = refgoodCause;
		RefempID = refempID;
		Reftime = reftime;
	}
	public RefunsedGood() {
		super();
	}
	@Override
	public String toString() {
		return "RefunsedGood [RefID=" + RefID + ", RefgoodID=" + RefgoodID + ", RefgoodCause=" + RefgoodCause
				+ ", RefempID=" + RefempID + ", Reftime=" + Reftime + "]";
	}
	
}
