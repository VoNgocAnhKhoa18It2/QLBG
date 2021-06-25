<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%= request.getRequestURL().substring(0, request.getRequestURL().indexOf("index.jsp")) %>">
<title>N-Air a E-commerce category Flat Bootstrap Responsive
	Website Template | Products :: w3layouts</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="N-Air Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	
	 addEventListener("load", function() {setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<meta charset utf="8">

<link href='//fonts.googleapis.com/css?family=Fredoka+One'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="public/css/js-snackbar.css">
<link href="public/css/form.css" rel="stylesheet" type="text/css"
	media="all" />

<link href="public/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="public/css/css.css" rel="stylesheet" type="text/css" />

<script src="public/js/simpleCart.js"></script>

<script src="public/js/jquery-2.1.4.min.js"></script>

<script src="public/js/bootstrap.min.js"></script>

<script src="public/js/imagezoom.js"></script>
<script defer src="public/js/jquery.flexslider.js"></script>
<link rel="stylesheet" href="public/css/flexslider.css" type="text/css"
	media="screen" />

<script>
	$(window).load(function() {
		$('.flexslider').flexslider({
			animation : "slide",
			controlNav : "thumbnails"
		});
	});
</script>
</head>
<body>
	<div class="main-body">
		<section class="content-main">
			<header>
				<%@include file="pages/menu.jsp"%>
			</header>

			<main>
				<c:import url="pages/${pages}.jsp"></c:import>
			</main>

			<footer>
				<%@include file="pages/footer.jsp"%>
			</footer>
		</section>
	</div>
	<script src="public/js/js-snackbar.js"></script>
</body>
</html>