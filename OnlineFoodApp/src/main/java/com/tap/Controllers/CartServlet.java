package com.tap.Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OnlineFood.dao.menudao;
import com.OnlineFood.daoimpl.Cart;
import com.OnlineFood.daoimpl.MenuDaoImpl;
import com.OnlineFood.model.CartItem;
import com.OnlineFood.model.menu;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		Cart cart=(Cart) session.getAttribute("cart");
		if(cart==null) {
			cart=new Cart();
			session.setAttribute("cart", cart);
		}
		
		String action =req.getParameter("action");
		if("add".equals(action)) {
			addItemToCart(req,cart);
			
		}
		else if("update".equals(action)) {
			updateItem(req,cart);
		}
		else if("delete".equals(action)) {
			deleteFromCart(req,cart);
		}
		session.setAttribute("cart",cart);
		resp.sendRedirect("Cart.jsp");;
			
	}
	
	//adding item to cart
	private void addItemToCart(HttpServletRequest req, Cart cart) {

		int itemId=Integer.parseInt(req.getParameter("itemId"));
		int quantity=Integer.parseInt(req.getParameter("quantity"));
		
		menudao menu=new  MenuDaoImpl();
		menu menuItem=menu.getMenuById(itemId);
		
		HttpSession session=req.getSession();
		session.setAttribute("restaurentId", menuItem.getRestaurant_id());
		
		if(menuItem !=null) {
			CartItem item=new CartItem(
					menuItem.getMenu_id(),
					menuItem.getRestaurant_id(),
					menuItem.getItemName(),
					quantity,
					menuItem.getPrice(),
					menuItem.getImgpath(),
					menuItem.getMenu_id()
					);
			cart.addItem(item);
			
			
		}
		
		
	}

	//updating quantity of item in cart
	private void updateItem(HttpServletRequest req, Cart cart) {
	    try {
	        int itemId = Integer.parseInt(req.getParameter("itemId"));
	        int quantity = Integer.parseInt(req.getParameter("quantity"));
	        
	        if (quantity > 0) {
	            cart.updateItem(itemId, quantity);
	        } else {
	            cart.deleteItem(itemId); // Remove if quantity is 0 or invalid
	        }
	    } catch (NumberFormatException e) {
	        e.printStackTrace(); // Handle or log error
	    }
	}

	private void deleteFromCart(HttpServletRequest req, Cart cart) {
	    try {
	        int itemId = Integer.parseInt(req.getParameter("itemId"));
	        cart.deleteItem(itemId);
	    } catch (NumberFormatException e) {
	        e.printStackTrace(); // Handle or log error
	    }
	}

	
}
