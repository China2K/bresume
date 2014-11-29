<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>bresume - 简历创建</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/agency.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

 <!-- Custom CSS for template -->
	<link rel="stylesheet" href="./About_files/css/stuck.css">
	<link rel="stylesheet" href="./About_files/css/ihover.css">
<style type="text/css">
	


</style>
	

</head>

<body id="page-top" class="index">



	<div class="container wrap">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h2 class="section-heading">我的简历</h2>
				
			</div>
		</div>

       <section style="">
	    <div class="panel-heading" style="background-color: #f5f5f5;height:45px;border-color: #ddd;">
			
		</div>
	    <div class="row resumeRow">

			<div class="col-md-3" style="background-color: #f9845b;height:250px;">
				<img class="img-rounded img-responsive center-block" src="http://image.knewone.com/photos/39cefafbe811a9ec9818aafe74d237b4.jpg!thing.fixed" data-pinit="registered">
			</div>

			<div class="col-md-6" style="background-color: #fff;height:250px;">
				
				<div class="row text-left">
					<h4 class="librarytitle">
						<a href="#">CSS3基础课程 Box-Shadow 阴影</a>
					</h4>
				</div>
				<div class="row text-left">
					<span class="glyphicon glyphicon-stats"> 入门 &nbsp;|&nbsp; 入门</span> 
				</div>
				
				<div class="row text-left">
					<span class="glyphicon glyphicon-time" style="margin-right:50px;">2014年10月27日</span> 
				</div>
				<div class="row text-center">
					<div class="col-md-4 text-left"><span>将我的简历分享到 </span></div>
					<div class="col-md-8">
						<ul class="list-inline social-buttons">
							<li><a href="#"><i class="fa fa-twitter"></i></a>
							</li>
							<li><a href="#"><i class="fa fa-facebook"></i></a>
							</li>
							<li><a href="#"><i class="fa fa-linkedin"></i></a>
							</li>
							<li><a href="#"><i class="fa fa-qq"></i></a>
							</li>
							<li><a href="#"><i class="fa fa-weibo"></i></a>
							</li>
						</ul>
					</div>
				</div>
				
				
			</div>

			<div class="col-md-3 actions" style="background-color: #3079ab;height:250px;filter:alpha(opacity=50);">
				<div class="col-md-6" style="height:125px;border-right: 1px dashed #eeeeee;border-bottom: 1px dashed #eeeeee;">
				<a href="#">
					<img class="img-rounded img-responsive center-block" src="http://www.gbtags.com/gb/networks/gblibicon/css3.png" style="margin-top:15px;">
				</a>
				</div>
				<div class="col-md-6" style="height:125px;border-bottom: 1px dashed #eeeeee;">
					<a href="#">
					<img class="img-rounded img-responsive center-block" src="http://www.gbtags.com/gb/networks/gblibicon/css3.png" style="margin-top:15px;">
				</a>
				</div>
				<div class="col-md-6" style="height:125px;border-right: 1px dashed #eeeeee;">
					<a href="#">
					<img class="img-rounded img-responsive center-block" src="http://www.gbtags.com/gb/networks/gblibicon/css3.png" style="margin-top:15px;">
				</a>
				</div>
				<div class="col-md-6" style="height:125px;">
					<a href="#">
					<img class="img-rounded img-responsive center-block" src="http://www.gbtags.com/gb/networks/gblibicon/css3.png" style="margin-top:15px;">
				</a>
				</div>
			</div>
		</div>
		

	
	   </section>
	</div>

    <footer>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <span class="copyright">Copyright &copy; bresum.com 2014</span>
                </div>
                <div class="col-md-4">
                    <ul class="list-inline social-buttons">
                        <li><a href="#"><i class="fa fa-twitter"></i></a>
                        </li>
                        <li><a href="#"><i class="fa fa-facebook"></i></a>
                        </li>
                        <li><a href="#"><i class="fa fa-linkedin"></i></a>
                        </li>
						<li><a href="#"><i class="fa fa-qq"></i></a>
						</li>
						<li><a href="#"><i class="fa fa-weibo"></i></a>
						</li>
                    </ul>
                </div>
                <div class="col-md-4">
                   
                </div>
            </div>
        </div>
    </footer>

	<div id="backtotop"><div class="bttbg"></div></div>


<div class="modal fade in" id="login-modal" aria-hidden="false" 
	style="top:40px;display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<ul class="nav nav-pills nav-pills-list nav-justified">
					<li class="active"><a data-toggle="tab"
						href="#email_sign_in_tab">登录<span>Sign In</span></a></li>
					<li class=""><a data-toggle="tab" href="#email_sign_up_tab">注册<span>Sign
								Up</span></a></li>
				</ul>
			</div>
			<div class="modal-body">
				<div class="tab-content">
					<div class="tab-pane active" id="email_sign_in_tab">
						<form accept-charset="UTF-8"
							action="/users/sign_in?redirect_from=%2F"
							class="simple_form new_user" data-remote="true" id="new_user"
							method="post">
							<div style="display: none">
								<input name="utf8" type="hidden" value="✓">
							</div>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-envelope"></i></span><input autofocus="autofocus"
										class="form-control" id="user_email" name="user[email]"
										placeholder="邮箱地址" required="required" type="email">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-key"></i></span><input
										class="form-control" id="user_password" name="user[password]"
										placeholder="密码" required="required" type="password"><input
										id="redirect_from" name="redirect_from" type="hidden">
								</div>
							</div>
							<div class="form-group addon">
								
								<div class="input-group checkbox col-md-10" id="remember_me">
									<label>
										<input name="user[remember_me]" type="hidden"
											value="0"><input checked="checked"
											id="user_remember_me" name="user[remember_me]"
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
										name="commit" type="submit" value="用邮箱登录">
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
						<form accept-charset="UTF-8" action="/users"
							class="simple_form new_user" data-remote="true" id="new_user"
							method="post">
							<div style="display: none">
								<input name="utf8" type="hidden" value="✓">
							</div>
							<div class="form-group name_group">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-user"></i></span><input
										class="form-control" id="user_name" name="user[name]"
										placeholder="昵称" required="required" type="text" value="">
								</div>
							</div>
							<div class="form-group email_group">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-envelope"></i></span><input class="form-control"
										id="user_email" name="user[email]" placeholder="邮箱地址"
										required="required" type="email">
								</div>
							</div>
							<div class="form-group password_group">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-key"></i></span><input
										class="form-control" id="user_password" name="user[password]"
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
										name="commit" type="submit" value="用邮箱注册">
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
							<li><a href="#"><i class="fa fa-qq"></i></a>
							</li>
							<li><a href="#"><i class="fa fa-weibo"></i></a>
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


    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="js/classie.js"></script>
    <script src="js/cbpAnimatedHeader.js"></script>

    <!-- Contact Form JavaScript -->
    <script src="js/jqBootstrapValidation.js"></script>
    <script src="js/contact_me.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/agency.js"></script>
	<script type="text/javascript">


	</script>



</body>

</html>
