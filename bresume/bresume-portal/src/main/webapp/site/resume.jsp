<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bresume - 简历创建</title>


<link href="<c:url value ='/resource/site/css/bootstrap.min.css'/>"
	rel="stylesheet" media="screen">
<link href="<c:url value ='/resource/app/css/common.css'/>"
	rel="stylesheet">
<link
	href="<c:url value ='/resource/plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css'/>"
	rel="stylesheet" media="screen">

<!-- jQuery-Validation-Engine -->
<link rel="stylesheet"
	href="<c:url value ='/resource/plugin/jQuery-Validation-Engine/css/validationEngine.jquery.css'/>">

<style type="text/css">
</style>


</head>
<%@include file="common/common.jsp"%>
<body id="page-top" class="index">
	<%@include file="common/header.jsp"%>

	<div class="container wrap">

		<div class="row">
			<h4>简历创建只需3步</h4>
			<hr />
			<div class="progress_bar col-md-12">
				<div class="col-md-4 progress_bar-normal" id="progress_bar_1"
					onclick="goStep(1);">
					<span>1,选择模板</span>
				</div>

				<div class="col-md-4 progress_bar-normal" id="progress_bar_2"
					onclick="goStep(2);">
					<span>2,创建简历</span>
				</div>

				<div class="col-md-4 progress_bar-normal" id="progress_bar_3"
					onclick="goStep(3);">
					<span>3,编辑简历信息</span>
				</div>
			</div>
		</div>

		<div class="row">

			<div class="col-md-8" id="pro-area">
				<div class="progress" id="progress-bar-div">
					<div class="progress-bar progress-bar-striped" role="progressbar"
						aria-valuenow="${resume.score}" aria-valuemin="0"
						aria-valuemax="100" style="width: ${resume.score}%">
						<span class="sr-only">100% Complete</span>
					</div>
				</div>
			</div>

			<div class="col-md-4" id="step-control">
				<div class="col-md-4 text-center" id="last-step">
					<span>上一步</span>
				</div>

				<div class="col-md-8 text-center" id=next-step>
					<span>下一步</span>
				</div>
			</div>
		</div>
		<div class="row text-center" id="step-header">
			<br />
			<h3>选择模板</h3>
			<hr />
		</div>

		<!-- the followings are resume steps -->
		<div class="row resume_step" id="resume_step_1">
			<c:forEach items="${templates}" var="template">
				<div class="col-md-3">
					<div class="ih-item circle effect2 left_to_right">
						<a
							href="javascript:chooseTemplate('${template.sn}','${template.siteUrl}','${staticUrlPrefix}${template.coverUrl}')"
							class="acover">
							<div class="img">
								<img src="${staticUrlPrefix}${template.coverUrl}" alt="img">
							</div>
							<div class="info">
								<h3>${template.name}</h3>
								<p>选择模板</p>
							</div>
						</a>
					</div>

					<div>
						<a href="${template.siteUrl}"
							class="btn btn-default btn btn-primary btn-lg btn-block">查看详细</a>
					</div>
				</div>
			</c:forEach>

		</div>
		<div class="row resume_step" id="resume_step_2">
			<h4>简历信息</h4>
			<hr />
			<div class="col-md-3">
				<div class="box show-item">
					<div class="box-gray aligncenter">
						<div class="icon">
							<a href="${template.siteUrl}" id="template-select"><img
								src="${staticUrlPrefix}${template.coverUrl}" /></a>
						</div>
					</div>
					<div class="box-bottom">
						<a href="javascript:goStep(1);">换个模板</a>
					</div>
				</div>
			</div>

			<div class="col-md-9 box-gray aligncenter div-center" style="">

				<s:form class="form-horizontal" action="/resume/save"
					id="resumeForm" onsubmit="return false;" commandName="resume">
					<s:hidden path="id" id="resumeId" />
					<s:hidden path="templateSn" id="templateSn" />
					<div class="col-md-9">
						<div class="form-group">
							<label for="name" class="col-md-3 control-label">简历名称</label>
							<div class="col-md-9">
								<s:input class="form-control validate[required,maxSize[50]]"
									id="name" path="name" placeholder="请输入简历名称" />
							</div>
						</div>
						<div class="form-group">
							<label for="desc" class="col-md-3 control-label">一句话简介</label>
							<div class="col-md-9">
								<s:input class="form-control validate[required,maxSize[100]]"
									id="desc" path="desc" placeholder="请输入简历名称" />
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<div class="col-md-offset-3 col-md-9">
								<button type="submit" class="btn btn-default"
									onclick="submitResume();">保存</button>
							</div>
						</div>
					</div>
				</s:form>
			</div>
		</div>

		<div class="row resume_step" id="resume_step_3">
			<h4>简历详细信息</h4>
			<hr />
			<div class="col-md-3">
				<h5 class="text-center">基本栏目</h5>
				<ul class="list-group resume-items items-top">

					<c:forEach items="${defaultItems }" var="item">
						<li class="list-group-item ajaxPage" _required = "${item.required}" data-href="${item.sn}">${item.name}
							<a
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
					<c:forEach items="${extraItems}" var="item">
						<li class="list-group-item ajaxPage" _required = "${item.required}" data-href="${item.sn}">${item.name
							}
							<a
							class="btn btn-warning btn-sm ladda-button linkbutton addtolibfav"
							data-style="expand-right" title="" data-libid="38"
							data-original-title="添加此栏目"><span
								class="glyphicon glyphicon-plus"></span></a>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-md-9 item-form-container"></div>
		</div>
	</div>


	<%@include file="common/footer.jsp"%>
	<div id="backtotop">
		<div class="bttbg"></div>
	</div>



	<script type="text/javascript"
		src="<c:url value ='/resource/site/js/jquery.js'/>" charset="UTF-8"></script>

	<script type="text/javascript"
		src="<c:url value ='/resource/site/js/jquery.form.js'/>"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="<c:url value ='/resource/site/js/bootstrap.min.js'/>"></script>
	<script type="text/javascript"
		src="<c:url value ='/resource/plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js'/>"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="<c:url value ='/resource/plugin/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js'/>"
		charset="UTF-8"></script>


	<!-- Plugin JavaScript -->
	<script src="<c:url value ='/resource/site/js/jquery.easing.min.js'/>"></script>

	<!-- Custom Theme JavaScript -->
	<script src="<c:url value ='/resource/site/js/agency.js'/>"></script>

	<script src="<c:url value ='/resource/app/js/common.js'/>"></script>


	<!-- jQuery-Validation-Engine -->
	<script
		src="<c:url value ='/resource/plugin/jQuery-Validation-Engine/js/jquery.validationEngine-zh_CN.js'/>"></script>
	<script
		src="<c:url value ='/resource/plugin/jQuery-Validation-Engine/js/jquery.validationEngine.min.js'/>"></script>



	<script type="text/javascript">
		//item add & remove
		$(".resume-items li a.linkbutton").click(function(event) {

			var sn = $(this).parent().attr("data-href");
			if ($(this).children("span").hasClass("glyphicon-minus")) {
				var required = $(this).parent().attr("_required");
				if(required==true||required=="true"){
					alert("该选项必填，不可被移除");
				}else{
					$(".items-down").append($(this).parent());
					$(this).children("span").removeClass("glyphicon-minus");
					$(this).children("span").addClass("glyphicon-plus");

					item_bar_remove(sn);
				}

				
			} else {
				$(".items-top").append($(this).parent());
				$(this).children("span").removeClass("glyphicon-plus");
				$(this).children("span").addClass("glyphicon-minus");

				item_bar_add(sn);
			}
			event.stopPropagation();

		});

		function item_bar_add(itemSn) {
			var resumeID = $("#resumeId").val();
			var url = "<c:url value ='/resume/addItem'/>";
			var data = {
				resumeId : resumeID,
				itemSn : itemSn
			}
			$.ajax({
				type : "post",
				url : url,
				data : data,
				async : false,
				cache : false,
				dataType : "json",
				error : function(request) {
				},
				success : function(data) {
				}
			});

		}

		function item_bar_remove(itemSn) {
			var resumeID = $("#resumeId").val();
			var url = "<c:url value ='/resume/removeItem'/>";
			var data = {
				resumeId : resumeID,
				itemSn : itemSn
			}
			$.ajax({
				type : "post",
				url : url,
				data : data,
				async : false,
				cache : false,
				dataType : "json",
				error : function(request) {
				},
				success : function(data) {
				}
			});
		}
		//*********************************************8
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

		function submitEdu() {
			$("#eduForm").ajaxSubmit(function(data) {
				if (data.success) {
				}
			});
		}

		var currentStep;
		var resumeStep = 2;
		var iniStep = '${step}';
		if (iniStep != null && iniStep != "") {
			goStep(iniStep * 1);
		} else {
			goStep(1);
		}
		function goStep(step) {

			if (step == "undefined" || step == null || step == "") {
				step = 1;
			}

			if (currentStep == step || (currentStep < 1) || (currentStep > 3)) {
				return;
			}

			step = step * 1;
			if (step > resumeStep) {
				if ($("#resumeId").val() == null || $("#resumeId").val() == '') {
					alert("请先完成前两步");
					return false;
				}
			}

			if (step == 2) {
				var sn = $("#resume_step_2 #templateSn").val();
				if (sn == null || sn == "") {
					alert("请先选择模版");
					goStep(1);
					return false;
				} else {
					if ($("#resumeId").val() == null
							|| $("#resumeId").val() == '') {
						setProcessBar(25);
					}
				}
			} else if (step == 3) {
				setProcessBarDynamic($("#resumeId").val());
			}

			var contentId = "#resume_step_" + step;
			var barId = "#progress_bar_" + step;

			$(".progress_bar div").removeClass("progress_bar-active");
			$(barId).addClass("progress_bar-active");

			$(".resume_step").css("display", "none");
			$(contentId).css("display", "block");

			$("#step-header h3").text($(barId + " span").text());
			currentStep = step;
		}

		$("#last-step").click(function() {
			if (currentStep == 1) {
				return;
			}
			goStep(currentStep - 1);
		});

		$("#next-step").click(function() {
			if (currentStep == 3) {
				return;
			}
			goStep(currentStep + 1);
		});

		function chooseTemplate(sn, siteUrl, coverUrl) {
			$("#resume_step_2 #template-select").attr("href", siteUrl);
			$("#resume_step_2 #template-select img").attr("src", coverUrl);
			$("#resume_step_2 #templateSn").val(sn);
			goStep(2);
		}

		function submitResume() {

			if (!$("#resumeForm").validationEngine("validate")) {
				return false;
			}
			$("#resumeForm").ajaxSubmit(
					function(data) {
						if (data.success) {
							if ($("#resumeId").val() == null
									|| $("#resumeId").val() == "") {
								$("#resumeId").val(data.id);
							}

							alert("保存成功");
							goStep(3);
						} else {
							alert(data.message);
						}
					});

		}

		//jquery validation
		//$("#resumeForm").validationEngine();

		//菜单 点击后， 局部刷新 主要内容部分 _mainContent

		$("li.ajaxPage").click(
				function(event) {

					var _mainContent = $("div.item-form-container");

					//console.log($(this).children("a"));
					var isextra = $(this).children("a").children("span")
							.hasClass("glyphicon-plus");
					if (isextra === true) {
						return;
					}
					var a = $(this);
					//取消 默认的点击事件 否则跳转页面
					event.preventDefault();
					var url = "/resume/resumeItem.do?itemSn=";
					url = url + a.attr("data-href");
					var resumeId = $("#resumeForm #resumeId").val();
					if (resumeId == null || resumeId == '') {
						alert("请先完成前两步！");
						goStep(1);
					}
					url = url + "&id=" + resumeId;
					$.ajax({
						type : "GET",
						url : url,
						async : false,
						cache : false,
						dataType : "html",
						error : function(request) {
							if (request.status == "747") {
								window.location.href = request
										.getResponseHeader('loginUrl');
							} else {
								alert("跳转页面出现错误");
							}
						},
						success : function(data) {
							//会寻找返回的HTML中是否 存在fullpage:true的注释，如果存在则整张页面改变（body里面内容替换为响应返回的html）
							//不想整张页面变化，则不需要别的操作
							if (data.indexOf("<!--fullpage:true-->") >= 0) {
								//整个页面改变
								$("body").html(data);
							} else {
								//局部页面填充，改变 class=mainCont的内部
								_mainContent.html(data);
								//改变点击样式 TODO

							}
						}
					});
				});

		function setProcessBarDynamic(id) {
			$.ajax({
				type : "POST",
				url : "/resume/getScore",
				data : {
					id : id
				},
				dataType : "json",
				success : function(resp) {
					setProcessBar(resp.data);
				}

			});

		}

		function setProcessBar(proportion) {
			$("#progress-bar-div .progress-bar").attr("aria-valuenow",
					proportion);

			$("#progress-bar-div .progress-bar").css("width", proportion + "%");
		}
	</script>

</body>

</html>
