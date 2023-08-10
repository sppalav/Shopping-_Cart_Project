package com.servalet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import com.model.*;

import org.apache.catalina.User;

/**
 * Servlet implementation class OrderNowServlet
 */
@WebServlet("/Order-Now-Servlet")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderNowServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {

			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

			Date date = new Date();

			Users auth = (Users) request.getSession().getAttribute("auth");

			if (auth != null) {
				String productId = request.getParameter("id");
				int productQuntity = Integer.parseInt(request.getParameter("quantity"));
				if (productQuntity <= 0) {
					productQuntity = 1;
				}

				Order ordermodel = new Order();
				ordermodel.setId(Integer.parseInt(productId));
				ordermodel.setUid(auth.getId());
				ordermodel.setQuantity(productQuntity);
				ordermodel.setDate(sd.format(date));

				OrderDao ord = new OrderDao(Dbcon.getConnection());
				boolean res = ord.insertOrder(ordermodel);

				if (res) {
					ArrayList<cart> cart_list = (ArrayList<cart>) request.getSession().getAttribute("cart-list");
					if (cart_list != null) {
						for (cart c : cart_list) {
							if (c.getId() == Integer.parseInt(productId)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}

						}
					
					}
					response.sendRedirect("order.jsp");
				} else {
					
                   out.print("Order Failed");
				}

			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
