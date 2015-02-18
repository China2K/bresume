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
</head>

<body class="no-skin">
	<div class="page-content">

		<div class="page-content-area">
			<div class="page-header">
				<h1>
					简历管理<small> 
					</small>
				</h1>
			</div>
			<!-- /.page-header -->

			<div class="row">
				<div class="col-xs-12">
					<table id="grid-table"></table>

					<div id="grid-pager"></div>

					<script type="text/javascript">
						var $path_base = ".";//in Ace demo this will be used for editurl parameter
					</script>

					<div class="search-div"
						style="display: none; position: absolute; top: 50px;">
						<div class="col-xs-12 col-sm-4">
							<form onsubmit="return false;" class="grid-search-form">
								<div class="widget-box">
									<div class="widget-header">
										<h4 class="widget-title">查询</h4>

										<div class="widget-toolbar">
											<a href="javascript:void(0);" class="close-search-btn"> <i
												class="ace-icon fa fa-times"></i>
											</a>
										</div>
									</div>

									<div class="widget-body">
										<div class="widget-main">
											<div>
												<label for="form-field-8">名称</label> <input type="text"
													name="name" class="form-control" placeholder="Default Text" />
											</div>
											<hr />
											<div>
												<label for="form-field-8">email</label> <input type="text"
													name="user.email" class="form-control"
													placeholder="Default Text" />
											</div>

											<hr />

											<div>
												<label for="form-field-9">注册方式</label> <select
													name="isPublic" class="form-control"">
													<option value="">全部</option>
													<option value="0">否</option>
													<option value="1">是</option>
												</select>
											</div>

											<hr />

											<div>
												<label for="form-field-11">状态</label> <select name="status"
													class="form-control">
													<option value="">全部</option>
													<option value="0">初始</option>
													<option value="1">激活</option>
													<option value="2">未激活</option>
												</select>

											</div>
											<hr />
											<div>
												<span class="input-group-btn">
													<button type="button"
														class="btn btn-purple btn-sm sub-search-btn">
														搜索 <i
															class="ace-icon fa fa-search icon-on-right bigger-110"></i>
													</button>
												</span>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
						<!-- /.span -->
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
		var resume_status = {
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
			url : "/resume/list.do",
			datatype : "json",
			height : 390,
			colNames : [ '操作', '用户', '名称', '是否推荐', '状态', '创建时间' ],
			colModel : [ {
				name : 'test',
				index : '',
				width : 150,
				fixed : true,
				sortable : false,
				resize : false,
				formatter : function(cellvalue, options, rowObject) {
					var id = rowObject.id;
					return applyActions(id, "view,active,inActive,delete");
				}
			}, {
				name : 'user.email',
				index : 'user.email',
				width : 160,
				formatter : function(cellvalue, options, rowObject) {
					return rowObject.user.userName || rowObject.user.email;
				}
			}, {
				name : 'name',
				index : 'name',
				width : 160
			}, {
				name : 'recommended',
				index : 'recommended',
				width : 100,
				formatter : function(cellvalue, options, rowObject) {
					return getRecoBtn(rowObject.recommended, rowObject.id);
					/* if (rowObject.recommended === true) {
						return "是";
					}
					return "否"; */
				}
			}, {
				name : 'status',
				index : 'status',
				width : 70,
				formatter : function(cellvalue, options, rowObject) {
					return resume_status[rowObject.status];
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
			caption : "简历列表",
			autowidth : true,

		});
		$(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
		function getRecoBtn(isReco,id){
			var htm="";
			if(isReco===true){
				htm ='是 &nbsp;<button class="btn btn-xs btn-info" onclick="setRecommend('+false+',\''+id+'\')">'
				+ '取消<i class="ace-icon fa fa-arrow-left icon-on-left"></i></button>';
			}else{
				htm ='否 &nbsp;<button class="btn btn-xs btn-success" onclick="setRecommend('+true+',\''+id+'\')">'
				+ '推荐<i class="ace-icon fa fa-arrow-right icon-on-right"></i></button>';
			}
			
			
			return htm;
		}
		
		function setRecommend(_isReco,_id){
			$.ajax({
				url : "/resume/recommend.do",
				type : "GET",
				data : {
					id : _id,
					recommend : _isReco
				},
				dataType : "json",
				success : function(resp) {
					refreshFunction();
					alertInfo(resp.message);
				}

			});
		}
		
		
		var status_url = "/resume/changStatus.do";
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

		function grid_row_view(id) {

		}
		function grid_row_delete(id) {
			change(status_url, 4, id);
		}
		function grid_row_edit(id) {

		}
		function grid_row_active(id) {
			change(status_url, 1, id);
		}
		function grid_row_inActive(id) {
			change(status_url, 2, id);
		}

		function activeFunction() {
			var selectedIds = $(grid_selector).jqGrid("getGridParam",
					"selarrrow");
			if (selectedIds == null || selectedIds == ""
					|| selectedIds == "undefined") {
				alertInfo("请选中至少一行");
				return;
			}
			change(status_url, 1, selectedIds.toString());
		}

		function inActiveFunction() {
			var selectedIds = $(grid_selector).jqGrid("getGridParam",
					"selarrrow");
			if (selectedIds == null || selectedIds == ""
					|| selectedIds == "undefined") {
				alertInfo("请选中至少一行");
				return;
			}
			change(status_url, 2, selectedIds.toString());
		}
		function addFunction() {
			alert(t);
		}
		function delFunction() {

			//获取选中单行
			//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
			//获取选中行
			var selectedIds = $(grid_selector).jqGrid("getGridParam",
					"selarrrow");
			if (selectedIds == null || selectedIds == ""
					|| selectedIds == "undefined") {
				alertInfo("请选中至少一行");
				return;
			}
			bootbox.confirm("确定删除吗?", function(result) {
				if (result) {
					change(status_url, 4, selectedIds.toString());
				}
			});

		}
		function refreshFunction() {
			jQuery(grid_selector).jqGrid('setGridParam', {
				page : 1
			}).trigger("reloadGrid");
		}
		function searchFunction() {
			$(".search-div").css("display", "block");
		}

		applyNavButton({
			/* add : true,
			addFunction : function() {
				addFunction();
			}, */
			del : true,
			delFunction : delFunction,
			refresh : true,
			refreshFunction : refreshFunction,
			search : true,
			searchFunction : searchFunction,
			active : true,
			activeFunction : activeFunction,
			inActive : true,
			inActiveFunction : inActiveFunction,
		});

		$(".sub-search-btn").click(function() {
			var fields = $('.grid-search-form').serializeArray(); //自动序列化表单元素为JSON对象
			var _query = "";
			$.each(fields, function(i, field) {
				_query += field.name + ":" + field.value + ",";//设置查询参数
			});
			jQuery(grid_selector).jqGrid('setGridParam', {
				page : 1,
				postData : {
					query : _query
				}
			}).trigger("reloadGrid");
			$(".search-div").css("display", "none");
		});
	</script>
</body>
</html>
