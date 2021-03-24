package com.dome.service.gift;

import java.sql.Connection;

import com.dome.dao.gift.giftDao;
import com.dome.dao.gift.giftDaoImpl;
import com.dome.dao.login.loginDao;
import com.dome.dao.login.loginDaoImpl;
import com.dome.entity.Login;
import com.dome.entity.UserGift;
import com.dome.utils.JDBCUtils;

public class giftServiceImpl implements giftService{

	@Override
	public void saveGift(UserGift ug) {
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			giftDao gd = new giftDaoImpl(connection);
			gd.saveGift(ug);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(connection);
		}
	}

	@Override
	public void updateGift(UserGift ug) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserGift findAllGift(Integer id) {
		UserGift ug = null;
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			giftDao gd = new giftDaoImpl(connection);
			ug = gd.findAllGift(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(connection);
		}		
		return ug;
	}

	@Override
	public void deleteupdateGift(Integer id) {
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			giftDao gd = new giftDaoImpl(connection);
			gd.deleteupdateGift(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(connection);
		}
	}

	@Override
	public UserGift getGiftID(String name) {
		UserGift uug = null;
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			giftDao gd = new giftDaoImpl(connection);
			uug = gd.getGiftId(name);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(connection);
		}		
		return uug;
	}
	
}
