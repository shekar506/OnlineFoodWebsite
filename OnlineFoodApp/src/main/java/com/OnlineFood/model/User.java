package com.OnlineFood.model;

import java.util.ArrayList;

public class User {
	
	private int user_id;
	ArrayList<User> user= new ArrayList<User>();
	private String username;
	private String password;
	private String email;
	private String address;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public ArrayList<User> getUser() {
		return user;
	}
	public void setUser(ArrayList<User> user) {
		this.user = user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public User(int user_id,  String username, String password, String email, String address) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		
	}
	public User( String username, String password, String email, String address) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return   username + "  " + password
				+ "  " + email + "  " + address  ;
	} 
	
	

}
