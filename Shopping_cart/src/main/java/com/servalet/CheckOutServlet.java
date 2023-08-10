package com.servalet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conection.Dbcon;
import com.dao.OrderDao;
import com.model.Order;
import com.model.Users;
import com.model.cart;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/Cart-Check-Out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out=response.getWriter()) {
			
			out.println("Check out Servlet");
			

			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

			Date date = new Date();
			
			//retrive all cart products
			
			ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");
             
			//User Authentication
			
			Users auth = (Users) request.getSession().getAttribute("auth");
			
			//check auth session and cart list
			
			if(cart_list!=null && auth !=null)
			{
				
				for(cart c:cart_list)
				{
					//prepare order object 
				Order ord =new Order();
				ord.setId(c.getId());
				ord.setUid(auth.getId());
				ord.setQuantity(c.getQuantity());
				ord.setDate(sd.format(date));
				   //instantiate the dao class
				OrderDao od= new OrderDao(Dbcon.getConnection());
				   //calling the insert method
				boolean result =od.insertOrder(ord);
				if(!result)break;
				
				}
				cart_list.clear();
				response.sendRedirect("order.jsp");
				
				
			}else {
				if(auth==null)response.sendRedirect("login.jsp");	
			response.sendRedirect("cart.jsp");
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
