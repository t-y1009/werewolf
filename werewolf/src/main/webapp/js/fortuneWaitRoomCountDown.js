$(function(){
	time = 10; //リダイレクトまでの秒数
	$('#countdown').text(time);
	cnDown = setInterval(function(){
		time--;
		if(time <= 0){ 
			clearInterval(cnDown);
			window.location.href = '/werewolf/RoleController?action=fortune';
		}
		$('#countdown').text(time);
	},1000);
});