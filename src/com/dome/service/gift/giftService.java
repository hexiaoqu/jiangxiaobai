package com.dome.service.gift;

import com.dome.entity.UserGift;

public interface giftService {
	
	
	public void saveGift(UserGift ug);
	
	public void updateGift(UserGift ug);
	
	public UserGift findAllGift(Integer id);
	
	public UserGift getGiftID(String name);
	
	public void  deleteupdateGift(Integer id);
	
}
