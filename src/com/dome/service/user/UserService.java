package com.dome.service.user;

import com.dome.entity.User;

public interface UserService {
	
	//登录
	public User FinderUser(String name, String pwd);
	
	//修改用户信息
	public void update(User user);

	
}
