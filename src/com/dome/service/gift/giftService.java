package com.dome.service.gift;

import java.util.Map;

import com.dome.entity.UserGift;

public interface giftService {
	
	
	public void saveGift(UserGift ug);
	
	public void updateGift(UserGift ug);
	
	public UserGift findAllGift(Integer id);
	
	public UserGift getGiftID(String name);
	
	public void  deleteupdateGift(Integer id);
		
	public Map<String,Object> queryGiftList(int page, int pageSize);
	
	public void delectGiftList(Integer id);
	
	public Map<String, Object> likeGift(String lname,Integer hot);
	
}
