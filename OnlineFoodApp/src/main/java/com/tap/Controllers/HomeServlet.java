package com.tap.Controllers;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OnlineFood.dao.RestaurantDao;
import com.OnlineFood.daoimpl.RestaurantDaoImpl;
import com.OnlineFood.model.Restaurant;

/**
 * Servlet implementation class Homeservlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

	private HttpSession session;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RestaurantDao rdao = new RestaurantDaoImpl();
		 List<Restaurant> r = rdao.getAllRestaurants();
		 session = req.getSession();
		 session.setAttribute("restaurant", r);
		 resp.sendRedirect("Home.jsp");

	}

}
