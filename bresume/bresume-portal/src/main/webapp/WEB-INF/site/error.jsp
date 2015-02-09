<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta
	Access-Control-Allow-Headers=" Content-Type, Content-Range, Content-Disposition, Content-Description">

<title>bresume - 简历创建</title>

<!-- Bootstrap Core CSS -->
<link href="<c:url value='/resource/site/css/bootstrap.min.css'/>"
	rel="stylesheet">


<!-- Custom Fonts -->
<link
	href="<c:url value='/resource/site/font-awesome-4.1.0/css/font-awesome.min.css'/>"
	rel="stylesheet" type="text/css">

<!-- jQuery-Validation-Engine -->
<link rel="stylesheet"
	href="<c:url value ='/resource/plugin/jQuery-Validation-Engine/css/validationEngine.jquery.css'/>">

<style type="text/css">
.right-dashed {
	border-right: 1px dashed #ccc;
}

.message-container{
font-family: Tahoma, Verdana, 微软雅黑, 新宋体;
font-size: 18px;
min-height: 450px;
line-height: 300px;
}
</style>


</head>
<%@include file="common/common.jsp"%>
<body id="page-top" class="index">
	<%@include file="common/header.jsp"%>

	<div class="container text-center message-container">
			${message}
	</div>

	<%@include file="common/footer.jsp"%>

	<div id="backtotop">
		<div class="bttbg"></div>
	</div>



	<!-- jQuery -->
	<script type="text/javascript"
		src="<c:url value ='/resource/site/js/jquery.js'/>" charset="UTF-8"></script>

	<!-- Bootstrap Core JavaScript -->
	<script type="text/javascript"
		src="<c:url value ='/resource/site/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value ='/resource/site/js/jquery.form.js'/>"></script>
	<script
		src="<c:url value ='/resource/plugin/jQuery-Validation-Engine/js/jquery.validationEngine-zh_CN.js'/>"></script>
	<script
		src="<c:url value ='/resource/plugin/jQuery-Validation-Engine/js/jquery.validationEngine.min.js'/>"></script>
</body>

</html>
