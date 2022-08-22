<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/werewolf/css/village_noon.css">
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<div style="text-align: center">
		<h2>ログイン画面</h2>
		<form action="/werewolf/Login" method="post">
			登録済みの方はユーザーIDとパスワードを入力してください。<br>
			ユーザーID：<input type="text" name="userId"><br>
			パスワード：<input type="password" name="pass"><br>
			<input type="submit" value="ログイン">
		</form>
		<form action="/werewolf/Login" method="get">
			<p>未登録の方は新規登録をしてください。<br>
			<input type="submit" value="新規登録">
			</p>
		</form>
	</div>
</body>
</html>