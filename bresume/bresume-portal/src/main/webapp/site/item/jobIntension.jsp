<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>

<s:form class="form-horizontal item-form" action="/portal/edu/save.do" onsubmit="return false;" commandName="jobIntension">
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
				<s:input path="address" class="form-control"/>
			</div>
		</div>

	</div>

	<div class="row">
		<div class="form-group col-md-6">
			<label for="tradeCode" class="col-md-3 control-label">行业</label>
			<div class="col-md-9">
				<s:select path="tradeCode" class="form-control" id="tradeCode">
					<s:option value="1">金融</s:option>
					<s:option value="2">IT</s:option>
					<s:option value="3">服务</s:option>
				</s:select>
			</div>
		</div>
		<div class="form-group col-md-6">
			<label for="profession" class="col-md-3 control-label">职能</label>
			<div class="col-md-9">
				<s:input path="profession" class="form-control" id="profession"
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
		<div class="form-group col-md-6">
			<label for="selfEvaluation" class="col-md-3 control-label">自我评价</label>
			<div class="col-md-9">
				<s:textarea path="selfEvaluation" class="form-control"
					id="selfEvaluation" placeholder="请输入个人简介" />
			</div>
		</div>
	</div>


	<div class="form-group">
		<div class="col-md-offset-3 col-md-9">
			<button type="submit" class="btn btn-default">保存</button>
		</div>
	</div>
</s:form>