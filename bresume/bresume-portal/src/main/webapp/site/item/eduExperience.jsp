<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<style type="text/css">
.item-bar {
	background-color: #eee;
	border: 1px solid #ccc;
	border-radius: 5px;
	height: 35px;
	line-height: 35px;
	cursor: pointer;
	text-align: center;
	cursor: pointer;
}

.item-bar div {
	height: 35px;
	line-height: 35px;
}

.item-bar:hover {
	background-color: #F5F5F5;
}
</style>



<link
	href="/portal/resource/site/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">

<div id="eduContiner" class="item-form" style="">
	<c:forEach items="${items}" var="eduExperience" varStatus="status">

		<div class="item-bar row text-center" data-value="${eduExperience.id}"
			id="item-bar_${eduExperience.id}">
			<div class="col-md-5">
				<span class=""><fmt:formatDate
						value="${eduExperience.startDate}" pattern="yyyy-MM-dd" /> --<fmt:formatDate
						value="${eduExperience.endDate}" pattern="yyyy-MM-dd" /></span> <span>${eduExperience.schoolName}</span>
			</div>
			<div class="col-md-offset-4 col-md-3">
				<a class="btn" href="#">删除</a>
			</div>
		</div>

	</c:forEach>

</div>

<div class="row">
	<a class="btn btn-warning btn-sm ladda-button linkbutton pull-right"
		data-style="expand-right" title="" data-libid="38" id="edu_add"
		data-original-title="添加此栏目"><span class="glyphicon glyphicon-plus"></span></a>
</div>


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

	function submitEdu() {
		$("#eduForm").ajaxSubmit(function(data) {
			if (data.success) {
				alert(1);
			}
		});
	}

	function load_new_form(id, key) {

		var url = "/portal/edu/load.do";
		if (id != "undenfined" && id != null & id != "") {
			url += "?id=" + id;
		}
		addItem2Container(url, $("#eduContiner"), key);

	}

	$("#edu_add").click(function() {
		load_new_form();
	});

	$(".item-bar").click(function() {
		var id = $(this).attr("data-value");

		var obj = document.getElementById("form_item_" + id);
		console.log(obj);
		if (obj) {
			$("#form_item_" + id).show();
		} else {
			load_new_form(id, id);
		}

		$(this).hide();
	});

	function delete_item(id) {

		if (id.length == 32) {
			real_id=id;
		}else{
			real_id=$("#eduForm_"+id+" .input-id").val();
		}
		if (real_id&&real_id.length==32) {
			//已经持久化
			var params = {
				"id" : real_id
			};
			$.ajax({
				type : "POST",
				url : "<c:url value ='/edu/delete.do'/>",
				async : false,
				data : params,
				success : function(data) {
					if (data.success) {
					}
				}
			});

		}

		$("#item-bar_" + id).remove();

		$("#form_item_" + id).remove();

	}

	function hide_item(id) {
		$("#form_item_" + id).hide();
		$("#item-bar_" + id).show();
	}

	function submit_form(id){
		$("#eduForm_"+id).ajaxSubmit(
				function(data) {
					if (data.success) {
						$("#eduForm_"+id+" .input-id").val(data.id);
					}else{
						alert(data.message);
					}
				}
			);
	}

	
</script>