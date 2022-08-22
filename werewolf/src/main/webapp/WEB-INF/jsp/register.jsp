<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/werewolf/css/village_noon.css">
<meta charset="UTF-8">
<title>会員登録</title>
</head>
<body>
	<div style="text-align: center">
		<form action = "/werewolf/Login" method="post">
			ユーザーID:<input type = "text" required pattern="^[a-zA-Z0-9]+$" maxlength="10" name="userId"  placeholder="英数字10文字以内" ><br>
			パスワード:<input type = "password" required pattern="^[a-zA-Z0-9]+$" maxlength="10" name = "pass" placeholder="英数字10文字以内"><br>
			名前:<input type = "text" required  maxlength="10" name ="name" placeholder="10文字以内"><br>
			<input type = "submit" value="登録">
		</form>
		<a href="/werewolf">TOPへ戻る</a>
	</div>
</body>
</html>