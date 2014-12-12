<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>

<s:form class="form-horizontal item-form" action="/portal/edu/save.do"
	onsubmit="return false;" commandName="workExperience">

	<div class="row ">
		<div class="form-group">
			<label for="desc" class="col-md-2 control-label">时间</label>
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
	</div>
	<div class="row">
		<div class="form-group col-md-6">
			<label for="desc" class="col-md-3 control-label">公司</label>
			<div class="col-md-9">
				<s:input path="companyName" id="companyName" class="form-control"
					placeholder="不超过20个字符" />
			</div>
		</div>

		<div class="form-group col-md-6">
			<label for="name" class="col-md-3 control-label">行业</label>
			<div class="col-md-9">
				<s:input path="manuName" id="manuName" class="form-control"
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
				<s:input path="position" id="position" class="form-control"
					placeholder="不超过20个字符" />
			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-6">
			<label for="name" class="col-md-3 control-label">工作描述</label>
			<div class="col-md-9">
				<s:input path="desc" id="desc" class="form-control"
					placeholder="不超过20个字符" />
			</div>
		</div>

	</div>

	<div class="form-group">
		<div class="col-md-offset-3 col-md-9">
			<button type="submit" class="btn btn-default">保存</button>
		</div>
	</div>
</s:form>