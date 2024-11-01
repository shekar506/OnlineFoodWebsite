package com.OnlineFood.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.OnlineFood.dao.menudao;
import com.OnlineFood.model.menu;

public class MenuDaoImpl implements menudao{
	
	static Connection con;
	menu menu;
	
	
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet result;
	static int x;
	ArrayList<menu> menulist = new ArrayList<menu>();

	private static final String INSERT_MENU="insert into menu(menu_id,restaurant_id,itemname,description,price,isAvailable,imgpath) values(?,?,?,?,?,?,?)";
	private static final String GET_ALL_REST_MENU="select * from menu where restaurant_id=?";
	private static final String GET_MENU_BY_ID="select * from menu where menu_id=?";
	private static final String DELETE_MENU_BY_ID="delete from menu where menu_id=?";
	private static final String UPDATE_MENU_BY_ID="update menu set isavailable =? where menu_id=?";


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
	public int insertMenu(menu m) {
		try {
			pstmt = con.prepareStatement(INSERT_MENU);
			pstmt.setInt(1, m.getMenu_id());
			pstmt.setInt(2, m.getRestaurant_id());
			pstmt.setString(3, m.getItemName());
			pstmt.setString(4, m.getDescription());
			pstmt.setInt(5, m.getPrice());
			pstmt.setBoolean(6,m.isAvailable());
			pstmt.setString(7,m.getImgpath());
			
			 x=pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return x;
	}

	@Override
	public List<menu> getAllRestMenu(int restaurant_id) {
		try {

			pstmt=con.prepareStatement(GET_ALL_REST_MENU, restaurant_id);
			pstmt.setInt(1, restaurant_id);
			result=pstmt.executeQuery();
		    menulist=(ArrayList<menu>) extractAllmenu(result);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return menulist;
	}

	@Override
	public menu getMenuById(int menu_id) {
		try {
			pstmt=con.prepareStatement(GET_MENU_BY_ID);
			pstmt.setInt(1, menu_id);
			 result=pstmt.executeQuery();
			 menulist=(ArrayList<menu>) extractAllmenu(result);
			  menu =menulist.get(0);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menu;
	}

	@Override
	public int deleteMenuById(int menu_id) {
		try {
			pstmt=con.prepareStatement(DELETE_MENU_BY_ID);
			pstmt.setInt(1, menu_id);
			x=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}

	@Override
	public int updateMenuById(int menu_id, boolean isavailable) {
		try {
			pstmt=con.prepareStatement(UPDATE_MENU_BY_ID);
			pstmt.setInt(1, menu_id);
			pstmt.setBoolean(2, isavailable);
			x=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}

	List<menu> extractAllmenu(ResultSet result){
		try {
			while(result.next()) {
				
			     menulist.add(new menu(
			    		 result.getInt(1),
			    		 result.getInt(2),
			    		 result.getString(3),
			    	     result.getString(4),
			    	     result.getInt(5),
			    	     result.getBoolean(6),
			    	     result.getString(7)));
			    }
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return menulist;
	}

}
