<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>bresume - 比简历</title>

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
							<a href="<c:url value='/resume/buildResume?template=${template.sn}'/>"
								class="acover">
								<div class="img">
									<img src="${staticUrlPrefix}${template.coverUrl}" alt="img">
								</div>
								<div class="info">
									<h3>${template.name}</h3>
									<p>以此创建简历</p>
								</div>
							</a>
						</div>

						<div>
							<a href="${template.siteUrl}"
								class="btn btn-default btn btn-primary btn-lg btn-block">查看详细</a>
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
