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

.ace-thumbnails li{
border: none;

}
</style>
</head>

<body class="no-skin">
	<div class="page-content">
		<div class="page-content-area">
			<div class="page-header">
				<h1>
					网站管理 <small> <i
						class="ace-icon fa fa-angle-double-right"></i> 热门简历
					</small>
				</h1>
			</div>
			<!-- /.page-header -->

			
					<!-- PAGE CONTENT BEGINS -->
					<div>
						<ul class="ace-thumbnails clearfix">
							<c:forEach items="${hotResumes}" var="resume">
								<li id="item-${resume.id}"><a href="${portalUrlPrefix}/resume/${resume.name}"  data-rel="colorbox"> 
										<img width="150" height="150" alt="150x150" src="${staticUrlPrefix}${resume.coverUrl}" />
									</a>
		
									<%--  <div class="tags">
										<span class="label-holder"> <span
											class="label label-info">breakfast</span>
										</span> <span class="label-holder"> <span
											class="label label-danger">fruits</span>
										</span> <span class="label-holder"> <span
											class="label label-success">toast</span>
										</span> <span class="label-holder"> <span
											class="label label-warning arrowed-in">diet</span>
										</span>
									</div> --%>
		
									<div class="tools">
										<!-- <a href="#"> <i class="ace-icon fa fa-link"></i></a>
										<a href="#"> <i class="ace-icon fa fa-paperclip"></i></a> -->
										<a href="javascript:reorderHot('${resume.id}');"> <i class="ace-icon fa fa-pencil"></i></a> 
										<a href="javascript:removeHot('${resume.id}');"> <i class="ace-icon fa fa-times red"></i></a>
									</div>
								</li>
							</c:forEach>
							
							<li>
								<a href="#"id="add-tem-btn" class="btn btn-info btn-app radius-8" style="margin-top: 35px;margin-left: 25px;">
												<i class="ace-icon fa glyphicon-plus bigger-350"></i>
												<span class="badge badge-pink">+1</span>
											</a>
							</li>
							
						</ul>
						
					</div>
					<div class="dialog-grid" style="display:none;z-index:99;position: absolute; top: 20px; left: 100px; text-align: center; width: 800px;background-color: white;">
						<table id="grid-table"></table>
						<div id="grid-pager"></div>
					</div>
					
			
		</div>
	</div>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
	function reorderHot(id){
 		bootbox.prompt("请输入新的序号", function(result) {
 			if (result === null) {
 				
 			}else{
 				var order=1;
				if (result != null&&result != "") {
					order = result * 1;
				}
				
				$.ajax({
					url:"/resume/setReommend.do",
					data:{id:id,recommend:true,order:order},
					dataType:"json",
					success:function(resp){
						alertInfo(resp.message);
					}
						
				});
 			}
				
		}); 
	}
	
	function removeHot(id){
		bootbox.confirm("确定删除吗?", function(result) {
			if (result) {
				$.ajax({
					url:"/resume/setReommend.do",
					data:{id:id,recommend:false},
					dataType:"json",
					success:function(resp){
						alertInfo(resp.message);
						$("#item-"+id).remove();
					}
						
				});
			}
		});

		
		
	}
	
	
	var tem_status = {
			0 : "<span class='label label-sm label-info arrowed arrowed-righ'>初始</span>",
			1 : "<span class='label label-sm label-success'>激活</span>",
			2 : "<span class='label label-sm label-warning'>未激活</span>",
			4 : "<span class='label label-sm label-inverse arrowed-in'>删除</span>"
		};//INTITAL(0), ACTIVE(1), INACTIVE(2), DELETED(4);
		
		var grid_selector = "#grid-table";
		var pager_selector = "#grid-pager";
	jQuery(grid_selector).jqGrid({
		url : "/resume/list.do",
		postData:{query :"status:1,recommended:0"},
		datatype : "json",
		height : 390,
		colNames : [ '名称' ,'用户' , '使用模版','创建时间','查看' ],
		colModel : [ {
			name : 'name',
			index : 'name',
			width : 160
		},{
			name : 'user.email',
			index : 'user.email',
			width : 160,
			formatter : function(cellvalue, options, rowObject) {
				return rowObject.user.userName || rowObject.user.email;
			}
		}, {
			name : 'templateSn',
			index : 'templateSn',
			width : 160
		}, {
			name : 'createdTime',
			index : 'createdTime',
			width : 120
		},{
			name : '',
			index : '',
			width : 150,
			fixed : true,
			sortable : false,
			resize : false,
			formatter : function(cellvalue, options, rowObject) {
				var id = rowObject.id;
				return applyActions(id, "view");
			}
		} ],
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
		toolbar:[true,"top"],
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

		caption : "简历列表",
		autowidth : true,

	});
	
	function grid_row_view(id) {
		window.open("http://www.baidu.com");
	}
	
	function activeFunction (){
		
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
		var ids = selectedIds.toString();
		$.ajax({
			url : "/resume/setReommends.do",
			type : "POST",
			data : {
				ids : ids,
				recommend : true
			},
			dataType : "json",
			success : function(resp) {
				alertInfo(resp.message);
				$(".dialog-grid").css("display","none");
				subUrl("/resume/hot.do");
			}

		});

	}
	applyNavButton({
		active : true,
		activeFunction : activeFunction,
	});
	
	
	$("#add-tem-btn").click(function(){
		$(".dialog-grid").css("display","block");
		jQuery(grid_selector).jqGrid('setGridParam', {
			page : 1
		}).trigger("reloadGrid");
	});
	
	var closeDialog =function (){
		$(".dialog-grid").css("display","none");
	};
	
	function applyCloseBtn(){
		var _a = $("<a></a>");
		_a.on("click", closeDialog);
		_a.attr("role", "link");
		_a.attr("cellpadding", "20");
		_a.addClass("ui-jqgrid-titlebar-close ui-corner-all HeaderButton");
		_a.css("right","0px");
		_a.css("background-color","white");
		_a.css("margin-top","5px");
		var _span=$("<span></span>");
		_span.addClass("ace-icon fa fa-times");
		_span.appendTo(_a);
		
		_a.appendTo($("#gview_grid-table .ui-jqgrid-titlebar"));
	}
	
	applyCloseBtn();
	</script>
</body>
</html>
