<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Player" %>
<% Player fortuneResultPlayer = (Player)request.getAttribute("fortuneResultPlayer"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/werewolf/css/fortuneteller.css">
<meta charset="UTF-8">
<title>人狼ゲーム</title>
</head>
<body>
	<div style="text-align: center">
		<p><%= fortuneResultPlayer.getName() %>は<span style="font-size:20px; color:red;font-weight:bold;"><%= fortuneResultPlayer.getRoleName() %></span>でした。</p>
		<a href="/werewolf/Discussion">確認</a>
	</div>
</body>
</html>