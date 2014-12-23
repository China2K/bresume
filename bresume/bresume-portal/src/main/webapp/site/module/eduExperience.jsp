<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE HTML>
<style type="text/css">

</style>

<div id="eduContiner" class="item-form" style="">
	<c:forEach items="${items}" var="eduExperience" varStatus="status">

		<div class="item-bar row" data-value="${eduExperience.id}"
			id="item-bar_${eduExperience.id}"
			onclick="load_detail('${eduExperience.id}');">
			<div class="col-md-5">
				<span class="time"><fmt:formatDate
						value="${eduExperience.startDate}" pattern="yyyy-MM-dd" /> -- <fmt:formatDate
						value="${eduExperience.endDate}" pattern="yyyy-MM-dd" /></span> <span
					class="school">${eduExperience.schoolName}</span>
			</div>
			<div class="col-md-offset-4 col-md-3">
				<a class="btn" href="javascript:delete_item('${eduExperience.id}');">删除</a>
			</div>

		</div>

	</c:forEach>

</div>

<div class="row">
	<a class="btn btn-warning btn-sm ladda-button linkbutton pull-right"
		data-style="expand-right" title="" data-libid="38" id="edu_add"
		data-original-title="添加此栏目"><span class="glyphicon glyphicon-plus"></span></a>
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
		pageType : 'child',
	});
	var size='${fn:length(items)}'*1;
	if(size<1){
		var key = randomString(8);
		load_new_form(null, key);
	}
	var resumeId = '${resumeId}';

	function load_new_form(id, key) {

		var url = "/portal/edu/load";
		if (id != "undenfined" && id != null & id != "") {
			url += "?id=" + id;
		}
		addItem2Container(url, $("#eduContiner"), key);

		var temp = $("#eduForm_" + key + " .input-id").val();

		if (temp == null || temp == "") {
			$("#eduForm_" + key + " .input-resumeId").val(resumeId);
		}

	}

	$("#edu_add").click(
			function() {
				var key = randomString(8);

				var htm = '<div hidden="hidden" onclick="load_detail(\'' + key
						+ '\');" class="item-bar row" hide data-value="' + key
						+ '"' + 'id="item-bar_' + key
						+ '"><div class="col-md-5"><span class="time">'
						+ '</span> <span class="school"></span> </div> '
						+ '<div class="col-md-offset-4 col-md-3"> '
						+ '<a class="btn" href="javascript:delete_item(\''
						+ key + '\');">删除</a> </div> </div>';

				$("#eduContiner").append(htm);

				load_new_form(null, key);
			});

	function load_detail(key) {

		var obj = document.getElementById("form_item_" + key);
		console.log(obj);
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
			real_id = $("#eduForm_" + key + " .input-id").val();
		}
		if (real_id && real_id.length == 32) {
			//已经持久化
			var params = {
				"id" : real_id
			};
			$.ajax({
				type : "POST",
				url : "<c:url value ='/edu/delete'/>",
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
		if (!$("#eduForm_" + key).validationEngine("validate")) {
			return false;
		}

		var date_start = $("#form_item_" + key + " #date_start_" + key).val();
		var date_end = $("#form_item_" + key + " #date_end_" + key).val();
		var schoolName = $("#form_item_" + key + " #schoolName").val();

		$("#item-bar_" + key + " .time").html(date_start + " -- " + date_end);
		$("#item-bar_" + key + " .school").html(schoolName);
		$("#form_item_" + key).hide();
		$("#item-bar_" + key).show();
	}

	//!$("#resumeForm").validationEngine("validate")
	function submit_form(key) {
		if (!$("#eduForm_" + key).validationEngine("validate")) {
			return false;
		}
		$("#eduForm_" + key).ajaxSubmit(function(data) {
			if (data.success) {
				$("#eduForm_" + key + " .input-id").val(data.id);
				alert("保存成功！");
			} else {
				alert(data.message);
			}
		});
	}
	

	
</script>