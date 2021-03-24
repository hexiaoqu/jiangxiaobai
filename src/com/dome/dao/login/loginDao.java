package com.dome.dao.login;

import com.dome.entity.Login;

public interface loginDao {
	public void save(Login login);
	
	public void update();
	
	public void delete();
	
	public Login findByID(Integer id);
	
	
}
