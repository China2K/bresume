<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>bresume - 简历创建</title>


<style type="text/css">
	
</style>
	

</head>
<%@include file="common/common.jsp" %>
<body id="page-top" class="index">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-shrink">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">B Resume</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">B Resume</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a href="#">简历模板</a>
                    </li>
                    <li>
                        <a href="">热门简历</a>
                    </li>
                    <li>
                        <a href="#">关于我们</a>
                    </li>
                    <li>
                        <a href="#">提供建议</a>
                    </li>
                    
                </ul>

				 <ul class="nav navbar-nav navbar-right">
                   
                    <li>
                        <a id="signinBtn" href="javascript:void(0);">登陆</a>
                    </li>
                    <li>
                        <a href="javascript:signin();">注册</a>
                    </li>
                   
                </ul>
            </div>

			
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>


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
					<div class="ih-item circle effect2 left_to_right"><a href="${template.siteUrl}">
					<div class="img"><img src="${template.coverUrl}" alt="img"></div>
					<div class="info">
					  <h3>${template.name}</h3>
					  <p>learn more</p>
					</div></a></div>
				  </div>
			</c:forEach>
		</div>
	  </div>
    </section>

   <%@include file="common/footer.jsp"%>
	<div id="backtotop"><div class="bttbg"></div></div>





   <!-- jQuery -->
    <script src="/portal/resource/site/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/portal/resource/site/js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="/portal/resource/site/js/jquery.easing.min.js"></script>
  

    <!-- Custom Theme JavaScript -->
    <script src="/portal/resource/site/js/agency.js"></script>
     <script src="/portal/resource/app/js/common.js"></script>
      <script src="/portal/resource/site/js/jquery.form.js"></script>

	<script type="text/javascript">


	</script>



</body>

</html>
