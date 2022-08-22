<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import = "model.Mutter, model.Account, java.util.List" %>
<% 
Account loginUser = (Account) session.getAttribute("loginUser");
List<Mutter> mutterList = (List<Mutter>)request.getAttribute("mutterList");
String errorMsg = (String) request.getAttribute("erroMsg");
int count = (int)application.getAttribute("count");
%>
<html>
<head>
<link rel="stylesheet" href="/werewolf/css/village_noon.css">
<meta charset="UTF-8">
<title>人狼ゲーム</title>
</head>
<body>
	<div style="text-align: center">
		<h1>ゲーム開始まで自由に話し合おう</h1>
		<p>	<%= loginUser.getName() %>さん、ログイン中</p>
		<p>現在のログイン数 <span style="font-weight:bold;"><%= count %></span></p>
		<a href="/werewolf/SubControll">更新</a>
		<form action="/werewolf/SubControll" method="post">
			<input type="text" name="text" >
			<input type="submit" value="つぶやく">
		</form>
		<% if(errorMsg != null) {%>
			<p><%= errorMsg %></p>
		<% } %>
		<% for(Mutter mutter : mutterList){ %>
			<p>ユーザー名:<span style="font-weight: bold;"><%= mutter.getUserName() %></span><br><%=mutter.getText() %></p>
		<% } %>
		<% if(count == 5){%>
			<p>参加人数が5人になりました。</p>
			<a href="/werewolf/SubControll?start=true">ゲームを開始する</a>
		<% } %>
	</div>
</body>
</html>