package com.dome.service.user;

import com.dome.entity.User;

public interface UserService {
	
	//��¼
	public User FinderUser(String name, String pwd);
	
	//�޸��û���Ϣ
	public void update(User user);

	
}
