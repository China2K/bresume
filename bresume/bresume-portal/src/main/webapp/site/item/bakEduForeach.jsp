<c:forEach items="${items}" var="eduExperience" varStatus="status">
		<form class="form-horizontal item-form" id="eduForm_${status.index}"
			method="post" action="/portal/edu/save.do" onsubmit="return false;">
			<input type="hidden" name="resume.id"
				value="${eduExperience.resume.id}">
			<fieldset>
				<div class="row form-group">

					<label for="desc" class="col-md-3 control-label">时间</label>
					<div class="input-group date form_date col-md-3 f-left"
						data-date="" data-date-format="dd MM yyyy"
						data-link-field="date_start" data-link-format="yyyy-mm-dd">
						<input class="form-control" size="16" type="text" value=""
							readonly> <span class="input-group-addon"><span
							class="glyphicon glyphicon-calendar"></span></span>
					</div>
					<input type="hidden" id="date_start" name="startDate"
						value="${eduExperience.startDate}">
					<div class="col-md-1 text-center f-left">&nbsp;-&nbsp;</div>
					<div class="input-group date form_date col-md-3 f-left"
						data-date="" data-date-format="dd MM yyyy"
						data-link-field="date_end" data-link-format="yyyy-mm-dd">
						<input class="form-control" size="16" type="text" value=""
							readonly> <span class="input-group-addon"><span
							class="glyphicon glyphicon-calendar"></span></span>
					</div>
					<input type="hidden" id="date_end" name="endDate"
						value="${eduExperience.endDate}">
				</div>
				<div class="row form-group">

					<label for="desc" class="col-md-3 control-label">学校</label>
					<div class="col-md-9">
						<input type="text" id="schoolName" class="form-control"
							placeholder="不超过20个字符" name="schoolName" value="" />
					</div>

				</div>
				<div class="row form-group">

					<label for="desc" class="col-md-3 control-label">专业</label>
					<div class="col-md-9">
						<input type="text" id="majorName" class="form-control"
							placeholder="不超过20个字符" name="majorName" value="" />
					</div>

				</div>
				<div class="row form-group">

					<label for="desc" class="col-md-3 control-label">学历</label>
					<div class="col-md-9">
						<select name="degree" class="form-control" id="degree">
							<option value="1">专科</option>
							<option value="2">本科</option>
							<option value="3">硕士</option>
							<option value="4">博士</option>
						</select>
					</div>

				</div>

				<div class="row form-group">

					<label for="desc" class="col-md-3 control-label">描述</label>
					<div class="col-md-9">
						<textarea rows="3" cols="50" name="desc" id="desc"
							class="form-control" placeholder="不超过500个字符"></textarea>
					</div>

				</div>


				<div class="row form-group">
					<div class="col-md-offset-3 col-md-9">
						<input type="button" class="btn btn-default"
							onclick="submitEdu();" value="保存" />
					</div>
				</div>
			</fieldset>
		</form>
		<hr align="center" width="90%" style="border: 2px dotted #fff" />
	</c:forEach>