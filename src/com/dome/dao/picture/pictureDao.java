package com.dome.dao.picture;

import com.dome.entity.Picture;

public interface pictureDao {
	
		public Picture findAll();
		
		public void  deleteurl();
		
		public void savePicture(int oid,String url,String name);
		
		
}
