<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>

<link href="/portal/resource/site/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="/portal/resource/app/css/common.css" rel="stylesheet">
<link
	href="/portal/resource/site/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">

<s:form class="form-horizontal item-form" action="/portal/edu/save" onsubmit="return false;" commandName="projectExperience" >
	<div class="row form-group">
	
		<label for="desc" class="col-md-3 control-label">项目名称</label>
		<div class="col-md-9">
			<s:input path="projectName" class="form-control"/>
		</div>
	</div>
	<div class="row form-group">

			<label for="desc" class="col-md-3 control-label">时间</label>
			<div class="input-group date form_date col-md-3 f-left" data-date=""
				data-date-format="dd MM yyyy" data-link-field="date_start"
				data-link-format="yyyy-mm-dd">
				<input class="form-control" size="16" type="text" value="" readonly>
				<span class="input-group-addon"><span
					class="glyphicon glyphicon-calendar"></span></span>
			</div>
			<s:hidden id="date_start" path="startDate" />
			<div class="col-md-1 text-center f-left">&nbsp;-&nbsp;</div>
			<div class="input-group date form_date col-md-3 f-left" data-date=""
				data-date-format="dd MM yyyy" data-link-field="date_end"
				data-link-format="yyyy-mm-dd">
				<input class="form-control" size="16" type="text" value="" readonly>
				<span class="input-group-addon"><span
					class="glyphicon glyphicon-calendar"></span></span>
			</div>

			<s:hidden id="date_end" path="endDate" />
		</div>

	<div class="row form-group">
	
		<label for="desc" class="col-md-3 control-label">项目描述</label>
		<div class="col-md-9">
			<s:textarea class="form-control" id="projectDesc"
				placeholder="不超过20个字符" path="projectDesc"/>
		</div>
	</div>

	<div class="row">
		<label for="desc" class="col-md-3 control-label">责任描述</label>
		<div class="col-md-9">
			<s:textarea class="form-control" id="respDesc"
				placeholder="不超过20个字符" path="respDesc"/>
		</div>
	</div>


	<div class="form-group">
		<div class="col-md-offset-3 col-md-9">
			<button type="submit" class="btn btn-default">保存</button>
		</div>
	</div>
</s:form>


<script type="text/javascript" src="/portal/resource/site/js/jquery.js"
	charset="UTF-8"></script>

<script type="text/javascript"
	src="/portal/resource/site/js/jquery.form.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="/portal/resource/site/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="/portal/resource/site/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="/portal/resource/site/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"
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