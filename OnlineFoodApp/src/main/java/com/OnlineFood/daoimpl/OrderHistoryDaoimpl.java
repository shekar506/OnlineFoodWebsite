package com.OnlineFood.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.OnlineFood.dao.OrderHistoryDao;
import com.OnlineFood.model.OrderHistory;

public class OrderHistoryDaoimpl implements OrderHistoryDao{
	

	static Connection con;
	OrderHistory orderhistory;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet result;
	static int x;
	ArrayList<OrderHistory> orderhistorylist = new ArrayList<OrderHistory>();
	
	private static final String INSERT_ORDERHISTORY="insert into orderhistory(orderhistory_id,user_id,order_id,Total,Status) values(?,?,?,?,?)";
	private static final String FETCH_OH_BY_ID="select * from orderhistory where user_id=?";
	private static final String UPDATE_ORDERHISTORY_BY_ID="update orderhistory set status=? where orderhistory_id=?";

	
	
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
	public int insertOrder(OrderHistory orderhistory) {

		try {
			pstmt = con.prepareStatement(INSERT_ORDERHISTORY);
			pstmt.setInt(1, orderhistory.getOrderhistory_id());
			pstmt.setInt(2, orderhistory.getUser_id());
			pstmt.setInt(3, orderhistory.getOrder_id());
			pstmt.setDouble(4, orderhistory.getTotal());
			pstmt.setString(5, orderhistory.getStatus());
			
			 x=pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return x;
	}

	@Override
	public ArrayList<OrderHistory> fetchOhById(int rest_id) {

		try {
			pstmt=con.prepareStatement(FETCH_OH_BY_ID);
			pstmt.setInt(1, rest_id);
			 result=pstmt.executeQuery();
			 orderhistorylist=(ArrayList<OrderHistory>) extractAllOrders(result);	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderhistorylist;
	}

	@Override
	public int updateOrderHistoryById(int id, String status) {
		try {
			pstmt=con.prepareStatement(UPDATE_ORDERHISTORY_BY_ID);
			pstmt.setInt(2, id);
			pstmt.setString(1, status);
			 x=pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	List<OrderHistory> extractAllOrders(ResultSet result){
		try {
			while(result.next()) {
				
			     orderhistorylist.add(new OrderHistory(
			    		 result.getInt(1),
			    		 result.getInt(2),
			    		 result.getInt(3),
			    	     result.getInt(5),
			    	     result.getString(6)));
			    }
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return orderhistorylist;
	}


}
