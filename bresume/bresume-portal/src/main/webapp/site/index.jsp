<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta property="qc:admins" content="2500654377622535556375" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>bresume - 简历创建</title>


<style type="text/css">
</style>


</head>
<%@include file="common/common.jsp"%>
<body id="page-top" class="index">

	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header page-scroll">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">B Resume</span> <span class="icon-bar"></span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand page-scroll" href="#page-top">B Resume</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="hidden"><a href="#page-top"></a></li>
					<li><a class="page-scroll" href="#templates">简历模板</a></li>
					<li><a class="page-scroll" href="#portfolio">热门简历</a></li>
					<li><a class="page-scroll" href="#about">关于我们</a></li>
					<li><a class="page-scroll" href="#contact">提供建议</a></li>

				</ul>

				<ul class="nav navbar-nav navbar-right" id="user_links">
					<c:choose>
						<c:when
							test="${sessionScope.loginUser!=null && sessionScope.loginUser.icon!=null}">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">${sessionScope.loginUser.nickName}</a>
								<ul class="dropdown-menu" role="menu"
									style="background: none repeat scroll 0% 0% #3D3F40;">
									<li><a href="<c:url value='resume/mine'/>">管理我的简历</a></li>
									<li><a href="#">账户设置</a></li>
									<li class="divider"></li>
									<li><a href="<c:url value='user/logout'/>">安全退出</a></li>
								</ul></li>

							

						</c:when>
						<c:otherwise>
							<li><a id="loginBtn" href="javascript:void(0);">登陆</a></li>
							<li><a id="signupBtn" href="javascript:void(0);">注册</a></li>
						</c:otherwise>
					</c:choose>

				</ul>
			</div>


			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<!-- Header -->
	<header>
		<div class="container">
			<div class="intro-text">
				<div class="intro-lead-in">简历创建从未如此简单！</div>
				<div class="intro-heading">Build Your Resume</div>
				<a href="resume/buildResume" class="page-scroll btn btn-xl">创建简历</a>
				<a href="#services" class="page-scroll btn btn-xl">了解如何使用</a>
			</div>
		</div>
	</header>

	<!-- templates Section -->
	<section id="templates">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">简历模板</h2>
					<h3 class="section-subheading text-muted">查看你所喜欢的简历模板</h3>
				</div>
			</div>
			<div class="row">
				<c:forEach items="${hotTemplates}" var="template">
					<div class="col-md-3">
						<div class="ih-item circle effect2 left_to_right">
							<div class="acover"  onclick="location.href='${template.siteUrl}'">
								<div class="img">
									<img src="${template.coverUrl}" alt="img">
								</div>
								
								<div class="info">
									<h3>${template.name}</h3>
									<p>learn more</p>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>

	<!-- Portfolio Grid Section -->
	<section id="portfolio" class="bg-light-gray">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">热门简历</h2>
					<h3 class="section-subheading text-muted">查看小伙伴们的简历寻找些灵感吧！</h3>
				</div>
			</div>
			<div class="row">

				<c:forEach items="${hotResumes}" var="resume">
					<div class="col-md-4 col-sm-6 portfolio-item">
						<a href="<c:url value='/resume/${resume.name}'/>" class="portfolio-link"
							data-toggle="modal">
							<div class="portfolio-hover">
								<div class="portfolio-hover-content">
									<i class="fa fa-1x"> I am more than what you can see! </i>
								</div>
							</div> <img src="${resume.coverUrl}" class="img-responsive" alt="">
						</a>
						<div class="portfolio-caption">
							<h4>${resume.name}</h4>
							<p class="text-muted">${resume.name}</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>

	<!-- About Section -->
	<section id="about">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">关于我们</h2>
					<h3 class="section-subheading text-muted">这是我们的故事</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<ul class="timeline">
						<li>
							<div class="timeline-image">
								<img class="img-circle img-responsive"
									src="resource/site/img/about/1.jpg" alt="">
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h4>2014.11</h4>
									<h4 class="subheading">我们的开始</h4>
								</div>
								<div class="timeline-body">
									<p class="text-muted">一个突发奇想让我们开始了一个不一样的旅程，bresume想做一个不一样的个人简历网站！</p>
								</div>
							</div>
						</li>
						<li class="timeline-inverted">
							<div class="timeline-image">
								<img class="img-circle img-responsive"
									src="resource/site/img/about/3.jpg" alt="">
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h4>2014.11-</h4>
									<h4 class="subheading">编码中</h4>
								</div>
								<div class="timeline-body">
									<p class="text-muted">这是一个程序员的故事...</p>
								</div>
							</div>
						</li>
						<li>
							<div class="timeline-image">
								<img class="img-circle img-responsive"
									src="resource/site/img/about/2.jpg" alt="">
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h4>2015.1-1</h4>
									<h4 class="subheading">我们来了</h4>
								</div>
								<div class="timeline-body">
									<p class="text-muted">测试版发布</p>
								</div>
							</div>
						</li>
						<li class="timeline-inverted">
							<div class="timeline-image">
								<img class="img-circle img-responsive"
									src="resource/site/img/about/4.jpg" alt="">
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h4>2015.1-2</h4>
									<h4 class="subheading">第二个用户</h4>
								</div>
								<div class="timeline-body">
									<p class="text-muted">没错我是第一个</p>
								</div>
							</div>
						</li>
						<li class="timeline-inverted">
							<div class="timeline-image">
								<h4>
									我们的 <br>故事 <br>在继续!
								</h4>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</section>


	<section id="contact">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">提供建议</h2>
					<h3 class="section-subheading text-muted">您的建议对我们至关重要！</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<form name="sentMessage" id="contactForm" onsubmit="return false;"
						action="contact">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="姓名 *"
										name="name" id="name" required
										data-validation-required-message="请填写您的姓名.">
								</div>
								<div class="form-group">
									<input type="email" class="form-control" placeholder="邮箱 *"
										name="email" id="email" required
										data-validation-required-message="请填写邮箱地址.">
								</div>
								<div class="form-group">
									<input type="tel" class="form-control" placeholder="手机"
										id="phone" name="cellPhone" required
										data-validation-required-message="请填写手机号.">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<textarea class="form-control" placeholder="建议 *" id="message"
										name="message" required
										data-validation-required-message="请填写您的建议."></textarea>

								</div>
							</div>
							<div class="clearfix"></div>
							<div class="col-lg-12 text-center">
								<div id="success"></div>
								<button type="button" id="submit_contact" class="btn btn-xl">发送建议</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

	<%@include file="common/footer.jsp"%>

	<div id="backtotop">
		<div class="bttbg"></div>
	</div>


	<!-- jQuery -->
	<script src="resource/site/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="resource/site/js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script src="resource/site/js/jquery.easing.min.js"></script>
	<script src="resource/site/js/classie.js"></script>
	<script src="resource/site/js/cbpAnimatedHeader.js"></script>

	<!-- Contact Form JavaScript -->
	<script src="resource/site/js/jqBootstrapValidation.js"></script>
	<script src="resource/site/js/contact_me.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="resource/site/js/agency.js"></script>
	<script src="resource/app/js/common.js"></script>
	<script src="<c:url value ='/resource/site/js/jquery.form.js'/>"></script>
		<!-- jQuery-Validation-Engine -->
	<link rel="stylesheet"
		href="<c:url value ='/resource/plugin/jQuery-Validation-Engine/css/validationEngine.jquery.css'/>">
	<script
		src="<c:url value ='/resource/plugin/jQuery-Validation-Engine/js/jquery.validationEngine-zh_CN.js'/>"></script>
	<script
		src="<c:url value ='/resource/plugin/jQuery-Validation-Engine/js/jquery.validationEngine.min.js'/>"></script>
    
   
	<script type="text/javascript">
	$("#submit_contact").click(function(){
		$("#contactForm").ajaxSubmit(
				function(data) {
					alert(data.message);
					$('html, body').animate({
						scrollTop : 0
					}, 'slow');
				}
			);
	});

	</script>



</body>

</html>
