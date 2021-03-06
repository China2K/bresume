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

<title>bresume - 比简历</title>

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
</style>


</head>
<%@include file="common/common.jsp"%>
<body id="page-top" class="index">
	<%@include file="common/header.jsp"%>

	<div class="container wrap">
		<div class="modal-content col-md-8 col-md-offset-2"
			style="margin-top: 100px;">
			<form accept-charset="UTF-8" onsubmit="return false;" class="simple_form" id="forget_form">
				<div class="modal-header">
					<p>您正在进行重置密码操作。请您将下列表单填写完毕。</p>
				</div>
				<div class="modal-body row">
					<div class="col-md-5 right-dashed">
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-envelope"></i></span><input
									autofocus="autofocus"
									class="form-control validate[required,custom[email]]"
									id="loginName" name="email" placeholder="邮箱地址"
									required="required">
							</div>
						</div>
					</div>
					<div class="row col-md-6"
						style="color: #B2B2B2; padding-left: 40px; padding-top: 6px;">
						<p>我们会发送一个链接到您的邮箱进行确认</p>
					</div>

				</div>
				<div class="modal-footer" style="text-align: left;">
					<div class="form-group">
						<div class="form-action">
							<input class="btn btn-info" type="button" value="重置密码"
								id="reset_btn" /> <input class="btn btn-success" type="button" onclick="location.href='/login'"
								value="返回登录" id="ingore_btn" style="float: right;" />
						</div>
					</div>

				</div>
			</form>
		</div>


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


	<script type="text/javascript">
	$("#reset_btn").click(function() {
		if (!$("#forget_form").validationEngine("validate")) {
			return false;
		}
		var _email=$("#loginName").val();
		$.ajax({
			url:"/user/send_forget",
			data:{email:_email},
			dataType:"json",
			success:function(resp){
				alert(resp.message);
			}
		});
	});
	</script>



</body>

</html>
