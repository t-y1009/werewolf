<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Player,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/werewolf/css/werewolf.css">
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript"src="/werewolf/js/werewolfWaitRoomCountDown.js"></script>
<title>人狼ゲーム</title>
</head>
<body>
	<div style="text-align: center"style="font-size:25px;font-weight:bold;color:red;">
		<p><span id="countdown"></span>秒毎に自動でページを更新します</p>
		<p>騎士が行動するまでお待ちください</p>
		<a href="/werewolf/RoleController?roleName=werewolf">更新</a>
	</div>
</body>
</html>