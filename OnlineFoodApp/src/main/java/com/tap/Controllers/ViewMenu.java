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

import com.OnlineFood.dao.menudao;
import com.OnlineFood.daoimpl.MenuDaoImpl;
import com.OnlineFood.model.menu;

/**
 * Servlet implementation class ViewMenu
 */
@WebServlet("/ViewMenu")
public class ViewMenu extends HttpServlet {
	static Connection con;
	PreparedStatement pstmt;
	String sql="select * from menu where restaurant_id=?";
	private ResultSet result;
	
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
		
		int id=Integer.parseInt(req.getParameter("id"));
		
		menudao menu = new  MenuDaoImpl();
		List<menu> m= menu.getAllRestMenu(id);
		req.getSession().setAttribute("menu", m);
		resp.sendRedirect("ViewMenu.jsp");
		

	}

}
