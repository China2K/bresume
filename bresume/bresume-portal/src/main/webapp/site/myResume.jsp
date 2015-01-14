<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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

<!-- Custom CSS -->
<link href="<c:url value='/resource/site/css/agency.css'/>"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<c:url value='/resource/site/font-awesome-4.1.0/css/font-awesome.min.css'/>"
	rel="stylesheet" type="text/css">

<!-- Custom CSS for template -->
<link rel="stylesheet"
	href="<c:url value='/resource/site/About_files/css/stuck.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resource/site/About_files/css/ihover.css'/>">

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

#fileupload {
	height: 250px;
	display: inline-block;
	color: white;
	text-align: center;
	overflow: hidden;
	text-align: left;
}

.uploadify-button, .uploadify-button-text {
	text-align: center;
	font-size: 20px;
}

#fileupload span {
	text-align: center;
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
		<c:forEach items="${resumes}" var="resume" varStatus="status">
			<section>
				<div class="panel-heading"
					style="background-color: #f5f5f5; height: 45px; border-color: #ddd;">

				</div>
				<div class="row resumeRow">

					<div class="col-md-3" id="portfolio" style="height: 250px;">

						<div class="portfolio-item" id="file-div-${status.index}"
							id-value="${resume.id}">
							<div class="portfolio-link" onclick="reUpload()"
								data-toggle="modal">
								<div class="portfolio-hover">
									<input class="portfolio-hover-content fileupload-file-input_"
										id="fileupload-${resume.id}" type="file" />
									<%-- <div class="portfolio-hover-content">
										<span class="fa fa-1x">
										
										</span>
									</div> --%>
								</div>
								<img src="${staticUrlPrefix}${resume.coverUrl}"
									style="height: 250px; width: 250px; line-height: 250px;"
									class="img-responsive text-center fileupload-img-input_" alt="">
							</div>
							<input type="hidden" value="${resume.coverUrl}"
								class="fileupload-hidden-input_" />
						</div>
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
						<%-- <div class="row text-center">
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
						</div> --%>
					</div>

					<div class="col-md-3 actions"
						style="background-color: #3079ab; height: 250px;">
						<div class="col-md-6"
							style="height: 125px; border-right: 1px dashed #eeeeee; border-bottom: 1px dashed #eeeeee;">
							<div class="center-block action-button text-center" id="view_btn"
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
	<script type="text/javascript"
		src="<c:url value ='/resource/site/js/jquery.js'/>" charset="UTF-8"></script>

	<!-- Bootstrap Core JavaScript -->
	<script type="text/javascript"
		src="<c:url value ='/resource/site/js/bootstrap.min.js'/>"></script>

	<!-- Plugin JavaScript -->
	<script src="<c:url value ='/resource/site/js/jquery.easing.min.js'/>"></script>
	<script src="<c:url value ='/resource/site/js/classie.js'/>"></script>
	<script src="<c:url value ='/resource/site/js/cbpAnimatedHeader.js'/>"></script>

	<!-- Contact Form JavaScript -->
	<script
		src="<c:url value ='/resource/site/js/jqBootstrapValidation.js'/>"></script>
	<script src="<c:url value ='/resource/site/js/contact_me.js'/>"></script>

	<!-- Custom Theme JavaScript -->
	<script src="<c:url value ='/resource/site/js/agency.js'/>"></script>





	<script
		src="<c:url value ='/resource/plugin/uploadify/jquery.uploadify.js'/>"></script>

	<script type="text/javascript">
		var rNum = '${fn:length(resumes)}';
		rNum= rNum*1;
		for (var i = 0; i < rNum; i++) {
			var idV = "#file-div-" + i;

			var fileEle = $(idV + " .fileupload-file-input_");
			var imgEle = $(idV + " .fileupload-img-input_");
			var valueEle = $(idV + " .fileupload-hidden-input_");

			var resumeId = $(idV).attr("id-value");
			console.log(fileEle);
			makeFileUpload(fileEle, imgEle, valueEle, resumeId);
		}
		//var uploadUrl = "http://static.bresume.com/upload/uploadImg";
		function makeFileUpload(fileEle, imgEle, valueEle, resumeId){
			fileEle.uploadify({
						'uploader' : 'http://static.bresume.com/upload/uploadImg',
						'swf' : '<c:url value ="/resource/plugin/uploadify/uploadify.swf"/>',
						'cancelImage' : '<c:url value ="/resource/plugin/uploadify/cancel.png"/>',
						'queueID' : 'imgFile',
						'fileObjName' : 'imgFile',
						'auto' : true,
						'width' : 285,
						'height' : 250,
						'multi' : false,
						'buttonText' : '重新上传',
						'formData' : {
							'user' : 'test',
							'source' : 'PORTAL'
						},
						'onUploadSuccess' : function(file, data, response) {
							data = JSON.parse(data);
							var res = data.success;
							if (res === true) {
								valueEle.val(data.message);
								imgEle.attr("src", "http://static.bresume.com"
										+ data.message);

								var param = {
									resumeId : resumeId,
									newImgUrl : data.message
								};
								$
										.ajax({
											type : "POST",
											url : "<c:url value='/resume/changeResumeCover'/>",
											dataType : "json",
											data : param,
											success : function(resp) {

											}

										});
							}
						},
					});

		}

		// 上传图片,图片预览,记录图片的url
		/* 	initUploadAndPreview($('#fileupload'), $("#resumeCoverUrl"),
				$("#resumeCoverImg")); */

		function view_resume(id) {
			alert("check");
		}

		function edit_resume(id) {
			location.href = "/resume/buildResume?id=" + id;
		}

		function delete_resume(id) {

		}

		function download_resume(id) {

		}
		/* var uploadUrl = "http://static.bresume.com/upload/uploadImg";
		$("#fileupload")
				.uploadify(
						{
							'uploader' : uploadUrl,
							'swf' : '<c:url value ="/resource/plugin/uploadify/uploadify.swf"/>',
							'cancelImage' : '<c:url value ="/resource/plugin/uploadify/cancel.png"/>',
							'queueID' : 'imgFile',
							'fileObjName' : 'imgFile',
							'auto' : true,
							'width' : 285,
							'height' : 250,
							'multi' : false,
							'buttonText' : '重新上传',
							'formData' : {
								'user' : 'test',
								'source' : 'PORTAL'
							},
							/*  'onSelect': function (file) {
							     $('#uploadify').uploadifySettings('formData', { 'ASPSESSID': ASPSESSID, 'AUTHID': auth });
							 }, */
		/* 'onUploadSuccess' : function(file, data, response) {
			data = JSON.parse(data);
			var res = data.success;
			if (res === true) {
				$("#resumeCoverUrl").val(data.message);
				$("#resumeCoverImg").attr(
						"src",
						"http://static.bresume.com"
								+ data.message);
				
				var param={resumeId:'',imgUrl:data.message};
				$.ajax({
					  type: "POST",
					  url: "test.js",
					  dataType: "json",
					  data:param,
					  success:function(resp){
						  
					  }
					  
					});
			}
		}, */
		/*
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
		/* }); */
	</script>



</body>

</html>
