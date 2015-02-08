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

<link href="<c:url value='/resource/app/css/settings.css'/>"
	rel="stylesheet">
<style type="text/css">
.right-dashed {
	border-right: 1px dashed #ccc;
}
</style>


</head>
<%@include file="common/common.jsp"%>
<body id="page-top" class="">
	<%@include file="common/header.jsp"%>

	<div class="container wrap settings">

		<div class="col-md-8 main col-md-offset-3">
			<div class="row">
				<div class="col-lg-12">
					<h2 class="section-heading">账户信息</h2>

				</div>
			</div>

			<div class="row">
				<h3 class="section_title">
					<i class="fa fa-envelope"></i>设置邮箱
				</h3>
				<form accept-charset="UTF-8" action="/user/email" id="email_form"
					onsubmit="return false;" class="simple_form form-horizontal"
					method="post">
					<c:choose>
						<c:when test="${email==null || email==''}">
							<div class="form-group email required user_email row">
								<label class="col-md-3 control-label" for="user_email">邮箱
									<abbr title="required">*</abbr>
								</label>
								<div class="input-group col-md-5">
									<span class="input-group-addon"><i
										class="fa fa-envelope"></i></span><input autofocus="autofocus"
										value="${email}"
										class="form-control validate[required,custom[email]]"
										id="email_" name="email" placeholder="邮箱地址"
										required="required">
								</div>
							</div>
							<div class="form-group">
								<div class="form-action col-md-offset-3">
									<input class="btn btn-info" type="submit"
										onclick="submitEmail();" value="更新">
								</div>
							</div>

						</c:when>
						<c:otherwise>
							<div class="form-group email required user_email row">
								<label class="col-md-3 control-label" for="user_email">邮箱
									<abbr title="required">*</abbr>
								</label>
								<div class=" col-md-5">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="fa fa-envelope"></i></span><input autofocus="autofocus"
											value="${email}" disabled="disabled" class="form-control"
											id="email_" name="email" placeholder="邮箱地址"
											required="required">
									</div>
								</div>
								<c:if test="${ reActive=='true'}">
									<div class="col-md-2 text-center">
										<span style="color: #999999">未激活</span>
									</div>
									<div class="col-md-2">
										<button id="reEmail" class="btn btn-info">重发激活邮件</button>
									</div>
								</c:if>


							</div>
						</c:otherwise>
					</c:choose>


				</form>
			</div>
			<div class="row">
				<h3 class="section_title">
					<i class="fa fa-key"></i>修改密码
				</h3>
				<form accept-charset="UTF-8" action="/user/updatePWD"
					onsubmit="return false;" id="upt_pwd_form"
					class="simple_form form-horizontal" id="setting-password"
					method="post">
					<div class="form-group">
						<label class="col-md-3 control-label" for="user_current_password">当前密码
							<abbr title="required">*</abbr>
						</label>

						<div class="input-group col-md-5">
							<span class="input-group-addon"><i class="fa fa-key"></i></span><input
								class="form-control validate[required,minSize[6],maxSize[30]]"
								id="user_current_password" name="password"
								placeholder="请输入当前设置的密码" required="required" type="password">
						</div>

					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="user_password">密码</label>

						<div class="input-group col-md-5">
							<span class="input-group-addon"><i class="fa fa-key"></i></span><input
								class="form-control validate[required,minSize[6],maxSize[30]]"
								id="new_password" name="new_password" placeholder="密码至少 6 位以上"
								required="required" type="password">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">密码确认</label>

						<div class="input-group col-md-5">
							<span class="input-group-addon"><i class="fa fa-key"></i></span><input
								class="form-control validate[required,minSize[6],maxSize[30]]"
								id="user_password_confirmation" placeholder="重复新密码"
								required="required" type="password">
						</div>

					</div>
					<div class="form-group">
						<div class="form-action col-md-offset-3">
							<input class="btn btn-default btn btn-primary"
								onclick="upt_pwd();" type="submit" value="设置密码">
						</div>
					</div>
				</form>
			</div>
			<div class="row">
				<h3 class="section_title">
					<i class="fa fa-group"></i>第三方登录
				</h3>
				<div class="clearfix">
					<table id="table_oauth">
						<thead>
							<tr>
								<th>应用</th>
								<th>状态</th>
								<th>操作</th>
								<th></th>
							</tr>
						</thead>
						<tbody>

							<c:choose>
								<c:when test="${sina==true}">
									<tr>
										<td><i class="fa fa-weibo"></i>微博</td>
										<td class="text-success">已绑定</td>
										<td><a class="btn btn-danger" data-confirm="真的要解除绑定么？"
											data-method="delete" href="/settings/remove-bind/2"
											rel="nofollow">解绑</a></td>
										<td><a class="btn btn-link" href="/sinalogin">刷新授权</a></td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td><i class="fa fa-weibo"></i>微博</td>
										<td class="text-danger">未绑定</td>
										<td><a class="btn btn-primary" href="/sinalogin">绑定</a></td>
									</tr>
								</c:otherwise>
							</c:choose>


							<c:choose>
								<c:when test="${qq==true}">
									<tr>
										<td><i class="fa fa-qq"></i>QQ</td>
										<td class="text-success">已绑定</td>
										<td><a class="btn btn-danger" data-confirm="真的要解除绑定么？"
											data-method="delete" href="/settings/remove-bind/1"
											rel="nofollow">解绑</a></td>
										<td><a class="btn btn-link" href="/qqlogin">刷新授权</a></td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td><i class="fa fa-qq"></i>QQ</td>
										<td class="text-danger">未绑定</td>
										<td><a class="btn btn-primary" href="/qqlogin">绑定</a></td>
									</tr>
								</c:otherwise>
							</c:choose>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<br />
	<br />
	<br />

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
		function submitEmail() {
			if (!$("#email_form").validationEngine("validate")) {
				return;
			}

			$("#email_form").ajaxSubmit(
					function(data) {
						if (data.success) {
							location.reload();
						} else {
							$('#email_').validationEngine('showPrompt',
									data.message, 'error');
						}
					});
		}

		function upt_pwd() {

			if (!$("#upt_pwd_form").validationEngine("validate")) {
				return false;
			}

			var a = $("#new_password").val();
			var b = $("#user_password_confirmation").val();

			if (a === b == false) {
				$('#user_password_confirmation').validationEngine('showPrompt',
						"两次密码输入不一致", 'error');
				return false;
			}

			$("#upt_pwd_form").ajaxSubmit(
					function(data) {
						alert(data.message);
						if (data.success) {
							location.reload();
						} else {
							$('#new_password').validationEngine('showPrompt',
									data.message, 'error');
						}
					});
		}

		$("#reEmail").click(function() {
			$.ajax({
				url : "/user/reEmail",
				type : "POST",
				dataType : "json",
				success : function(resp) {
					alert(resp.message);
					$("#reEmail").attr("disabled", true);
					setTimeout(function() {
						$("#reEmail").removeAttr("disabled");
					}, 60000);
				}
			});
		});
	</script>



</body>

</html>
