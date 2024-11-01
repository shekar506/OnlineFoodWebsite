package com.tap.Controllers;

import java.awt.Menu;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OnlineFood.dao.OrderDao;
import com.OnlineFood.dao.OrderItemsDao;
import com.OnlineFood.dao.menudao;
import com.OnlineFood.daoimpl.MenuDaoImpl;
import com.OnlineFood.daoimpl.OrderItemsDaoImpl;
import com.OnlineFood.daoimpl.OrdersDaoImpl;
import com.OnlineFood.model.OrderItems;
import com.OnlineFood.model.Orders;
import com.OnlineFood.model.menu;

/**
 * Servlet implementation class OrderDetails
 */
@WebServlet("/OrderDetails")
public class OrderDetails extends HttpServlet {
	HttpSession session;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	 session=req.getSession();
	 int orderId = Integer.parseInt(req.getParameter("order_id"));

	OrderDao od=new OrdersDaoImpl();
	Orders o=od.getOrderById(orderId);
	session.setAttribute("OrderDetails",o);
	
	
	OrderItemsDao oi=new OrderItemsDaoImpl();
	OrderItems oitem=oi.fetchOrderItems(orderId);
	
	int menuId=oitem.getMenu_id();
	menudao m=new MenuDaoImpl();
	menu menu=m.getMenuById(menuId);
	
	session.setAttribute("Item", menu.getItemName());
	session.setAttribute("ItemImage", menu.getImgpath());
	
	resp.sendRedirect("OrderDetails.jsp");
	 
	 
	}
}
