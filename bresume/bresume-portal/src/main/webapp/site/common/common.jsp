<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>


    <!-- Bootstrap Core CSS -->
    <link href="<c:url value='/resource/site/css/bootstrap.min.css'/>" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value='/resource/site/css/agency.css'/>" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:url value='/resource/site/font-awesome-4.1.0/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css">

 <!-- Custom CSS for template -->
	<link rel="stylesheet" href="<c:url value='/resource/site/About_files/css/stuck.css'/>">
	<link rel="stylesheet" href="<c:url value='/resource/site/About_files/css/ihover.css'/>">
	<link rel="stylesheet" href="<c:url value='/resource/app/css/common.css'/>">
	
	<script type="text/javascript" src="<c:url value ='/resource/site/js/jquery.js'/>"
		charset="UTF-8"></script>
		
	<!-- jQuery-Validation-Engine -->
	<link rel="stylesheet"
		href="<c:url value ='/resource/plugin/jQuery-Validation-Engine/css/validationEngine.jquery.css'/>">
	<script
		src="<c:url value ='/resource/plugin/jQuery-Validation-Engine/js/jquery.validationEngine-zh_CN.js'/>"></script>
	<script
		src="<c:url value ='/resource/plugin/jQuery-Validation-Engine/js/jquery.validationEngine.min.js'/>"></script>
    
    
<div class="modal fade in" id="login-modal" aria-hidden="false" 
	style="top:40px;display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<ul class="nav nav-pills nav-pills-list nav-justified">
					<li class="active"><a data-toggle="tab"  id="email_sign_in_header"
						href="#email_sign_in_tab">登录<span>Sign In</span></a></li>
					<li class=""><a data-toggle="tab" id="email_sign_up_header" href="#email_sign_up_tab">注册<span>Sign
								Up</span></a></li>
				</ul>
			</div>
			<div class="modal-body">
				<div class="tab-content">
					<div class="tab-pane active" id="email_sign_in_tab">
						<form accept-charset="UTF-8"
						action="<c:url value='/user/login'/>" onsubmit="return false;"
							class="simple_form new_user" data-remote="true" id="login_form"
							method="post">
							<div style="display: none">
								<input name="utf8" type="hidden" value="✓">
							</div>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-envelope"></i></span><input autofocus="autofocus"
										class="form-control validate[required,custom[email],minSize[6],maxSize[100]]" name="loginName"
										placeholder="邮箱地址" required="required">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-key"></i></span><input
										class="form-control validate[required,minSize[6],maxSize[100]]" id="user_password" name="password"
										placeholder="密码" required="required" type="password"><input
										id="redirect_from" name="redirect_from" type="hidden">
								</div>
							</div>
							<div class="form-group addon">
								
								<div class="input-group checkbox col-md-10" id="remember_me">
									<label>
										<input checked="checked"
											id="user_remember_me" name="remember_me"
											type="checkbox" value="1">记住我
										</label>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="/users/password/new">
											<i class="fa fa-question-circle"></i>忘记密码？
										</a>
								</div>
								
							</div>
							<div class="form-group">
								<div class="form-action">
									<input class="btn btn-default btn btn-primary btn-lg btn-block"
										name="commit" type="button" value="用邮箱登录" onclick="login();">
								</div>
							</div>
							<div class="form-group hidden" id="sign_in_alert_field">
								<div class="form-result">
									<div class="alert alert-danger">用户名或密码错误，请重新输入。</div>
								</div>
							</div>
						</form>
					</div>
					<div class="tab-pane" id="email_sign_up_tab">
						<form accept-charset="UTF-8" onsubmit="return false;" action="user/register"
							class="simple_form new_user" data-remote="true" id="register_form"
							method="post">
							<div style="display: none">
								<input name="utf8" type="hidden" value="✓">
							</div>
							<%--  <div class="form-group name_group">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-user"></i></span><input
										class="form-control validate[required,maxSize[20],custom[userNameRegx]]" id="user_name" name="userName"
										placeholder="用户名" required="required" type="text" value="">
								</div>
							</div> --%>
							<div class="form-group email_group">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-envelope"></i></span><input class="form-control validate[required,custom[email],minSize[6],maxSize[100]]"
										id="user_email" name="email" placeholder="邮箱地址"
										required="required" type="email">
								</div>
							</div>
							<div class="form-group password_group">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-key"></i></span><input
										class="form-control validate[required,minSize[6],maxSize[100]]" id="user_password" name="password"
										placeholder="密码" required="required" type="password">
								</div>
							</div>
							<div class="checkbox">
								<label><input checked="checked" id="user_agreement"
									name="agreement" type="checkbox" value="true">同意<a
									href="/user_agreement" target="_blank">《bresume 用户协议》</a></label>
							</div>
							<input id="path" name="path" type="hidden" value="/"><input
								id="redirect_from" name="redirect_from" type="hidden">
							<div class="form-group">
								<div class="form-action">
									<input class="btn btn-default btn btn-primary btn-lg btn-block"
										name="commit" type="button" value="用邮箱注册" onclick="register();">
								</div>
							</div>
							<div class="form-group hidden" id="sign_up_alert_field">
								<div class="form-result">
									<div class="alert alert-danger"></div>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="social_sign_in row">

					<div class="col-md-4">
						<span style="line-height: 40px;">社交网络账号直接登陆<i class="fa fa-caret-right"></i></span>
					</div>
					<div class="col-md-4">
						<ul class="list-inline social-buttons" style="text-align: center;">
							<li><a href="<c:url value='/qqlogin'/>"><i class="fa fa-qq"></i></a>
							</li>
							<li><a href="<c:url value='/sinalogin'/>"><i class="fa fa-weibo"></i></a>
							</li>
						</ul>
					</div>
					<div class="col-md-4"></div>

				</div>
			</div>
			<div class="modal-footer">
				<button data-dismiss="modal" type="button">
					<i class="fa fa-chevron-up"></i>
				</button>
			</div>
		</div>
	</div>
</div>

	<script type="text/javascript">
		function login(){
			if (!$("#login_form").validationEngine("validate")) {
				return false;
			}
				$("#login_form").ajaxSubmit(
					function(data) {
						if (data.success) {
							$("#login-modal").hide();
							location.reload();
						}else{
							alert(data.message);
						}
					}
				);
		}
		
		$("#register_form").validationEngine();
		$("#login_form").validationEngine();
		
		function register(){
				if (!$("#register_form").validationEngine("validate")) {
					return false;
				}
				$("#register_form").ajaxSubmit(
					function(data) {
						if (data.success) {
							alert("注册成功,激活邮件已发送,请验证!");
						}else{
							alert(data.message);
						}
					}
				);
		}


		function reloadUserLinks(){
			
			}

	</script>
