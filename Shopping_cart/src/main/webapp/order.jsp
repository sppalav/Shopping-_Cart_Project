<%@page import="java.util.List"%>
<%@page import="com.dao.OrderDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model.Users"%>
<%@page import="com.conection.Dbcon"%>
<%@page import="com.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Order> orders = null;
Users auth = (Users) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
	orders = new OrderDao(Dbcon.getConnection()).userOrders(auth.getId());
} else {
	response.sendRedirect("login.jsp");
}
ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");

if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Order page</title>
<%@ include file="includes/head.jsp"%>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>
	<br>
	<div class="container">
		<div class="row no-gutters">
				<div class="p-3 mb-2 bg-light text-dark">All Orders</div>
				
				
			<table class="table table-light">
				<thead>
					<tr>
						<th scope="col">Date</th>
						<th scope="col">Name</th>
						<th scope="col">Category</th>
						<th scope="col">Quantity</th>
						<th scope="col">Price</th>
						<th scope="col">Remove Orders</th>
					</tr>
				</thead>
				<tbody>
					<%
					if (orders != null) {
						for (Order o : orders) {
					%>
					<tr>
						<td><%=o.getDate()%></td>
						<td><%=o.getName()%></td>
						<td><%=o.getCategory()%></td>
						<td><%=o.getQuantity()%></td>
						<td><%=o.getPrice()%></td>
						<td><a class="btn btn-sm btn-danger p-2"
							href="Cancel-Order-Servlet?id=<%=o.getId()%>">Cancel</a></td>
					<tr>
						<%
						}
						}
						%>
				</tbody>


			</table>
		</div>
	</div>


	<%@ include file="includes/footer.jsp"%>
</body>
</html>