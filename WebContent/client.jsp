<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>EC-GUEST</title>
	<!-- Font Awesome -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
	<!-- Bootstrap core CSS -->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
	<!-- Material Design Bootstrap -->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.19.1/css/mdb.min.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="client.css">
</head>

<body class="dusty-grass-gradient">
		<nav class="navbar navbar-light " style="background-color:#6d844d">
	  		<a class="navbar-brand" href="http://localhost:8080/webExam2/ClientServlet">
	    	<img src="reg.png" width="auto" height="32"></a>

	  		<script>
				(function() {
				  window.addEventListener("load", function () {
				    $('[data-toggle="popover"]').popover();
				  });
				})();
			</script>

	  		<button type="button" class="btn btn-light"
	    		data-toggle="popover"
	    		data-placement="bottom"
	    		title="　↓　↓　↓　↓　↓　"
	    		data-content="&lt;a href='http://localhost:8080/webExam2/LogoutServlet'
	    					  class='btn btn-outline-secondary waves-effect btn-block'&gt;Logout&lt;/a&gt;"
	    		data-html="true">
	  		<i class="fas fa-user-circle"></i> ゲスト 様</button>
		</nav>

		<div class="container-fluid">

		<c:if test="${message != null}">
			<h3>${message}</h3>

			<a href="http://localhost:8080/webExam2/ClientServlet" class="continue">
				<span data-text="続けて購入する">続けて購入する</span>
			</a>
		</c:if>

		<c:if test="${list != null}">
			<table class="table table-striped table-responsive-lg text-nowrap">
				<tr>
					<th>商品ID</th><th>商品コード</th><th>商品名</th><th>カテゴリ</th><th>価格</th><th>在庫</th><th></th>
				</tr>

				<c:forEach var="item" items="${list}">
					<tr>
						<td>${item.id}</td><td>${item.code}</td><td>${item.name}</td><td>${item.category}</td><td>${item.price}</td><td>${item.stock}</td>
						<td>
							<form action="ClientServlet" method="get">
								<button name="btn" value="buy"
								class="sunny-morning-gradient">購入</button>
								<input type="hidden" name="code" value="${item.code}">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>

	<!-- JQuery -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!-- Bootstrap tooltips -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/js/bootstrap.min.js"></script>
	<!-- MDB core JavaScript -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.19.1/js/mdb.min.js"></script>
</body>
</html>