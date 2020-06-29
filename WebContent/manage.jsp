<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>Buddit Management</title>
	<!-- Font Awesome -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
	<!-- Google Fonts -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
	<!-- Bootstrap core CSS -->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
	<!-- Material Design Bootstrap -->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.19.1/css/mdb.min.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="manage.css">
</head>

<body>
	<nav class="navbar navbar-light mb-2" style="background-color:#6d844d;">
  		<a class="navbar-brand" href="http://localhost:8080/webExam2/ManageServlet">
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
  		<i class="fas fa-user-circle"></i> 管理者 様</button>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-3">

				<div class="list-group">
				    <form action="ManageServlet" method="get">
					   	<button name="init" class="list-group-item list-group-item-action list-group-item-primary">
					   		<i class="fas fa-home"></i> ホーム</button>
			        </form>
			    </div>

				<div class="accordion" id="productAccordion">
					<div class="card">

						<div class="card-header">
							<a class="text-dark d-block p-3 m-n3" data-toggle="collapse" href="#productCollapse">
							<i class="fas fa-cube"></i> 商品管理</a>
						</div>

						<div class="collapse show" id="productCollapse" data-parent="#productAccordion">
							<div class="card-body">

							<form action="ManageServlet" method="get">
								<button name="btn" value="search" class="btn btn-outline-default waves-effect btn-block">
									商品一覧</button>
								<input type="hidden" name="selectWay" value="all">
							</form>

							<form action="ManageServlet" method="get">
								<button name="btn" value="insert" class="btn btn-outline-default waves-effect btn-block">
									商品登録</button>
							</form>

							</div>
						</div>
					</div>
				</div>

				<div class="list-group">
					<form action="ManageServlet" method="get">
						<button name="btn" value="search" class="list-group-item list-group-item-action">
							<i class="fas fa-coins"></i> 売上管理</button>
						<input type="hidden" name="selectWay" value="sales">
					</form>
				</div>

				<div class="accordion" id="searchAccordion">
					<div class="card">

						<div class="card-header">
							<a class="text-dark d-block p-3 m-n3" data-toggle="collapse" href="#searchCollapse">
							<i class="fas fa-search-plus"></i> 検索条件</a>
						</div>

						<div class="collapse show" id="searchCollapse" data-parent="#searchAccordion">

							<div class="card-body">

								<form action="ManageServlet" method="get">
									<div class="form-group">
										<label for="Name">Search Name</label>

										<div class="form-row">

											<div class="col-10">
												<input type="text" name="name" class="form-control" id="Name" placeholder="テキスト入力欄">
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="Category">Search Category</label>

										<div class="form-row">

											<div class="col-10">
												<select name="category" class="form-control" id="Category">
													<option value="default" selected>選択...</option>
													<option value="general">雑貨</option>
													<option value="electric">家電</option>
													<option value="book">書籍</option>
													<option value="food">食品</option>
													<option value="fashion">ファッション</option>
													<option value="other">未分類</option>
												</select>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="Price">Search Price</label>

										<div class="form-row">

											<div class="col-10">
												<select name="price" class="form-control" id="Price">
													<option value="0" selected>選択...</option>
													<option value="1">0-1500円</option>
													<option value="2">1500-3000円</option>
													<option value="3">3000-5000円</option>
													<option value="4">5000-10000円</option>
													<option value="5">10000円以上</option>
												</select>
											</div>

											<div class="col-10 ">
												<button name="btn" value="search"
													 class="btn btn-info btn-block">検索</button>
												<input type="hidden" name="selectWay" value="search">
											</div>

										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-lg-9">

				<h4>${message}</h4>

				<c:if test="${list != null}">
					<table class="table table-striped table-responsive-md text-nowrap">
						<tr>
							<th>商品id</th><th>商品コード</th><th>商品名</th><th>カテゴリ</th><th>価格</th><th>在庫</th><th></th><th></th><th></th>
						</tr>

						<c:forEach var="item" items="${list}">
							<tr>
								<td>${item.id}</td><td>${item.code}</td><td>${item.name}</td><td>${item.category}</td><td>${item.price}</td><td>${item.stock}</td>
								<td>
									<form action="ManageServlet" method="get">
										<button name="btn" value="edit"
											class="light-green">変更</button>
										<input type="hidden" name="code" value="${item.code}">
									</form>
								</td>
								<td>
									<form action="ManageServlet" method="get">
										<button name="btn" value="stock">補充</button>
										<input type="hidden" name="code" value="${item.code}">
									</form>
								</td>
								<td>
									<form action="ManageServlet" method="get">
										<button name="btn" value="delete"
											class="rgba-red-strong">削除</button>
										<input type="hidden" name="code" value="${item.code}">
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>

				<c:if test="${list2 != null}">

					<h4>売上数を表示しています</h4>

					<table class="table table-striped table-responsive-md text-nowrap">
						<tr>
							<th>商品id</th><th>商品コード</th><th>商品名</th><th>カテゴリ</th><th>売上</th>
						</tr>

						<c:forEach var="item" items="${list2}">
							<tr>
								<td>${item.id}</td><td>${item.code}</td><td>${item.name}</td><td>${item.category}</td><td>${item.sales}</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
		</div>
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