package com.server.bean;

public class JobRelation {
	private int jr_id;
	private int tp_id;
	private int u_id;
	private int jr_top;
	private String jr_nickname;
	
	
	public String getJr_nickname() {
		return jr_nickname;
	}
	public void setJr_nickname(String jr_nickname) {
		this.jr_nickname = jr_nickname;
	}
	public int getJr_id() {
		return jr_id;
	}
	public void setJr_id(int jr_id) {
		this.jr_id = jr_id;
	}
	public int getTp_id() {
		return tp_id;
	}
	public void setTp_id(int tp_id) {
		this.tp_id = tp_id;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public int getJr_top() {
		return jr_top;
	}
	public void setJr_top(int jr_top) {
		this.jr_top = jr_top;
	}
	
	
}
