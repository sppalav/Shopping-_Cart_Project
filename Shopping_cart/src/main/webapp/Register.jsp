<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="includes/head.jsp" %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="tech">
<div class="container min-vh-80 d-flex justify-content-center align-items-center">
<div class="card w-50 mx-outo my-5">
<div class="container d-flex justify-content-center">
<div class="fw-light"><h2>Register</h2></div>
</div>
<div class="card-body">
<form action="register-new" method="post">

<div class="form-group">
<label>Name</label>
<input type="text" class="form-control" name="name"  required="required"> 
</div>

<div class="form-group">
<label>Email Address</label>
<input type="text" class="form-control" name="email" placeholder="Enter the Email"  required="required"> 
</div>

<div class="form-group">
<label>Password</label>
<input type="password" class="form-control" name="pass"  required="required"> 
</div>
<br>
<div class="text-center">
<button type="submit" class="btn btn-primary" >Register</button>

</div>
</form>
</div>
</div>
</div>
</div>
</div>
<%@ include file="includes/footer.jsp" %>



</body>
</html>