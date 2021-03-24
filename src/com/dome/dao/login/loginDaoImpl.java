package com.dome.dao.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

import com.dome.dao.BaseDaoImpl;
import com.dome.entity.Login;

public class loginDaoImpl extends BaseDaoImpl implements loginDao{

	public loginDaoImpl(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object tableToClass(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		 Login login = new Login();
		 login.setLoginId(rs.getInt("login_id"));
		 login.setUserId(rs.getInt("login_id"));
		 login.setLoginTime(rs.getDate("login_time"));
		 login.setLoginStatus(rs.getInt("login_status"));
		 login.setLoginRemark(rs.getString("login_remark"));
		return null;
	}

	@Override
	public void save(Login login) {
		String sql = "INSERT INTO `user_login`(user_id,login_time,login_status,login_remark) values(?,?,?,?)";
		Object[] params = new Object[] {login.getUserId(),new Date(),login.getLoginStatus(),login.getLoginRemark()};
		this.excuteUpdate(sql, params);	
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Login findByID(Integer id) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
