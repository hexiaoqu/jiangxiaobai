package com.dome.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dome.utils.JDBCUtils;

public abstract class BaseDaoImpl implements IBaseDao{
	protected static Connection conn;
	protected static PreparedStatement pstm;
	
	public BaseDaoImpl(Connection conn) {
		this.conn=conn;
	}
	
	
	public static ResultSet executeQuery(String sql ,Object[] params) {		
		ResultSet rs =null;		
		try {
			pstm=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++) {
				pstm.setObject(i+1, params[i]);
			}
			rs=pstm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;		
	}
	
	public int excuteUpdate(String sql,Object[] params) {
		int updateRows = 0;
		try {
			pstm = conn.prepareStatement(sql);
			for(int i=0; i<params.length; i++) {
				pstm.setObject(i+1, params[i]);
			}
			updateRows = pstm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			updateRows = -1;
		}
		return updateRows;
	}
	
	
	public int executeInsert(String sql,Object[] params) {
		Long id = 0L;
		try {
			pstm = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			for(int i=0; i<params.length; i++) {
				pstm.setObject(i+1, params[i]);
			}
			pstm.executeUpdate();
			ResultSet rs = pstm.getGeneratedKeys();
			if(rs.next()) {
				id = rs.getLong(1);

			}
		}catch(Exception e) {
			e.printStackTrace();
			id = null;
		}
		return id.intValue();
	}
	
	
	//释放资源
	public boolean closeResource() {
		if(pstm != null) {
			try {
				pstm.close();
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	//关闭资源
	public boolean closeResource(ResultSet reSet) {
		if(reSet != null) {
			try {
				reSet.close();
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 需要重写的方法，子类实现对象关系映射
	 */
	public abstract Object tableToClass(ResultSet rs)throws Exception;
	
	
	
	
	
}
