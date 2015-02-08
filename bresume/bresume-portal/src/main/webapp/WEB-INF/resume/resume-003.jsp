<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="">
<link rel="shortcut icon"
	href="../resource/templates/template-bresume-003/ico/favicon.png">

<title>${persionalInfo.name}-简历</title>

<!-- Bootstrap core CSS -->
<link
	href="../resource/templates/template-bresume-003/css/bootstrap.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../resource/templates/template-bresume-003/css/main.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="../resource/templates/template-bresume-003/css/font-awesome.min.css">

<link
	href='http://fonts.useso.com/css?family=Lato:300,400,700,300italic,400italic'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Raleway:400,300,700'
	rel='stylesheet' type='text/css'>

<script
	src="../resource/templates/template-bresume-003/js/jquery.min.js"></script>
<script type="text/javascript"
	src="../resource/templates/template-bresume-003/js/smoothscroll.js"></script>
<script src="../resource/templates/template-bresume-003/js/Chart.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../resource/templates/template-bresume-003/js/html5shiv.js"></script>
      <script src="../resource/templates/template-bresume-003/js/respond.min.js"></script>
    <![endif]-->

<style type="text/css">
body {
	font-family: Tahoma, Verdana, 微软雅黑, 新宋体;
}

.item-header {
	margin-bottom: 35px;
}

.item-header h3 {
	font-family: Tahoma, Verdana, 微软雅黑, 新宋体 !important;
}
</style>
</head>

<body data-spy="scroll" data-offset="0" data-target="#nav">

	<div id="section-topbar">
		<div id="topbar-inner">
			<div class="container">
				<div class="row">
					<div class="dropdown">
						<ul id="nav" class="nav">
							<li class="menu-item"><a class="smoothScroll" href="#about"
								title="基本信息"><i class="icon-user"></i></a></li>
							<li class="menu-item"><a class="smoothScroll" href="#resume"
								title="履历介绍"><i class="icon-file"></i></a></li>
							<c:if test="${fn:length(projectExperiences)>0}">
								<li class="menu-item"><a class="smoothScroll" href="#project"
									title="作品展示"><i class="icon-briefcase"></i></a></li>
							</c:if>
							<li class="menu-item"><a class="smoothScroll"
								href="#contact" title="联系方式"><i class="icon-envelope"></i></a></li>
						</ul>
						<!--/ uL#nav -->
					</div>
					<!-- /.dropdown -->

					<div class="clear"></div>
				</div>
				<!--/.row -->
			</div>
			<!--/.container -->

			<div class="clear"></div>
		</div>
		<!--/ #topbar-inner -->
	</div>
	<!--/ #section-topbar -->

	<div id="headerwrap" style="background: url('${staticUrlPrefix}${resume.coverUrl}') no-repeat center;background-size: cover;">
		<div class="container">
			<div class="row centered">
				<div class="col-lg-12">
					<h1>${persionalInfo.name}</h1>
					<h3>${jobIntension.trade}/${jobIntension.profession}</h3>
				</div>
				<!--/.col-lg-12 -->
			</div>
			<!--/.row -->
		</div>
		<!--/.container -->
	</div>
	<!--/.#headerwrap -->


	<section id="about" name="about"></section>
	<div id="intro">
		<div class="container">
			<div class="row">

				<div class="col-lg-2 col-lg-offset-1">
					<h5>自我评价</h5>
				</div>
				<div class="col-lg-6">
					<p>${jobIntension.selfEvaluation}</p>
				</div>
				<!-- <div class="col-lg-3">
					<p><a href="#"><i class="icon-file"></i></a> <sm>DOWNLOAD PDF</sm></p>
				</div> -->

			</div>
			<!--/.row -->
		</div>
		<!--/.container -->
	</div>
	<!--/ #intro -->


	<section id="resume" name="resume"></section>
	<!--EDUCATION DESCRIPTION -->
	<div class="container desc">
		<div class="row text-center item-header">
			<h3>教育经历</h3>
		</div>
		<c:forEach items="${eduExperiences}" var="eduExperience">
			<div class="row">

				<div class="col-lg-2 col-lg-offset-1">
					<h5>${eduExperience.degree}</h5>
				</div>
				<div class="col-lg-6">
					<p>
						<t>${eduExperience.schoolName}-${eduExperience.majorName}</t>
						<br /> ${eduExperience.desc}<br />
					</p>
				</div>
				<div class="col-lg-3">
					<p>
						<sm>${eduExperience.startDate} - ${eduExperience.endDate}</sm>
						<br />
					</p>
				</div>



			</div>
		</c:forEach>
		<!--/.row -->
		<br>
		<hr>
	</div>
	<!--/.container -->
	<c:if test="${fn:length(workExperiences)>0}">

		<!--WORK DESCRIPTION -->
		<div class="container desc">
			<div class="row text-center item-header">
				<h3>工作经历</h3>
			</div>

			<c:forEach items="${workExperiences}" var="workExperience">
				<div class="row">

					<div class="col-lg-2 col-lg-offset-1">
						<h5>${workExperience.companyName}</h5>
					</div>
					<div class="col-lg-6">
						<p>
							<t>${workExperience.position}</t>
							<br />
						</p>
						<p>
							<more>${workExperience.desc}</more>
						</p>
					</div>
					<div class="col-lg-3">
						<p>
							<sm>${workExperience.startDate}--${workExperience.endDate}</sm>
						</p>
					</div>



				</div>
				<!--/.row -->
				<br>
			</c:forEach>
			<hr>
		</div>
		<!--/.container -->
	</c:if>

	<!--AWARDS DESCRIPTION -->
	<div class="container desc">

		<div class="row text-center item-header">
			<h3>求职意向</h3>
		</div>
		<div class="row">
			<div class="col-lg-1 col-lg-offset-1"></div>
			<div class="col-lg-3">
				<p>
					期望地区:
					<t>${jobIntension.address}</t>
					<br />
				</p>
			</div>
			<div class="col-lg-3">
				<p>
					期望薪水 :
					<t>${jobIntension.expertSalary}</t>
					<br />
				</p>
			</div>
			<div class="col-lg-3">
				<p>
					到岗时间 :
					<t>${jobIntension.readyTime}</t>
					<br />
				</p>
			</div>

		</div>
		<!--/.row -->
		<br>
	</div>
	<!--/.container -->

	<c:if test="${fn:length(skills)>0}">
		<!--SKILLS DESCRIPTION -->
		<div id="skillswrap">
			<div class="container">

				<div class="row text-center item-header">
					<h3 style="color: white;">技能</h3>
				</div>
				<div class="row">
					<c:forEach items="${skills}" var="skill">
						<div class="col-lg-3 centered">
							<canvas id="javascript_${skill.id}" height="130" width="130"></canvas>
							<p>${skill.name}</p>
							<br>
							<script>
								var sc = '${skill.score}' * 1;
								var mi = 100 - sc;
								var doughnutData = [ {
									value : sc,
									color : "#1abc9c"
								}, {
									value : mi,
									color : "#ecf0f1"
								} ];
								var myDoughnut = new Chart(document
										.getElementById(
												"javascript_${skill.id}")
										.getContext("2d"))
										.Doughnut(doughnutData);
							</script>
						</div>
					</c:forEach>
				</div>
				<!--/.row -->
				<br>
			</div>
			<!--/.container -->
		</div>
		<!--/ #skillswrap -->
	</c:if>

	<c:if test="${fn:length(projectExperiences)>0}">
		<!-- <section id="work" name="work"></section> -->
		<!--PORTFOLIO DESCRIPTION -->
		<div class="container desc" id="project">

			<div class="row text-center item-header">
				<h3>作品展现</h3>
			</div>

			<c:forEach items="${projectExperiences}" var="project">
				<div class="row">
					<div class="col-lg-1"></div>
					<div class="col-lg-6">
						<p>${project.projectName}</p>
						<p>
							<more>${project.projectDesc}<br />
							<br />
							<sm> <i class="icon-tag"></i> ${project.respDesc}</sm></more>
						</p>


					</div>
					<div class="col-lg-4">
						<p>
							<img class="img-responsive"
								src="${staticUrlPrefix}${project.coverUrl}" alt="" width="320"
								height="260" />
						</p>
					</div>

				</div>
				<!--/.row -->
				<br />
			</c:forEach>
			<br /> <br />
		</div>
		<!--/.container -->
	</c:if>


	<section id="contact" name="contact"></section>
	<!--FOOTER DESCRIPTION -->
	<div id="footwrap">
		<div class="container">

			<div class="row text-center item-header">
				<h3 style="color: white;">联系方式</h3>
			</div>

			<div class="row">

				<div class="col-lg-2 col-lg-offset-1"></div>
				<div class="col-lg-4">
					<p>
						<sm>邮件&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</sm>
						${persionalInfo.email}<br />
					</p>
					<p>
						<sm>地址&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</sm>
						${persionalInfo.address}
					</p>

				</div>
				<div class="col-lg-5">
					<p>
						<sm>电话&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</sm>
						${persionalInfo.cellPhone } <br />
					</p>

					<p>
						<sm>主页&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</sm>
						<a href="${persionalInfo.siteUrl } "><i class="icon-dribbble">${persionalInfo.siteUrl }</i></a>
				</div>
			</div>
			<!--/.row -->
		</div>
		<!--/.container -->
	</div>
	<!--/ #footer -->

	<div id="c">
		<div class="container">
			<p>
				Designed by <a href="http://www.bresume.com">bresume.com</a>
			</p>

		</div>
	</div>


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="../resource/templates/template-bresume-003/js/bootstrap.js"></script>
</body>
</html>
