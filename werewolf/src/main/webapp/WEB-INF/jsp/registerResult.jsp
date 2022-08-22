<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% boolean result = (boolean)request.getAttribute("result"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/werewolf/css/village_noon.css">
<meta charset="UTF-8">
<title>会員登録</title>
</head>
<body>
	<div style="text-align: center">
		<% if(result){ %>
			<p>登録できました</p>
			<a href="/werewolf">トップへ戻る</a>
		<%}else{ %>
			<p>登録失敗しました</p>
		    <a href="/werewolf/Login">再登録画面に戻る</a>
		<% } %>
	</div>
</body>
</html>