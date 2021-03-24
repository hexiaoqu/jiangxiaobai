package com.dome.service.login;

import java.sql.Connection;

import com.dome.dao.login.loginDao;
import com.dome.dao.login.loginDaoImpl;
import com.dome.entity.Login;
import com.dome.utils.JDBCUtils;


public class loginServiceImpl implements loginService{

	@Override
	public void saveLogin(Login login) {
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			loginDao logindao = new loginDaoImpl(connection);
			logindao.save(login);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(connection);
		}
		
	}

	@Override
	public Login findById(Integer id) {
		Login login = null;
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			loginDao logindao = new loginDaoImpl(connection);
			login = logindao.findByID(id);

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(connection);
		}		
		return login;
	}

}
