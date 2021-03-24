package com.dome.param;
import java.util.Date;

public class EmailParams {
	
	private int States ;//状态
	private String Code;//验证码
	private Date time;//时间
	private Object Date; //数据
	public int getStates() {
		return States;
	}
	public void setStates(int states) {
		States = states;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Object getDate() {
		return Date;
	}
	public void setDate(Object date) {
		Date = date;
	}

	
	
	
}
