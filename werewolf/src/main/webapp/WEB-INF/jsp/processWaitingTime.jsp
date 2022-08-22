<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.Player,model.Account,java.util.List" %>
<%	List<Player> playerList = (List<Player>)request.getAttribute("playerList");
	String yourRole = (String)session.getAttribute("yourRole");
	Account loginUser = (Account)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/werewolf/css/village_noon.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript"src="/werewolf/js/processWaitCoundDown.js"></script>
<meta charset="UTF-8">
<title>人狼ゲーム</title>
</head>
<body>
	<div style="text-align: center">
		<p><span id="countdown"style="font-size:25px;font-weight:bold;color:red;"></span> 秒後に自動でページを更新します。</p>
		<p>あなたの役職は<span style="color:red;font-weight:bold;"><%= yourRole %></span></p>
		<h1>話し合って、騙し疑い、勝利しよう</h1>
		<p>ゲーム開始までお待ちください(大体20秒くらいかな)</p>
		<a href="/werewolf/FortuneTellerPhaseController">更新</a>
	</div>
</body>
</html>