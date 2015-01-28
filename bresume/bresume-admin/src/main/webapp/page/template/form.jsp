<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>jqGrid - Ace Admin</title>

<meta name="description" content="简历" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<%@ include file="../common/common.jsp"%>

<style type="text/css">
</style>
</head>

<body class="no-skin">
	<div class="page-content">
		<div class="page-content-area">
			<div class="page-header">
				<h1>
					模版管理 <small> <i class="ace-icon fa fa-angle-double-right"></i>
						新增
					</small>
				</h1>
			</div>
			<!-- /.page-header -->

			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<s:form class="form-horizontal" action="/tem/save.do"
						onsubmit="return false;" commandName="template" id="template_form">
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1"> 名称 </label>

							<div class="col-sm-9">
								<s:input path="name" id="name" placeholder="名称"
									class="col-xs-10 col-sm-5 validate[required,maxSize[50]]" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1"> 编号 </label>

							<div class="col-sm-9">
								<s:input path="sn" id="sn" placeholder="编号"
									class="col-xs-10 col-sm-5 validate[required,maxSize[50]]" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1"> 封面</label>

							<div class="col-sm-9">
								<input class="fileupload-file-input" id="fileupload" type="file" />
								<img src="${staticUrlPrefix}${template.coverUrl}" width="145px;" height="135px;" id="fileupload-img-input"
											class="img-responsive text-center" alt="">
								<input type="hidden" value="${template.coverUrl}" id="fileupload-hidden-input" />
							</div>
							
								
						</div>
						

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1"> 地址 </label>

							<div class="col-sm-9">
								<s:input path="siteUrl" id="siteUrl" placeholder="模版地址"
									class="col-xs-10 col-sm-5 validate[required,custom[url]]" />
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1"> 模版类型 </label>

							<div class="col-sm-3">
								<s:select path="type" class="form-control">
									<s:option value="0">通用</s:option>
									<s:option value="1">程序猿</s:option>
									<s:option value="2">攻城狮</s:option>
									<s:option value="3">人事</s:option>
								</s:select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1"> 来源 </label>

							<div class="col-sm-9">
								<s:input path="source" id="source" placeholder="来源"
									class="col-xs-10 col-sm-5" />
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1"> 排序 </label>

							<div class="col-sm-9">

								<s:input path="order" id="order" placeholder="0"
									class="col-xs-5 col-sm-1 validate[custom[integer]]" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1"> 是否推荐 </label>

							<div class="col-sm-9">
					
								<label> <input name="recommended" checked="{template.recommended}"
									class="ace ace-switch ace-switch-7" type="checkbox"/> <span
									class="lbl"></span>
								</label>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"
								for="form-field-1"> 描述 </label>

							<div class="col-sm-9">
								<s:textarea path="desc" class="col-xs-10 col-sm-5" />
							</div>
						</div>

						<!-- <div class="space-4"></div> -->
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="button" id="sub_tem_btn">
									<i class="ace-icon fa fa-check bigger-110"></i> Submit
								</button>

								&nbsp; &nbsp; &nbsp;
								<button class="btn" type="reset">
									<i class="ace-icon fa fa-undo bigger-110"></i> Reset
								</button>
							</div>
						</div>
					</s:form>
				</div>
			</div>
		</div>
	</div>
	<!-- /.page-content -->


	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		$('#spinner3').ace_spinner({
			value : 0,
			min : 0,
			max : 100,
			step : 1,
			on_sides : true,
			icon_up : 'ace-icon fa fa-plus smaller-75',
			icon_down : 'ace-icon fa fa-minus smaller-75',
			btn_up_class : 'btn-success',
			btn_down_class : 'btn-danger'
		});
		
		$("#template_form").validationEngine();

		$("#sub_tem_btn").click(function() {
			if (!$("#template_form").validationEngine("validate")) {
				return false;
			}
			$("#template_form").ajaxSubmit(function(data) {
				if (data.success) {
					alert("保存成功！");
				} else {
					alert(data.message);
				}
			});

		});
		
		
		var upload_info ='${sessionScope.upload_info}';
		makeFileUpload($("#fileupload"), $("#fileupload-img-input"), $("#fileupload-hidden-input"),upload_info);
	</script>
</body>
</html>
