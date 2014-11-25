$("#loginBtn").click(function() {
	$("#login-modal").css("display", "block");
});

$(".modal-footer button").click(function() {
	$("#login-modal").css("display", "none");
});

$(window).scroll(function() {

	if ($(window).scrollTop() > $(window).height()) {
		$("#backtotop").addClass("showme");

	} else {
		$("#backtotop").removeClass("showme");
	}
});
$("#backtotop").click(function() {
	$('html, body').animate({
		scrollTop : 0
	}, 'slow');
});