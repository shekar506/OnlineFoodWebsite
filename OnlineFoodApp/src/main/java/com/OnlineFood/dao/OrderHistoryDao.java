package com.OnlineFood.dao;

import java.util.List;

import com.OnlineFood.model.OrderHistory;

public interface OrderHistoryDao {
	
int insertOrder(OrderHistory orderhistory);
    
    List<OrderHistory> fetchOhById(int id);
    
    int updateOrderHistoryById(int id,String status);
	

}
