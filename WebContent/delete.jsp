<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>delete</title>
	<!-- Font Awesome -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
	<!-- Bootstrap core CSS -->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
	<!-- Material Design Bootstrap -->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.19.1/css/mdb.min.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="confirm.css">
</head>

<body>
	<div class="container">

		<h2>以下の商品を削除します</h2>

		<div class="md-form">
			<table class="table table-bordered table-responsive-md text-nowrap">
				<thead class="thead-dark">
					<tr>
						<th>商品ID</th><th>商品コード</th><th>商品名</th><th>カテゴリ</th><th>価格</th><th>在庫</th>
					</tr>
				</thead>

				<tbody>
					<tr>
						<td>${item.id}</td><td>${item.code}</td><td>${item.name}</td><td>${item.category}</td><td>${item.price}</td><td>${item.stock}</td>
					</tr>
				</tbody>
			</table>
		</div>

		<form action="DeleteServlet" method="post">
			<div class="modal fade" id="modDelete" data-backdrop="static" data-keyboard="false" tabindex="-1">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h4 class="modal-title" id="modTitle">商品を削除します</h4>
				        <button class="close" data-dismiss="modal">
				          <span>&times;</span>
				        </button>
				      </div>

				      <div class="modal-footer">
				      	<button class="btn cloudy-knoxville-gradient" name="btn" value="no">
							キャンセル</button>
				        <button class="btn btn-primary" name="btn" value="yes">
				        	確認</button>
				      </div>
				    </div>
				  </div>
				</div>
			<input type="hidden" name="code" value="${item.code}">
		</form>

			<button class="btn blue-gradient btn-block" data-toggle="modal" data-target="#modDelete">
				削除</button>
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