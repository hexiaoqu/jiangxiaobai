package com.dome.service.picture;

import java.sql.Connection;

import com.dome.dao.login.loginDao;
import com.dome.dao.login.loginDaoImpl;
import com.dome.dao.picture.pictureDao;
import com.dome.dao.picture.pictureDaoImpl;
import com.dome.entity.Picture;
import com.dome.utils.JDBCUtils;

public class pictureServiceImpl implements pictureService{

	@Override
	public void savePicture(int oid,String url,String name) {
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			pictureDao pd = new pictureDaoImpl(connection);
			pd.savePicture(oid, url, name);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(connection);
		}
		
	}

	@Override
	public Picture findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
