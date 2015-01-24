<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<%@ include file="../common/common.jsp"%>

<style type="text/css">
</style>

<script type="text/javascript">
	
</script>
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

					<div class="col-sm-4">
						<div class="widget-box">
							<div class="widget-header widget-header-flat">
								<h4 class="widget-title smaller"></h4>
							</div>

							<div class="widget-body">
								<div class="widget-main"></div>
							</div>
						</div>
					</div>

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
		var advice_status = {
			0 : "<span class='label label-sm label-info arrowed arrowed-righ'>初始</span>",
			1 : "<span class='label label-sm label-success'>激活</span>",
			2 : "<span class='label label-sm label-warning'>未激活</span>",
			4 : "<span class='label label-sm label-inverse arrowed-in'>删除</span>"
		};//INTITAL(0), ACTIVE(1), INACTIVE(2), DELETED(4);
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

		jQuery(grid_selector).jqGrid({
			url : "/advice/list.do",
			datatype : "json",
			height : 390,
			colNames : ['处理', '用户', '邮箱', '手机号', '状态', '创建时间' ],
			colModel : [ 
			              {
				index : '',
				width : 150,
				fixed : true,
				sortable : false,
				resize : false,
				formatter : function(cellvalue, options, rowObject) {
					var id = rowObject.id;
					return applyActions(id, "active,delete");
				}
			},{
				name : 'name',
				index : 'name',
				width : 160,
				formatter : function(cellvalue, options, rowObject) {
					return rowObject.name;
				}
			}, {
				name : 'email',
				index : 'email',
				width : 160
			}, {
				name : 'cellPhone',
				index : 'cellPhone',
				width : 100
			}, {
				name : 'status',
				index : 'status',
				width : 70,
				formatter : function(cellvalue, options, rowObject) {
					return advice_status[rowObject.status];
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
			rownumbers : true,
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
			caption : "用户建议列表",
			autowidth : true,

		});
		$(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

		var status_url = "/advice/changStatus.do";
		function change(_url, _status, _ids) {
			$.ajax({
				url : _url,
				type : "GET",
				data : {
					ids : _ids,
					status : _status
				},
				dataType : "json",
				success : function(resp) {
					refreshFunction();
					alertInfo(resp.message);
				}

			});
		}

		var grid_row_view = function(id) {
			alert(22);
		}
		function grid_row_delete(id) {
			change(status_url, 4, id);
		}
		function grid_row_active(id) {
			change(status_url, 1, id);
		}

	</script>
</body>
</html>
