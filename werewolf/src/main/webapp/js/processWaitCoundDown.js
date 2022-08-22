$(function(){
	time = 20; //リダイレクトまでの秒数
	$('#countdown').text(time);
	cnDown = setInterval(function(){
		time--;
		if(time <= 0){ 
			clearInterval(cnDown);
			window.location.href = '/werewolf/FortuneTellerPhaseController';
		}
		$('#countdown').text(time);
	},1000);
});