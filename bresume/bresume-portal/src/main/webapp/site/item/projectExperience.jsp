<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>

<form class="form-horizontal item-form" role="form" >
	<div class="row">
		<div class="form-group col-md-6">
			<label for="name" class="col-md-3 control-label">时间</label>
			<div class="col-md-9">
				<input type="text" class="form-control" id="name"
					placeholder="请输入您的姓名">
			</div>
		</div>
		<div class="form-group col-md-6">
			<label for="desc" class="col-md-3 control-label">项目名称</label>
			<div class="col-md-9">
				<input type="text" class="form-control" id="lastname"
					placeholder="不超过20个字符">
			</div>
		</div>

	</div>

	<div class="row form-group">
	
		<label for="desc" class="col-md-3 control-label">项目描述</label>
		<div class="col-md-9">
			<textarea type="text" class="form-control" id="lastname"
				placeholder="不超过20个字符"/>
		</div>
	</div>

	<div class="row">
		<label for="desc" class="col-md-3 control-label">责任描述</label>
		<div class="col-md-9">
			<textarea type="text" class="form-control" id="lastname"
				placeholder="不超过20个字符"/>
		</div>
	</div>


	<div class="form-group">
		<div class="col-md-offset-3 col-md-9">
			<button type="submit" class="btn btn-default">保存</button>
		</div>
	</div>
</form>