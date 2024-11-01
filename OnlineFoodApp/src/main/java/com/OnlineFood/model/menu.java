package com.OnlineFood.model;

public class menu 
{

	private int menu_id;
	private int restaurant_id;
	private String itemName;
	private String description;
	private int price;
	private boolean isAvailable;
	private String imgpath;
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public int getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public menu(int menu_id, int restaurant_id, String itemName, String description, int price, boolean isAvailable,
			String imgpath) {
		super();
		this.menu_id = menu_id;
		this.restaurant_id = restaurant_id;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.isAvailable = isAvailable;
		this.imgpath = imgpath;
	}
	public menu(int restaurant_id, String itemName, String description, int price, boolean isAvailable,
			String imgpath) {
		super();
		this.restaurant_id = restaurant_id;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.isAvailable = isAvailable;
		this.imgpath = imgpath;
	}
	public menu() {
		super();
	}
	@Override
	public String toString() {
		return "menu [menu_id=" + menu_id + ", restaurant_id=" + restaurant_id + ", itemName=" + itemName
				+ ", description=" + description + ", price=" + price + ", isAvailable=" + isAvailable + ", imgpath="
				+ imgpath + "]";
	}
	
	
}
