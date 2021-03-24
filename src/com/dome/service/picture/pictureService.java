package com.dome.service.picture;

import com.dome.entity.Picture;

public interface pictureService {
	
	
	public void  savePicture(int oid,String url,String name);
	
	public Picture findById(Integer id);
	
	
	
}
