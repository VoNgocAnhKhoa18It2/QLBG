<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page isELIgnored="false"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('.close').on('click', function() {
			var index = $(this).attr('id');
			$.post("./add-cart", {
				id : index,
				gia : 0
			}, function(data, status) {
				if (status == 'success') {
					var arr = data.split(",");
					if (arr.length == 2) {
						$('#' + index).fadeOut('slow', function() {
							$('#' + index).remove();
						});
						$(".simpleCart_total").text(numberWithCommas(arr[1]));
						$("#simpleCart_quantity").text(arr[0]);
						toalt("Delete Cart Success", "success")
						$("#total1").text(numberWithCommas(arr[1]));
						$(".last_price span").text(numberWithCommas(arr[1]));
						$(".cart-items h1").text("Shopping Bag ("+arr[0]+")")				
					} else {
						toalt("Delete Cart Fail", "error")
					}
				}
			});
		});
		$('input[name=qty]').change(function() {
			var qty = $(this).val();
			if (qty > 10) {
				toalt("The number of products is not more than 10 ", "warning")
				return
			}
			if (qty == "") {
				toalt("Please enter product quantity", "warning")
				return
			}
			var id = $(this).attr('id');
			var sl = $("#simpleCart_quantity").text();
			$.post("./edit-cart", {
				qty : qty,
				id : id
			}, function(data, status) {
				if (status == 'success') {
					var arr = data.split(",");
					if (arr.length == 3) {
						$("#" + id+" .total-product").text("Price each : $"+numberWithCommas(arr[2]))
						if(arr[2] == 0) {
							$('#' + id).fadeOut('slow', function() {
								$('#' + id).remove();
							});
						}
						$(".simpleCart_total").text(numberWithCommas(arr[1]));
						$("#simpleCart_quantity").text(arr[0]);
						if (sl == arr[0]) {
							toalt("Edit Cart Fail", "error")
						} else {
							toalt("Edit Cart Success", "success")	
						}
						$("#total1").text(numberWithCommas(arr[1]));
						$(".last_price span").text(numberWithCommas(arr[1]));
						$(".cart-items h1").text("Shopping Bag ("+arr[0]+")")	
					} else {
						toalt("Edit Cart Fail", "error")
					}
				}
			});
		});
	});
</script>
<div class="head-bread">
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="">Home</a></li>
			<li><a href="/product">Products</a></li>
			<li class="active">CART</li>
		</ol>
	</div>
</div>
<!-- check-out -->
<div class="check">
	<div class="container">
		<div class="col-md-3 cart-total">
			<a class="continue" href="">Continue to basket</a>
			<div class="price-details">
				<h3>Price Details</h3>
				<span>Total</span> <span id="total1" class="total1"></span> <span>Discount</span>
				<span class="total1">10%(Festival Offer)</span> <span>Delivery
					Charges</span> <span class="total1">Free</span>
				<div class="clearfix"></div>
			</div>
			<hr class="featurette-divider">
			<ul class="total_price">
				<li class="last_price">
					<h4>TOTAL</h4>
				</li>
				<li class="last_price" style="text-align: end;"><span></span></li>
				<div class="clearfix"></div>
			</ul>
			<div class="clearfix"></div>
			<a class="order" href="Home/Pay">Place Order</a>
		</div>
		<div class="col-md-9 cart-items">
			<h1></h1>
			<c:if test="${not empty cart}">
				<c:forEach items="${products}" var="product" varStatus="loop">
					<c:if test="${not empty product.khuyenMai}">
						<c:set value="${(1.0 - (product.khuyenMai / 100))*product.gia*cart[loop.index].sl}"
							var="km" />
					</c:if>
					<c:if test="${empty product.khuyenMai}">
						<c:set value="${product.gia*cart[loop.index].sl}" var="km" />
					</c:if>
					<div class="cart-header" id="${product.masp}">
						<div class="close" id="${product.masp}">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						</div>
						<div class="cart-sec simpleCart_shelfItem">
							<div class="cart-item cyc">
								<img src="public/images/${product.anh }" class="img-responsive"
									alt="" />
							</div>
							<div class="cart-item-info">
								<ul class="qty">
									<li><p>Size : 9 US</p></li>
									<li>
										<p>
											Qty : <input type="number" name="qty" id="${product.masp }"
												value="${cart[loop.index].sl}">
										</p>
									</li>
									<li>
										<p class="total-product">
											Price each : $
											<fmt:formatNumber type="number" maxFractionDigits="0"
												value="${km}" />
										</p>
									</li>
								</ul>
								<div class="delivery">
									<p>Service Charges : Rs.190.00</p>
									<span>Delivered in 2-3 bussiness days</span>
									<div class="clearfix"></div>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
