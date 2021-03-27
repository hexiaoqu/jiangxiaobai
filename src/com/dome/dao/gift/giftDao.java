package com.dome.dao.gift;

import java.util.Map;

import com.dome.entity.UserGift;

public interface giftDao {
	
	public void saveGift(UserGift ug);
	
	public void updateGift(UserGift ug);
	
	public UserGift findAllGift(Integer id);
	
	public UserGift getGiftId(String name);
	
	public void  deleteupdateGift(Integer id);
	
	public void deleteGift(Integer id);
	
	public Map<String,Object>  queryGiftPage(Integer limit,Integer pageSize);
	
	public Map<String, Object> likeGift(String lname,Integer hot);
}
