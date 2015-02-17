<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>bresume管理系统</title>
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="/resource/site/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="/resource/site/font-awesome/4.1.0/css/font-awesome.min.css" />

<!-- page specific plugin styles -->

<!-- text fonts -->
<link rel="stylesheet"
	href="http://fonts.useso.com/css?family=Open+Sans:400,300" />

<!-- ace styles -->
<link rel="stylesheet" href="/resource/site/css/ace.min.css"
	id="main-ace-style" />

<!--[if lte IE 9]>
			<link rel="stylesheet" href="/resource/site/css/ace-part2.min.css" />
		<![endif]-->
<link rel="stylesheet" href="/resource/site/css/ace-skins.min.css" />
<link rel="stylesheet" href="/resource/site/css/ace-rtl.min.css" />

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="/resource/site/css/ace-ie.min.css" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script src="/resource/site/js/ace-extra.min.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
		<script src="/resource/site/js/html5shiv.min.js"></script>
		<script src="/resource/site/js/respond.min.js"></script>
		<![endif]-->

</head>

<body class="no-skin">
	<div id="navbar" class="navbar navbar-default">
		<script type="text/javascript">
			/* try {
				ace.settings.check('navbar', 'fixed')
			} catch (e) {
			} */
		</script>

		<div class="navbar-container" id="navbar-container">
			<button type="button" class="navbar-toggle menu-toggler pull-left"
				id="menu-toggler">
				<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>

				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand"> <small> <i
						class="fa fa-leaf"></i> BResume Admin
				</small>
				</a>
			</div>

			<div class="navbar-buttons navbar-header pull-right"
				role="navigation">
				<ul class="nav ace-nav">
					<li class="grey"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <i
							class="ace-icon fa fa-tasks"></i> <span class="badge badge-grey">4</span>
					</a>

						<ul
							class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i class="ace-icon fa fa-check"></i>
								4 Tasks to complete</li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left">Software Update</span> <span
											class="pull-right">65%</span>
									</div>

									<div class="progress progress-mini">
										<div style="width: 65%" class="progress-bar"></div>
									</div>
							</a></li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left">Hardware Upgrade</span> <span
											class="pull-right">35%</span>
									</div>

									<div class="progress progress-mini">
										<div style="width: 35%"
											class="progress-bar progress-bar-danger"></div>
									</div>
							</a></li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left">Unit Testing</span> <span
											class="pull-right">15%</span>
									</div>

									<div class="progress progress-mini">
										<div style="width: 15%"
											class="progress-bar progress-bar-warning"></div>
									</div>
							</a></li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left">Bug Fixes</span> <span
											class="pull-right">90%</span>
									</div>

									<div class="progress progress-mini progress-striped active">
										<div style="width: 90%"
											class="progress-bar progress-bar-success"></div>
									</div>
							</a></li>

							<li class="dropdown-footer"><a href="#"> See tasks with
									details <i class="ace-icon fa fa-arrow-right"></i>
							</a></li>
						</ul></li>

					<li class="purple"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <i
							class="ace-icon fa fa-bell icon-animated-bell"></i> <span
							class="badge badge-important">8</span>
					</a>

						<ul
							class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i
								class="ace-icon fa fa-exclamation-triangle"></i> 8 Notifications
							</li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left"> <i
											class="btn btn-xs no-hover btn-pink fa fa-comment"></i> New
											Comments
										</span> <span class="pull-right badge badge-info">+12</span>
									</div>
							</a></li>

							<li><a href="#"> <i
									class="btn btn-xs btn-primary fa fa-user"></i> Bob just signed
									up as an editor ...
							</a></li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left"> <i
											class="btn btn-xs no-hover btn-success fa fa-shopping-cart"></i>
											New Orders
										</span> <span class="pull-right badge badge-success">+8</span>
									</div>
							</a></li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left"> <i
											class="btn btn-xs no-hover btn-info fa fa-twitter"></i>
											Followers
										</span> <span class="pull-right badge badge-info">+11</span>
									</div>
							</a></li>

							<li class="dropdown-footer"><a href="#"> See all
									notifications <i class="ace-icon fa fa-arrow-right"></i>
							</a></li>
						</ul></li>

					<li class="green"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <i
							class="ace-icon fa fa-envelope icon-animated-vertical"></i> <span
							class="badge badge-success">5</span>
					</a>

						<ul
							class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i
								class="ace-icon fa fa-envelope-o"></i> 5 Messages</li>

							<li class="dropdown-content">
								<ul class="dropdown-menu dropdown-navbar">
									<li><a href="#"> <img
											src="/resource/site/avatars/avatar.png" class="msg-photo"
											alt="Alex's Avatar" /> <span class="msg-body"> <span
												class="msg-title"> <span class="blue">Alex:</span>
													Ciao sociis natoque penatibus et auctor ...
											</span> <span class="msg-time"> <i
													class="ace-icon fa fa-clock-o"></i> <span>a moment
														ago</span>
											</span>
										</span>
									</a></li>

									<li><a href="#"> <img
											src="/resource/site/avatars/avatar3.png" class="msg-photo"
											alt="Susan's Avatar" /> <span class="msg-body"> <span
												class="msg-title"> <span class="blue">Susan:</span>
													Vestibulum id ligula porta felis euismod ...
											</span> <span class="msg-time"> <i
													class="ace-icon fa fa-clock-o"></i> <span>20 minutes
														ago</span>
											</span>
										</span>
									</a></li>

									<li><a href="#"> <img
											src="/resource/site/avatars/avatar4.png" class="msg-photo"
											alt="Bob's Avatar" /> <span class="msg-body"> <span
												class="msg-title"> <span class="blue">Bob:</span>
													Nullam quis risus eget urna mollis ornare ...
											</span> <span class="msg-time"> <i
													class="ace-icon fa fa-clock-o"></i> <span>3:15 pm</span>
											</span>
										</span>
									</a></li>

									<li><a href="#"> <img
											src="/resource/site/avatars/avatar2.png" class="msg-photo"
											alt="Kate's Avatar" /> <span class="msg-body"> <span
												class="msg-title"> <span class="blue">Kate:</span>
													Ciao sociis natoque eget urna mollis ornare ...
											</span> <span class="msg-time"> <i
													class="ace-icon fa fa-clock-o"></i> <span>1:33 pm</span>
											</span>
										</span>
									</a></li>

									<li><a href="#"> <img
											src="/resource/site/avatars/avatar5.png" class="msg-photo"
											alt="Fred's Avatar" /> <span class="msg-body"> <span
												class="msg-title"> <span class="blue">Fred:</span>
													Vestibulum id penatibus et auctor ...
											</span> <span class="msg-time"> <i
													class="ace-icon fa fa-clock-o"></i> <span>10:09 am</span>
											</span>
										</span>
									</a></li>
								</ul>
							</li>

							<li class="dropdown-footer"><a href="inbox.html"> See
									all messages <i class="ace-icon fa fa-arrow-right"></i>
							</a></li>
						</ul></li>

					<li class="light-blue"><a data-toggle="dropdown" href="#"
						class="dropdown-toggle"> <img class="nav-user-photo"
							src="/resource/site/avatars/user.jpg" alt="Jason's Photo" /> <span
							class="user-info"> <small>超级管理员</small>
						</span> <i class="ace-icon fa fa-caret-down"></i>
					</a>

						<ul
							class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li><a href="#"> <i class="ace-icon fa fa-cog"></i> 设置
							</a></li>

							<li class="divider"></li>

							<li><a href="/sys/user/logout.do"> <i
									class="ace-icon fa fa-power-off"></i> 退出
							</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		<!-- /.navbar-container -->
	</div>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			/* try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			} */
		</script>

		<div id="sidebar" class="sidebar                  responsive">
			<script type="text/javascript">
				/* try {
					ace.settings.check('sidebar', 'fixed')
				} catch (e) {
				} */
			</script>

			<div class="sidebar-shortcuts" id="sidebar-shortcuts">
				<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
					<button class="btn btn-success">
						<i class="ace-icon fa fa-signal"></i>
					</button>

					<button class="btn btn-info">
						<i class="ace-icon fa fa-pencil"></i>
					</button>

					<button class="btn btn-warning">
						<i class="ace-icon fa fa-users"></i>
					</button>

					<button class="btn btn-danger">
						<i class="ace-icon fa fa-cogs"></i>
					</button>
				</div>

				<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
					<span class="btn btn-success"></span> <span class="btn btn-info"></span>

					<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
				</div>
			</div>
			<!-- /.sidebar-shortcuts -->

			<ul class="nav nav-list index-nav-list">
				<li class=""><a href="javasrcipt:void(0);"
					data-href="/dashboard.do" class="ajaxPage"> <i
						class="menu-icon fa fa-tachometer"></i> <span class="menu-text">
							概览 </span>
				</a> <b class="arrow"></b></li>

				<li class="sub-nav-bar"><a href="javasrcipt:void(0);"
					data-href="/page/calendar.jsp" class="ajaxPage"> <i
						class="menu-icon fa fa-calendar"></i> <span class="menu-text">
							待办事项 <span class="badge badge-transparent tooltip-error"
							title="2 Important Events"> <i
								class="ace-icon fa fa-exclamation-triangle red bigger-130"></i>
						</span>
					</span>
				</a> <b class="arrow"></b></li>

				<li class="hsub"><a href="#" class="dropdown-toggle"> <i
						class="menu-icon fa fa-desktop"></i> <span class="menu-text">
							网站管理 </span> <b class="arrow fa fa-angle-down"></b>
				</a> <b class="arrow"></b>

					<ul class="submenu">
						<li class="sub-nav-bar"><a href="javasrcipt:void(0);"
							data-href="/resume/hot.do" class="ajaxPage"> <i
								class="menu-icon fa fa-caret-right"></i> 主页热门简历
						</a> <b class="arrow"></b></li>

						<li class="sub-nav-bar"><a href="javasrcipt:void(0);"
							data-href="/tem/hot.do" class="ajaxPage"> <i
								class="menu-icon fa fa-caret-right"></i> 主页热门模版
						</a> <b class="arrow"></b></li>
					</ul></li>

				<li class="hsub"><a href="#" class="dropdown-toggle"> <i
						class="menu-icon fa fa-list"></i> <span class="menu-text">
							用户管理 </span> <b class="arrow fa fa-angle-down"></b>
				</a> <b class="arrow"></b>

					<ul class="submenu">
						<li class="sub-nav-bar"><a href="javasrcipt:void(0);"
							data-href="/page/user/customer.jsp" class="ajaxPage"> <i
								class="menu-icon fa fa-caret-right"></i> 客户管理
						</a> <b class="arrow"></b></li>

						<li class="sub-nav-bar"><a href="javasrcipt:void(0);"
							data-href="/page/user/advice.jsp" class="ajaxPage"> <i
								class="menu-icon fa fa-caret-right"></i>建议管理
						</a> <b class="arrow"></b></li>
					</ul></li>


				<li class="hsub"><a href="#" class="dropdown-toggle"> <i
						class="menu-icon fa fa-list"></i> <span class="menu-text">
							简历管理 </span> <b class="arrow fa fa-angle-down"></b>
				</a> <b class="arrow"></b>

					<ul class="submenu">
						<li class="sub-nav-bar"><a href="javasrcipt:void(0);"
							data-href="/page/resume/resumes.jsp" class="ajaxPage"> <i
								class="menu-icon fa fa-caret-right"></i> 用户简历
						</a> <b class="arrow"></b></li>

						<li class="sub-nav-bar"><a href="javasrcipt:void(0);"
							data-href="/page/resume/item/items.jsp" class="ajaxPage"> <i
								class="menu-icon fa fa-caret-right"></i>模块管理
						</a> <b class="arrow"></b></li>
					</ul></li>

				<li class="sub-nav-bar"><a href="javasrcipt:void(0);"
					data-href="/page/template/templates.jsp" class="ajaxPage"> <i
						class="menu-icon fa fa-picture-o"></i> <span class="menu-text">
							模版管理 </span>
				</a> <b class="arrow"></b></li>

			</ul>
			<!-- /.nav-list -->

			<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
				<i class="ace-icon fa fa-angle-double-left"
					data-icon1="ace-icon fa fa-angle-double-left"
					data-icon2="ace-icon fa fa-angle-double-right"></i>
			</div>

			<script type="text/javascript">
				try {
					ace.settings.check('sidebar', 'collapsed')
				} catch (e) {
				}
			</script>
		</div>

		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					/* try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					} */
				</script>

				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">主页</a>
					</li>

				</ul>
				<!-- /.breadcrumb -->

				<div class="nav-search" id="nav-search">
					<form class="form-search">
						<span class="input-icon"> <input type="text"
							placeholder="Search ..." class="nav-search-input"
							id="nav-search-input" autocomplete="off" /> <i
							class="ace-icon fa fa-search nav-search-icon"></i>
						</span>
					</form>
				</div>
				<!-- /.nav-search -->
			</div>

			<div id="page-container"></div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->

		<div class="footer">
			<div class="footer-inner">
				<div class="footer-content">
					<span class="bigger-120"> <span class="blue bolder">bresume.com</span>
						&copy; 2014-2015
					</span>


				</div>
			</div>
		</div>

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script src="/resource/site/js/jquery/jquery-2.1.1.min.js"></script>
	<!-- <![endif]-->

	<!--[if IE]>
		<script src="/resource/site/js/jquery/jquery-1.11.1.min.js"></script>
		<![endif]-->

	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='/resource/site/js/jquery.min.js'>"
								+ "<"+"/script>");
	</script>
	<!-- <![endif]-->

	<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='/resource/site/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->

	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='/resource/site/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<script src="/resource/site/bootstrap/3.2.0/js/bootstrap.min.js"></script>

	<!-- page specific plugin scripts -->

	<!-- ace scripts -->
	<script src="/resource/site/js/ace-elements.min.js"></script>
	<script src="/resource/site/js/ace.min.js"></script>
	<script src="/resource/site/js/jquery.livequery.js"></script>
	<script src="/resource/common/common.js"></script>

	<!-- inline scripts related to this page -->


	<script type="text/javascript">
		// 主要内容部分，要来局部刷新不同模块的主要部分

		// 菜单 点击后， 局部刷新 主要内容部分 _mainContent
		$("a.ajaxPage").click(function(event) {
			$(".tooltip").css("display", "none");
			var _mainContent = $("div#page-container");
			var a = $(this);
			// 取消 默认的点击事件 否则跳转页面
			event.preventDefault();
			event.stopPropagation();
			var url = a.attr("data-href");
			if (url.endWith('.jsp')) {
				$.ajax({
					type : "post",
					url : "/jsptransit.do?url=" + url,
					async : false,
					cache : false,
					dataType : "html",
					error : function(request) {
						if (request.status == "747") {
							window.location.href = "/login.do";
						} else {
							alert("跳转页面出现错误");
						}
					},
					success : function(data) {
						// 会寻找返回的HTML中是否
						// 存在fullpage:true的注释，如果存在则整张页面改变（body里面内容替换为响应返回的html）
						// 不想整张页面变化，则不需要别的操作
						if (data.indexOf("<!--fullpage:true-->") >= 0) {
							// 整个页面改变
							$("body").html(data);
						} else {
							// 局部页面填充，改变 class=mainCont的内部
							_mainContent.html(data);
							// 改变面包屑
							// changeCrumb(generateCrumbArray(a));
						}
					}
				});
			} else {

				$.ajax({
					type : "POST",
					url : url,
					async : false,
					cache : false,
					dataType : "html",
					error : function(request) {
						if (request.status == "747") {
							window.location.href = "/login.do";
						} else {
							alert("跳转页面出现错误");
						}
					},
					success : function(data) {
						// 会寻找返回的HTML中是否
						// 存在fullpage:true的注释，如果存在则整张页面改变（body里面内容替换为响应返回的html）
						// 不想整张页面变化，则不需要别的操作
						if (data.indexOf("<!--fullpage:true-->") >= 0) {
							// 整个页面改变
							$("body").html(data);
						} else {
							// 局部页面填充，改变 class=mainCont的内部
							_mainContent.html(data);
							// 改变面包屑
							// changeCrumb(generateCrumbArray(a));
						}
					}
				});
			}

			$(".index-nav-list li").removeClass("active");
			//$(".index-nav-list li").removeClass("open");
			$(this).parent("li.sub-nav-bar").addClass("active");
			var uls = $(this).parent("li.sub-nav-bar").parent("ul.submenu")
			if (uls) {
				uls.parent("li.hsub").addClass("active");
			}
		});

		$(".hsub").click(function(event) {

			console.log(this);
			//if(event.target == this){   
			if ($(this).hasClass("open")) {
				$(this).removeClass("open");
				$(this).children("ul.submenu").css("display", "none");

			} else {
				$(this).addClass("open");
				$(this).children("ul.submenu").css("display", "block");
			}
			// }
			event.preventDefault();
			event.stopPropagation();
		});
	</script>
</body>
</html>