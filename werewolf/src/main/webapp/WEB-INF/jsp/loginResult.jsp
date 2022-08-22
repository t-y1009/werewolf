<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import= "model.Account" %>
<%Account loginUser = (Account)session.getAttribute("loginUser");%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/werewolf/css/village_noon.css">
<meta charset="UTF-8">
<title>ログイン結果</title>
</head>
<body>
	<div style="text-align: center">
		<%if(loginUser !=null){ %>
			<p>ログイン完了</p>
			<a href="/werewolf/SubControll">次へ</a>
		<%}else{%>
			<p>ログイン失敗</p>
			<a href="/werewolf">トップへ戻る</a>
		<%}%>
	</div>
</body>
</html>