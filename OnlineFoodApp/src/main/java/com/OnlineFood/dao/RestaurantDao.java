package com.OnlineFood.dao;

import java.util.List;

import com.OnlineFood.model.Restaurant;

public interface RestaurantDao {
	

	int insertRestaurant(Restaurant rest);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantById(int id);
    int deleteRestaurantById(int id);
    int updateRestaurantById(int id,int isactive);

}
