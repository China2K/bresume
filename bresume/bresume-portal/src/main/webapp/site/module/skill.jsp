<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>

<form class="form-horizontal item-form" action="/portal/skill/save.do" id="skills_form" method="post"
	onsubmit="return false;">
	
	<input type="hidden" name="resumeId" id="form_skill_resumeId" value="${resumeId}"/>
	<c:forEach items="${items}" var="skill" varStatus="status">
		<div class="row item-skill" id="item_${status.index}" data-index="${status.index}">
			<input type="hidden" name="items[${status.index}].id" id="form_skill_id_${status.index}" value="${skill.id}"/>
			<div class="form-group col-md-4">
				<label for="name" class="col-md-3 control-label">技能</label>
				<div class="col-md-9">
					<input name="items[${status.index}].name" class="form-control" value="${skill.name}"/>
				</div>
			</div>
			<div class="form-group col-md-4">
				<label for="desc" class="col-md-5 control-label">使用时间</label>
				<div class="col-md-7">
					<input name="items[${status.index}].masterTime" class="form-control"
						style="width:65px;display:inline-block;" value="${skill.masterTime}"/>
					月
				</div>
			</div>
			<div class="form-group col-md-4">
				<label for="desc" class="col-md-5 control-label">掌握程度</label>
				<div class="col-md-7" onload="iniCombo();">
					<select name="items[${status.index}].level"  value="${skill.level}" onloadeddata="iniCombo();" class="form-control iniCombo" >
						<option value="1">无</option>
						<option value="2">了解</option>
						<option value="3">一般</option>
						<option value="4">熟练</option>
						<option value="5">精通</option>
					</select>
				</div>
			</div>

		</div>


	</c:forEach>

	<div class="row">
		<a class="btn btn-warning btn-sm ladda-button linkbutton pull-right"
			data-style="expand-right" title="" data-libid="38" id="skill_add"
			data-original-title="添加此栏目"><span class="glyphicon glyphicon-plus"></span></a>
	</div>

	<div class="form-group">
		<div class="col-md-offset-11col-md-9">
			<button type="submit" class="btn btn-default" id="sub_skill_btn">保存</button>
		</div>
	</div>
</form>




<div class="row item-skill" id="item_copy" data-index="copy" hidden="hidden" >
			<input type="hidden" name="items[copy].id" id="form_skill_id_copy" />
			<div class="form-group col-md-4">
				<label for="name" class="col-md-3 control-label">技能</label>
				<div class="col-md-9">
					<input name="items[copy].name" class="form-control" />
				</div>
			</div>
			<div class="form-group col-md-4">
				<label for="desc" class="col-md-5 control-label">使用时间</label>
				<div class="col-md-7">
					<input name="items[copy].masterTime" class="form-control"
						style="width:65px;display:inline-block;" />
					月
				</div>
			</div>
			<div class="form-group col-md-4">
				<label for="desc" class="col-md-5 control-label">掌握程度</label>
				<div class="col-md-7">
					<select name="items[copy].level" class="form-control">
						<option value="1">无</option>
						<option value="2">了解</option>
						<option value="3">一般</option>
						<option value="4">熟练</option>
						<option value="5">精通</option>
					</select>
				</div>
			</div>

</div>
<script type="text/javascript" src="/portal/resource/site/js/jquery.js"
	charset="UTF-8"></script>

<script type="text/javascript"
	src="/portal/resource/site/js/jquery.form.js" charset="UTF-8"></script>
<!-- jQuery-Validation-Engine -->
<link rel="stylesheet"
	href="<c:url value ='/resource/site/jQuery-Validation-Engine/css/validationEngine.jquery.css'/>">
<script
	src="<c:url value ='/resource/site/jQuery-Validation-Engine/js/jquery.validationEngine-zh_CN.js'/>"></script>
<script
	src="<c:url value ='/resource/site/jQuery-Validation-Engine/js/jquery.validationEngine.min.js'/>"></script>
	
	<script
	src="<c:url value ='/resource/app/js/common.js'/>"></script>

<script type="text/javascript">
	$("#skills_form").validationEngine();

	$("#sub_skill_btn").click(function() {
		if (!$("#skills_form").validationEngine("validate")) {
			return false;
		}
		$("#skills_form").ajaxSubmit(function(data) {
			if (data.success) {
				alert("保存成功！");
				subUrl("/portal/resume/resumeItem.do?itemSn=ITEM-0006");
			} else {
				alert(data.message);
			}
		});

	});
	
	
	
	$("#skill_add").click(function() {
		
		
			var last_index=$("#skills_form .item-skill").last().attr("data-index");
				var key=last_index*1+1;
				var new_item=document.getElementById('item_copy').outerHTML;//$("#item_copy").clone().html();
				new_item = new_item.replaceAll("copy", key,true);
				console.log(new_item);
				$("#skills_form .item-skill").last().after(new_item);
				$("#item_"+key).show();
				
			});
		
	
		console.log($(".iniCombo"));
		
		$(".iniCombo").each(function(index, domEle){
			   console.log(domEle);
			   var index = $(this).attr("value")*1-1;
			   $(this).find("option").eq(index).attr("selected","selected")
			 });
		
</script>