<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>

<link
	href="/portal/resource/plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">

<c:forEach items="${items}" var="eduExperience" varStatus="status">
	<s:form class="form-horizontal item-form" id="eduForm_${status.index}" method="post"
		action="/portal/edu/save" onsubmit="return false;"
		modelAttribute="eduExperience">
		<s:hidden path="resume.id" />
		<fieldset>
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

				<label for="desc" class="col-md-3 control-label">学校</label>
				<div class="col-md-9">
					<s:input path="schoolName" id="schoolName" class="form-control"
						placeholder="不超过20个字符" />
				</div>

			</div>
			<div class="row form-group">

				<label for="desc" class="col-md-3 control-label">专业</label>
				<div class="col-md-9">
					<s:input path="majorName" id="majorName" class="form-control"
						placeholder="不超过20个字符" />
				</div>

			</div>
			<div class="row form-group">

				<label for="desc" class="col-md-3 control-label">学历</label>
				<div class="col-md-9">
					<s:select path="degree" class="form-control" id="degree">
						<s:option value="1">专科</s:option>
						<s:option value="2">本科</s:option>
						<s:option value="3">硕士</s:option>
						<s:option value="4">博士</s:option>
					</s:select>

				</div>

			</div>

			<div class="row form-group">

				<label for="desc" class="col-md-3 control-label">描述</label>
				<div class="col-md-9">
					<s:textarea path="desc" id="desc" class="form-control"
						placeholder="不超过500个字符" />
				</div>

			</div>


			<div class="row form-group">
				<div class="col-md-offset-3 col-md-9">
					<s:button class="btn btn-default" onclick="submitEdu();">保存</s:button>
				</div>
			</div>
		</fieldset>
	</s:form>
	<hr align="center" width="90%" style="border: 2px dotted #fff" />
</c:forEach>


<div class="row">
	<a class="btn btn-warning btn-sm ladda-button linkbutton pull-right"
		data-style="expand-right" title="" data-libid="38"
		data-original-title="添加此栏目"><span class="glyphicon glyphicon-plus"></span></a>
</div>



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

	function submitEdu() {
		$("#eduForm").ajaxSubmit(function(data) {
			if (data.success) {
				alert(1);
			}
		});
	}
</script>