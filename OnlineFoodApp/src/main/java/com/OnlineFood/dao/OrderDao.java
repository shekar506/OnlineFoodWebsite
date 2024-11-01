package com.OnlineFood.dao;

import com.OnlineFood.model.Orders;

public interface OrderDao {

	int insertOrder(Orders order);
    
    Orders getOrderById(int id);
    
    int updateOrderById(int id,String status);
	
	

}
