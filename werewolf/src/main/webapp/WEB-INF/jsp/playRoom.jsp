<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.Mutter,java.util.List" %>
    <% List<Mutter> mutterList = (List<Mutter>)request.getAttribute("mutterList");%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/werewolf/css/village_noon.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript"src="/werewolf/js/playRoomCount.js"></script>
<meta charset="UTF-8">
<title>人狼ゲーム</title>
</head>
<body>
	<div class="wrapper"style="text-align: center">
		<p>話し合いの時間終了まで残り<span id="countdown"style="font-size:30px;font-weight:bold;color:red;"></span>秒</p>
		<a href="/werewolf/Discussion">更新</a>
		<form action="/werewolf/Discussion" method="post">
			<input type="text" name="text">
			<input type="submit" value="書き込み">
		</form>
		<div class="mutterList">
			<% for(Mutter mutter: mutterList){ %>
			<p>プレイヤー名：<span style="font-weight:bold;"><%= mutter.getUserName() %></span><br>
			<%= mutter.getText() %></p>
			<% } %>
		</div>
	</div>
</body>
</html>