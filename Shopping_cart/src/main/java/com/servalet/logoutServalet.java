package com.servalet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class logoutServalet
 */
@WebServlet(name = "LogoutServalet", urlPatterns = { "/Log-out" })
public class logoutServalet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		try (PrintWriter out=response.getWriter()){
			if(request.getSession().getAttribute("auth")!=null) {
				request.getSession().removeAttribute("auth");			
			response.sendRedirect("login.jsp");
			}else {
			response.sendRedirect("index.jsp");
			
		}
	
	}

}
}
