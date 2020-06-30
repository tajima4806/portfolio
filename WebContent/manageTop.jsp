<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
		<title>Login</title>
	<link rel="stylesheet" type="text/css" href="manageTopStyle.css">
</head>

<body>

	<div class="form-wrapper">

		<h2>ログインページ</h2>

		<div class="form-item">

		<div class="message">${message}</div>

			<form action="LoginServlet" method="post">

				<div class="form-item">
					<label for="username"></label>
						<p>USER NAME</p>
					<input type="text" name="name"></input>
				</div>

				<div class="form-item">
					<label for="password"></label>
						<p>PASSWORD</p>
					<input type="password" name="pass"></input>
				</div>

				<div class="button-panel">
					<button>Login</button>
				</div>

			</form>
		</div>
	</div>
</body>
</html>