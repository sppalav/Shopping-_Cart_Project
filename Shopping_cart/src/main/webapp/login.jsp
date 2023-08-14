<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.model.*"%>
    
     <%
   Users auth=(Users)request.getSession().getAttribute("auth");
    if(auth != null)
    {
    	response.sendRedirect("index.jsp");
    }
    %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="dx.css">
<title>Shopping cart login</title>
<%@ include file="includes/head.jsp" %>
</head>
<body>
<header>
<div><%@ include file="includes/navbar.jsp" %>
<div class="tech">
<div class="container min-vh-80 d-flex justify-content-center align-items-center">
<div class="card w-50 mx-outo my-5">
<div class="container d-flex justify-content-center">
<div class="fw-light"><h2>Login</h2></div>
</div>
<div class="card-body">
<form action="LoginServlet" method="post">

<div class="form-group">
<label>Email Address</label>
<input type="text" class="form-control" name="login-email" placeholder="Enter the Email"  required="required"> 
</div>
<div class="form-group">
<label>Password</label>
<input type="password" class="form-control" name="login-pass"  required="required"> 
</div>
<br>
<div class="text-center">
<button type="submit" class="btn btn-primary" >Login</button>
<a href="Register.jsp"class="btn btn-primary">Register</a>
</div>
</form>
</div>
</div>
</div>
</div>
</div>
</header>
<%@ include file="includes/footer.jsp" %>
</body>
</html>
