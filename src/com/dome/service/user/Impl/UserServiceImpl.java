package com.dome.service.user.Impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.dome.dao.user.UserDao;
import com.dome.dao.user.UserDaoImpl;
import com.dome.entity.User;
import com.dome.service.user.UserService;
import com.dome.utils.JDBCUtils;


public class UserServiceImpl implements UserService{
	
	//登录验证
	@Override
	public User FinderUser(String name, String pwd) {		
		User user =null;
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			UserDao userdao = new UserDaoImpl(connection);
			user= userdao.findUser(name, pwd);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(connection);
		}
		return user;
	}
	
	//修改信息
	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		Connection conn = null;		
		try {
			conn = JDBCUtils.getConnection();
			UserDao userdao = new UserDaoImpl(conn);
			userdao.updateUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(conn);
		}
		
	}

	@Override
	public User FinderUserEmail(String name, String email) {
		User user =null;
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			UserDao userdao = new UserDaoImpl(connection);
			user= userdao.findUserEmail(name, email);

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(connection);
		}
		return user;
	}

	@Override
	public void updateUserCode(int id, String code) {
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			UserDao userDao = new UserDaoImpl(connection);
			userDao.updateUserCode(id, code);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(connection);
		}
		
	}
	
	@Override
	public User FinderUserAll(int id) {
		User user =null;
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			UserDao userdao = new UserDaoImpl(connection);
			user= userdao.findUserAll(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(connection);
		}
		return user;
	}

	@Override
	public int updateUserPwd(int id, String pwd) {
		int a=1;
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			UserDao userDao = new UserDaoImpl(connection);
			userDao.updateUserPwd(id, pwd);
			a=0;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(connection);
		}
		return a;
	}
}
