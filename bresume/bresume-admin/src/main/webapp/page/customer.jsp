<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>jqGrid - Ace Admin</title>

<meta name="description"
	content="Dynamic tables and grids using jqGrid plugin" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<%@ include file="common/common.jsp"%>

<style type="text/css">
.ui-jqgrid{
border: 1px solid #E1E1E1;
}
</style>
</head>

<body class="no-skin">
	<div class="page-content">

		<div class="page-content-area">
			<div class="page-header">
				<h1></h1>
			</div>
			<!-- /.page-header -->

			<div class="row">
				<div class="col-xs-12">
					<table id="grid-table"></table>

					<div id="grid-pager"></div>

					<script type="text/javascript">
						var $path_base = ".";//in Ace demo this will be used for editurl parameter
					</script>

					<!-- PAGE CONTENT ENDS -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.page-content-area -->
	</div>
	<!-- /.page-content -->


	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		var regist_type = {
			1 : "门户注册",
			2 : "主用户添加",
			3 : "系统添加",
			4 : "qq自动注册",
			5 : "微博自动注册"
		};
		var user_status = {
			0 : "<span class='label label-sm label-info arrowed arrowed-righ'>初始</span>",
			1 : "<span class='label label-sm label-success'>激活</span>",
			2 : "<span class='label label-sm label-warning'>未激活</span>",
			4 : "<span class='label label-sm label-inverse arrowed-in'>删除</span>"
		};//INTITAL(0), ACTIVE(1), INACTIVE(2), DELETED(4);
		jQuery(function($) {
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";

			//resize to fit page size
			$(window).on(
					'resize.jqGrid',
					function() {
						$(grid_selector).jqGrid('setGridWidth',
								$(".page-content").width());
					})
			//resize on sidebar collapse/expand
			var parent_column = $(grid_selector).closest('[class*="col-"]');
			$(document).on(
					'settings.ace.jqGrid',
					function(ev, event_name, collapsed) {
						if (event_name === 'sidebar_collapsed'
								|| event_name === 'main_container_fixed') {
							//setTimeout is for webkit only to give time for DOM changes and then redraw!!!
							setTimeout(function() {
								$(grid_selector).jqGrid('setGridWidth',
										parent_column.width());
							}, 0);
						}
					})

			jQuery(grid_selector)
					.jqGrid(
							{
								url : "/user/customerList.do",
								datatype : "json",
								height : 390,
								colNames : [ '操作', '用户名/邮箱', '昵称', '注册方式',
										'状态', '注册时间' ],
								colModel : [
										{
											name : 'test',
											index : '',
											width : 150,
											fixed : true,
											sortable : false,
											resize : false,
											formatter : function(cellvalue,
													options, rowObject) {
												var id = rowObject.id;
												console.log("id=" + id);
												return applyActions(id,
														"view,active,inActive,edit,delete");
											}
										},
										{
											name : 'userName',
											index : 'userName',
											width : 160,
											formatter : function(cellvalue,
													options, rowObject) {
												return rowObject.userName
														|| rowObject.email;
											}
										},
										{
											name : 'nickName',
											index : 'nickName',
											width : 160
										},
										{
											name : 'registerType',
											index : 'registerType',
											width : 100,
											formatter : function(cellvalue,
													options, rowObject) {
												return regist_type[rowObject.registerType];
											}
										},
										{
											name : 'status',
											index : 'status',
											width : 70,
											formatter : function(cellvalue,
													options, rowObject) {
												return user_status[rowObject.status];
											}
										}, {
											name : 'createdTime',
											index : 'createdTime',
											width : 120
										}, ],
								jsonReader : {
									root : "data",
									total : "totalpages",
									page : "currpage",
									records : "totalCount",
									repeatitems : false
								},

								viewrecords : true,
								rowNum : 10,
								rowList : [ 10, 20, 30 ],
								pager : pager_selector,
								altRows : true,
								//toppager: true,

								multiselect : true,
								//multikey: "ctrlKey",
								multiboxonly : true,

								loadComplete : function() {
									var table = this;
									setTimeout(function() {
										styleCheckbox(table);
										updateActionIcons(table);
										updatePagerIcons(table);
										enableTooltips(table);
									}, 0);
								},

								//editurl: "/dummy.html",//nothing is saved
								caption : "用户列表",
								autowidth : true,

							});
			$(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

			function grid_row_view(id) {

			}
			function grid_row_delete(id) {

			}
			function grid_row_edit(id) {

			}
			function grid_row_active(id) {

			}
			function grid_row_inAactive(id) {

			}

			function addFunction() {
				alert(t);
			}
			function delFunction() {
				alert(2);
			}
			function refreshFuction() {
				alert(3);
			}
			function searchFunction() {
				alert(4);
			}

			applyNavButton({
				add : true,
				addFunction : function() {
					addFunction();
				},
				del : true,
				delFunction : delFunction,
				refresh : true,
				refreshFuction : refreshFuction,
				search : true,
				searchFunction : searchFunction
			});

		});
	</script>
</body>
</html>
