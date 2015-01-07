<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>bresume - 简历创建</title>


<style type="text/css">
</style>


</head>
<%@include file="common/common.jsp"%>
<body id="page-top" class="index">
	<%@include file="common/header.jsp"%>


	<section id="portfolio" class="bg-light-gray">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">热门简历</h2>
					<h3 class="section-subheading text-muted">查看小伙伴们的简历寻找些灵感吧！</h3>
				</div>
			</div>
			<div class="row">

				<c:forEach items="${resumes}" var="resume">
					<div class="col-md-4 col-sm-6 portfolio-item">
						<a href="#portfolioModal1" class="portfolio-link"
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

	<%@include file="common/footer.jsp"%>
	<div id="backtotop">
		<div class="bttbg"></div>
	</div>





	<!-- jQuery -->
	<script type="text/javascript"
		src="<c:url value ='/resource/site/js/jquery.js'/>" charset="UTF-8"></script>

	<script type="text/javascript"
		src="<c:url value ='/resource/site/js/bootstrap.min.js'/>"></script>

	<!-- Plugin JavaScript -->
	<script src="<c:url value ='/resource/site/js/jquery.easing.min.js'/>"></script>

	<!-- Custom Theme JavaScript -->
	<script src="<c:url value ='/resource/site/js/agency.js'/>"></script>

	<script src="<c:url value ='/resource/app/js/common.js'/>"></script>
	<script type="text/javascript"
		src="<c:url value ='/resource/site/js/jquery.form.js'/>"
		charset="UTF-8"></script>

	<script type="text/javascript">
		
	</script>



</body>

</html>
