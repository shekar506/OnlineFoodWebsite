package com.OnlineFood.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.OnlineFood.dao.UserDao;
import com.OnlineFood.model.User;

public class UserDaoimpl implements UserDao{
	User user;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet result;
	static int x;
	ArrayList<User> userlist = new ArrayList<User>();

	static Connection con;
	private static final String INSERT_USER="insert into user(username,password,email,address) values(?,?,?,?)";
	private static final String GET_ALL_USERS="select * from user";
	private static final String GET_USERS_BY_ID="select * from user where email=?";
	private static final String DELETE_USER_BY_ID="delete from user where user_id=?";
	private static final String UPDATE_USER_BY_ID="update user set address =? where user_id=?";

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
	public int insertUser(User user) {

		try {
			pstmt = con.prepareStatement(INSERT_USER);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());
			
			
			 x=pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return x;
	}

	@Override
	public List<User> getAllUsers() {
		
		try {
			
			stmt=con.createStatement();
		    result= stmt.executeQuery(GET_ALL_USERS);
		    userlist=(ArrayList<User>) extractAllUsers(result);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return userlist;
	}

	@Override
	public User getUserByEmail(String email) {
		try {
			pstmt=con.prepareStatement(GET_USERS_BY_ID);
			pstmt.setString(1, email);
			 result=pstmt.executeQuery();
			 while(result.next()) {
				 user =new User(result.getInt("user_id"),result.getString("username"),result.getString("password"),
						  result.getString("email"),result.getString("address"));
			 }
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return user;
	}

	@Override
	public int deleteUserById(int id) {

		try {
			pstmt=con.prepareStatement(DELETE_USER_BY_ID);
			pstmt.setInt(1, id);
			 x=pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}

	@Override
	public int updateUserById(int id, String address) {

		try {
			pstmt=con.prepareStatement(UPDATE_USER_BY_ID);
			pstmt.setInt(2, id);
			pstmt.setString(1, address);
			 x=pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	List<User> extractAllUsers(ResultSet result){
		try {
			while(result.next()) {
				
			     userlist.add(new User(
			    		 result.getInt(1),
			    		 result.getString(2),
			    		 result.getString(3),
			    	     result.getString(4),
			    	     result.getString(5)));
		
			    }
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return userlist;
	}


}
