<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bresume - 比简历</title>


<link href="/portal/resource/site/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="/portal/resource/app/css/common.css" rel="stylesheet">
<link
	href="/portal/resource/plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">

<style type="text/css">

/* box */
.box-bottom {
	width: 180px;
	background: #fec503;
	text-align: center;
}

.show-item {
	
}

.show-item img {
	width: 180px;
	height: 180px;
	border: 1px solid #ddd;
}

.box-bottom a {
	width: 180px;
	line-height: 35px;
	font-family: "Roboto Slab", "Helvetica Neue", Helvetica, Arial,
		sans-serif;
	font-size: 14px;
	color: white;
	font-weight: 700;
	text-align: center;
}

.box-bottom a:hover {
	color: #eee;
	text-decoration: none;
}

.div-center {
	
}

media ="screen"
.ladda-button {
	-webkit-transition: .3s cubic-bezier(0.175, 0.885, 0.32, 1.275) all
		!important;
	-moz-transition: .3s cubic-bezier(0.175, 0.885, 0.32, 1.275) all
		!important;
	-ms-transition: .3s cubic-bezier(0.175, 0.885, 0.32, 1.275) all
		!important;
	-o-transition: .3s cubic-bezier(0.175, 0.885, 0.32, 1.275) all
		!important;
	transition: .3s cubic-bezier(0.175, 0.885, 0.32, 1.275) all !important;
}
</style>


</head>
<%-- <%@include file="common/common.jsp"%> --%>
<body id="page-top" class="index">
	<%-- <%@include file="common/header.jsp"%> --%>

	<div class="container wrap">

		<div class="row">
			<h4>简历信息</h4>
			<hr />
			<div class="col-md-3">
				<div class="box show-item">
					<div class="box-gray aligncenter">
						<div class="icon">
							<a href="${template.siteUrl}"><img src="${template.coverUrl}" /></a>
						</div>
					</div>
					<div class="box-bottom">
						<a href="#">换个模板</a>
					</div>
				</div>
			</div>

			<div class="col-md-9 box-gray aligncenter div-center" style="">

				<form class="form-horizontal" role="form">
					<div class="col-md-9">
						<div class="form-group">
							<label for="name" class="col-md-3 control-label">简历名称</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="name"
									placeholder="请输入简历名称">
							</div>
						</div>
						<div class="form-group">
							<label for="desc" class="col-md-3 control-label">一句话简介</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="lastname"
									placeholder="不超过20个字符">
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<div class="col-md-offset-3 col-md-9">
								<button type="submit" class="btn btn-default">保存</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="row">
			<h4>信息完整度</h4>
			<hr />
			<div class="col-md-12">
				<div class="progress">
					<div class="progress-bar progress-bar-striped" role="progressbar"
						aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
						style="width: 60%">
						<span class="sr-only">100% Complete</span>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<h4>简历详细信息</h4>
			<hr />
			<div class="col-md-3">
				<h5 class="text-center">基本栏目</h5>
				<ul class="list-group resume-items items-top">

					<c:forEach items="${allResumeItems }" var="item">
						<li class="list-group-item ajaxPage"
							data-href="/portal/resume/resumeItem?itemSn=${item.sn}">${item.name
							} <a
							class="btn btn-warning btn-sm ladda-button linkbutton addtolibfav"
							data-style="expand-right" title="" data-libid="38"
							data-original-title="去除此栏目"><span
								class="glyphicon glyphicon-minus"></span></a>
						</li>

					</c:forEach>
				</ul>
				<span>附加栏目</span>
				<hr />
				<ul class="list-group resume-items items-down">
				
					<li class="list-group-item ajaxPage">Cras justo odio <a
						class="btn btn-warning btn-sm ladda-button linkbutton addtolibfav"
						data-style="expand-right" title="" data-libid="38"
						data-original-title="添加此栏目"><span
							class="glyphicon glyphicon-plus"></span></a>
					</li>
				</ul>
			</div>
			<div class="col-md-9 item-form-container"></div>
		</div>
	</div>


	<%-- <%@include file="common/footer.jsp"%> --%>
	<div id="backtotop">
		<div class="bttbg"></div>
	</div>


	<script src="/portal/resource/site/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="/portal/resource/site/js/bootstrap.min.js"></script>

	<script src="/portal/resource/app/js/common.js"></script>




	<script type="text/javascript">
		$(".resume-items li a.linkbutton").click(function(event) {
			if($(this).children("span").hasClass("glyphicon-minus")){
				
				$(".items-down").append($(this).parent());
				$(this).children("span").removeClass("glyphicon-minus");
				$(this).children("span").addClass("glyphicon-plus");
			}else{
				$(".items-top").append($(this).parent());
				$(this).children("span").removeClass("glyphicon-plus");
				$(this).children("span").addClass("glyphicon-minus");
			}
			event.stopPropagation();
			
		});

		
	</script>

<script type="text/javascript" src="/portal/resource/site/js/jquery.js"
	charset="UTF-8"></script>

<script type="text/javascript"
	src="/portal/resource/site/js/jquery.form.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="/portal/resource/site/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="/portal/resource/plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="/portal/resource/plugin/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"
	charset="UTF-8"></script>

<script type="text/javascript">
	$('.form_date').datetimepicker({
		language : 'zh-CN',
		format : "yyyy-mm-dd",
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		forceParse : 0,
		pageType : 'child'
	});


	function submitEdu(){
			$("#eduForm").ajaxSubmit(
				function(data) {
					if (data.success) {
						alert(1);
					}
				}
			);
	}

	
</script>

</body>

</html>
