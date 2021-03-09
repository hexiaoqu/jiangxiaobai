package com.dome.entity;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer uId;	//id
	private String  uName;	//”√ªß√˚
	private String 	uPwd;	//√‹¬Î
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPwd() {
		return uPwd;
	}
	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}
	
	public User() {
		
	}
	public User(Integer uId, String uName, String uPwd) {
		super();
		this.uId = uId;
		this.uName = uName;
		this.uPwd = uPwd;
	}

	
	
	
}
