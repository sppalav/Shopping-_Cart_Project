<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model.Users"%>
<%@page import="com.conection.Dbcon"%>
<%@page import="com.model.*"%>
<%@page import="com.dao.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Users auth = (Users) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}
ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");
List<cart> cartproduct = null;
if (cart_list != null) {
	ProductDao pdao = new ProductDao(Dbcon.getConnection());
	cartproduct = pdao.getCartProducts1(cart_list);
	double total = pdao.getTotalCartPrice(cart_list);
	request.setAttribute("cart_list", cart_list);
	request.setAttribute("total", total);
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Cart page</title>
<%@ include file="includes/head.jsp"%>
<style type="text/css">
.table tbody td {
	vertical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: non;
	font-size: 25px;
}
</style>
</head>
<body>

	<%@ include file="includes/navbar.jsp"%>
	<br>

	<div class="container">

		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cart_list != null) {
					for (cart c : cartproduct) {
				%>

				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td><%=c.getPrice()%></td>
					<td>
						<form method="post" action="Order-Now-Servlet" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId()%>"
								class="form-input">
							<div class="form-group d-flex justify-content-between w-50">
								<a class="btn btn-sm btn-decre"
									href="Qun-Inc-Dec?action=dec&id=<%=c.getId()%>"> <i
									class="fas fa-minus-square"></i></a> <input type="text"
									name="quantity" class="form-control"
									value="<%=c.getQuantity()%>" readonly> <a
									class="btn btn-sm btn-incre"
									href="Qun-Inc-Dec?action=inc&id=<%=c.getId()%>"> <i
									class="fas fa-plus-square"></i></a> <br>
								<button type="submit" class="btn btn-primary max-3">Buy</button>
							</div>

						</form>
					</td>
					<td><a class="btn btn-sm btn-danger"
						href="Remove-Form-Cart?id=<%=c.getId()%>">Remove</a></td>
				</tr>

				<%
				}
				}
				%>
			</tbody>
			<br>
		</table>
		<div class="d-flex justify-content-end p-4">
			<h3>
				<b>Total price :</b>${(total>0)?total:0}</h3>
			<a class="mx-3 btn btn-primary" href="Cart-Check-Out">Check out</a>
		</div>
	</div>
	<%@ include file="includes/footer.jsp"%>
</body>
</html>