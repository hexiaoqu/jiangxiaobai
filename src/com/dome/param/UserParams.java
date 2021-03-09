package com.dome.param;

import com.dome.entity.User;

public class UserParams extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private int states;
	private int rem;
	private User user;
	public int getStates() {
		return states;
	}
	public void setStates(int states) {
		this.states = states;
	}
	public int getRem() {
		return rem;
	}
	public void setRem(int rem) {
		this.rem = rem;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
