package com.dome.service.user;

import com.dome.entity.User;

public interface UserService {
	
	//��¼
	public User FinderUser(String name, String pwd);
	
	//�޸��û���Ϣ
	public void update(User user);
	
	//����id��
	public User FinderUserAll(int id);
	
	//����
	public User FinderUserEmail(String name,String email);
	
	public void updateUserCode(int id,String code);
	
	public int updateUserPwd(int id,String pwd);
}
