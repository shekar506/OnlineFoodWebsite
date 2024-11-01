package com.OnlineFood.dao;

import java.util.List;

import com.OnlineFood.model.menu;

public interface menudao 
{

	int insertMenu(menu m);
	List<menu> getAllRestMenu(int restaurant_id);
	menu getMenuById(int menu_id);
	int deleteMenuById(int menu_id);
	int updateMenuById(int menu_id,boolean isAvailable);
		
}
