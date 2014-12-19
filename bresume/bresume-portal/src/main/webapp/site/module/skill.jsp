<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>

<form class="form-horizontal item-form" action="/portal/edu/save.do"
	onsubmit="return false;">
	<c:forEach items="items" var="skill" step="index">
		<div class="row">
			<div class="form-group col-md-4">
				<label for="name" class="col-md-3 control-label">技能</label>
				<div class="col-md-9">
					<input name="items[index].name" class="form-control" />
				</div>
			</div>
			<div class="form-group col-md-4">
				<label for="desc" class="col-md-5 control-label">使用时间</label>
				<div class="col-md-7">
					<input name="items[index].masterTime" class="form-control"
						style="width:65px;display:inline-block;" />
					月
				</div>
			</div>
			<div class="form-group col-md-4">
				<label for="desc" class="col-md-5 control-label">掌握程度</label>
				<div class="col-md-7">
					<select name="items[index].level" class="form-control">
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


	<div class="form-group">
		<div class=col-md-offset-11col-md-9">
			<button type="submit" class="btn btn-default">保存</button>
		</div>
	</div>
</form>