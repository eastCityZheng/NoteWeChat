package com.server.bean;

public class Message {
	private int m_id;
	private int u_id;
	private int uu_id;
	private String m_content;
	private int m_color;
	private String m_time;
	
	
	private String u_headimg;
	private String u_nickname;
	
	
	
	public String getU_headimg() {
		return u_headimg;
	}
	public void setU_headimg(String u_headimg) {
		this.u_headimg = u_headimg;
	}
	public String getU_nickname() {
		return u_nickname;
	}
	public void setU_nickname(String u_nickname) {
		this.u_nickname = u_nickname;
	}
	public String getM_time() {
		return m_time;
	}
	public void setM_time(String m_time) {
		this.m_time = m_time;
	}
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public int getUu_id() {
		return uu_id;
	}
	public void setUu_id(int uu_id) {
		this.uu_id = uu_id;
	}
	public String getM_content() {
		return m_content;
	}
	public void setM_content(String m_content) {
		this.m_content = m_content;
	}
	public int getM_color() {
		return m_color;
	}
	public void setM_color(int m_color) {
		this.m_color = m_color;
	}
	
	

}
