package com.tap.Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.OnlineFood.dao.UserDao;
import com.OnlineFood.daoimpl.UserDaoimpl;
import com.OnlineFood.model.User;

/**
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
       
	@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String Username=req.getParameter("username");
			String email=req.getParameter("email");
			String password=req.getParameter("password");
			String address=req.getParameter("address");
			
			User u=new User(Username, password, email, address);
			UserDao udao=new UserDaoimpl();
			int x=udao.insertUser(u);
			if(x==1) {
				resp.sendRedirect("success.html");
			}
			else {
				resp.sendRedirect("failure.html");;
			}
	
	}

}
