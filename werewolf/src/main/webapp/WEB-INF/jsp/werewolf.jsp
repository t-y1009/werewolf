<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Player,model.Account,java.util.List" %>
<%	List<Player> playerList = (List<Player>)request. getAttribute("playerList");
	int countMember = 0;
	application.setAttribute("countMember",countMember);
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/werewolf/css/werewolf.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="text-align: center">
		<form action="/werewolf/RoleController?roleName=werewolf"method="post" >
			<p>誰を攻撃しますか？(自分以外選んでください)</p>
			<% for(Player player : playerList){ %>
			<%= player.getName() %>:<input type="radio" name="account_id" value="<%= player.getAccount_id()%>">
			<% } %>
			<input type="submit" value="更新">
		</form>
	</div>
</body>
</html>