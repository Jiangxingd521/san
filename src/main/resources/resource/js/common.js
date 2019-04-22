$(document).ready(function () {
	var _winwidth = $(window).width();
	var _winheight = $(window).height();
	_winwidth > 640 ? _winwidth = 640 : " " ;
	$("html").css("fontSize",_winwidth/160*7+"px");
	$('.xfts_topts').css('width',_winwidth);
});
$(window).on('resize',function(){
	var _winwidth = $(window).width();
	_winwidth > 640 ? _winwidth = 640 : " " ;
	$("html").css("fontSize",_winwidth/160*7+"px");
});