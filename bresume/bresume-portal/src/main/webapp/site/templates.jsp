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


	<!-- templates Section -->
	<section id="templates">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">简历模板</h2>
					<h3 class="section-subheading text-muted">选择喜欢的模板开始创建简历吧！</h3>
				</div>
			</div>
			<div class="row">

				<c:forEach items="${templates}" var="template">
					<div class="col-md-3">
						<div class="ih-item circle effect2 left_to_right">
							<a href="/portal/resume/buildResume?template=${template.sn}" class="acover">
								<div class="img">
									<img src="${template.coverUrl}" alt="img">
								</div>
								<div class="info">
									<h3>${template.name}</h3>
									<p><</p>
								</div>
							</a>
						</div>
						
						<div>
						<a href="" class="btn btn-default btn btn-primary btn-lg btn-block">查看详细</a>
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
	<script src="/portal/resource/site/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="/portal/resource/site/js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script src="/portal/resource/site/js/jquery.easing.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="/portal/resource/site/js/agency.js"></script>
	<script src="/portal/resource/app/js/common.js"></script>
	<script src="/portal/resource/site/js/jquery.form.js"></script>

	<script type="text/javascript">
		
	</script>



</body>

</html>
