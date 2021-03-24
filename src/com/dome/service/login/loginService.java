package com.dome.service.login;

import com.dome.entity.Login;

public interface loginService {
		
	
	public void  saveLogin(Login login);
	
	public Login findById(Integer id);
}
