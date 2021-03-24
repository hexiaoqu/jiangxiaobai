package com.dome.entity;

import java.io.Serializable;
import java.util.Date;

public class Login implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer  loginId;
	private Integer userId;
	private Date loginTime;
	private Integer loginStatus;
	private String loginRemark;
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Integer getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getLoginRemark() {
		return loginRemark;
	}
	public void setLoginRemark(String loginRemark) {
		this.loginRemark = loginRemark;
	}
	public Login() {
		
	}
	

	

	
	
}

