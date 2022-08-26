<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/werewolf/css/village_night.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript"src="/werewolf/js/nightCountDown.js"></script>
<meta charset="UTF-8">
<title>人狼ゲーム</title>
</head>
<body>
	<div style="text-align: center">
		<p><span id="countdown"style="font-size:25px;font-weight:bold;color:red;"></span> 秒後に自動的にページを更新します。</p>
		<p>現在人狼及び騎士が行動中です</p>
		<p>行動完了までお待ちください</p>
		<a href="/werewolf/WerewolfController">更新</a>
	</div>
</body>
</html>