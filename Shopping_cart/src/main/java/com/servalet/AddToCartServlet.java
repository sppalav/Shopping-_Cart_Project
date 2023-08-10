package com.servalet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");

		try (PrintWriter out = response.getWriter()) {

			ArrayList<cart> cartlist = new ArrayList<>(); 
			int id = Integer.parseInt(request.getParameter("id"));

			cart cm = new cart();
			cm.setId(id);
			cm.setQuantity(1);

			HttpSession session = request.getSession();
			ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");

			if (cart_list == null) {
				cartlist.add(cm);
				session.setAttribute("cart-list", cartlist);
				response.sendRedirect("index.jsp");
			} else {
				cartlist = cart_list;
				boolean exist = false;

				for (cart c : cart_list) {
					if (c.getId() == id) {
						exist = true;
						out.println(
								"<h3 style='color:crimson; text-align:center'>Item already in cart..<a href='cart.jsp'>Go to the Cart Page</a></>");
					}
				}
				if (!exist) {

					cartlist.add(cm);
					response.sendRedirect("index.jsp");

				}
			}

		}

	}

}
