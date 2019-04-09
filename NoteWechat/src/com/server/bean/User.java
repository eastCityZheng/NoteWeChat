package com.server.bean;

public class User {
	private int u_id;
	private String u_openid;
	private String nickname;
	private String headimg;
	private String u_qrcode;
	private int u_isuse;
	
	
	
	public int getU_isuse() {
		return u_isuse;
	}
	public void setU_isuse(int u_isuse) {
		this.u_isuse = u_isuse;
	}
	public String getU_qrcode() {
		return u_qrcode;
	}
	public void setU_qrcode(String u_qrcode) {
		this.u_qrcode = u_qrcode;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_openid() {
		return u_openid;
	}
	public void setU_openid(String u_openid) {
		this.u_openid = u_openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	
	
}
