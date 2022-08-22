<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Player,model.Account" %>    
<%	Player deadPlayer = (Player)request.getAttribute("deadPlayer"); 
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
		<p>夜が明けました</p>
		<p>昨晩、人狼の襲撃による被害は</p>
		<% if(deadPlayer == null){ %>
			<p>ありませんでした</p>
			<a href="/werewolf/Discussion">話合いを行う</a>
		<%}else { %>
			<p style="color:red;font-weight:bold;"><%= deadPlayer.getName() %>さんでした</p>
			<% if(deadPlayer.getAccount_id() == loginUser.getId()) {%>
				<p>あなたは人狼に殺されてしまったので次の話合いに参加できません</p>
				<p>みんなの結果を見守りましょう</p>
				<a href="/werewolf/Discussion?player=dead">見守る</a>
			<% }else{ %>
				<a href="/werewolf/Discussion">話合いを行う</a>
			<% } %>
		<% } %>
	</div>
</body>
</html>