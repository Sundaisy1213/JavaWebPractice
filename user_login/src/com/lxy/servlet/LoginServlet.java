package com.lxy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lxy.User;
import com.lxy.dao.UserDao;

/**
 * Servlet implementation class LoginServlet
 * �û���¼servlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;//??????????
	
  	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
  					throws ServletException, IOException {
  		String username = request.getParameter("username");
  		String password = request.getParameter("password");
  		
  		UserDao userDao = new UserDao();
  		User user = userDao.login(username, password);
  		if(user != null){
  			request.getSession().setAttribute("user", user);
  			request.getRequestDispatcher("message.jsp").forward(request, response);
  		}else{
  			request.setAttribute("info", "�����û������������!");
  			request.getRequestDispatcher("message.jsp").forward(request, response);
  		}  		
	}
}
