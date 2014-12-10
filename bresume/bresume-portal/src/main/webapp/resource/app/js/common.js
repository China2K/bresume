

function replaceAll(data, substr, replacement) {
	while (data.indexOf(substr) >= 0) {
		data = data.replace(substr, replacement);
	}
	return data;
}

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

function randomString(len) {
	len = len || 32;
	var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
	var maxPos = $chars.length;
	var pwd = '';
	for (i = 0; i < len; i++) {
		pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
	}
	return pwd;
}

/*for inside url forward*/
var subUrl = function(url) {
	//主要内容部分，要来局部刷新不同模块的主要部分
	var _mainContent = $("div.item-form-container");
	$.ajax({
		type : "GET",
		url : url,
		async : false,
		cache : false,
		dataType : "html",
		error : function(request) {
			if (request.status == "747") {
				window.location.href = request.getResponseHeader('loginUrl');
			} else {
				alert("跳转页面出现错误");
			}
		},
		success : function(data) {
			_mainContent.html(data);

			//内容较长的话，高度重新设置，让浏览器重新绘制。否则会出现滚动条
			_mainContent.css("height", "100%");
		}
	});
};

//主要内容部分，要来局部刷新不同模块的主要部分
var _mainContent = $("div.item-form-container");
//菜单 点击后， 局部刷新 主要内容部分 _mainContent

$(".resume-items li.ajaxPage").click(function(event) {
	var a = $(this);
	//取消 默认的点击事件 否则跳转页面
	event.preventDefault();

	var url = a.attr("data-href");
	var resumeId = $("#resumeForm #resumeId").val();
	if (resumeId == null || resumeId == '') {
		alert("请先完成前两步！");
		goStep(1);
	}
	url = url + "&id=" + resumeId;
	$.ajax({
		type : "GET",
		url : url,
		async : false,
		cache : false,
		dataType : "html",
		error : function(request) {
			if (request.status == "747") {
				window.location.href = request.getResponseHeader('loginUrl');
			} else {
				alert("跳转页面出现错误");
			}
		},
		success : function(data) {
			//会寻找返回的HTML中是否 存在fullpage:true的注释，如果存在则整张页面改变（body里面内容替换为响应返回的html）
			//不想整张页面变化，则不需要别的操作
			if (data.indexOf("<!--fullpage:true-->") >= 0) {
				//整个页面改变
				$("body").html(data);
			} else {
				//局部页面填充，改变 class=mainCont的内部
				_mainContent.html(data);
				//改变点击样式 TODO

			}
		}
	});
});

/*for inside url forward*/
/*replate*/
var replateItem2Container = function(url, _mainContent, key) {
	//主要内容部分，要来局部刷新不同模块的主要部分
	$.ajax({
		type : "GET",
		url : url,
		async : false,
		cache : false,
		dataType : "html",
		error : function(request) {
			if (request.status == "747") {
				window.location.href = request.getResponseHeader('loginUrl');
			} else {
				alert("跳转页面出现错误");
			}
		},
		success : function(data) {
			_mainContent.html(data);

			//内容较长的话，高度重新设置，让浏览器重新绘制。否则会出现滚动条
			_mainContent.css("height", "100%");
		}
	});
};

/*add -- 将连接的jsp内容以html格式添加到指定容器中*/

var addItem2Container = function(url, _mainContent, key) {
	//主要内容部分，要来局部刷新不同模块的主要部分
	$.ajax({
		type : "GET",
		url : url,
		async : false,
		cache : false,
		dataType : "html",
		error : function(request) {
			if (request.status == "747") {
				window.location.href = request.getResponseHeader('loginUrl');
			} else {
				alert("跳转页面出现错误");
			}
		},
		success : function(data) {

			data = replaceAll(data, "$$$$", key);
			_mainContent.append(data);
			//内容较长的话，高度重新设置，让浏览器重新绘制。否则会出现滚动条
			_mainContent.css("height", "100%");
		}
	});
};
