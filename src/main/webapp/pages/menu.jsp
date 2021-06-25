<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page isELIgnored="false"%>
<script>
	function toalt(message, status) {
		SnackBar({
			message : message,
			status : status,
			timeout : 3000
		})
	}
	function numberWithCommas(x) {
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	$(document).ready(function() {
		$('.simpleCart_empty').click(function() {
			$.get("./empty-cart", {}, function(data, status) {
				if (status == 'success') {
					var arr = data.split(",");
					$(".simpleCart_total").text(numberWithCommas(arr[1]));
					$("#simpleCart_quantity").text(arr[0]);
					toalt("Empty Card Success", "success")
					var pathname = window.location.pathname;
					var n = pathname.indexOf("cart");
					if (n != -1) {
						$("#total1").text(numberWithCommas(arr[1]));
						$(".last_price span").text(numberWithCommas(arr[1]));
						$(".cart-items h1").text("Shopping Bag ("+arr[0]+")")
						var html = $(".cart-items h1")
						$(".cart-items").html(html)
					}
				}
			});
		});
		$("button[name='cart']").click(function() {
			var id = $(this).val();
			var gia = $(this).children().text().trim().replace(".", "");
			var sl = $("#simpleCart_quantity").text();
			$.post("./add-cart", {
				id : id,
				gia : gia
			}, function(data, status) {
				if (status == 'success') {
					var arr = data.split(",");
					$(".simpleCart_total").text(numberWithCommas(arr[1]));
					$("#simpleCart_quantity").text(arr[0]);
					if (sl == arr[0]) {
						toalt("Add Cart Fail", "error")
					} else {
						toalt("Add Cart Success", "success")	
					}
				}
			});
		});
	});
	$.post("./add-cart", {
		id : "*",
		gia : 1
	}, function(data, status) {
		if (status == 'success') {
			var arr = data.split(",");
			$(".simpleCart_total").text(numberWithCommas(arr[1]));
			$("#simpleCart_quantity").text(arr[0]);
			var pathname = window.location.pathname;
			var n = pathname.indexOf("cart");
			if (n != -1) {
				$("#total1").text(numberWithCommas(arr[1]));
				$(".last_price span").text(numberWithCommas(arr[1]));
				$(".cart-items h1").text("Shopping Bag ("+arr[0]+")")
			}
		}
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
					<a class="btn btn-default log-bar" href="" role="button">${userobj.name}</a>
					<a class="btn btn-default log-bar" href="logout" role="button">Logout</a>
				</c:if>
				<c:if test="${empty userobj}">
					<a class="btn btn-default log-bar" href="register" role="button">Sign
						up</a>
					<a class="btn btn-default log-bar" href="login" role="button">Login</a>
				</c:if>
				<div class="cart box_1">
					<a href="./cart">
						<h3>
							<div class="total">
								$<span class='simpleCart_total'> </span>(<span
									id="simpleCart_quantity" class="simpleCart_quantity"> </span>)
							</div>
						</h3>
					</a> <a href="javascript:;" class="simpleCart_empty">Empty Cart</a>
					<div class="clearfix"></div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="header-botom">
			<div class="content white">
				<nav class="navbar navbar-default nav-menu" role="navigation">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
					</div>
					<div class="clearfix"></div>

					<div class="collapse navbar-collapse collapse-pdng"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav nav-font">
							<li class="nav-item"><a href="./" class="nav-link">Home</a>
							</li>
							<li class="nav-item dropdown"><a href="product"
								class="dropdown-toggle">Product</a></li>
							<li class="nav-item "><a href="./cart">Checkout</a></li>
							<li class="nav-item "><a href="Home/Pay">Pay</a></li>
						</ul>
					</div>
				</nav>
			</div>
		</div>
	</div>
</div>
<c:if test="${not empty succMsg }">
	<script>
		$(document).ready(function() {
			toalt("${succMsg}", "success")
		})
	</script>
	<c:remove var="succMsg" scope="session" />
</c:if>