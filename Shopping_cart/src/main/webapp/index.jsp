<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.ProductDao"%>
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
ProductDao pd = new ProductDao(Dbcon.getConnection());
List<product> products = pd.getAllProducts();

ArrayList<cart> cart_list = (ArrayList<cart>) session.getAttribute("cart-list");

if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome to Shopping Cart</title>
<%@ include file="includes/head.jsp"%>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>
	<div class="container">
		<br>
		<div class="p-3 mb-2 bg-light text-dark">All Products</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (product p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
					<img class="card-img-top" src="product-image/<%=p.getImage()%>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName()%></h5>
						<h6 class="price"><%=p.getPrice()%></h6>
						<h6 class="category">
							<%=p.getCategory()%></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a href="add-to-cart?id=<%=p.getId()%>" class="btn btn-dark">Add to Cart</a> 
								<a href="Order-Now-Servlet?quantity=1&id=<%=p.getId()%>" class="btn btn-primary">Buy Now</a>
						</div>

					</div>
				</div>
			</div>
			<%
			}
			}
			%>
			

		</div>
	</div>

	<%@ include file="includes/footer.jsp"%>



</body>
</html>