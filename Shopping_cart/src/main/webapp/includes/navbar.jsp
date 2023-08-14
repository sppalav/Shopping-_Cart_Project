<nav class="navbar navbar-expand-sm navbar-light bg-light">
<div class="container">

  
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    </button>
    
    <ul class="navbar-nav ml-auto">
    
     <div class="logo">
    
    </div>
        <li class="nav-item active">
          <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link me-1" href="cart.jsp">Cart<span class="badge bg-danger ms-1">${cart_list.size()}</span></a>
        </li> 
        <%if(auth!=null){%>
            <li class="nav-item">
            <a class="nav-link" href="order.jsp">Orders</a>
            <li/>
              <li class="nav-item">
              <a class="nav-link" href="Log-out">Logout</a>
                       <li/>
        	
       <%}else{%>
       
              <li class="nav-item">
              
              <a class="nav-link" href="login.jsp">Login</a>
               <li/>
        	
        <%}
        %>
       
    
      </ul>

    </div>
  </div>

</nav>