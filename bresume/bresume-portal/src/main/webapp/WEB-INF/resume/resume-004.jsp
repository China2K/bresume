<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<title>${persionalInfo.name}-简历</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href='http://fonts.useso.com/css?family=Open+Sans:400,600'
	rel='stylesheet' type='text/css'>
<link href="http://www.bresume.com/resource/templates/template-bresume-004/css/style.css"
	rel="stylesheet" type="text/css" media="all" />
<link
	href="http://www.bresume.com/resource/templates/template-bresume-004/css/header_style1.css"
	rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css"
	href="http://www.bresume.com/resource/templates/template-bresume-004/css/slider.css" />
<!-- Owl Carousel Assets -->
<link
	href="http://www.bresume.com/resource/templates/template-bresume-004/css/owl.carousel.css"
	rel="stylesheet">

<style type="text/css">
.group_2_img1 img{
width: 600px !important;
}
</style>
</head>
<body>
	<div class="content" id="home" style="background: url('${staticUrlPrefix}${resume.coverUrl}') no-repeat center;background-size: cover;">
		<div class="wrap">
			<div class="header_style1">
				<!-- start h_logo -->
				<div class="logo">
					<h1>
						<a href="index.html">简历</a>
					</h1>
				</div>
				<!-- start h_menu -->
				<div class="h_menu">
					<ul class="flexy-menu thick orange">
						<li><a href="#home" class="scroll">主页</a></li>
						<li><a href="#Portfolio" class="scroll">作品展示</a></li>
						<li><a href="#skills" class="scroll">技能</a></li>
						<li><a class="scroll" href="#Experience">工作经验</a></li>
						<li><a href="#contact" class="scroll">联系方式</a></li>
					</ul>
				</div>
				<!-- end h_menu -->
				<div class="clear"></div>
			</div>
			<!---------menu-script------------->

			<!-- start manu -->

			<div class="slider">
				<div id="da-slider" class="da-slider">
					<div class="da-slide">
						<h2>${persionalInfo.name}</h2>
						<p>${jobIntension.trade}-${jobIntension.profession}</p>
						<!-- <a href="#" class="da-link"><span> READ NOW</span></a> -->
					</div>
					<!-- <nav class="da-arrows">
						<span class="da-arrows-prev"></span> <span class="da-arrows-next"></span>
					</nav> -->
				</div>
				<div class="down-arrow">
					<a href="#group_1" class="scroll"><span> </span></a>
				</div>
			</div>


		</div>
	</div>
	<!----start-team--------->
	<div class="group_1" id="group_1">
		<div class="group_1-items">
			<div class="wrap">
				<div id="owl-demo" class="owl-carousel">
					<c:forEach items="${eduExperiences}" var="eduExperience">
						<div class="item">
							<div class="carousel">
								<div class="group_1_img">
									<h3 class="ui">${eduExperience.degree}</h3>
									<div class="clear"></div>
								</div>
								<div class="group_1_text">
									<h3>${eduExperience.schoolName}-${eduExperience.majorName}</h3>
									<p>${eduExperience.desc}</p>
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</c:forEach>

				</div>
			</div>
		</div>
	</div>
	<div class="group_2" id="Portfolio">
		<h3 class="heading">项目展示</h3>
		<div class="group_2_items">
			<div class="wrap">
				<div id="owl-demo1" class="owl-carousel">
					<c:forEach items="${projectExperiences}" var="project">
						<div class="item">
							<div class="carousel">
								<div class="group_2_img1">
									<img
										src="${staticUrlPrefix}${project.coverUrl}"
										alt="" width="600!important" height="450">
								</div>
								<div class="group_2_text2">
									<div class="desc">
										<h3>作品名称</h3>
										<h4>${project.projectName}</h4>
									</div>
									<div class="desc">
										<h3>作品介绍</h3>
										<h4>${project.projectDesc}</h4>
									</div>
									<div class="desc">
										<h3>个人总结</h3>
										<h4>${project.respDesc}</h4>
									</div>
									<div class="button_2">
										<a href="#">了解项目</a>
									</div>
								</div>
								<div class="clear"></div>
							</div>
						</div>

					</c:forEach>
				</div>
			</div>
		</div>
	</div>

	<!-- Owl Carousel Assets -->

	<!-- //Owl Carousel Assets -->
	<div class="skills" id="skills">
		<section class="text-light">
			<div class="wrap">
				<div class="row-content1">
					<h3 class="heading">技能</h3>
					<p class="para">我是一个技能强大的手艺人！</p>
					<c:forEach items="${skills}" var="skill">
						<div class="chart" data-percent="${skill.score}"
							data-bar-color="#35AFBA" data-animate="4000">
							<div class="chart-content">
								<div class="percent"></div>
								<div class="chart-title">${skill.name}</div>
							</div>
							<!-- chart-content -->
						</div>
					</c:forEach>

					<!-- chart -->
					<div class="clear"></div>
				</div>
			</div>
		</section>

	</div>
	<div class="exper" id="Experience">
		<div class="wrap">
			<h3 class="heading">经验</h3>
			<section class="row section">
				<div class="row-content2">
					<div class="timeline-label column six">
						<h4>工作经验</h4>
						<!-- <p>俺工作很努力地。。。</p>
						<div class="brows_button_4">
							<a href="http://www.bresume.com">bresume.com</a>
						</div> -->
					</div>

					<!-- timeline-label -->
					<div class="timeline column six last">
						<c:forEach items="${workExperiences}" var="workExperience">
							<div class="experience">
								<span class="circle"></span>
								<!-- <div class="experience-img">
									<img
										src="http://www.bresume.com/resource/templates/template-bresume-004/images/5.jpg"
										alt="">
								</div> -->
								<div class="experience-info clear-after">
									<h5>${workExperience.companyName}-
										${workExperience.position}</h5>
									<div class="role">(${workExperience.startDate}--${workExperience.endDate})</div>
									<p>${workExperience.desc}</p>
								</div>
								<!-- experience-info -->
							</div>
						</c:forEach>
					</div>
					<!-- timeline -->
					<div class="clear"></div>
				</div>
			</section>
		</div>
	</div>
	<div class="group_3">
		<div class="group_3-items" id="contact">
			<div class="wrap">
				<div id="" class="">
					<div class="item">
						<div class="">
							<div class="group_3_img" style="width: 100%">
								<img
									src="${staticUrlPrefix}${resume.coverUrl}"
									alt="" width="300px" height="200px">

								<div class="group_1_text group_3_text">
									<h3>联系方式</h3>
									<p>
										手机号码：${persionalInfo.cellPhone }<br /> 电子邮箱：<a href="mailto:${persionalInfo.email}">${persionalInfo.email}</a><br/>
										个人主页：<a href="mailto:${persionalInfo.siteUrl}">${persionalInfo.siteUrl}</a>
									</p>
								</div>
							</div>
							<div class="group_1_text group_3_text">
								<h3>自我评价</h3>
								<p>${jobIntension.selfEvaluation}</p>
							</div>

							<div class="clear"></div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>

	<div class="footer">
		<!-- row -->
		<div class="copy">
			<p>© 2014 bresume.com</p>
		</div>


		<script
			src="http://www.bresume.com/resource/templates/template-bresume-004/js/jquery.min.js"></script>
		<script type="text/javascript"
			src="http://www.bresume.com/resource/templates/template-bresume-004/js/flexy-menu.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$(".flexy-menu").flexymenu({
					speed : 400,
					type : "horizontal",
					align : "right"
				});
			});
		</script>

		<script type="text/javascript"
			src="http://www.bresume.com/resource/templates/template-bresume-004/js/modernizr.custom.28468.js"></script>
		<script type="text/javascript"
			src="http://www.bresume.com/resource/templates/template-bresume-004/js/jquery.cslider.js"></script>
		<script type="text/javascript">
			$(function() {

				$('#da-slider').cslider({
					autoplay : true,
					bgincrement : 450
				});

			});
		</script>
		<!-- Prettify -->
		<script
			src="http://www.bresume.com/resource/templates/template-bresume-004/js/owl.carousel.js"></script>
		<script>
			$(document).ready(function() {

				$("#owl-demo").owlCarousel({
					items : 1,
					lazyLoad : true,
					autoPlay : true,
					navigation : true,
					navigationText : [ "", "" ],
					rewindNav : true,
					scrollPerPage : true,
					pagination : true,
					paginationNumbers : false,
				});

			});
			$(document).ready(function() {

				$("#owl-demo1").owlCarousel({
					items : 1,
					lazyLoad : true,
					autoPlay : true,
					navigation : true,
					navigationText : [ "", "" ],
					rewindNav : true,
					scrollPerPage : true,
					pagination : false,
					paginationNumbers : false,
				});

			});
			$(document).ready(function() {

				$("#owl-demo2").owlCarousel({
					items : 1,
					lazyLoad : true,
					autoPlay : true,
					navigation : false,
					navigationText : [ "", "" ],
					rewindNav : false,
					scrollPerPage : true,
					pagination : false,
					paginationNumbers : false,
				});

			});
		</script>
		<script src="http://www.bresume.com/resource/templates/template-bresume-004/js/plugins.js"></script>
		<script src="http://www.bresume.com/resource/templates/template-bresume-004/js/script.js"></script>
		<!-- scroll_top_btn -->
		<script type="text/javascript"
			src="http://www.bresume.com/resource/templates/template-bresume-004/js/move-top.js"></script>
		<script type="text/javascript"
			src="http://www.bresume.com/resource/templates/template-bresume-004/js/easing.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {

				var defaults = {
					containerID : 'toTop', // fading element id
					containerHoverID : 'toTopHover', // fading element hover id
					scrollSpeed : 1200,
					easingType : 'linear'
				};

				$().UItoTop({
					easingType : 'easeOutQuart'
				});

			});
		</script>
		<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event) {
					event.preventDefault();
					$('html,body').animate({
						scrollTop : $(this.hash).offset().top
					}, 1200);
				});
			});
		</script>
		<a href="#" id="toTop" style="display: block;"><span
			id="toTopHover" style="opacity: 1;"></span></a>
	</div>

</body>
</html>