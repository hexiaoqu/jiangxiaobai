package com.dome.dao.user;

import com.dome.entity.User;

public interface UserDao {

	
	public  User findUser(String name,String pwd);
	
	public void updateUser(User user);	
}
