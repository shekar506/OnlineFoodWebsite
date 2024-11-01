package com.tap.Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OnlineFood.dao.OrderDao;
import com.OnlineFood.dao.OrderHistoryDao;
import com.OnlineFood.dao.OrderItemsDao;
import com.OnlineFood.daoimpl.Cart;
import com.OnlineFood.daoimpl.OrderHistoryDaoimpl;
import com.OnlineFood.daoimpl.OrderItemsDaoImpl;
import com.OnlineFood.daoimpl.OrdersDaoImpl;
import com.OnlineFood.model.CartItem;
import com.OnlineFood.model.OrderHistory;
import com.OnlineFood.model.OrderItems;
import com.OnlineFood.model.Orders;
import com.OnlineFood.model.User;
import com.OnlineFood.model.menu;

/**
 * Servlet implementation class CheckOut
 */
@WebServlet("/CheckOut")
public class CheckOut extends HttpServlet {
	
	static Connection con;
	static int quantity;
	static int menuId;
	private HttpSession session;
	private Statement stmt;
	private String itemName;
	static double totalPrice=0;

	
	//connecting to database
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
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		session= req.getSession();
		User u=(User) session.getAttribute("user");
		int user_id=u.getUser_id();
		int restaurant_id=(int) session.getAttribute("restaurentId");
		String paymentOpt = req.getParameter("paymentMethod");	

		Cart cart=(Cart) session.getAttribute("cart");
				if (cart == null) {
		    resp.sendRedirect("Cart.jsp");
		    return;
		}
				
		//getting totalPrice,Quantity,menuId from cart session
				
		 Map<Integer, CartItem> items = cart.getItems();
		 for (CartItem item : items.values()) {
             totalPrice += item.getPrice() * item.getQuantity();
             quantity=item.getQuantity();
             menuId=item.getMenuId();
             itemName=item.getName();
		 }
		 session.setAttribute("totalPrice", totalPrice);
		 session.setAttribute("orderId", maxOrderId());
		 session.setAttribute("OrderHistory", itemName);
		 
		 
		 
		//inserting into orders table
		Orders od=new Orders(user_id,restaurant_id,totalPrice,"order placed",paymentOpt);
		OrderDao odao=new OrdersDaoImpl();
		int x=odao.insertOrder(od);
		
		//inserting into orderItems table
		OrderItems odi=new OrderItems(maxOrderId(),menuId,quantity,totalPrice);
		OrderItemsDao odid=new OrderItemsDaoImpl();
		int y=odid.insertOrderItem(odi);
		
		//inserting into orderHistory table 
		OrderHistory oh=new OrderHistory(user_id,maxOrderId(),totalPrice,"delivered");
		OrderHistoryDao ohd=new OrderHistoryDaoimpl();
		int z=ohd.insertOrder(oh);
		
		resp.sendRedirect("orderPlaced.jsp");
			
		
		
	}
	
	// for getting order id just inserted into table 
	
	public int maxOrderId() {
	    int maxOrderId = 0;
	    try {
	        stmt = con.createStatement();
	        ResultSet result = stmt.executeQuery("SELECT MAX(order_id) FROM orders");
	        
	        if (result.next()) { 
	        	// Retrieve the value of order id
	            maxOrderId = result.getInt(1); 
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    return maxOrderId;
	}
	
	
	

}
