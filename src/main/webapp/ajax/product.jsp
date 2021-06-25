<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page isELIgnored="false"%>
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
							<button value="${product.masp }" type='submit' name="cart"
								class="item_add btn btn-light" style="float: right;">
								<span class="glyphicon glyphicon-shopping-cart grid-cart"
									aria-hidden="true"></span>$<span class="item_price"> <fmt:formatNumber
										type="number" maxFractionDigits="0" value="${km}" />
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