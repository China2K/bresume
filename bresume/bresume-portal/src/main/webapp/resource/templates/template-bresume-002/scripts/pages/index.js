define(['jquery', 
		'joshua/ui/Sprite', 
		'joshua/ui/Picture', 
		'joshua/interact/smooth_mousewheel',
		'greensock/TweenMax',
		'custom/timeliner',
		'domReady!'], 
	function($, Sprite, Picture, SmoothMouseWheel){

	var $body = $('body');
	var $window = $(window);
	var nav_li = $('.colors li');
	var home_wrapper = $('#home-wrapper');
	var content_wrapper = $('#content-wrapper');
	var contents = content_wrapper.find('.content');
	var backhome = $('.backhome');

	var lastContent, currentContent;
	var canClickNav = true, canClickBack = true;

	// preload pictures
	(function(){
		$('.js-picture').each(function(i, item){
			var p = new Picture(item);

			$(p).on('done', function(){})
			.on('error', function(){});
		});

		Picture.load();
	})();






	// click nav
	nav_li.on('click', function(){
		if(!canClickNav || !canClickBack) return;
		canClickNav = false;

		var $this = $(this);
		if($this.hasClass('active')) return;

		var className = $this.attr('class');

		$body.addClass('show-content');
		nav_li.filter('.active').removeClass('active');
		$(this).addClass('active');

		SmoothMouseWheel.lock();
		lastContent = contents.filter('.active').removeClass('active done');
		currentContent = contents.filter('.' + className).addClass('active').css('display', 'block');
		setTimeout(function(){
			currentContent.addClass('done');
		}, 300);
		TweenMax.fromTo(currentContent, 0.8, {
			autoAlpha: 0
		}, {
			autoAlpha: 1,
			onComplete: function(){
				SmoothMouseWheel.unlock();
				lastContent.css('display', 'none');
				canClickNav = true;
			}
		});
	});




	// back home
	backhome.on('click', function(){
		if(!canClickNav || !canClickBack) return;
		canClickBack = false;

		$body.removeClass('show-content');
		nav_li.removeClass('active');
		contents.removeClass('active');
		setTimeout(function(){
			contents.attr('style','');
			canClickBack = true;
		}, 800);
	});




	// smooth mouse wheel
	SmoothMouseWheel.enable({
		spring: .4,
        duration: 900,
        maxDetail: 40
	});




	// timeliner
	$.timeliner({
		timelineContainer: '#timelineContainer'
	});
});