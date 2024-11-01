package com.OnlineFood.model;

import java.util.ArrayList;

public class Orders {

	private int order_id;
	private int user_id;
	private int restaurant_id;
	private double totalAmount;
	private String status;
	private String paymentOption;
	ArrayList<Orders> orders= new ArrayList<Orders>();
	public int getOrder_id() {
		return order_id;
	}
	
	
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentOption() {
		return paymentOption;
	}
	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}
	public Orders(int order_id, int user_id, int restaurant_id, double totalAmount, String status, String paymentOption) {
		super();
		this.order_id = order_id;
		this.user_id = user_id;
		this.restaurant_id = restaurant_id;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentOption = paymentOption;
	}
	public Orders(int user_id, int restaurant_id, double totalPrice, String status, String paymentOption) {
		super();
		this.user_id = user_id;
		this.restaurant_id = restaurant_id;
		this.totalAmount = totalPrice;
		this.status = status;
		this.paymentOption = paymentOption;
	}
	public Orders() {
		super();
	}
	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", user_id=" + user_id + ", restaurant_id=" + restaurant_id
				+ ", totalAmount=" + totalAmount + ", status=" + status + ", paymentOption=" + paymentOption + "]";
	}
	
}
