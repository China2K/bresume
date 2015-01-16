<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon"
	href="../resource/templates/template-bresume-003/ico/favicon.png">

<title>孙悟空 - 简历</title>

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
							<li class="menu-item"><a class="smoothScroll" href="#work"
								title="作品展示"><i class="icon-briefcase"></i></a></li>
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

	<div id="headerwrap">
		<div class="container">
			<div class="row centered">
				<div class="col-lg-12">
					<h1>${persionalInfo.name}<</h1>
					<h3>${jobIntension.trade}-${jobIntension.profession}</h3>
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
		<c:forEach items="${eduExperiences}" var="eduExperience">
			<div class="row">

				<div class="col-lg-2 col-lg-offset-1">
					<h5>${eduExperience.schoolName}-${eduExperience.majorName}</h5>
				</div>
				<div class="col-lg-6">
					<p>
						<t>${eduExperience.degree}</t>
						<br /> ${eduExperience.desc}<br /> <i>10年课程</i>
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
		<!-- <div class="row">

			<div class="col-lg-2 col-lg-offset-1">
				<h5>教育经历</h5>
			</div>
			<div class="col-lg-6">
				<p>
					<t>Master of Web Design</t>
					<br /> 西牛贺洲灵台方寸山斜月三星洞学院 <br /> <i>10年课程</i>
				</p>
			</div>
			<div class="col-lg-3">
				<p>
					<sm>公元前245---公元前235年</sm>
					<br />
				</p>
			</div>



		</div> -->
		<!--/.row -->
		<br>
		<hr>
	</div>
	<!--/.container -->


	<!--WORK DESCRIPTION -->
	<div class="container desc">
		<div class="row">

			<div class="col-lg-2 col-lg-offset-1">
				<h5>工作经历</h5>
			</div>
			<c:forEach items="${workExperiences}" var="workExperience">
				<div class="col-lg-6">
					<p>
						<t>${workExperience.companyName}- ${workExperience.position}</t>
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
			</c:forEach>


		</div>
		<!--/.row -->
		<br>
		<hr>
	</div>
	<!--/.container -->


	<!--AWARDS DESCRIPTION -->
	<div class="container desc">
		<div class="row">
			<div class="col-lg-2 col-lg-offset-1">
				<h5>求职意向</h5>
			</div>
			<div class="col-lg-6">
				<p>
					<t>${jobIntension.trade}</t>
					<br /> ${jobIntension.profession}<br />
				</p>
			</div>
			<div class="col-lg-3">
				<p>
					<sm>2014.12</sm>
				</p>
			</div>

			<div class="col-lg-6 col-lg-offset-3">
				<p>
					<t>其他</t>
				</p>
				<p>
					<more>能吃饱就好，：PM2.5不要太高就好</more>
				</p>
			</div>
			<div class="col-lg-3">
				<p>
					<sm>2014</sm>
				</p>
			</div>

		</div>
		<!--/.row -->
		<br>
	</div>
	<!--/.container -->


	<!--SKILLS DESCRIPTION -->
	<div id="skillswrap">
		<div class="container">
			<div class="row">
				<div class="col-lg-2 col-lg-offset-1">
					<h5>技能</h5>
				</div>

				<c:forEach items="${skills}" var="skill">
					<div class="col-lg-3 centered">
						<canvas id="javascript" height="130" width="130"></canvas>
						<p>${skill.name}</p>
						<br>
						<script>
							var doughnutData = [ {
								value : '${skill.level}',
								color : "#1abc9c"
							}, {
								value : '${100-skill.level}',
								color : "#ecf0f1"
							} ];
							var myDoughnut = new Chart(document.getElementById(
									"javascript").getContext("2d"))
									.Doughnut(doughnutData);
						</script>
					</div>
				</c:forEach>
				<%-- <div class="col-lg-3 centered">
					<canvas id="javascript" height="130" width="130"></canvas>
					<p>棍术</p>
					<br>
					<script>
						var doughnutData = [ {
							value : 70,
							color : "#1abc9c"
						}, {
							value : 30,
							color : "#ecf0f1"
						} ];
						var myDoughnut = new Chart(document.getElementById(
								"javascript").getContext("2d"))
								.Doughnut(doughnutData);
					</script>
				</div>
				<div class="col-lg-3 centered">
					<canvas id="bootstrap" height="130" width="130"></canvas>
					<p>筋斗云</p>
					<br>
					<script>
						var doughnutData = [ {
							value : 90,
							color : "#1abc9c"
						}, {
							value : 10,
							color : "#ecf0f1"
						} ];
						var myDoughnut = new Chart(document.getElementById(
								"bootstrap").getContext("2d"))
								.Doughnut(doughnutData);
					</script>
				</div>
				<div class="col-lg-3 centered">
					<canvas id="wordpress" height="130" width="130"></canvas>
					<p>72般变化</p>
					<br>
					<script>
						var doughnutData = [ {
							value : 65,
							color : "#1abc9c"
						}, {
							value : 35,
							color : "#ecf0f1"
						} ];
						var myDoughnut = new Chart(document.getElementById(
								"wordpress").getContext("2d"))
								.Doughnut(doughnutData);
					</script>
				</div>

				<div class="col-lg-3 col-lg-offset-3 centered">
					<canvas id="html" height="130" width="130"></canvas>
					<p>搬救兵</p>
					<br>
					<script>
						var doughnutData = [ {
							value : 80,
							color : "#1abc9c"
						}, {
							value : 20,
							color : "#ecf0f1"
						} ];
						var myDoughnut = new Chart(document.getElementById(
								"html").getContext("2d"))
								.Doughnut(doughnutData);
					</script>
				</div>
				<div class="col-lg-3 centered">
					<canvas id="photoshop" height="130" width="130"></canvas>
					<p>金刚不坏</p>
					<br>
					<script>
						var doughnutData = [ {
							value : 70,
							color : "#1abc9c"
						}, {
							value : 30,
							color : "#ecf0f1"
						} ];
						var myDoughnut = new Chart(document.getElementById(
								"photoshop").getContext("2d"))
								.Doughnut(doughnutData);
					</script>
				</div>
				<div class="col-lg-3 centered">
					<canvas id="illustrator" height="130" width="130"></canvas>
					<p>火眼金睛</p>
					<br>
					<script>
						var doughnutData = [ {
							value : 50,
							color : "#1abc9c"
						}, {
							value : 50,
							color : "#ecf0f1"
						} ];
						var myDoughnut = new Chart(document.getElementById(
								"illustrator").getContext("2d"))
								.Doughnut(doughnutData);
					</script>
				</div>
 --%>
			</div>
			<!--/.row -->
			<br>
		</div>
		<!--/.container -->
	</div>
	<!--/ #skillswrap -->



	<section id="work" name="work"></section>
	<!--PORTFOLIO DESCRIPTION -->
	<div class="container desc">
		<div class="row">
			<div class="col-lg-2 col-lg-offset-1">
				<h5>作品展现</h5>
			</div>
			<c:forEach items="${projectExperiences}" var="project">
				<div class="col-lg-6">
					<p>
						<img class="img-responsive"
							src="${project.siteUrl}" alt="">
					</p>
				</div>
				<div class="col-lg-3">
					<p>${project.projectName}</p>
					<p>
						<more>${project.projectDesc}<br />
						<br />
						<sm> <i class="icon-tag"></i> ${project.respDesc}</sm></more>
					</p>
				</div>
			</c:forEach>
			<!-- <div class="col-lg-6">
				<p>
					<img class="img-responsive"
						src="../resource/templates/template-bresume-003/img/3.jpg" alt="">
				</p>
			</div>
			<div class="col-lg-3">
				<p>三打白骨精</p>
				<p>
					<more>斩妖除魔<br />
					<br />
					<sm> <i class="icon-tag"></i> 西天取经</sm></more>
				</p>
			</div>

			<div class="col-lg-6 col-lg-offset-3">
				<p>
					<img class="img-responsive"
						src="../resource/templates/template-bresume-003/img/4.jpg" alt="">
				</p>
			</div>
			<div class="col-lg-3">
				<p>大战金角大王</p>
				<p>
					<more>斩妖除魔<br />
					<br />
					<sm> <i class="icon-tag"></i>西天取经</sm></more>
				</p>
			</div>

			<div class="col-lg-6 col-lg-offset-3">
				<p>
					<img class="img-responsive"
						src="../resource/templates/template-bresume-003/img/5.jpg" alt="">
				</p>
			</div>
			<div class="col-lg-3">
				<p>大闹天宫</p>
				<p>
					<more>给个弼马温就像打发老子，也要当齐天大圣<br />
					<br />
					<sm> <i class="icon-tag"></i>任性</sm></more>
				</p>
			</div> -->

		</div>
		<!--/.row -->
		<br> <br>
	</div>
	<!--/.container -->



	<section id="contact" name="contact"></section>
	<!--FOOTER DESCRIPTION -->
	<div id="footwrap">
		<div class="container">
			<div class="row">

				<div class="col-lg-2 col-lg-offset-1">
					<h5>联系方式</h5>
				</div>
				<div class="col-lg-6">
					<p>
						<t>邮件</t>
						<br />${persionalInfo.email}<br />
					</p>
					<p>
						<t>地址</t>
						<br /> 东胜神洲傲来国<br /> 花果山<br /> 水帘洞 <br />
					</p>
					<p>
						<t>电话</t>
						<br />${persionalInfo.cellPhone } <br />
					</p>
				</div>
				<div class="col-lg-3">
					<p>
						<sm>主页</sm>
					</p>
					<p>
						<a href="${persionalInfo.siteUrl } "><i class="icon-dribbble">${persionalInfo.siteUrl }</i></a>

					</p>
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
				Created by <a href="http://www.bresume.com">bresume.com</a>
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
