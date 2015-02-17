String.prototype.endWith = function(s) {
	if (s == null || s == "" || this.length == 0 || s.length > this.length) {
		return false;
	}

	if (this.substring(this.length - s.length) == s) {
		return true;
	} else {
		return false;
	}
	return true;
};

String.prototype.startWith = function(s) {
	if (s == null || s == "" || this.length == 0 || s.length > this.length) {
		return false;
	}
	if (this.substr(0, s.length) == s) {
		return true;
	} else {
		return false;
	}
	return true;
};

String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, '');
};
function style_edit_form(form) {
	// enable datepicker on "sdate" field and switches for "stock" field
	form.find('input[name=sdate]').datepicker({
		format : 'yyyy-mm-dd',
		autoclose : true
	}).end().find('input[name=stock]').addClass('ace ace-switch ace-switch-5')
			.after('<span class="lbl"></span>');
	// don't wrap inside a label element, the checkbox value won't be submitted
	// (POST'ed)
	// .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline"
	// />').after('<span class="lbl"></span>');

	// update buttons classes
	var buttons = form.next().find('.EditButton .fm-button');
	buttons.addClass('btn btn-sm').find('[class*="-icon"]').hide();// ui-icon,
	// s-icon
	buttons.eq(0).addClass('btn-primary').prepend(
			'<i class="ace-icon fa fa-check"></i>');
	buttons.eq(1).prepend('<i class="ace-icon fa fa-times"></i>')

	buttons = form.next().find('.navButton a');
	buttons.find('.ui-icon').hide();
	buttons.eq(0).append('<i class="ace-icon fa fa-chevron-left"></i>');
	buttons.eq(1).append('<i class="ace-icon fa fa-chevron-right"></i>');
}

function style_delete_form(form) {
	var buttons = form.next().find('.EditButton .fm-button');
	buttons.addClass('btn btn-sm btn-white btn-round').find('[class*="-icon"]')
			.hide();// ui-icon, s-icon
	buttons.eq(0).addClass('btn-danger').prepend(
			'<i class="ace-icon fa fa-trash-o"></i>');
	buttons.eq(1).addClass('btn-default').prepend(
			'<i class="ace-icon fa fa-times"></i>')
}

function style_search_filters(form) {
	form.find('.delete-rule').val('X');
	form.find('.add-rule').addClass('btn btn-xs btn-primary');
	form.find('.add-group').addClass('btn btn-xs btn-success');
	form.find('.delete-group').addClass('btn btn-xs btn-danger');
}
function style_search_form(form) {
	var dialog = form.closest('.ui-jqdialog');
	var buttons = dialog.find('.EditTable')
	buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info')
			.find('.ui-icon').attr('class', 'ace-icon fa fa-retweet');
	buttons.find('.EditButton a[id*="_query"]').addClass(
			'btn btn-sm btn-inverse').find('.ui-icon').attr('class',
			'ace-icon fa fa-comment-o');
	buttons.find('.EditButton a[id*="_search"]').addClass(
			'btn btn-sm btn-purple').find('.ui-icon').attr('class',
			'ace-icon fa fa-search');
}

function beforeDeleteCallback(e) {
	var form = $(e[0]);
	if (form.data('styled'))
		return false;

	form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner(
			'<div class="widget-header" />')
	style_delete_form(form);

	form.data('styled', true);
}

function beforeEditCallback(e) {
	var form = $(e[0]);
	form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner(
			'<div class="widget-header" />')
	style_edit_form(form);
}

// it causes some flicker when reloading or navigating grid
// it may be possible to have some custom formatter to do this as the grid is
// being created to prevent this
// or go back to default browser checkbox styles for the grid
function styleCheckbox(table) {
	/**
	 * $(table).find('input:checkbox').addClass('ace') .wrap('<label />')
	 * .after('<span class="lbl align-top" />')
	 * 
	 * 
	 * $('.ui-jqgrid-labels th[id*="_cb"]:first-child')
	 * .find('input.cbox[type=checkbox]').addClass('ace') .wrap('<label
	 * />').after('<span class="lbl align-top" />');
	 */
}

// unlike navButtons icons, action icons in rows seem to be hard-coded
// you can change them like this in here if you want
function updateActionIcons(table) {

	var replacement = {
		'ui-ace-icon fa fa-pencil' : 'ace-icon fa fa-pencil blue',
		'ui-ace-icon fa fa-trash-o' : 'ace-icon fa fa-trash-o red',
		'ui-icon-disk' : 'ace-icon fa fa-check green',
		'ui-icon-cancel' : 'ace-icon fa fa-times red'
	};
	$(table).find('.ui-pg-div span.ui-icon').each(function() {
		var icon = $(this);
		var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
		if ($class in replacement)
			icon.attr('class', 'ui-icon ' + replacement[$class]);
	})

}

// replace icons with FontAwesome icons like above
function updatePagerIcons(table) {
	var replacement = {
		'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
		'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
		'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
		'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
	};
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon')
			.each(function() {
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

				if ($class in replacement)
					icon.attr('class', 'ui-icon ' + replacement[$class]);
			})
}

function enableTooltips(table) {
	$('.navtable .ui-pg-button').tooltip({
		container : 'body'
	});
	$(table).find('.ui-pg-div').tooltip({
		container : 'body'
	});
}

// var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

$(document).on('ajaxloadstart', function(e) {
	$(grid_selector).jqGrid('GridUnload');
	$('.ui-jqdialog').remove();
});

function applyActions(id, actions) {
	if (actions == null || actions == "" || actions == "undefined") {
		return "";
	}
	var htm = '<div style="margin-left: 3px;">';

	if (actions.indexOf("view") > -1) {
		var aHtm = '<div title="" style="float: left; cursor: pointer;margin-left: 5px;" class="ui-pg-div"'
				+ 'id="jEditButton_'
				+ id
				+ '" '
				+ 'onclick="grid_row_view(\''
				+ id
				+ '\');"'
				+ 'onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" '
				+ 'data-original-title="详细"><span class="ui-icon  fa-eye blue"></span></div>';

		htm += aHtm;
	}

	if (actions.indexOf("active") > -1) {
		var aHtm = '<div title="" style="float: left; cursor: pointer;margin-left: 5px;" class="ui-pg-div"'
				+ 'id="jEditButton_'
				+ id
				+ '" '
				+ 'onclick="grid_row_active(\''
				+ id
				+ '\');"'
				+ 'onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" '
				+ 'data-original-title="激活"><span class="ui-icon fa-check green"></span></div>';

		htm += aHtm;
	}
	if (actions.indexOf("inActive") > -1) {
		var iHtm = '<div title="" style="float: left; cursor: pointer;margin-left: 5px;" class="ui-pg-div"'
				+ 'id="jEditButton_'
				+ id
				+ '" '
				+ 'onclick="grid_row_inActive(\''
				+ id
				+ '\');"'
				+ 'onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" '
				+ 'data-original-title="下线"><span class="ui-icon fa-ban grey"></span></div>';

		htm += iHtm;
	}

	if (actions.indexOf("edit") > -1) {
		var editHtm = '<div title="" style="float: left; cursor: pointer;margin-left: 5px;" class="ui-pg-div ui-inline-edit"'
				+ 'id="jEditButton_'
				+ id
				+ '" '
				+ 'onclick="grid_row_edit(\''
				+ id
				+ '\');"'
				+ 'onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" '
				+ 'data-original-title="编辑"><span class="ui-icon ui-icon-pencil"></span></div>';

		htm += editHtm;
	}
	if (actions.indexOf("delete") > -1) {
		var dHtm = '<div title="" style="float: left; cursor: pointer;margin-left: 5px;" class="ui-pg-div ui-inline-del"'
				+ 'id="jDeleteButton_'
				+ id
				+ '" '
				+ 'onclick="grid_row_delete(\''
				+ id
				+ '\');"'
				+ 'onmouseover="jQuery(this).addClass(\'ui-state-hover\');" onmouseout="jQuery(this).removeClass(\'ui-state-hover\')" '
				+ 'data-original-title="删除"><span class="ui-icon ui-icon-trash"></span></div>';

		htm += dHtm;
	}
	htm += "</div>";

	return htm;
}

function applyNavButton(option) {
	// grid-pager_left
	var table = $("<table></table>");

	table.attr("cellspacing", "0");
	table.attr("cellpadding", "0");
	table.attr("border", "0");
	table.addClass("ui-pg-table");
	table.addClass("navtable");
	table.css({
		"float" : "left",
		"table-layout" : "auto"
	});

	var tr = $("<tr></tr>");
	tr.appendTo(table);
	if (option.active == true) {
		var _td = createTD(5, option.activeFunction);
		_td.appendTo(tr);
	}
	if (option.inActive == true) {
		var _td = createTD(6, option.inActiveFunction);
		_td.appendTo(tr);
	}

	if (option.add == true) {
		var _td = createTD(1, option.addFunction);
		_td.appendTo(tr);
	}
	if (option.del == true) {
		var _td = createTD(2, option.delFunction);
		_td.appendTo(tr);
	}
	if (option.refresh == true) {
		var _td = createTD(3, option.refreshFunction);
		_td.appendTo(tr);
	}
	if (option.search == true) {
		var _td = createTD(4, option.searchFunction);
		_td.appendTo(tr);
	}
	console.log(table);
	table.appendTo($("#grid-pager_left"));

}

function createTD(type, fn) {

	var iconClass;
	var hoverTitle;
	switch (type) {
	case 1:
		iconClass = "ui-icon ace-icon fa fa-plus-circle purple";
		hoverTitle = "添加一条记录";
		break;
	case 2:
		iconClass = "ui-icon ace-icon fa fa-trash-o red";
		hoverTitle = "删除选中记录";
		break;
	case 3:
		iconClass = "ui-icon ace-icon fa fa-refresh green";
		hoverTitle = "刷新记录";
		break;

	case 4:
		iconClass = "ui-icon ace-icon fa fa-search orange";
		hoverTitle = "搜索";
		break;

	case 5:
		iconClass = "ui-icon ace-icon fa fa-check green";
		hoverTitle = "激活";
		break;

	case 6:
		iconClass = "ui-icon ace-icon fa fa-ban grey";
		hoverTitle = "下线";
		break;

	default:
		break;
	}
	var td = $("<td></td>");
	var _div = $("<div></div>");
	var _span = $("<span></span>");

	_div.appendTo(td);
	_span.appendTo(_div);
	td.on("click", fn);

	_div.addClass("ui-pg-div");
	td.addClass("ui-pg-button ui-corner-all");
	td.attr("data-original-title", hoverTitle);
	_span.addClass(iconClass);

	return td;

}

function alertInfo(msg) {
	bootbox.dialog({
		message : "<span class='bigger-110'>" + msg + "</span>",
		buttons : {
			"click" : {
				"label" : "关闭",
				"className" : "btn-sm btn-primary",
				"callback" : function() {
				}
			},
		}
	});
}

$(".close-search-btn").click(function() {
	$(".search-div").css("display", "none");
});

$(".open-search-btn").click(function() {
	$(".search-div").css("display", "block");
});

// 主要内容部分，要来局部刷新不同模块的主要部分
var _mainContent = $("div#page-container");

/* for inside url forward */
var subUrl = function(url) {
	$(".tooltip").css("display", "none");
	// 主要内容部分，要来局部刷新不同模块的主要部分
	$.ajax({
		type : "GET",
		url : url,
		async : false,
		cache : false,
		dataType : "html",
		error : function(request) {
			if (request.status == "747") {
				window.location.href = "/login.do";
			} else {
				alert("跳转页面出现错误");
			}
		},
		success : function(data) {
			// 会寻找返回的HTML中是否
			// 存在fullpage:true的注释，如果存在则整张页面改变（body里面内容替换为响应返回的html）
			// 不想整张页面变化，则不需要别的操作
			if (data.indexOf("<!--fullpage:true-->") >= 0) {
				// 整个页面改变
				$("body").html(data);
			} else {
				// 局部页面填充，改变 class=mainCont的内部
				_mainContent.html(data);
				// 改变面包屑
				// changeCrumb(generateCrumbArray(a));
			}
		}
	});
};

function makeFileUpload(fileEle, imgEle, valueEle, upload_info) {
	fileEle.uploadify({
		'uploader' : 'http://static.bresume.com/upload/uploadImg',
		'swf' : '/resource/plugin/uploadify/uploadify.swf',
		'cancelImage' : '/resource/plugin/uploadify/cancel.png',
		'queueID' : 'imgFile',
		'fileObjName' : 'imgFile',
		'auto' : true,
		'width' : 120,
		'height' : 40,
		'multi' : false,
		'buttonText' : '上传图片',
		'formData' : {
			'upload_info' : upload_info,
			'source' : 'ADMIN'
		},
		'onUploadSuccess' : function(file, data, response) {
			data = JSON.parse(data);
			console.log(data);
			var res = data.success;
			if (res === true) {
				valueEle.val(data.message);
				imgEle.attr("src", "http://static.bresume.com" + data.message);
			} else {
				alert(data.message);
			}
		},
	});

}