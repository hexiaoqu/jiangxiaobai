package com.dome.dao.picture;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

import com.dome.dao.user.UserDaoImpl;
import com.dome.entity.Picture;
import com.dome.entity.User;

public class pictureDaoImpl extends UserDaoImpl implements pictureDao{

	public pictureDaoImpl(Connection conn) {
		super(conn);
	}
	
	@Override
	public Object tableToClass(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub	
		Picture picture = new Picture();
		picture.setOid(rs.getInt("oid"));
		picture.setSave_url(rs.getString("save_url"));
		picture.setPname(rs.getString("pname"));
		picture.setPid(rs.getInt("pid"));
		return picture;
	}
	@Override
	public Picture findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteurl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePicture(int oid,String url,String name) {
		String sql = "INSERT INTO `picture`(oid,save_url,pname) values(?,?,?)";
		Object[] params = new Object[] {oid,url,name};
		this.excuteUpdate(sql, params);	
		
	}


	
	
}
