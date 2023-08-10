package com.servalet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conection.Dbcon;
import com.dao.OrderDao;
import com.dao.RegisterDao;
import com.model.Register;
import com.model.Users;

/**
 * Servlet implementation class registernew
 */
@WebServlet("/register-new")
public class registernew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		try (PrintWriter out = response.getWriter()) {
			
			out.print("sppalav");
			
			
			String name =request.getParameter("name");
			String email =request.getParameter("email");
			String password=request.getParameter("pass");
			
			
				
				Register r=new Register();
				r.setName(name);
				r.setEmail(email);
				r.setPassword(password);
				
				RegisterDao rd= new RegisterDao(Dbcon.getConnection());
			    rd.insertregister(r);
			    
			    
			    response.sendRedirect("index.jsp");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
		
	}


