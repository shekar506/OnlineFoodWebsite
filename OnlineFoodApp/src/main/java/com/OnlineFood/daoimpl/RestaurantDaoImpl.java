package com.OnlineFood.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.OnlineFood.dao.RestaurantDao;
import com.OnlineFood.model.Restaurant;

public class RestaurantDaoImpl implements RestaurantDao{
	
	int x;
	Restaurant restaurant;
	ArrayList<Restaurant> restlist = new ArrayList<Restaurant>();
   private static final String INSERT_INTO_REST = "INSERT INTO restaurant (restaurant_id,name, Cuisine_Type, delivery_time, isActive, rating, imgpath) VALUES (?,?, ?, ?, ?, ?, ?)";
   private static final String GET_REST_BY_ID = "SELECT * FROM restaurant WHERE restaurant_id = ?";
   private static final String  UPDATE_REST_BY_ID = "UPDATE restaurant SET isActive = ? WHERE restaurant_id = ?";
   private static final String GET_ALL_RESTAURANTS = "SELECT * FROM restaurant";

   private static final String DELETE_REST_BY_ID = "DELETE FROM restaurant WHERE restaurant_id = ?";
   static Connection con;
   
   static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinefood","root","root");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
@Override
public int insertRestaurant(Restaurant rest) {
	try {
	         PreparedStatement pstmt = con.prepareStatement(INSERT_INTO_REST);
	        pstmt.setInt(1, rest.getRestaurant_id());
	        pstmt.setString(2, rest.getName());
	        pstmt.setString(3, rest.getCuisinetype());
	        pstmt.setInt(4, rest.getDeliveryttime());
	        pstmt.setInt(5, rest.getIsactive());
	        pstmt.setDouble(6, rest.getRating());
	        pstmt.setString(7, rest.getImgpath());

	         x = pstmt.executeUpdate();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	return x;
}
@Override
public List<Restaurant> getAllRestaurants() {

	try {
	         Statement stmt = con.createStatement();
	         ResultSet result = stmt.executeQuery(GET_ALL_RESTAURANTS);
	         
	         restlist= (ArrayList<Restaurant>) extractAllUsers(result);

	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	return restlist;
}
@Override
public Restaurant getRestaurantById(int id) {
	 try {
	         PreparedStatement pstmt = con.prepareStatement(GET_REST_BY_ID);
	        
	        pstmt.setInt(1, id);
	        ResultSet result=pstmt.executeQuery();
	          restlist = (ArrayList<Restaurant>) extractAllUsers(result);
	           restaurant = restlist.get(0);
	       
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	
	return restaurant;
}
@Override
public int deleteRestaurantById(int id) {
	try {
        PreparedStatement pstmt = con.prepareStatement(DELETE_REST_BY_ID);
       
       pstmt.setInt(1, id);
       x=pstmt.executeUpdate();
      
   } catch (SQLException e) {
       e.printStackTrace();
   }
	return x;
}
@Override
public int updateRestaurantById(int id, int isactive) {
	try {
        PreparedStatement pstmt = con.prepareStatement(UPDATE_REST_BY_ID);
       
       pstmt.setInt(2, id);
       pstmt.setInt(1,isactive);
       x=pstmt.executeUpdate();
      
   } catch (SQLException e) {
       e.printStackTrace();
   }
	return x;
}

List<Restaurant> extractAllUsers(ResultSet result){
	try {
		while(result.next()) {
			
		     restlist.add(new Restaurant(
		    		 result.getInt(1),
		    		 result.getString(2),
		    		 result.getString(3),
		    	     result.getInt(4),
		    	     result.getInt(5),
		    	     result.getDouble(6),
		    	     result.getString(7)));

		     
		    }
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}
	return restlist;
}
	

}
