package com.servalet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conection.Dbcon;
import com.dao.UserDao;
import com.model.Users;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	
	try
	{
		PrintWriter out=response.getWriter();
		String email=request.getParameter("login-email");
		String password=request.getParameter("login-pass");
		
        UserDao udao=new UserDao(Dbcon.getConnection());
	  Users user=  udao.userLogin(email, password);
	    
	  if(user !=null)
	  {
		  request.getSession().setAttribute("auth", user);
		  
		  response.sendRedirect("index.jsp");
	  }else {
		  out.println(
					"<h3 style='color:crimson; text-align:center'>Please Insert The correct Password ..<a href='login.jsp'>Go to the Login Page</a></>");
	  }
		
	}
	catch(Exception e){
		e.printStackTrace();
		System.out.println(e.getMessage());
		
	}
	}

}
     