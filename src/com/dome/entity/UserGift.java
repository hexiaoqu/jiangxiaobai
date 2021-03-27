package com.dome.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserGift implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer lid;
	private String lname;
	private Double lprice;  //成本价
	private Integer lintegral;	//所需积分
	private String lread;	//简单介绍
	private String lbaiduread;	//详细介绍
	private Date ltime;		//时间
	private Integer hot;
	private String ishot;
	
	public Integer getLid() {
		return lid;
	}
	public void setLid(Integer lid) {
		this.lid = lid;
	}
	public Double getLprice() {
		return lprice;
	}
	public void setLprice(Double lprice) {
		this.lprice = lprice;
	}
	public Integer getLintegral() {
		return lintegral;
	}
	public void setLintegral(Integer lintegral) {
		this.lintegral = lintegral;
	}
	public String getLread() {
		return lread;
	}
	public void setLread(String lread) {
		this.lread = lread;
	}
	public String getLbaiduread() {
		return lbaiduread;
	}
	public void setLbaiduread(String lbaiduread) {
		this.lbaiduread = lbaiduread;
	}
	public Date getLtime() {
		return ltime;
	}
	public void setLtime(Date ltime) {
		this.ltime = ltime;
	}

	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getIshot() {
		return ishot;
	}
	public void setIshot(String ishot) {
		this.ishot = ishot;
	}
	@Override
	public String toString() {
		return "UserGift [lid=" + lid + ", lname=" + lname + ", lprice=" + lprice + ", lintegral=" + lintegral
				+ ", lread=" + lread + ", lbaiduread=" + lbaiduread + ", ltime=" + ltime + ", hot=" + hot + "]";
	}


	
	
	
	
	
}
