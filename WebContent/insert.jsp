<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>insert</title>
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

		<h2>各項目を入力して下さい</h2>

		<form action="InsertServlet" method="post">
			<div class="md-form">
				<table class="table table-bordered">
					<tr>
						<th>商品名</th><td><input type="text" name="name"></td>
					</tr>
					<tr>
						<th>カテゴリ</th>
						<td>
							<select name="category">
								<option value="general">雑貨</option>
								<option value="electric">家電</option>
								<option value="book">書籍</option>
								<option value="food">食品</option>
								<option value="fashion">ファッション</option>
								<option value="other">未分類</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>価格</th><td><input type="text" name="price"></td>
					</tr>
					<tr>
						<th>在庫</th><td><input type="text" name="stock"></td>
					</tr>
				</table>
			</div>

			<div class="modal fade" id="modInsert" data-backdrop="static" data-keyboard="false" tabindex="-1">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h4 class="modal-title" id="modTitle">新規商品を登録します</h4>
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
		</form>

			<button class="btn blue-gradient btn-block" data-toggle="modal" data-target="#modInsert">
				登録</button>
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