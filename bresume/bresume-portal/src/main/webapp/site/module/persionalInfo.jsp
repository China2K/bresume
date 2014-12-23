<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>

<s:form class="form-horizontal item-form" action="/portal/pi/save"
	onsubmit="return false;" commandName="persionalInfo"
	id="persionalInfo_form">
	<s:hidden path="id" id="form_persionalInfo_id" />
	<s:hidden path="resume.id" id="form_persionalInfo_resumeId" />
	<div class="row">
		<div class="form-group col-md-6">
			<label for="name" class="col-md-3 control-label">姓名</label>
			<div class="col-md-9">
				<s:input path="name"
					class="form-control validate[required,maxSize[50]]" id="name"
					placeholder="请输入您的姓名" />
			</div>
		</div>
		<div class="form-group col-md-6">
			<label for="sex" class="col-md-3 control-label">性别</label>
			<div class="col-md-9">

				<s:select path="sex" class="form-control">
					<s:option value="1">男</s:option>
					<s:option value="2">女</s:option>
				</s:select>
			</div>
		</div>

	</div>

	<div class="row">
		<div class="form-group col-md-6">
			<label for="credentialsType" class="col-md-3 control-label">证件类型</label>
			<div class="col-md-9">
				<s:select path="credentialsType" class="form-control">
					<s:option value="1">身份证</s:option>
					<s:option value="2">学生证</s:option>
				</s:select>
			</div>
		</div>
		<div class="form-group col-md-6">
			<label for="desc" class="col-md-3 control-label">证件号码</label>
			<div class="col-md-9">
				<s:input path="credentialsNumber" class="form-control"
					id="credentialsNumber" placeholder="请输入您的证件号码" />
			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-6">
			<label for="name" class="col-md-3 control-label">手机号码</label>
			<div class="col-md-9">
				<s:input path="cellPhone"
					class="form-control validate[required,custom[phone]]"
					id="cellPhone" placeholder="请输入您的手机号码" />
			</div>
		</div>
		<div class="form-group col-md-6">
			<label for="desc" class="col-md-3 control-label">邮箱地址</label>
			<div class="col-md-9">
				<s:input path="email"
					class="form-control validate[required,custom[email]]" id="email"
					placeholder="请输入您的邮箱" />
			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-6">
			<label for="name" class="col-md-3 control-label">求职状态</label>
			<div class="col-md-9">
				<s:select path="jobStatus" class="form-control">
					<s:option value="1">正在找工作</s:option>
					<s:option value="2">观望有机会在考虑</s:option>
					<s:option value="3">暂时不考虑换工作</s:option>
				</s:select>
			</div>
		</div>

		<div class="form-group col-md-6">
			<label for="name" class="col-md-3 control-label">个人主页</label>
			<div class="col-md-9">
				<s:input path="siteUrl" class="form-control" id="siteUrl"
					placeholder="请输入您的个人主页地址" />
			</div>
		</div>
	</div>


	<div class="row">
		<div class="form-group col-md-6">
			<label for="desc" class="col-md-3 control-label">出生日期</label>
			<div class="input-group date form_date col-md-9 f-left" data-date=""
				data-date-format="dd MM yyyy" data-link-field="birthday"
				data-link-format="yyyy-mm-dd">
				<input class="form-control" size="16" type="text"
					value="<fmt:formatDate value='${persionalInfo.birthday}' pattern='yyyy-MM-dd' />"
					readonly> <span class="input-group-addon"><span
					class="glyphicon glyphicon-calendar"></span></span>
			</div>
			<s:hidden id="birthday" path="birthday" />

		</div>
		<div class="form-group col-md-6">
			<label for="desc" class="col-md-3 control-label">居住地</label>
			<div class="col-md-9">
				<s:input path="address" class="form-control" id="address"
					placeholder="请输入您的地址" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-md-offset-3 col-md-9">
			<button type="submit" class="btn btn-default" id="sub_pi_btn">保存</button>
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

<!-- jQuery-Validation-Engine -->
<link rel="stylesheet"
	href="<c:url value ='/resource/site/jQuery-Validation-Engine/css/validationEngine.jquery.css'/>">
<script
	src="<c:url value ='/resource/site/jQuery-Validation-Engine/js/jquery.validationEngine-zh_CN.js'/>"></script>
<script
	src="<c:url value ='/resource/site/jQuery-Validation-Engine/js/jquery.validationEngine.min.js'/>"></script>


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
	$("#persionalInfo_form").validationEngine();

	$("#sub_pi_btn").click(function() {
		if (!$("#persionalInfo_form").validationEngine("validate")) {
			return false;
		}
		$("#persionalInfo_form").ajaxSubmit(function(data) {
			if (data.success) {
				$("#form_persionalInfo_id").val(data.id);
				alert("保存成功！");
			} else {
				alert(data.message);
			}
		});

	});

	
</script>