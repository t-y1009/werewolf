<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/werewolf/css/village_noon.css">
<meta charset="UTF-8">
<title>人狼ゲーム</title>
</head>
<body>
	<div style="text-align: center">
		<h2>ルール説明</h2>
		<h3>●人狼ゲームとは</h3>
			人狼ゲームとは、村人陣営と人狼陣営に分かれて、自分の陣営の勝利を目指すゲームです。<br>
			村人陣営は議論を重ね、誰が人狼なのかを推理していきます。<br>
			人狼陣営は正体を隠しながら、村人陣営の議論を引っ掻き回します。<br>
		
			<p>
			【村人陣営】<br>
			人狼で村人陣営が勝利する条件は、人狼を処刑することです。<br>
			役職ごとのやり方を覚え、村人陣営で相談し合いながら、人狼を探していきましょう。<br>
			</p>
		
			<p>
			【人狼陣営】<br>
			人狼陣営の勝利条件は、村人を人狼だと勘違いさせ、処刑されることです。<br>
			村人になりすまし生き延びましょう。<br>
			</p>
		
		<h3>●人狼ゲームの役職と役割</h3>
			人狼ゲームで最初にするのは役職決めです。このゲームは5人制です。<br>
			役職と役割は下記の表のとおりです。<br>
		
		<table border="1" style="margin: 0 auto;">
			<tr>
				<th>役職</th>
				<th>人数</th>
				<th>役割</th>
			</tr>
		
			<tr>
				<td>村人</td>
				<td>2</td>
				<th><p style="text-align:left">
				村人は、特別な能力を持たない普通の人間です。<br>
				ほかの村人と議論を重ね、誰が人狼なのかを推理していくのが、村人のやり方です。<br>
				自分が人狼でないことを証明しながら、誰が人狼なのかを考えなければならず、説得力と考察力が必要です。<br>
				</th>
			</tr>
			
			<tr>
				<td>占い師</td>
				<td>1</td>
				<th><p style="text-align:left">
				占い師は、1人だけプレイヤーを選び、そのプレイヤーが人狼かどうかを占えます。<br>
				やり方はいたってシンプルで、ゲームの進行を見ながら、人狼と思われるプレイヤーに目星を付け、占いをするだけです。<br>
				人狼側に騙されることなく、1日1回の占い能力を活かすのが重要です。<br>
				</th>
			</tr>
			
			<tr>
				<td>騎士</td>
				<td>1</td>
				<th><p style="text-align:left">
				人狼は、村人陣営を襲い、リタイアさせる役職です。<br>
				人狼のやり方はシンプルで、正体を隠しながら、処刑されないようにします。<br>
				しかし、占い師に占われると正体がバレてしまうことや、<br>
				騎士に守られた相手はリタイアさせられないなど、考えることは多い役職でもあります。<br>
				</th>
			</tr>
			<tr>
			
				<td>人狼</td>
				<td>1</td>
				<th><p style="text-align:left">
				人狼は、村人陣営を襲い、リタイアさせる役職です。<br>
				人狼のやり方はシンプルで、正体を隠しながら、処刑されないようにします。<br>
				しかし、占い師に占われると正体がバレてしまうことや、<br>
				騎士に守られた相手はリタイアさせられないなど、考えることは多い役職でもあります。<br>
				</th>
			</tr>
		</table>
		
		<h3>●人狼ゲームの流れ</h3>
			
		<table border="1" style="margin: 0 auto;">
			<tr>
				<td>1日目の昼</td>
				<td>話し合い時間</td>
				<th><p style="text-align:left">
				誰が人狼か話し合う時間です。<br>
				人狼は正体がバレないように行動しましょう。<br>
				初日は投票はありませんが、時間制限があるので、<br>
				テンポよく議論を進めていく必要があります。<br>
				</th>
			</tr>
			
			<tr>
				<td>1日目の夜</td>
				<td>処刑時間</td>
				<th><p style="text-align:left">
				騎士は守るプレイヤーの選定、人狼は処刑するプレイヤーの選定をする時間です。<br>
				</th>
			</tr>
			
			<tr>
				<td>2日目の昼</td>
				<td>話し合い時間</td>
				<th><p style="text-align:left">
				誰が人狼かを話し合います。<br>
				人狼は正体がバレないように行動しましょう。<br>
				</th>
			</tr>
			<tr>
			
				<td>2日目の夜</td>
				<td>投票時間</td>
				<th><p style="text-align:left">
				処刑するプレイヤーを投票する時間です。<br>
				全プレイヤーがそれぞれ処刑したいプレイヤーを指名します。<br>
				同数だった場合は人狼の処刑失敗となり、人狼が勝利となります。<br>
				</th>
			</tr>
		</table>
		<a href="/werewolf/StandByController">OK</a>
	</div>
</body>
</html>