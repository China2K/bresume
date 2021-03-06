<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-shrink"
	style="background-color: #222;">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header page-scroll">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">B Resume</span> <span class="icon-bar"></span>
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand page-scroll" href="<c:url value='/index'/>">B
				Resume</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="hidden"><a href="#page-top"></a></li>
				<li><a href="<c:url value='/templates'/>">简历模板</a></li>
				<li><a href="<c:url value='/resumes'/>">热门简历</a></li>
				<li><a href="#">关于我们</a></li>
				<li><a href="#">提供建议</a></li>

			</ul>

			<ul class="nav navbar-nav navbar-right" id="user_links">
				<c:choose>
					<c:when
						test="${sessionScope.loginUser!=null && sessionScope.loginUser.icon!=null}">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">${sessionScope.loginUser.nickName }</a>
							<ul class="dropdown-menu" role="menu"
								style="background: none repeat scroll 0% 0% #3D3F40;">
								<li><a href="<c:url value='/resume/mine'/>">管理我的简历</a></li>
								<li><a href="<c:url value='/user/settings'/>">账户设置</a></li>
								<li class="divider"></li>
								<li><a href="<c:url value='/user/logout'/>">安全退出</a></li>
							</ul></li>
					</c:when>
					<c:otherwise>
						<li><a id="loginBtn" href="javascript:void(0);">登录</a></li>
						<li><a id="signupBtn" href="javascript:void(0);">注册</a></li>
					</c:otherwise>
				</c:choose>

			</ul>





		</div>


		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>