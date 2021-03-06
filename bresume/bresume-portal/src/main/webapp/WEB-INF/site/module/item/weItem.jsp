<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>

<div class="work_form_item"
	style="background-color: #E5E5E5; width: 90%;" id="form_item_@@@@">
	<s:form class="form-horizontal work-form" id="workForm_@@@@"
		method="post" action="/we/save" onsubmit="return false;"
		modelAttribute="workExperience">
		<s:hidden path="resume.id" class="input-resumeId" />
		<s:hidden path="id" class="input-id" />
		<div class="row">
			<div class="form-group col-md-12" style="margin-left: -45px;">
				<label for="desc" class="col-md-2 control-label">时间</label>
				<div class="input-group date form_date col-md-4 f-left" data-date=""
					data-date-format="dd MM yyyy" data-link-field="date_start_@@@@"
					data-link-format="yyyy-mm-dd">
					<input class="form-control validate[required]" size="16"
						type="text"
						value="<fmt:formatDate value='${workExperience.startDate}' pattern='yyyy-MM-dd' />"
						readonly> <span class="input-group-addon"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
				<s:hidden id="date_start_@@@@" path="startDate" />
				<div class="col-md-1 text-center f-left">&nbsp;-&nbsp;</div>
				<div class="input-group date form_date col-md-4 f-left" data-date=""
					data-date-format="dd MM yyyy" data-link-field="date_end_@@@@"
					data-link-format="yyyy-mm-dd">
					<input class="form-control validate[required]" size="16"
						type="text"
						value="<fmt:formatDate value='${workExperience.endDate}' pattern='yyyy-MM-dd' />"
						readonly> <span class="input-group-addon"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>

				<s:hidden id="date_end_@@@@" path="endDate" />
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-6">
				<label for="desc" class="col-md-3 control-label">公司</label>
				<div class="col-md-9">
					<s:input path="companyName" id="companyName" class="form-control validate[required,maxSize[20]]"
						placeholder="不超过20个字符" />
				</div>
			</div>

			<div class="form-group col-md-6">
				<label for="name" class="col-md-3 control-label">行业</label>
				<div class="col-md-9">
					<s:input path="tradeName" id="tradeName" class="form-control"
						placeholder="不超过20个字符" />
				</div>
			</div>

		</div>

		<div class="row">


			<div class="form-group col-md-6">
				<label for="desc" class="col-md-3 control-label">部门</label>
				<div class="col-md-9">
					<s:input path="department" id="department" class="form-control"
						placeholder="不超过20个字符" />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label for="desc" class="col-md-3 control-label">职位</label>
				<div class="col-md-9">
					<s:input path="position" id="position" class="form-control validate[required,maxSize[20]]"
						placeholder="不超过20个字符" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="form-group col-md-12" style="margin-left: -45px;">
				<label for="name" class="col-md-2 control-label">工作描述</label>
				<div class="col-md-9">
					<s:textarea path="desc" id="desc" class="form-control" rows="5"
						placeholder="不超过200个字符" />
				</div>
			</div>

		</div>

		<div class="row form-group">
			<div class="col-md-offset-8 col-md-4">
				<s:button class="btn btn-default" onclick="submit_form('@@@@');">保存</s:button>


				<button class="btn btn-default" onclick="delete_item('@@@@')">删除</button>
				<button class="btn btn-default" onclick="hide_item('@@@@')">隐藏</button>
			</div>
		</div>
	</s:form>
	<hr align="center" width="90%" style="border: 2px dotted #fff" />
</div>



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
</script>