<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">

<title>孙悟空-简历</title>
<link rel="stylesheet" type="text/css"
	href="../resource/templates/template-bresume-002/styles/d.css">
<link rel="stylesheet" type="text/css"
	href="../resource/templates/template-bresume-002/styles/index.css">
<script
	data-main="../resource/templates/template-bresume-002/scripts/config"
	src="../resource/templates/template-bresume-002/vendors/requirejs/require.js"></script>

<!--[if lt IE 9]>
        <script>
            document.createElement('header');
            document.createElement('nav');
            document.createElement('section');
            document.createElement('article');
            document.createElement('aside');
            document.createElement('footer');
            document.createElement('hgroup');
        </script>
    <![endif]-->
</head>
<body>
	<div id="home-wrapper">
		<nav class="colors">
			<ul>
				<li id="nav-1" class="introduce"><a href="#"> <span
						class="center"> 基本资料 </span>
				</a></li>
				<li id="nav-2" class="evaluate"><a href="#"> <span
						class="center"> 自我评价 </span>
				</a></li>
				<li id="nav-3" class="skill"><a href="#"> <span
						class="center"> 技能专长 </span>
				</a></li>
				<li id="nav-4" class="project"><a href="#"> <span
						class="center"> 项目作品 </span>
				</a></li>
				<li id="nav-5" class="college"><a href="#"> <span
						class="center"> 教育经历 </span>
				</a></li>
				<li id="nav-6" class="work"><a href="#"> <span
						class="center"> 工作经历 </span>
				</a></li>
				<li id="nav-7" class="study"><a href="#"> <span
						class="center"> 求职意向 </span>
				</a></li>
				<li id="nav-8" class="contact"><a href="#"> <span
						class="center"> 联系 </span>
				</a></li>
			</ul>
		</nav>

		<div class="circle">
			<div class="js-picture"
				js-source="../resource/templates/template-bresume-002/images/circle.png"></div>
		</div>

		<div class="home-block">
			<aside class="social">
				<div class="icons"></div>

				<span class="github">主页: <a href="${persionalInfo.siteUrl}"
					target="_blank">${persionalInfo.siteUrl}</a></span>
			</aside>

			<aside class="introduction">
				<h1>${jobIntension.trade}- ${jobIntension.profession}</h1>
				<h2>
					<span></span>
					<div class="js-picture qrcode" js-source=""></div>
				</h2>
			</aside>

			<aside class="bottom">
				<p>
					${resume.desc} <br>

				</p>
				<span>Web design by [bresume.com]</span>
			</aside>
		</div>
	</div>

	<div id="content-wrapper">
		<section class="content introduce">
			<div class="bg"></div>
			<div class="content-panel">
				<h1>基本资料</h1>

				<article>
					<section>
						<ul class="line">
							<li>姓名：${persionalInfo.name}</li>
							<li>性别：${persionalInfo.sex}</li>
							<li>籍贯：${persionalInfo.address}</li>
							<li>出生日期：${persionalInfo.birthday}</li>
						</ul>
					</section>
					<section>
						<ul class="line">
							<li>毕业院校：${persionalInfo.schoolName}</li>
							<li>学历：博士</li>
							<li>专业：仙术</li>
						</ul>
					</section>
				</article>
			</div>
		</section>
		<section class="content evaluate">
			<div class="bg"></div>
			<div class="content-panel">
				<h1>自我评价</h1>
				<article>
					<section>
						<div class="line">${jobIntension.selfEvaluation}</div>
					</section>
				</article>
			</div>
		</section>
		<section class="content skill">
			<div class="bg"></div>
			<div class="content-panel">
				<h1>技能专长</h1>
				<article>
					<section>
						<ul class="item">
							<c:forEach items="${skills}" var="skill">
								<li>${skill.name}</li>

							</c:forEach>

						</ul>
					</section>



				</article>
			</div>
		</section>
		<section class="content project">
			<div class="bg"></div>
			<div class="content-panel">
				<h1>项目作品</h1>

				<article>
					<section>
						<ul class="line">
							<c:forEach items="${projectExperiences}" var="project">
								<li><span>${project.projectDesc}</span></li>
								<li><span>${project.respDesc}</span></li>
								<li><a href="${project.siteUrl}" target="_blank">
										<div class="cover js-picture"
											js-source="../resource/cover/bresume.png"></div>
										<p class="desc">${project.projectName}</p>
								</a></li>

								<hr />
							</c:forEach>

						</ul>
					</section>
				</article>
			</div>
		</section>
		<section class="content college">
			<div class="bg"></div>
			<div class="content-panel">
				<h1>教育经历</h1>
				<article>
					<section>
						<h2>教育经历</h2>
						<ul class="line">
							<c:forEach items="${eduExperienced}" var="eduExperience">
								<li>
									<p>时间：${eduExperience.startDate} - ${eduExperience.endDate}</p>
									<p>学校：${eduExperience.schoolName}</p>
									<p>专业：${eduExperience.majorName}</p>
									<p>${eduExperience.desc}</p>
								</li>
							</c:forEach>
							
						</ul>
					</section>

				</article>
			</div>
		</section>
		<section class="content work">
			<div class="bg"></div>
			<div class="content-panel">
				<h1>工作经历</h1>
				<article>
					<section>
						<ul class="line">
						
							<c:forEach items="${eduExperiences}" var="eduExperience">
								<li>
									<h2>${eduExperience.companyName} - ${eduExperience.position} (${eduExperience.startDate}--${eduExperience.endDate})</h2>
									<p class="">${eduExperience.desc}</p>
								</li>
							</c:forEach>
						</ul>
					</section>
				</article>
			</div>
		</section>
		<section class="content study">
			<div class="bg"></div>
			<div class="content-panel">
				<h1>求职意向</h1>
				<article id="" class="">
					<section>
						<ul class="line">
							<li>期望薪水：${jobIntension.expertSalary}</li>
							<li>工作地点：${jobIntension.address}</li>
							<li>工作职位：${jobIntension.profession}</li>
						</ul>
					</section>


					<br class="clear">
				</article>
			</div>
		</section>
		<section class="content contact">
			<div class="bg"></div>
			<div class="content-panel">
				<h1>联系</h1>
				<article>
					<section>
						<h2>联系方式</h2>
						<ul class="line">
							<li>手机号码：${persionalInfo.cellPhone }</li>
							<li>电子邮箱：<a href="mailto:${persionalInfo.email}">${persionalInfo.email}</a></li>
						</ul>
					</section>
				</article>
			</div>
		</section>
	</div>

	<div class="js-picture backhome"
		js-source="../resource/templates/template-bresume-002/images/backhome.png"></div>
</body>
</html>