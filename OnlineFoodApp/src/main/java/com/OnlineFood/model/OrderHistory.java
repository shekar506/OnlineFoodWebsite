package com.OnlineFood.model;

import java.util.ArrayList;

public class OrderHistory {
	
	private int orderhistory_id;
	private int user_id;
	private int order_id;
	private double total;
	private String status;
	ArrayList<OrderHistory> orders= new ArrayList<OrderHistory>();

	
	public ArrayList<OrderHistory> getOrders() {
		return orders;
	}
	public void setOrders(ArrayList<OrderHistory> orders) {
		this.orders = orders;
	}
	public int getOrderhistory_id() {
		return orderhistory_id;
	}
	public void setOrderhistory_id(int orderhistory_id) {
		this.orderhistory_id = orderhistory_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public OrderHistory(int orderhistory_id, int user_id, int order_id, double total, String status) {
		super();
		this.orderhistory_id = orderhistory_id;
		this.user_id = user_id;
		this.order_id = order_id;
		this.total = total;
		this.status = status;
		
	}
	public OrderHistory(int user_id, int order_id, double totalPrice, String status) {
		super();
		this.user_id = user_id;
		this.order_id = order_id;
		this.total = totalPrice;
		this.status = status;
	}
	public OrderHistory() {
		super();
	}
	@Override
	public String toString() {
		return  orderhistory_id + "  " + user_id + "   " + order_id
				+ "  " + total + "  " + status ;
	}
	
	

}
