<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>resume-template-001</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet"
	href="../resource/templates/template-bresume-001/css/normalize.min.css">
<link rel="stylesheet"
	href="../resource/templates/template-bresume-001/css/main.css">
<script
	src="../resource/templates/template-bresume-001/js/vendor/jquery.min.js"></script>
<script
	src="../resource/templates/template-bresume-001/js/vendor/jquery.hashchange.min.js"></script>
<script
	src="../resource/templates/template-bresume-001/js/vendor/jquery.easytabs.min.js"></script>
<script src="../resource/templates/template-bresume-001/js/main.js"></script>

<!--[if lt IE 9]>
      <script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
      <script>window.html5 || document.write('<script src="../resource/templates/template-bresume-001/js/vendor/html5shiv.js"><\/script>')</script>
      <![endif]-->
</head>
<body class="bg-fixed bg-1">
	<div class="main-container">
		<div class="main wrapper clearfix">
			<!-- Header Start -->
			<header id="header">
				<div id="logo">
					<h2>${persionalInfo.name}</h2>
					<h4>${jobIntension.trade}/${jobIntension.profession}</h4>
				</div>
			</header>
			<!-- Header End -->
			<!-- Main Tab Container -->
			<div id="tab-container" class="tab-container">
				<!-- Tab List -->
				<ul class='etabs'>
					<li class='tab' id="tab-about"><a href="#about"><i
							class="icon-user"></i><span> 关于我</span></a></li>
					<li class='tab'><a href="#resume"><i
							class="icon-file-text"></i><span> 履历</span></a></li>
					<li class='tab'><a href="#portfolio"><i class="icon-heart"></i><span>
								个性</span></a></li>
					<li class='tab'><a href="#contact"><i
							class="icon-envelope"></i><span> 联系方式</span></a></li>
				</ul>
				<!-- End Tab List -->
				<div id="tab-data-wrap">
					<!-- About Tab Data -->
					<div id="about">
						<section class="clearfix">
							<div class="g2">
								<div class="photo">
									<img
										src="../resource/templates/template-bresume-001/images/wukong.jpg"
										alt="孙悟空">
								</div>
								<div class="info">
									<h2>${persionalInfo.name}</h2>
									<h4>${jobIntension.trade}/${jobIntension.profession}</h4>
									<p>${jobIntension.selfEvaluation}</p>
								</div>
							</div>
							<div class="g1">
								<div class="main-links sidebar">
									<ul>
										<li><a href="#resume">查看履历</a></li>
										<li><a href="#portfolio">我的个性</a></li>
										<li><a href="#contact">联系方式</a></li>
										<!--  <li>
                                        <a href="#features">Features</a>
                                    </li> -->
									</ul>
								</div>
							</div>
							<div class="break"></div>
							<div class="contact-info">
								<div class="g1">
									<div class="item-box clearfix">
										<i class="icon-envelope"></i>
										<div class="item-data">
											<h3>
												<a href="mailto:${persionalInfo.email}">${persionalInfo.email}</a>
											</h3>
											<p>Email Address</p>
										</div>
									</div>
								</div>
								<div class="g1">
									<div class="item-box clearfix">
										<i class="icon-weibo"></i>
										<div class="item-data">
											<h3>
												<a href="http://www.weibo.com/swk">${persionalInfo.name}</a>
											</h3>
											<p>微博</p>
										</div>
									</div>
								</div>
							</div>
						</section>
						<!-- content -->
					</div>
					<!-- End About Tab Data -->
					<!-- Resume Tab Data -->
					<div id="resume">
						<section class="clearfix">
							<div class="g2">
								<h3>Work Experience</h3>
								<ul class="no-list work">
									<c:forEach items="${workExperiences}" var="workExperience">
										<li>
											<h5>${workExperience.companyName}-
												${workExperience.position}</h5> <span class="label label-info">${workExperience.startDate}--${workExperience.endDate}</span>
											<p>${workExperience.desc}</p>
										</li>
									</c:forEach>
								</ul>
								<h3>教育经历</h3>
								<ul class="no-list work">

									<c:forEach items="${eduExperiences}" var="eduExperience">
										<li>
											<h5>${eduExperience.schoolName}-
												${eduExperience.majorName}</h5> <span class="label label-success">${eduExperience.startDate}
												- ${eduExperience.endDate}</span>
											<p>${eduExperience.desc}</p>
										</li>
									</c:forEach>



								</ul>
							</div>
							<div class="g1">
								<div class="sidebar">
									<h3>技能</h3>
									<h5>Software</h5>

									<c:forEach items="${skills}" var="skill">
										<div class="meter emerald">
											<span style="width: ${skill.level}%"><span>${skill.name}</span></span>
										</div>
									</c:forEach>

									<!-- <div class="meter emerald">
										<span style="width: 95%"><span>棍术</span></span>
									</div>
									<div class="meter carrot">
										<span style="width: 90%"><span>筋斗云</span></span>
									</div>
									<div class="meter wisteria">
										<span style="width: 70%"><span>72般变化</span></span>
									</div>
									<div class="meter sunflower">
										<span style="width: 40%"><span> 搬救兵</span></span>
									</div>
									<div class="meter midnight">
										<span style="width: 70%"><span>金刚不坏</span></span>
									</div>
									<div class="meter pomengrate">
										<span style="width: 60%"><span>火眼金睛</span></span>
									</div>
									<div class="meter asbestos">
										<span style="width: 65%"><span>分身术</span></span>
									</div>
									<div class="break"></div>
									<div class="meter emerald">
										<span style="width: 33.3%"><span>金毫毛</span></span>
									</div>
									<div class="meter carrot">
										<span style="width: 90%"><span>奇门异术</span></span>
									</div>
									<div class="meter wisteria">
										<span style="width: 60%"><span>三头六臂</span></span>
									</div>
									<div class="break"></div>
									<div class="meter emerald">
										<span style="width: 53.3%"><span>金箍棒漫天飞</span></span>
									</div>
									<div class="meter carrot">
										<span style="width: 80%"><span>灵魂出窍</span></span>
									</div> -->
								</div>
							</div>
						</section>
					</div>
					<!-- End Resume Tab Data -->
					<!-- Portfolio Tab Data -->
					<div id="portfolio">
						<section class="clearfix">
							<div class="g1">
								<div class="image">
									<img
										src="../resource/templates/template-bresume-001/images/1.jpg"
										width="310px;" height="260px;" alt="">
									<div class="image-overlay">
										<div class="image-link"></div>
									</div>
								</div>
							</div>
							<div class="g1">
								<div class="image">
									<img
										src="../resource/templates/template-bresume-001/images/2.jpg"
										width="310px;" height="260px;" alt="">
									<div class="image-overlay">
										<div class="image-link"></div>
									</div>
								</div>
							</div>
							<div class="g1">
								<div class="image">
									<img
										src="../resource/templates/template-bresume-001/images/3.jpg"
										width="310px;" height="260px;" alt="">
									<div class="image-overlay">
										<div class="image-link"></div>
									</div>
								</div>
							</div>
							<div class="break"></div>
							<div class="g1">
								<div class="image">
									<img
										src="../resource/templates/template-bresume-001/images/4.jpg"
										width="310px;" height="260px;" alt="">
									<div class="image-overlay">
										<div class="image-link"></div>
									</div>
								</div>
							</div>
							<div class="g1">
								<div class="image">
									<img
										src="../resource/templates/template-bresume-001/images/5.jpg"
										width="310px;" height="260px;" alt="">
									<div class="image-overlay">
										<div class="image-link"></div>
									</div>
								</div>
							</div>
							<div class="g1">
								<div class="image">
									<img
										src="../resource/templates/template-bresume-001/images/6.jpg"
										width="310px;" height="260px;" alt="">
									<div class="image-overlay">
										<div class="image-link"></div>
									</div>
								</div>
							</div>

						</section>
					</div>
					<!-- End Portfolio Data -->
					<!-- Contact Tab Data -->
					<div id="contact">
						<section class="clearfix">
							<div class="g1">
								<div class="sny-icon-box">
									<div class="sny-icon">
										<i class="icon-globe"></i>
									</div>
									<div class="sny-icon-content">
										<h4>地址</h4>
										<p>${persionalInfo.address}花果山，水帘洞</p>
									</div>
								</div>
							</div>
							<div class="g1">
								<div class="sny-icon-box">
									<div class="sny-icon">
										<i class="icon-phone"></i>
									</div>
									<div class="sny-icon-content">
										<h4>电话</h4>
										<p>${persionalInfo.cellPhone }</p>
									</div>
								</div>
							</div>
							<div class="g1">
								<div class="sny-icon-box">
									<div class="sny-icon">
										<i class="icon-user"></i>
									</div>
									<div class="sny-icon-content">
										<h4>主页</h4>
										<p>${persionalInfo.siteUrl }</p>
									</div>
								</div>
							</div>
							<div class="break"></div>

						</section>
					</div>
					<!-- End Contact Data -->
				</div>
			</div>
			<!-- End Tab Container -->
			<footer>
				<p>
					<a href="http://www.bresume.com" title="查看更多">bresume.com</a>
				</p>
			</footer>
		</div>
		<!-- #main -->
	</div>
	<!-- #main-container -->



</body>
</html>
