package com.hwua.pojo;

public class Worker {
	private String w_name;//����
	private String w_id;//����
	private String w_pwd;//����
	private String w_position;//ְ��
	private int w_permissions;//ְ��
	
	public int getW_permissions() {
		return w_permissions;
	}
	public void setW_permissions(int w_permissions) {
		this.w_permissions = w_permissions;
	}
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	public String getW_id() {
		return w_id;
	}
	public void setW_id(String w_id) {
		this.w_id = w_id;
	}
	public String getW_pwd() {
		return w_pwd;
	}
	public void setW_pwd(String w_pwd) {
		this.w_pwd = w_pwd;
	}
	public String getW_position() {
		return w_position;
	}
	public void setW_position(String w_position) {
		this.w_position = w_position;
	}
	
	public Worker(String w_name, String w_id, String w_pwd, String w_position, int w_permissions) {
		super();
		this.w_name = w_name;
		this.w_id = w_id;
		this.w_pwd = w_pwd;
		this.w_position = w_position;
		this.w_permissions = w_permissions;
	}
	public Worker() {
		super();
	}

	
}
