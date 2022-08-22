<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Player,model.Account,java.util.List" %>
<%	List<Player> playerList = (List<Player>)session.getAttribute("finalPlayerList");
	Account loginUser = (Account)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/werewolf/css/village_noon.css">
<meta charset="UTF-8">
<title>人狼ゲーム</title>
</head>
<body>
	<div style="text-align: center">
		<form action="/werewolf/VotePhaseController" method="post">
			<p>誰が人狼だと思いますか？</p><br>
			<p>投票してください</p>
			<% for(Player player : playerList){ %>
				<% if(player.getAccount_id() != loginUser.getId()){ %>
					<%= player.getName() %>:<input type="radio" name="id" value="<%= player.getId()%>">
				<% } %>
			<% } %>
			<input type="submit" value="投票する">
		</form>
	</div>
</body>
</html>