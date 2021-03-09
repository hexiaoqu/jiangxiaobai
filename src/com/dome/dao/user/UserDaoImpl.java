package com.dome.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dome.dao.BaseDaoImpl;
import com.dome.entity.User;
import com.dome.utils.JDBCUtils;

public class UserDaoImpl extends BaseDaoImpl implements UserDao{

	public UserDaoImpl(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Object tableToClass(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		user.setuId(rs.getInt("uid"));
		user.setuName(rs.getString("uname"));
		user.setuPwd(rs.getString("upwd"));
		return user;
	}

	@Override
	public User findUser(String name, String pwd) {
		// TODO Auto-generated method stub
		String sql = "select * from `user` where uname=? and upwd=?";		
		ResultSet rs = null;
		User user = null;
		try {		
			Object[] params = new Object[] {name,pwd};
			rs = this.executeQuery(sql, params);
			while(rs.next()) {
				user = (User) tableToClass(rs);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.closeResource(rs);
			this.closeResource();
			
			return user;
	}

	}
	@Override
	public void updateUser(User user) {
		String sql = "select * from `user` set upwd=? where uid=?";
		Object[] params =new Object[] {user.getuPwd(),user.getuId()}; 
		this.excuteUpdate(sql, params);		
	}
}
