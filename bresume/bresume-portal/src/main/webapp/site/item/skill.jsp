<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>

<s:form class="form-horizontal item-form" action="/portal/edu/save.do" onsubmit="return false;" commandName="skill" >
	<div class="row">
		<div class="form-group col-md-4">
			<label for="name" class="col-md-3 control-label">技能</label>
			<div class="col-md-9">
				<s:input path="name" class="form-control"/>
			</div>
		</div>
		<div class="form-group col-md-4">
			<label for="desc" class="col-md-5 control-label">使用时间</label>
			<div class="col-md-7">
				<s:input path="masterTime" class="form-control" style="width:65px;display:inline-block;"/>月
			</div>
		</div>
		<div class="form-group col-md-4">
			<label for="desc" class="col-md-5 control-label">掌握程度</label>
			<div class="col-md-7">
				<s:select path="level"  class="form-control">
					<s:option value="1">无</s:option>
					<s:option value="2">了解</s:option>
					<s:option value="3">一般</s:option>
					<s:option value="4">熟练</s:option>
					<s:option value="5">精通</s:option>
				</s:select>
			</div>
		</div>

	</div>


	<div class="form-group">
		<div class=col-md-offset-11 col-md-9">
			<button type="submit" class="btn btn-default">保存</button>
		</div>
	</div>
</s:form>