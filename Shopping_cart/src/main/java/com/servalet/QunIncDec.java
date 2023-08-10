package com.servalet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.cart;

/**
 * Servlet implementation class QunIncDec
 */
@WebServlet("/Qun-Inc-Dec")
public class QunIncDec extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public QunIncDec() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter();) {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));

			ArrayList<cart> cartlist = (ArrayList<cart>) request.getSession().getAttribute("cart-list");

			if (action != null && id >= 1) {
				if (action.equals("inc")) {
					for (cart c : cartlist) {
						if (c.getId() == id) {
							int qun = c.getQuantity();
							qun++;
							c.setQuantity(qun);
							response.sendRedirect("cart.jsp");

						}
					}
				}
				if (action.equals("dec")) {
					for (cart c : cartlist) {
						if (c.getId() == id && c.getQuantity()>1) {
							int qun = c.getQuantity();
							qun--;
							c.setQuantity(qun);
							break;
						}
					}
					response.sendRedirect("cart.jsp");
				}
				
			}else {
				response.sendRedirect("cart.jsp");
			}
		}

	}

}
