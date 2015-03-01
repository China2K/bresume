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
			<form accept-charset="UTF-8" onsubmit="return false;"
				class="simple_form" data-remote="true" id="bind_form" method="post">
				<input type="hidden" name="openId" value="${openId}" /> <input
					type="hidden" name="loginFrom" value="${loginFrom}" />
				<div class="modal-header">
					<p>正将您的bresume账号同${login_from}账号绑定。请您将下列表单填写完毕。</p>
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
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-key"></i></span><input
									class="form-control validate[required]" id="user_password"
									name="password" placeholder="密码" required="required"
									type="password">
							</div>
						</div>
					</div>
					<div class="row col-md-6"
						style="color: #B2B2B2; padding-left: 40px; padding-top: 20px;">
						<p>已有账号？直接输入邮箱、密码即可绑定</p>
						<p>还没有账号？输入邮箱、密码即可完成注册并绑定</p>
					</div>

				</div>
				<div class="modal-footer" style="text-align: left;">
					<div class="form-group">
						<div class="form-action">
							<input class="btn btn-success" type="button" value="绑定账户"
								id="bind_btn" /> <input class="btn btn-info" type="button"
								value="注册并绑定账户" id="regist_btn" /> <input
								class="btn btn-primary" type="button" value="跳过直接登录"
								id="ingore_btn" style="float: right;" />
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
		function bind_fn(url) {
			var options = {
				url : url,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						location.href = '<c:url value='/index'/>';
					} else {
						var msg = data.message;
						if (msg == "404") {
							alert("系统错误，请重新登录");
							location.href = "<c:url value='/login-bind'/>";
						} else {
							$('#loginName').validationEngine('showPrompt',
									data.message, 'error');
						}
					}
				}
			};
			$("#bind_form").ajaxSubmit(options);
		}
		$("#bind_btn").click(function() {
			if (!$("#bind_form").validationEngine("validate")) {
				return false;
			}
			bind_fn("<c:url value='/login-bind'/>");
		});
		$("#regist_btn").click(function() {
			if (!$("#bind_form").validationEngine("validate")) {
				return false;
			}
			bind_fn("<c:url value='/regist-bind'/>");
		});
		$("#ingore_btn").click(
				function() {
					location.href = "<c:url value='/ingore-bind'/>"
							+ "?loginFrom=${loginFrom}&openId=${openId}";
				});
	</script>



</body>

</html>
