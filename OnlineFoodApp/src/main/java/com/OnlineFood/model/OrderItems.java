package com.OnlineFood.model;

public class OrderItems 

{
	
	private int orderItem_id;
	private int order_id;
	private int menu_id;
	private int quantity;
	private double subtotal;
	public int getOrderItem_id() {
		return orderItem_id;
	}
	public void setOrderItem_id(int orderItem_id) {
		this.orderItem_id = orderItem_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public OrderItems(int orderItem_id, int order_id, int menu_id, int quantity, double subtotal) {
		super();
		this.orderItem_id = orderItem_id;
		this.order_id = order_id;
		this.menu_id = menu_id;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}
	public OrderItems(int order_id, int menu_id, int quantity, double totalPrice) {
		super();
		this.order_id = order_id;
		this.menu_id = menu_id;
		this.quantity = quantity;
		this.subtotal = totalPrice;
	}
	public OrderItems() {
		super();
	}
	@Override
	public String toString() {
		return "OrderItems [orderItem_id=" + orderItem_id + ", order_id=" + order_id + ", menu_id=" + menu_id
				+ ", quantity=" + quantity + ", subtotal=" + subtotal + "]";
	}

	

}
