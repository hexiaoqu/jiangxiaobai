package com.dome.dao.user;

import com.dome.entity.User;

public interface UserDao {

	
	public  User findUser(String name,String pwd);
	
	public void updateUser(User user);	
	
	public  User findUserEmail(String name,String email);
	
	public 	void updateUserCode(int id,String code);
	
	public User findUserAll(int id);
	
	public  void updateUserPwd(int id,String pwd);
}
