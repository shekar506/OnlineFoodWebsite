package com.OnlineFood.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.OnlineFood.dao.OrderDao;
import com.OnlineFood.model.Orders;

public class OrdersDaoImpl implements OrderDao{
	static Connection con;
	Orders order;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet result;
	static int x;
	ArrayList<Orders> orderslist = new ArrayList<Orders>();
	
	private static final String INSERT_ORDER="insert into orders(order_id,user_id,restaurant_id,totalAmount,status,paymentOption) values(?,?,?,?,?,?)";
	private static final String GET_ORDER_BY_ID="select * from orders where order_id=?";
	private static final String UPDATE_ORDER_BY_ID="update orders set status=? where order_id=?";

	
	
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
	public int insertOrder(Orders order) {
		try {
			pstmt = con.prepareStatement(INSERT_ORDER);
			pstmt.setInt(1, order.getOrder_id());
			pstmt.setInt(2, order.getUser_id());
			pstmt.setInt(3, order.getRestaurant_id());
			pstmt.setDouble(4, order.getTotalAmount());
			pstmt.setString(5, order.getStatus());
			pstmt.setString(6, order.getPaymentOption());
			
			 x=pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return x;
	}

	@Override
	public Orders getOrderById(int id) {

		try {
			pstmt=con.prepareStatement(GET_ORDER_BY_ID);
			pstmt.setInt(1, id);
			 result=pstmt.executeQuery();
			 orderslist=(ArrayList<Orders>) extractAllOrders(result);
			  order =orderslist.get(0);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public int updateOrderById(int id, String status) {

		try {
			pstmt=con.prepareStatement(UPDATE_ORDER_BY_ID);
			pstmt.setInt(2, id);
			pstmt.setString(1, status);
			 x=pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	List<Orders> extractAllOrders(ResultSet result){
		try {
			while(result.next()) {
				
			     orderslist.add(new Orders(
			    		 result.getInt(1),
			    		 result.getInt(2),
			    		 result.getInt(3),
			    	     result.getDouble(4),
			    	     result.getString(5),
			    	     result.getString(7)));
			    }
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return orderslist;
	}
	

}
