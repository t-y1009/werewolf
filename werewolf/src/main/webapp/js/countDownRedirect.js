$(function(){
	time = 30; //リダイレクトまでの秒数
	$('#countdown').text(time);
	cnDown = setInterval(function(){
		time--;
		if(time <= 0){ 
			clearInterval(cnDown);
			window.location.href = '/werewolf/Discussion';
		}
		$('#countdown').text(time);
	},1000);
});