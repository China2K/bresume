<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html>
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>${persionalInfo.name}-简历</title>
<meta name="description" content="">
<link rel="stylesheet"
	href="http://www.bresume.com/resource/templates/template-bresume-001/css/normalize.min.css">
<link rel="stylesheet"
	href="http://www.bresume.com/resource/templates/template-bresume-001/css/main.css">
<script
	src="http://www.bresume.com/resource/templates/template-bresume-001/js/vendor/jquery.min.js"></script>
<script
	src="http://www.bresume.com/resource/templates/template-bresume-001/js/vendor/jquery.hashchange.min.js"></script>
<script
	src="http://www.bresume.com/resource/templates/template-bresume-001/js/vendor/jquery.easytabs.min.js"></script>
<script src="http://www.bresume.com/resource/templates/template-bresume-001/js/main.js"></script>

<!--[if lt IE 9]>
      <script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
      <script>window.html5 || document.write('<script src="http://www.bresume.com/resource/templates/template-bresume-001/js/vendor/html5shiv.js"><\/script>')</script>
      <![endif]-->


<style type="text/css">
.info-base ul {
	list-style: none;
	float: left;
	margin-right: 35px;
}

.jobI .g1 {
	width: 23%;
}

body {
	font-family: Tahoma, Verdana, 微软雅黑, 新宋体;
}
</style>
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
					<li class='tab'><a href="#portfolio"><i class="icon-heart"></i><span>
								求职意向</span></a></li>
					<li class='tab'><a href="#resume"><i
							class="icon-file-text"></i><span> 履历</span></a></li>

					<li class='tab'><a href="#contact"><i
							class="icon-envelope"></i><span> 联系方式</span></a></li>
				</ul>
				<!-- End Tab List -->
				<div id="tab-data-wrap" style="min-height: 400px;">
					<!-- About Tab Data -->
					<div id="about">
						<section class="clearfix">
							<div class="g2">
								<div class="photo">
									<img src="${staticUrlPrefix}${resume.coverUrl}"
										alt="${persionalInfo.name}">
								</div>
								<div class="info">
									<h2>${persionalInfo.name}</h2>
									<h4>${jobIntension.trade}/${jobIntension.profession}</h4>
									<div class="info-base">
										<ul class="">
											<li>姓名：${persionalInfo.name}</li>
											<li>性别：${persionalInfo.sex}</li>
											<li>地址：${persionalInfo.address}</li>
											<li>出生日期：${persionalInfo.birthday}</li>
										</ul>
										<ul class="">
											<li>毕业院校：${eduExperience.schoolName}</li>
											<li>学历：${eduExperience.degree}</li>
											<li>专业：${eduExperience.majorName}</li>
										</ul>
									</div>


								</div>
							</div>
							<div class="g1">
								<div class="main-links sidebar">
									<ul>
										<li><a href="#resume">查看履历</a></li>
										<li><a href="#portfolio">求职意向</a></li>
										<li><a href="#contact">联系方式</a></li>
										<!--  <li>
                                        <a href="#features">Features</a>
                                    </li> -->
									</ul>
								</div>
							</div>
							<div class="g2 line">
								<p>${jobIntension.selfEvaluation}</p>
							</div>

							<div class="break"></div>
							<div class="contact-info">
								<div class="g1">
									<div class="item-box clearfix">
										<i class="icon-envelope"></i>
										<div class="item-data">

											<p>邮箱</p>
											<br />
											<h3>
												<a href="mailto:${persionalInfo.email}">${persionalInfo.email}</a>
											</h3>
										</div>
									</div>
								</div>
								<div class="g1">
									<div class="item-box clearfix">
										<i class="icon-weibo"></i>
										<div class="item-data">
											<p>微博</p>
											<br />
											<h3>
												<a href="http://www.weibo.com/swk">${persionalInfo.name}</a>
											</h3>
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
								<c:if test="${fn:length(workExperiences)>0}">
									<h3>工作经历</h3>
									<ul class="no-list work">
										<c:forEach items="${workExperiences}" var="workExperience">
											<li>
												<h5>${workExperience.companyName}-
													${workExperience.position}</h5> <span class="label label-info">${workExperience.startDate}--${workExperience.endDate}</span>
												<p>${workExperience.desc}</p>
											</li>
										</c:forEach>
									</ul>
								</c:if>

								<%-- 
								<c:if test="${fn:length(projectExperiences)>0}">
									<h3>项目经验</h3>
									<ul class="no-list work">
										<c:forEach items="${projectExperiences}" var="project">
											<li>
												<h5>${project.projectName}</h5> <span
												class="label label-info">${project.startDate}--${project.endDate}</span>
												<p>
													<more>${project.projectDesc}<br />
													<br />
													<sm> <i class="icon-tag"></i> ${project.respDesc}</sm></more>
												</p>
											</li>
										</c:forEach>
									</ul>
								</c:if> --%>



							</div>
							<div class="g1">
								<c:if test="${fn:length(skills)>0}">
									<div class="sidebar">
										<h3>技能</h3>
										<c:forEach items="${skills}" var="skill">
											<div class="meter skill-item">
												<span style="width: ${skill.score}%"><span>${skill.name}</span></span>
											</div>
										</c:forEach>
									</div>
								</c:if>
							</div>

						</section>



						<c:if test="${fn:length(projectExperiences)>0}">
							<section class="clearfix">
								<div>
									<h3>项目经验</h3>
									<ul class="no-list work">
										<c:forEach items="${projectExperiences}" var="project">
											<li class="line" style="display: table; width: 100%;">
												<div
													style="width: 66%; min-height: 300px; display: block; float: left;">
													<h5>${project.projectName}</h5>
													<span class="label label-info">${project.startDate}--${project.endDate}</span>
													<p>
														<more>${project.projectDesc}<br />
														<br />
														<sm> <i class="icon-tag"></i> ${project.respDesc}</sm></more>
													</p>
												</div>
												<div
													style="width: 30%; height: 300px; position: relative; float: left; margin-left: 4%; cursor: pointer;"
													onclick="window.open('${project.siteUrl}')">
													<img class="img-responsive"
														src="${staticUrlPrefix}${project.coverUrl}" alt=""
														width="300" height="260" />
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
							</section>
						</c:if>
					</div>
					<!-- End Resume Tab Data -->
					<!-- Portfolio Tab Data -->
					<div id="portfolio">
						<!-- <section class="clearfix">
							<div class="g1">
								<div class="image">
									<img
										src="http://www.bresume.com/resource/templates/template-bresume-001/images/1.jpg"
										width="310px;" height="260px;" alt="">
									<div class="image-overlay">
										<div class="image-link"></div>
									</div>
								</div>
							</div>
							<div class="g1">
								<div class="image">
									<img
										src="http://www.bresume.com/resource/templates/template-bresume-001/images/2.jpg"
										width="310px;" height="260px;" alt="">
									<div class="image-overlay">
										<div class="image-link"></div>
									</div>
								</div>
							</div>
							<div class="g1">
								<div class="image">
									<img
										src="http://www.bresume.com/resource/templates/template-bresume-001/images/3.jpg"
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
										src="http://www.bresume.com/resource/templates/template-bresume-001/images/4.jpg"
										width="310px;" height="260px;" alt="">
									<div class="image-overlay">
										<div class="image-link"></div>
									</div>
								</div>
							</div>
							<div class="g1">
								<div class="image">
									<img
										src="http://www.bresume.com/resource/templates/template-bresume-001/images/5.jpg"
										width="310px;" height="260px;" alt="">
									<div class="image-overlay">
										<div class="image-link"></div>
									</div>
								</div>
							</div>
							<div class="g1">
								<div class="image">
									<img
										src="http://www.bresume.com/resource/templates/template-bresume-001/images/6.jpg"
										width="310px;" height="260px;" alt="">
									<div class="image-overlay">
										<div class="image-link"></div>
									</div>
								</div>
							</div>

						</section> -->

						<section class="clearfix">
							<article id="" class="jobI">
								<section>
									<div class="g1">
										<div class="item-box clearfix">
											<i class="icon-envelope"></i>
											<div class="item-data">

												<p>期望薪水</p>
												<h3>${jobIntension.expertSalary}</h3>
											</div>
										</div>
									</div>
									<div class="g1">
										<div class="item-box clearfix">
											<i class="icon-envelope"></i>
											<div class="item-data">

												<p>工作地点</p>
												<h3>${jobIntension.address}</h3>
											</div>
										</div>
									</div>
									<div class="g1">
										<div class="item-box clearfix">
											<i class="icon-envelope"></i>
											<div class="item-data">

												<p>工作职位</p>
												<h3>${jobIntension.profession}</h3>
											</div>
										</div>
									</div>

									<div class="g1">
										<div class="item-box clearfix">
											<i class="icon-envelope"></i>
											<div class="item-data">

												<p>到岗时间</p>
												<h3>${jobIntension.readyTime}</h3>
											</div>
										</div>
									</div>
								</section>


								<br class="clear">
							</article>
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
										<p>${persionalInfo.address}</p>
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
										<p>
											<a href="${persionalInfo.siteUrl }">${persionalInfo.siteUrl }</a>
										</p>
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


	<script type="text/javascript">
		var s_style = new Array("emerald", "carrot", "wisteria", "sunflower",
				"midnight", "pomengrate", "asbestos");
		$(".skill-item").each(function(i) {
			$(this).addClass(s_style[i % s_style.length]);
		});
	</script>
</body>
</html>
