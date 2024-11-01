package com.tap.Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OnlineFood.dao.OrderDao;
import com.OnlineFood.dao.OrderHistoryDao;
import com.OnlineFood.daoimpl.OrderHistoryDaoimpl;
import com.OnlineFood.model.User;
import com.OnlineFood.model.menu;

/**
 * Servlet implementation class OrderHistory
 */
@WebServlet("/OrderHistory")
public class OrderHistory extends HttpServlet {
	static Connection con;
	PreparedStatement pstmt;
	String sql="select * from restaurant where restaurant_id=?";
	private ResultSet result;
	private HttpSession session;
	
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
		session=req.getSession();
		User u=(User) session.getAttribute("user");
		int user_id=u.getUser_id();
	
		OrderHistoryDao oh = new  OrderHistoryDaoimpl();
		List<com.OnlineFood.model.OrderHistory> rl=oh.fetchOhById(user_id);
		session.setAttribute("OrderHistory",rl);
		resp.sendRedirect("OrderHistory.jsp");
		
		

	}


}
