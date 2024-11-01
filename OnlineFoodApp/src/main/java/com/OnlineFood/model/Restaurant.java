	package com.OnlineFood.model;

import java.util.ArrayList;

import com.OnlineFood.dao.RestaurantDao;


public class Restaurant {
	
	private int restaurant_id;
	ArrayList<RestaurantDao> user= new ArrayList<RestaurantDao>();
	private String name;
	private String cuisinetype;
	private int deliveryttime;
	private int isactive;
	private double rating;
	private String imgpath;
	public int getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public ArrayList<RestaurantDao> getUser() {
		return user;
	}
	public void setUser(ArrayList<RestaurantDao> user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCuisinetype() {
		return cuisinetype;
	}
	public void setCuisinetype(String cuisinetype) {
		this.cuisinetype = cuisinetype;
	}
	public int getDeliveryttime() {
		return deliveryttime;
	}
	public void setDeliveryttime(int deliveryttime) {
		this.deliveryttime = deliveryttime;
	}
	public int getIsactive() {
		return isactive;
	}
	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public Restaurant(int restaurant_id, String name, String cuisinetype, int deliveryttime,
			int isactive, double rating, String imgpath) {
		super();
		this.restaurant_id = restaurant_id;
		this.name = name;
		this.cuisinetype = cuisinetype;
		this.deliveryttime = deliveryttime;
		this.isactive = isactive;
		this.rating = rating;
		this.imgpath = imgpath;
	}
	public Restaurant( String name, String cuisinetype, int deliveryttime, int isactive,
			double rating, String imgpath) {
		super();
		this.name = name;
		this.cuisinetype = cuisinetype;
		this.deliveryttime = deliveryttime;
		this.isactive = isactive;
		this.rating = rating;
		this.imgpath = imgpath;
	}
	public Restaurant() {
		super();
	}
	@Override
	public String toString() {
		return  restaurant_id + "  "  + name + "  "
				+ cuisinetype + "  " + deliveryttime + "  " + isactive + "  " + rating
				+ "  " + imgpath ;
	}
	
	
	

}
