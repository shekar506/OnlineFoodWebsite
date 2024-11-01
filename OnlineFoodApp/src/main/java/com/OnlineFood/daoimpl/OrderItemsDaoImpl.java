package com.OnlineFood.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.OnlineFood.dao.OrderItemsDao;
import com.OnlineFood.model.OrderItems;

public class OrderItemsDaoImpl implements OrderItemsDao{
	

	static Connection con;
	OrderItems orderitem;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet result;
	static int x;
	ArrayList<OrderItems> orderItemlist = new ArrayList<OrderItems>();
	
	private static final String INSERT_ORDERITEMS="insert into orderitems(orderItem_id,order_id,menu_id,quantity,subtotal) values(?,?,?,?,?)";
	private static final String FETCH_ORDERITEMS_BY_ID="select * from orderitems where order_id=?";

	
	
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
	public int insertOrderItem(OrderItems orderitem) {

		try {
			pstmt = con.prepareStatement(INSERT_ORDERITEMS);
			pstmt.setInt(1, orderitem.getOrderItem_id());
			pstmt.setInt(2, orderitem.getOrder_id());
			pstmt.setInt(3, orderitem.getMenu_id());
			pstmt.setInt(4, orderitem.getQuantity());
			pstmt.setDouble(5, orderitem.getSubtotal());
			
			
			 x=pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return x;
	}



	@Override
	public OrderItems fetchOrderItems(int id) {
		try {
			pstmt=con.prepareStatement(FETCH_ORDERITEMS_BY_ID);
			pstmt.setInt(1, id);
			 result=pstmt.executeQuery();
			 orderItemlist=(ArrayList<OrderItems>) extractAllOrders(result);
			  orderitem =orderItemlist.get(0);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orderitem;
	}
	
	List<OrderItems> extractAllOrders(ResultSet result){
		try {
			while(result.next()) {
				
			     orderItemlist.add(new OrderItems(
			    		 result.getInt(1),
			    		 result.getInt(2),
			    		 result.getInt(3),
			    	     result.getInt(4),
			    	     result.getInt(5)));
			    }
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return orderItemlist;
	}



}
