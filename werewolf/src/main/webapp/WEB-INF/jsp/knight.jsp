<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Player,java.util.List" %>
<% List<Player> playerList = (List<Player>)request. getAttribute("playerList"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/werewolf/css/knight.css">
<meta charset="UTF-8">
<title>人狼ゲーム</title>
</head>
<body>
	<div style="text-align: center">
		<form action="/werewolf/RoleController?roleName=knight"method="post" >
			<p>誰を守りますか？</p>
			<% for(Player player : playerList){ %>
			<%= player.getName() %>:<input type="radio" name="account_id" value="<%= player.getAccount_id()%>">
			<% } %>
			<input type="submit" value="更新">
		</form>
	</div>
</body>
</html>