package com.dome.service.user;

import com.dome.entity.User;

public interface UserService {
	
	//登录
	public User FinderUser(String name, String pwd);
	
	//修改用户信息
	public void update(User user);
	
	//根据id查
	public User FinderUserAll(int id);
	
	//邮箱
	public User FinderUserEmail(String name,String email);
	
	public void updateUserCode(int id,String code);
	
	public int updateUserPwd(int id,String pwd);
}
