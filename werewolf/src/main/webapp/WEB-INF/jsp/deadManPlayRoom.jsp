<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Mutter,java.util.List" %>
<% List<Mutter> mutterList = (List<Mutter>)request.getAttribute("mutterList");%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/werewolf/css/village_night.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript"src="/werewolf/js/playRoomCount.js"></script>
<meta charset="UTF-8">
<title>人狼ゲーム</title>
</head>
	<body>
	<div style="text-align: center">
		<h2>
			あなたは人狼に殺されてしまった・・・
			結末を見守ろう・・・
		</h2>
		<% for(Mutter mutter: mutterList){ %>
			<p><%= mutter.getUserName() %> <%= mutter.getText() %></p>
		<% } %>
		<p>話し合いの時間終了まで残り<span id="countdown"></span>秒</p>
		<a href="/werewolf/Discussion?player=deadPlayer">更新</a>
	</div>
</body>
</html>