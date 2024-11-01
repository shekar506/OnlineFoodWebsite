package com.tap.Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.OnlineFood.dao.UserDao;
import com.OnlineFood.daoimpl.UserDaoimpl;
import com.OnlineFood.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {

	HttpSession session ;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String email=req.getParameter("username");
		String password=req.getParameter("password");
		

		UserDao udao = new UserDaoimpl();
		User user=udao.getUserByEmail(email);
		
		if(user != null) 
		{
			if(password.equals(user.getPassword())) {
				 session = req.getSession();
				 session.setAttribute("user", user);

				 req.getRequestDispatcher("HomeServlet").forward(req, resp);
			}
			else {
				resp.sendRedirect("error.html");;
			}
		}
		
		else 
		{
			resp.sendRedirect("UserNotRegister.html");
		}
		
		
	}
	
}
