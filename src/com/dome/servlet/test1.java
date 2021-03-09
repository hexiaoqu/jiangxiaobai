package com.dome.servlet;

import com.dome.entity.User;
import com.dome.service.user.UserService;
import com.dome.service.user.Impl.UserServiceImpl;

public class test1 {
	public static void main(String[] args) {
		
		UserService us= new UserServiceImpl();
		String name="sa";
		String pwd="123";
		
		User user = us.FinderUser(name, pwd);
		System.out.println(user.getuName());
		System.out.println(user.getuPwd());
	}
}
