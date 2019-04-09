package com.server.bean;

public class BlackList {
	private int u_id;
	private int b_time;
	private String start_time;
	private String end_time;
	private String reason;
	
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public int getB_time() {
		return b_time;
	}
	public void setB_time(int b_time) {
		this.b_time = b_time;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
