<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Player,java.util.List" %>
<% List<Player> playerList = (List<Player>)request.getAttribute("playerList");%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/werewolf/css/fortuneteller.css">
<meta charset="UTF-8">
<title>人狼ゲーム</title>
</head>
<body>
	<div style="text-align: center">
		<form action="/werewolf/FortuneTellerPhaseController"method="post">
			<% for(Player player : playerList){ %>
			<%= player.getName() %>:<input type="radio" name="roleNumber" value="<%= player.getRole()%>">
			<%} %><br>
			<input type="submit" value="確認">
		</form>
	</div>
</body>
</html>