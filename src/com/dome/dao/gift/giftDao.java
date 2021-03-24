package com.dome.dao.gift;

import com.dome.entity.UserGift;

public interface giftDao {
	
	public void saveGift(UserGift ug);
	
	public void updateGift(UserGift ug);
	
	public UserGift findAllGift(Integer id);
	
	public UserGift getGiftId(String name);
	
	public void  deleteupdateGift(Integer id);
}
