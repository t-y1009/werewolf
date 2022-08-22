$(function(){
	if(!(localStorage.getItem("acces"))){
		localStorage.setItem("acces",1);
		time = 120; //リダイレクトまでの秒数
	$('#countdown').text(time);
		cnDown = setInterval(function(){
		time--;
		localStorage.setItem("time",time);
		if(time <= 0){ 
			localStorage.clear();
			clearInterval(cnDown);
			window.location.href = '/werewolf/Discussion?phase=end';
		}
		$('#countdown').text(time);
	},1000);
	}else{
		time =localStorage.getItem("time");
		$('#countdown').text(time);
			cnDown = setInterval(function(){
			time--;
			localStorage.setItem("time",time);
			if(time <= 0){ 
				localStorage.clear();
				clearInterval(cnDown);
				window.location.href = '/werewolf/Discussion?phase=end';
			}
			$('#countdown').text(time);
		},1000);
	}
});