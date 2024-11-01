package com.OnlineFood.dao;

import com.OnlineFood.model.OrderItems;

public interface OrderItemsDao {

int insertOrderItem(OrderItems orderitem);
	
	OrderItems fetchOrderItems(int id);

}
