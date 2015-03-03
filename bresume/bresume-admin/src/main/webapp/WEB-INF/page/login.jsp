<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html lang="en">
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>Login Page - bresume</title>

<meta name="description" content="User login page" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="/resource/site/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="/resource/site/font-awesome/4.1.0/css/font-awesome.min.css" />

<!-- text fonts -->
<link rel="stylesheet"
	href="http://fonts.useso.com/css?family=Open+Sans:400,300" />

<!-- ace styles -->
<link rel="stylesheet" href="/resource/site/css/ace.min.css" />

<!-- [if lte IE 9]>
<link rel="stylesheet" href="/resource/site/css/ace-part2.min.css" />
<![endif] -->

<link rel="stylesheet" href="/resource/site/css/ace-rtl.min.css" />

<!-- [if lte IE 9]>
<link rel="stylesheet" href="/resource/site/css/ace-ie.min.css" />
<![endif] -->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!-- [if lt IE 9]>
<script src="/resource/site/js/html5shiv.js"></script>
<script src="/resource/site/js/respond.min.js"></script>
<![endif] -->

</head>

<body class="login-layout">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<i class="ace-icon fa fa-leaf green"></i> <span class="red">BResume</span>
								<span class="white" id="id-text2">管理系统</span>
							</h1>
							<h4 class="blue" id="id-company-text">&copy; bresume.com</h4>
						</div>

						<div class="space-6"></div>

						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="ace-icon fa fa-coffee green"></i> 请登录
										</h4>

										<div class="space-6"></div>

										<form action="/sys/user/login.do" onsubmit="return false;"
											id="login_form">
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														name="loginName" type="text" class="form-control"
														placeholder="用户名" /> <i class="ace-icon fa fa-user"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														name="password" type="password" class="form-control"
														placeholder="密码" /> <i class="ace-icon fa fa-lock"></i>
												</span>
												</label>

												<div class="space"></div>

												<div class="clearfix">
													<label class="inline"> <input type="checkbox"
														class="ace" /> <span class="lbl"> 记住我</span>
													</label>

													<button type="button" id="login-sub-btn"
														class="width-35 pull-right btn btn-sm btn-primary">
														<i class="ace-icon fa fa-key"></i> <span
															class="bigger-110">登录</span>
													</button>
												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>

									</div>
									<!-- /.widget-main -->

									<!-- 	<div class="toolbar clearfix">
										<div>
											<a href="#" data-target="#forgot-box"
												class="forgot-password-link"> <i
												class="ace-icon fa fa-arrow-left"></i> I forgot my password
											</a>
										</div>
									</div> -->
								</div>
								<!-- /.widget-body -->
							</div>
							<!-- /.login-box -->

							<%-- <div id="forgot-box" class="forgot-box widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header red lighter bigger">
											<i class="ace-icon fa fa-key"></i> 重置密码
										</h4>

										<div class="space-6"></div>
										<p>请输入您的邮箱</p>

										<form>
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="email" class="form-control" placeholder="Email" />
														<i class="ace-icon fa fa-envelope"></i>
												</span>
												</label>

												<div class="clearfix">
													<button type="button"
														class="width-35 pull-right btn btn-sm btn-danger">
														<i class="ace-icon fa fa-lightbulb-o"></i> <span
															class="bigger-110">重置密码</span>
													</button>
												</div>
											</fieldset>
										</form>
									</div>
									<!-- /.widget-main -->

									<div class="toolbar center">
										<a href="#" data-target="#login-box"
											class="back-to-login-link"> 返回登录 <i
											class="ace-icon fa fa-arrow-right"></i>
										</a>
									</div>
								</div>
								<!-- /.widget-body -->
							</div> --%>
							<!-- /.forgot-box -->

						</div>
						<!-- /.position-relative -->
					</div>
				</div>
			</div>
		</div>
		<!-- /.main-content -->
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

	<script type="text/javascript"
		src="<c:url value ='/resource/site/js/jquery.form.js'/>"></script>


	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='/resource/site/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		jQuery(function($) {
			$(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			});
		});

		$("#login-sub-btn").click(function() {
			$("#login_form").ajaxSubmit(function(data) {
				if (data.success) {
					location.href = "<c:url value='/index.do'/>";
				} else {
					alert(data.message);
				}
			});
		});
	</script>
</body>
</html>
