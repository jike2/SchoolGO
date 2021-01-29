package com.hwua.pojo;

public class MessegeException extends Exception{
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public MessegeException(String msg) {
		super();
		this.msg = msg;
	}
	

}
