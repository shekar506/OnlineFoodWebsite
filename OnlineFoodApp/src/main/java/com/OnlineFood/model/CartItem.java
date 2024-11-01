package com.OnlineFood.model;

public class CartItem {
	
	private int itemId;
	private int restaurantId;
	private String name;
	private int quantity;
	private int price;
	private String imgpath;
	private int menuId;
	
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartItem(int itemId, int restaurantId, String name, int quantity, int price,String imgpath,int menuId) {
		super();
		this.itemId = itemId;
		this.restaurantId = restaurantId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.imgpath=imgpath;
		this.menuId=menuId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	@Override
	public String toString() {
		return itemId + " " + restaurantId + " " + name + " "
				+ quantity + " " + price +" "+imgpath+" "+menuId;
	}
	
	
	

	
}
