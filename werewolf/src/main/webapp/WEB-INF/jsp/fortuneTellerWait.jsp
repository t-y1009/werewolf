<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Player,java.util.List" %>
<% List<Player> playerList = (List<Player>)request.getAttribute("playerList"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/werewolf/css/fortuneteller.css">
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript"src="/werewolf/js/fortuneWaitRoomCountDown.js"></script>
<title>人狼ゲーム</title>
</head>
<body>
	<div style="text-align: center">
		<p><span id="countdown" style="font-size:25px;font-weight:bold;color:red;"></span>秒毎に自動でページを更新します</p>
		<p style="color:red;font-weight:bold;">あたなの役職は占い師です</p>
		<p>他の方が準備できるまでお待ちください</p>
		<p>現在<%= playerList.size() %>人</p>
		<a href="/werewolf/RoleController?action=fortune">更新</a>
	</div>
</body>
</html>