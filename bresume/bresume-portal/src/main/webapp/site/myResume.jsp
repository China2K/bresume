<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta Access-Control-Allow-Headers=" Content-Type, Content-Range, Content-Disposition, Content-Description">

<title>bresume - 简历创建</title>

<!-- Bootstrap Core CSS -->
<link href="/portal/resource/site/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="/portal/resource/site/css/agency.css" rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="/portal/resource/site/font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- Custom CSS for template -->
<link rel="stylesheet"
	href="/portal/resource/site/About_files/css/stuck.css">
<link rel="stylesheet"
	href="/portal/resource/site/About_files/css/ihover.css">

<link rel="stylesheet"
	href="<c:url value ='/resource/plugin/jQuery-File-Upload/css/jquery.fileupload.css'/>">
<style type="text/css">
/*my resume page*/
.action-button {
	border: 1px solid #379082;
	border-radius: 10px;
	height: 99px;
	width: 99px;
	background-color: #6DA1C4;
	margin-top: 15px;
	width: 100%;
	font-size: 45px;
	color: white;
	cursor: pointer;
}

.action-icon {
	margin-top: 8px;
	height: 65px;
	line-height: 65px;
	height: 65px;
}

.action-icon span {
	width: 60px;
	height: 60px;
}

.action-text {
	margin-top: -5px;
	height: 25px;
	font-size: 18px;
}

.action-button:hover {
	background-color: #93C1E0;
}

.action-button span {
	opacity: 1;
}
</style>


</head>
<%@include file="common/common.jsp"%>
<body id="page-top" class="index">
	<%@include file="common/header.jsp"%>



	<div class="container wrap">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">我的简历</h2>

			</div>
		</div>

		<c:forEach items="${resumes}" var="resume">
			<section>
				<div class="panel-heading"
					style="background-color: #f5f5f5; height: 45px; border-color: #ddd;">

				</div>
				<div class="row resumeRow">

					<div class="col-md-3"
						style="background-color: #f9845b; height: 250px;">
						<!-- <a href="" class=""> -->
							<div class="portfolio-hover">
								<div class="portfolio-hover-content">
									<div class="">
									    <span class="btn btn-success fileinput-button">
									        <i class="glyphicon glyphicon-plus"></i>
									        <input id="fileupload" type="file"/>
									    </span>
								    </div>
								</div>
							</div> <img src="${resume.coverUrl}" id="resumeCoverImg" class="img-responsive" alt="">
							<input type="hidden" value="${resume.coverUrl}" id="resumeCoverUrl"/>
						<!-- </a> -->
					</div>

					<div class="col-md-6"
						style="background-color: #fff; height: 250px;">

						<div class="row text-left">
							<h4 class="librarytitle">
								<a href="#">${resume.name}</a>
							</h4>
						</div>
						<div class="row text-left">
							<span class="glyphicon glyphicon-stats">
								${resume.name}&nbsp;|&nbsp; ${resume.name}</span>
						</div>
						<div class="row text-left">
							<span class="glyphicon glyphicon-time"
								style="margin-right: 50px;">${resume.createdTime}</span>
						</div>
						<div class="row text-center">
							<div class="col-md-4 text-left">
								<span>将我的简历分享到 </span>
							</div>
							<div class="col-md-8">
								<ul class="list-inline social-buttons">
									<li><a href="#"><i class="fa fa-twitter"></i></a></li>
									<li><a href="#"><i class="fa fa-facebook"></i></a></li>
									<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
									<li><a href="#"><i class="fa fa-qq"></i></a></li>
									<li><a href="#"><i class="fa fa-weibo"></i></a></li>
								</ul>
							</div>
						</div>
					</div>

					<div class="col-md-3 actions"
						style="background-color: #3079ab; height: 250px;">
						<div class="col-md-6"
							style="height: 125px; border-right: 1px dashed #eeeeee; border-bottom: 1px dashed #eeeeee;">
							<div class="center-block action-button text-center"
								onclick="view_resume('${resume.id}');">
								<div class="action-icon">
									<span class="text-center glyphicon glyphicon-zoom-in"></span>
								</div>
								<div class="action-text">查看</div>
							</div>
						</div>
						<div class="col-md-6"
							style="height: 125px; border-bottom: 1px dashed #eeeeee;">
							<div class="center-block action-button text-center"
								onclick="edit_resume('${resume.id}');">
								<div class="action-icon">
									<span class="text-center glyphicon glyphicon-edit"></span>
								</div>
								<div class="action-text">编辑</div>
							</div>
						</div>
						<div class="col-md-6"
							style="height: 125px; border-right: 1px dashed #eeeeee;">
							<div class="center-block action-button text-center"
								onclick="delete_resume('${resume.id}');">
								<div class="action-icon">
									<span class="text-center glyphicon glyphicon-trash"></span>
								</div>
								<div class="action-text">删除</div>
							</div>
						</div>
						<div class="col-md-6" style="height: 125px;">
							<div class="center-block action-button text-center"
								onclick="download_resume('${resume.id}');">
								<div class="action-icon">
									<span class="text-center glyphicon glyphicon-download-alt"></span>
								</div>
								<div class="action-text">下载</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</c:forEach>

	</div>

	<%@include file="common/footer.jsp"%>

	<div id="backtotop">
		<div class="bttbg"></div>
	</div>



	<!-- jQuery -->
	<script src="/portal/resource/site/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="/portal/resource/site/js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="/portal/resource/site/js/classie.js"></script>
	<script src="/portal/resource/site/js/cbpAnimatedHeader.js"></script>

	<!-- Contact Form JavaScript -->
	<script src="/portal/resource/site/js/jqBootstrapValidation.js"></script>
	<script src="/portal/resource/site/js/contact_me.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="/portal/resource/site/js/agency.js"></script>



	
	
	<script src="<c:url value ='/resource/plugin/uploadify/jquery.uploadify.js'/>"></script>
	
	<script type="text/javascript">
	
	 // 上传图片,图片预览,记录图片的url
	/* 	initUploadAndPreview($('#fileupload'), $("#resumeCoverUrl"),
			$("#resumeCoverImg")); */
	 
		function view_resume(id) {

		}

		function edit_resume(id) {
			location.href = "/portal/resume/buildResume?id=" + id;
		}

		function delete_resume(id) {

		}

		function download_resume(id) {

		}
		
		var uploadUrl="http://localhost:8081/static/upload/uploadImg";
		$("#fileupload").uploadify({
            'uploader': uploadUrl,
            'swf': '<c:url value ="/resource/plugin/uploadify/uploadify.swf"/>',
            'cancelImage': '<c:url value ="/resource/plugin/uploadify/cancel.png"/>',
            'queueID': 'imgFile',
            'fileObjName'   : 'imgFile',  
            'auto': true,
            'multi': true,
            'buttonText': '文件上传',
            'formData': { 'ASPSESSID': 'a', 'AUTHID': 'aa' },
           /*  'onSelect': function (file) {
                $('#uploadify').uploadifySettings('formData', { 'ASPSESSID': ASPSESSID, 'AUTHID': auth });
            }, */
            /* 'onComplete': function (file, data, response) {
            },

            'onQueueComplete': function () {
                alert("上传完成！");
                $('#fileQueue').attr('style', 'visibility :hidden');
            },
            'onSelectError': function (file, errorCode, errorMsg) {
                $('#fileQueue').attr('style', 'visibility :hidden');
            },
            'onUploadStart': function (file) {
                $('#fileQueue').attr('style', 'top:200px;left:400px;width:400px;height :400px;visibility :visible');
            } */
        });
	</script>



</body>

</html>
