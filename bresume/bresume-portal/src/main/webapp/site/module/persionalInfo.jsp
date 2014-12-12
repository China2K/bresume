<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>

<s:form class="form-horizontal item-form" action="/portal/edu/save.do" onsubmit="return false;" commandName="persionalInfo">
	<div class="row">
		<div class="form-group col-md-6">
			<label for="name" class="col-md-3 control-label">姓名</label>
			<div class="col-md-9">
				<s:input path="name" class="form-control" id="name"
					placeholder="请输入您的姓名" />
			</div>
		</div>
		<div class="form-group col-md-6">
			<label for="sex" class="col-md-3 control-label">性别</label>
			<div class="col-md-9">
			
				<s:select path="sex"  class="form-control">
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
				<s:select path="credentialsType"  class="form-control">
					<s:option value="1">身份证</s:option>
					<s:option value="2">学生证</s:option>
				</s:select>
			</div>
		</div>
		<div class="form-group col-md-6">
			<label for="desc" class="col-md-3 control-label">证件号码</label>
			<div class="col-md-9">
				<s:input path="credentialsNumber" class="form-control" id="credentialsNumber"
					placeholder="请输入您的证件号码" />
			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-6">
			<label for="name" class="col-md-3 control-label">手机号码</label>
			<div class="col-md-9">
				<s:input path="cellPhone" class="form-control" id="cellPhone"
					placeholder="请输入您的手机号码" />
			</div>
		</div>
		<div class="form-group col-md-6">
			<label for="desc" class="col-md-3 control-label">邮箱地址</label>
			<div class="col-md-9">
				<s:input path="email" class="form-control" id="email"
					placeholder="请输入您的邮箱" />
			</div>
		</div>
	</div>

	<div class="row">
		<div class="form-group col-md-6">
			<label for="name" class="col-md-3 control-label">求职状态</label>
			<div class="col-md-9">
				<s:select path="jobStatus"  class="form-control">
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
			<label for="desc" class="col-md-3 control-label">居住地</label>
			<div class="col-md-9">
				<s:input path="address" class="form-control" id="address"
					placeholder="请输入您的地址" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-md-offset-3 col-md-9">
			<button type="submit" class="btn btn-default">保存</button>
		</div>
	</div>
</s:form>