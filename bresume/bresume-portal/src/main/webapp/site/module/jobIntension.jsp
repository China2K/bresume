<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>

<s:form class="form-horizontal item-form" action="/jobi/save"
	onsubmit="return false;" commandName="jobIntension" id="form_jobinten">
	<s:hidden path="id" id="form_jobinten_id" />
	<s:hidden path="resume.id" id="form_jobinten_resumeId" />
	<div class="row">
		<div class="form-group col-md-6">
			<label for="jobType" class="col-md-3 control-label">工作类型</label>
			<div class="col-md-9">
				<s:select path="jobType" class="form-control" id="jobType">
					<s:option value="">全职</s:option>
					<s:option value="1">全职</s:option>
					<s:option value="2">兼职</s:option>
					<s:option value="3">实习</s:option>
				</s:select>
			</div>
		</div>
		<div class="form-group col-md-6">
			<label for="address" class="col-md-3 control-label">地区</label>
			<div class="col-md-9">
				<s:input path="address"
					class="form-control validate[required,maxSize[50]]"
					placeholder="多个请以,隔开" />
			</div>
		</div>

	</div>

	<div class="row">
		<div class="form-group col-md-6">
			<label for="trade" class="col-md-3 control-label">行业</label>
			<div class="col-md-9">
				<s:input path="trade"
					class="form-control validate[required,maxSize[50]]"
					placeholder="多个请以,隔开" />
				<!-- <s:select path="trade" class="form-control" id="trade">
					<s:option value="1">金融</s:option>
					<s:option value="2">IT</s:option>
					<s:option value="3">服务</s:option>
				</s:select> -->
			</div>
		</div>
		<div class="form-group col-md-6">
			<label for="profession" class="col-md-3 control-label">职能</label>
			<div class="col-md-9">
				<s:input path="profession"
					class="form-control validate[required,maxSize[50]]" id="profession"
					placeholder="不超过20个字符" />
			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-6">
			<label for="expertSalary" class="col-md-3 control-label">期望薪水</label>
			<div class="col-md-9">
				<s:select path="expertSalary" class="form-control" id="expertSalary">
					<s:option value="1">面议</s:option>
					<s:option value="2">1500以下</s:option>
					<s:option value="3">2000-3000</s:option>
				</s:select>
			</div>
		</div>
		<div class="form-group col-md-6">
			<label for="readyTime" class="col-md-3 control-label">到岗时间</label>
			<div class="col-md-9">
				<s:select path="readyTime" class="form-control" id="readyTime">
					<s:option value="1">一周内</s:option>
					<s:option value="2">一月内</s:option>
					<s:option value="3">1-3个月</s:option>
				</s:select>
			</div>
		</div>
	</div>

	<div class="row">
		<label for="selfEvaluation" class="col-md-2 control-label">自我评价</label>

		<div class="col-md-9">
			<s:textarea path="selfEvaluation"
				class="form-control validate[required,maxSize[500]]"
				id="selfEvaluation" placeholder="请输入个人简介" />
		</div>
	</div>


	<div class="form-group">
		<div class="col-md-offset-3 col-md-9">
			<button type="submit" class="btn btn-default" id="subJobBtn">保存</button>
		</div>
	</div>
</s:form>

<script type="text/javascript">
	$("#form_jobinten").validationEngine();

	$("#subJobBtn").click(function() {
		if (!$("#form_jobinten").validationEngine("validate")) {
			return false;
		}
		$("#form_jobinten").ajaxSubmit(function(data) {
			if (data.success) {
				$("#form_jobinten_id").val(data.id);
				alert("保存成功！");
			} else {
				alert(data.message);
			}
		});

	});
</script>