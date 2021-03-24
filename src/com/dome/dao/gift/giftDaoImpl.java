package com.dome.dao.gift;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

import com.dome.dao.BaseDaoImpl;
import com.dome.entity.User;
import com.dome.entity.UserGift;

public class giftDaoImpl extends BaseDaoImpl implements giftDao{

	public giftDaoImpl(Connection conn) {
		super(conn);
	}

	@Override
	public Object tableToClass(ResultSet rs) throws Exception {
			UserGift ug = new UserGift();
			ug.setLid(rs.getInt("lid"));
			ug.setLname(rs.getString("lname"));		
			ug.setLprice(rs.getDouble("lprice"));
			ug.setLread(rs.getString("lread"));
			ug.setLtime(rs.getDate("ltime"));
			ug.setDelete(rs.getInt("hot"));
			ug.setLintegral(rs.getInt("lintegral"));
			ug.setLbaiduread(rs.getString("lbaiduread"));
		return ug;
	}
	public Object aaa(ResultSet rs) throws Exception {
		UserGift ug = new UserGift();
		ug.setLid(rs.getInt("lid"));
	return ug;
}
	

	@Override
	public void saveGift(UserGift ug) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO `gift_add`(lprice,lintegral,lread,lbaiduread,ltime,lname) values(?,?,?,?,?,?)";
		Object[] params = new Object[] {ug.getLprice(),ug.getLintegral(),ug.getLread(),ug.getLbaiduread(),new Date(),ug.getLname()};
		this.excuteUpdate(sql, params);	
	}

	@Override
	public void updateGift(UserGift ug) {
		// TODO Auto-generated method stub
		String sql = "update `gift_add` set delete=?  where lid=?";		
		Object[] params = new Object[] {ug.getClass()};
		this.excuteUpdate(sql, params);	
	}

	@Override
	public UserGift findAllGift(Integer id) {
		String sql = "select * from `gift_add` where lid=? and delete = 1";		
		ResultSet rs = null;
		UserGift ug = null;
		try {		
			Object[] params = new Object[] {id};
			rs = this.executeQuery(sql, params);
			while(rs.next()) {
				ug = (UserGift) tableToClass(rs);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.closeResource(rs);
			this.closeResource();
		}
			return ug;
	}

	@Override
	public void deleteupdateGift(Integer id) {
		String sql = "update `gift_add` set delete=0  where lid=?";		
		Object[] params = new Object[] {id};
		this.excuteUpdate(sql, params);	
	}

	@Override
	public UserGift getGiftId(String name) {
		UserGift uug=null;
		String sql = "select lid from `gift_add` where lname=?";		
		ResultSet rs = null;		
		try {		
			Object[] params = new Object[] {name};
			rs = this.executeQuery(sql, params);
			while(rs.next()) {
				uug=(UserGift)aaa(rs);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.closeResource(rs);
			this.closeResource();
		}
			return uug;
	}

	

}
