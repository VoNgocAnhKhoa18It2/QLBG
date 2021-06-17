<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page isELIgnored="false"%>
<script>
  $(document).ready(function(){
    $('.simpleCart_empty').click(function(){
      $.post("./mvc/core/xuly_ajax.php",
      {
        delete : '1',
      },
      function(data,status){
        if (status == 'success') {
          window.location.reload();
        }
      });
    });
  });
</script>
<div class="header">
  <div class="container-fluid" style="padding: 0;">
    <div class="header-top">
      <div class="logo">
        <a href="/">N-AIR</a>
      </div>
      <div class="login-bars">
      	<c:if test="${not empty userobj}">
      		<a class="btn btn-default log-bar" href="" role="button" >${userobj.name}</a>
      		<a class="btn btn-default log-bar" href="logout" role="button">Logout</a>
      	</c:if>
        <c:if test="${empty userobj}">
          <a class="btn btn-default log-bar" href="register" role="button">Sign up</a>
          <a class="btn btn-default log-bar" href="login" role="button">Login</a>		
        </c:if>
        <div class="cart box_1">
          <a href="Home/Checkout">
            <h3>
              <div class="total">
                $<span class='simpleCart_total'>
                 
                </span>(<span id="simpleCart_quantity" class="simpleCart_quantity">
                
              </span>)
            </div>
          </h3>
        </a>
        <a href="javascript:;" class="simpleCart_empty">Empty Cart</a>
        <div class="clearfix"> </div>
      </div>  
    </div>
    <div class="clearfix"></div>
  </div>
  <div class="header-botom">
    <div class="content white">
      <nav class="navbar navbar-default nav-menu" role="navigation">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div class="clearfix"></div>

        <div class="collapse navbar-collapse collapse-pdng" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav nav-font">
            <li class="nav-item">
              <a href="./" class="nav-link">Home</a>
            </li>
            <li class="nav-item dropdown">
              <a href="Home/Product" class="dropdown-toggle" >Product</a>
            </li>
            <li class="nav-item "><a href="Home/Checkout">Checkout</a></li>
            <li class="nav-item "><a href="Home/Pay" >Pay</a></li>
          </ul>
        </div>
      </nav>
    </div>
  </div>
</div>
</div>
<c:if test="${not empty succMsg }">
	<div id="snackbar">
		<h3 class="text-center alert alert-success">${succMsg}</h3>
	</div>
	<c:remove var="succMsg" scope="session" />
	<script>
			var x = document.getElementById("snackbar");
			x.className = "show";
			setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
		</script>
</c:if>