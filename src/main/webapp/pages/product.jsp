<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page isELIgnored="false"%>
<script>
	$(document).ready(function() {
		const x = [];
		$("input[name='checkbox']").click(function(ev) {
			var id = $(this).attr('id');
			if ($(this).is(':checked')) {
				var newLength = x.push(id);
			} else {
				var pos = x.indexOf(id);
				var removedItem = x.splice(pos, 1);
			}
			var str = x.toString();
			$.post("./product-category", {
				id_dm : str.trim(),
			}, function(data, status) {
				if (status == 'success') {
					$('.col-md-9').html(data);
				}
			});
		});
	});
</script>
<div class="head-bread">
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="./">Home</a></li>
			<li class="active">PRODUCTS</li>
		</ol>
	</div>
</div>
<div class="products-gallery">
	<div class="container">
		<div class="col-md-9 grid-gallery">
			<c:forEach items="${products}" var="product">
				<c:if test="${not empty product.khuyenMai}">
					<c:set value="${(1.0 - (product.khuyenMai / 100))*product.gia}"
						var="km" />
				</c:if>
				<c:if test="${empty product.khuyenMai}">
					<c:set value="${1*product.gia}" var="km" />
				</c:if>
				<div class="col-md-4 grid-stn simpleCart_shelfItem">
					<div class="ih-item square effect3 bottom_to_top">
						<div class="bottom-2-top">
							<div class="img">
								<img src="public/images/${product.anh }" alt="/"
									class="img-responsive gri-wid">
							</div>
							<div class="info">
								<div class="pull-left styl-hdn">
									<h3>${product.tensp}</h3>
								</div>
								<div class="pull-right styl-price">
									<p>
									<form action="" method="POST" accept-charset="utf-8">
										<button value="${product.masp }" type='button' name="cart"
											class="item_add btn btn-light" style="float: right;">
											<span class="glyphicon glyphicon-shopping-cart grid-cart"
												aria-hidden="true"></span>$<span class="item_price">
												<fmt:formatNumber type="number" maxFractionDigits="0"
													value="${km}" />
											</span>
										</button>
									</form>
									</p>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
					<div class="quick-view">
						<a href="product/${product.masp }">Quick view</a>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="col-md-3 grid-details">
			<div class="grid-addon">
				<section class="sky-form">
					<div class="product_right">
						<h4 class="m_2">
							<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>Categories
						</h4>
						<div class="row row1 scroll-pane">
							<div class="col col-4">
								<c:forEach items="${categories}" var="category">
									<label class="checkbox"><input type="checkbox"
										name="checkbox" id="${category.id }"><i></i>
										${category.name } </label>
								</c:forEach>
							</div>
						</div>
					</div>
				</section>
				<section class="sky-form">
					<h4>
						<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>DISCOUNTS
					</h4>
					<div class="row row1 scroll-pane">
						<div class="col col-4">
							<label class="checkbox"><input type="checkbox"
								name="checkbox"><i></i>Upto - 10% (20)</label>
						</div>
						<div class="col col-4">
							<label class="checkbox"><input type="checkbox"
								name="checkbox"><i></i>40% - 50% (5)</label> <label
								class="checkbox"><input type="checkbox" name="checkbox"><i></i>30%
								- 20% (7)</label> <label class="checkbox"><input type="checkbox"
								name="checkbox"><i></i>10% - 5% (2)</label> <label
								class="checkbox"><input type="checkbox" name="checkbox"><i></i>Other(50)</label>
						</div>
					</div>
				</section>
				<link rel="stylesheet" type="text/css"
					href="public/css/jquery-ui.css">
				<section class="sky-form">
					<h4>
						<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>Type
					</h4>
					<div class="row row1 scroll-pane">
						<div class="col col-4">
							<label class="checkbox"><input type="checkbox"
								name="checkbox"><i></i>Air Max (30)</label>
						</div>
						<div class="col col-4">
							<label class="checkbox"><input type="checkbox"
								name="checkbox"><i></i>Armagadon (30)</label> <label
								class="checkbox"><input type="checkbox" name="checkbox"><i></i>Helium
								(30)</label> <label class="checkbox"><input type="checkbox"
								name="checkbox"><i></i>Kyron (30)</label> <label
								class="checkbox"><input type="checkbox" name="checkbox"><i></i>Napolean
								(30)</label> <label class="checkbox"><input type="checkbox"
								name="checkbox"><i></i>Foot Rock (30)</label> <label
								class="checkbox"><input type="checkbox" name="checkbox"><i></i>Radiated
								(30)</label> <label class="checkbox"><input type="checkbox"
								name="checkbox"><i></i>Spiked (30)</label>
						</div>
					</div>
				</section>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>