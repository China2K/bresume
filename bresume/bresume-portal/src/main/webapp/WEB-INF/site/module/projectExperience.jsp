<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE HTML>
<style type="text/css">
.uploadify-button, .uploadify-button-text {
	text-align: center;
	font-size: 20px;
	font-family: Tahoma,Verdana,微软雅黑,新宋体;
}
</style>

<div id="projectContiner" class="item-form" style="">
	<c:forEach items="${items}" var="projectExperience" varStatus="status">

		<div class="item-bar row" data-value="${projectExperience.id}"
			id="item-bar_${projectExperience.id}"
			onclick="load_detail('${projectExperience.id}');">
			<div class="col-md-8">
				<span class="time"><fmt:formatDate
						value="${projectExperience.startDate}" pattern="yyyy-MM-dd" /> -- <fmt:formatDate
						value="${projectExperience.endDate}" pattern="yyyy-MM-dd" /></span> <span
					class="project">${projectExperience.projectName}</span>
			</div>
			<div class="col-md-offset-1 col-md-3">
				<a class="btn" href="javascript:delete_item('${projectExperience.id}');">删除</a>
			</div>

		</div>

	</c:forEach>

</div>

<div class="row">
	<a class="btn btn-warning btn-sm ladda-button linkbutton pull-right"
		data-style="expand-right" title="" data-libid="38" id="project_add"
		data-original-title="添加此栏目"><span class="glyphicon glyphicon-plus"></span></a>
</div>
<link rel="stylesheet"
	href="<c:url value ='/resource/plugin/jQuery-File-Upload/css/jquery.fileupload.css'/>">
	<script
		src="<c:url value ='/resource/plugin/uploadify/jquery.uploadify.js'/>"></script>

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
		pageType : 'child',
	});

	var resumeId = '${resumeId}';
	
	var size='${fn:length(items)}'*1;
	if(size<1){
		var key = randomString(8);
		load_new_form(null, key);
	}

	function load_new_form(id, key) {

		var url = "<c:url value='/pe/load' />";
		if (id != "undenfined" && id != null & id != "") {
			url += "?id=" + id;
		}
		addItem2Container(url, $("#projectContiner"), key);

		var temp = $("#projectForm_" + key + " .input-id").val();

		if (temp == null || temp == "") {
			$("#projectForm_" + key + " .input-resumeId").val(resumeId);
		}
		var _div ="#file-div-" + key;
		makeFileUpload( $(_div +" .fileupload-file-input_"), $(_div +" .fileupload-img-input_"), $(_div +" .fileupload-hidden-input_"),'${sessionScope.upload_info}');


	}

	function addBar2Container(key){

		var htm = '<div hidden="hidden" onclick="load_detail(\'' + key
				+ '\');" class="item-bar row" hide data-value="' + key
				+ '"' + 'id="item-bar_' + key
				+ '"><div class="col-md-8"><span class="time">'
				+ '</span> <span class="project"></span> </div> '
				+ '<div class="col-md-offset-1 col-md-3"> '
				+ '<a class="btn" href="javascript:delete_item(\''
				+ key + '\');">删除</a> </div> </div>';

		$("#projectContiner").append(htm);
	}
	$("#project_add").click(
			function() {
				var key = randomString(8);

				addBar2Container(key);
				load_new_form(null, key);
			});

	function load_detail(key) {

		var obj = document.getElementById("form_item_" + key);
		//console.log(obj);
		if (obj) {
			$("#form_item_" + key).show();
		} else {
			if (key.length == 32) {
				load_new_form(key, key);
			} else {
				load_new_form(null, key);
			}
		}

		$("#item-bar_" + key).hide();
	}

	function delete_item(key) {
		var real_id;
		if (key.length == 32) {
			real_id = key;
		} else {
			real_id = $("#projectForm_" + key + " .input-id").val();
		}
		if (real_id && real_id.length == 32) {
			//已经持久化
			var params = {
				"id" : real_id
			};
			$.ajax({
				type : "POST",
				url : "<c:url value ='/pe/delete'/>",
				async : false,
				data : params,
				success : function(data) {
					if (data.success) {
					}
				}
			});

		}

		$("#item-bar_" + key).remove();

		$("#form_item_" + key).remove();

	}

	function hide_item(key) {
		if (!$("#projectForm_" + key).validationEngine("validate")) {
			return false;
		}
		
		if($("#item-bar_" + key).length<1){
			addBar2Container(key);
		}

		var date_start = $("#form_item_" + key + " #date_start_" + key).val();
		var date_end = $("#form_item_" + key + " #date_end_" + key).val();
		var projectName = $("#form_item_" + key + " #projectName").val();

		$("#item-bar_" + key + " .time").html(date_start + " -- " + date_end);
		$("#item-bar_" + key + " .project").html(projectName);
		$("#form_item_" + key).hide();
		$("#item-bar_" + key).show();
	}

	//!$("#resumeForm").validationEngine("validate")
	function submit_form(key) {
		if (!$("#projectForm_" + key).validationEngine("validate")) {
			return false;
		}
		$("#projectForm_" + key).ajaxSubmit(function(data) {
			if (data.success) {
				$("#projectForm_" + key + " .input-id").val(data.id);
				alert("保存成功！");
			} else {
				alert(data.message);
			}
		});
	}
	
	function makeFileUpload(fileEle, imgEle, valueEle, upload_info) {
		fileEle
				.uploadify({
					'uploader' : 'http://static.bresume.com/upload/uploadImg',
					'swf' : '<c:url value ="/resource/plugin/uploadify/uploadify.swf"/>',
					'cancelImage' : '<c:url value ="/resource/plugin/uploadify/cancel.png"/>',
					'queueID' : 'imgFile',
					'fileObjName' : 'imgFile',
					'auto' : true,
					'width' : 285,
					'height' : 250,
					'multi' : false,
					'buttonText' : '上传',
					'formData' : {
						'source' : 'PORTAL',
						'upload_info' : upload_info,
						'upload_key' : 'whatadiors',
					},
					'onUploadSuccess' : function(file, data, response) {
						data = JSON.parse(data);
						var res = data.success;
						if (res === true) {
							valueEle.val(data.message);
							imgEle.attr("src", "http://static.bresume.com"
									+ data.message);
						} else {
							alert("上传失败");
						}
					},
				});

	}
	
</script>