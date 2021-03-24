package com.dome.entity;

import java.io.Serializable;

public class Picture implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer pid;
	private Integer oid;
	private String pname;
	private String save_url;
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public String getSave_url() {
		return save_url;
	}
	public void setSave_url(String save_url) {
		this.save_url = save_url;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	
	
}
