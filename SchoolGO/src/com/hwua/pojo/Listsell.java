package com.hwua.pojo;

import java.util.List;

public class Listsell {
	private List<Sellrecords> sell;
	private List<Goods> goodid;
	

	public List<Goods> getGoodid() {
		return goodid;
	}

	public void setGoodid(List<Goods> goodid) {
		this.goodid = goodid;
	}

	public List<Sellrecords> getSell() {
		return sell;
	}

	public void setSell(List<Sellrecords> sell) {
		this.sell = sell;
	}

	@Override
	public String toString() {
		return "Listsell [sell=" + sell + ", goodid=" + goodid + "]";
	}

	public Listsell(List<Sellrecords> sell, List<Goods> goodid) {
		super();
		this.sell = sell;
		this.goodid = goodid;
	}

	public Listsell() {
		super();
	}


	
}
