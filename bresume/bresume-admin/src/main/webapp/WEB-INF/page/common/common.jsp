<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>




<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="/resource/site/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="/resource/site/font-awesome/4.1.0/css/font-awesome.min.css" />

<!-- page specific plugin styles -->
<link rel="stylesheet" href="/resource/site/css/jquery-ui.min.css" />
<link rel="stylesheet" href="/resource/site/css/datepicker.css" />
<link rel="stylesheet" href="/resource/site/css/ui.jqgrid.css" />

<!-- text fonts -->
<link rel="stylesheet"
	href="http://fonts.useso.com/css?family=Open+Sans:400,300" />

<!-- ace styles -->
<link rel="stylesheet" href="/resource/site/css/ace.min.css"
	id="main-ace-style" />

<!--[if lte IE 9]>
			<link rel="stylesheet" href="/resource/site/css/ace-part2.min.css" />
		<![endif]-->
<link rel="stylesheet" href="/resource/site/css/ace-skins.min.css" />
<link rel="stylesheet" href="/resource/site/css/ace-rtl.min.css" />

<!-- common styles -->
<link rel="stylesheet" href="/resource/common/common.css" />

<link rel="stylesheet" href="/resource/plugin/uploadify/uploadify.css" />

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="/resource/site/css/ace-ie.min.css" />
		<![endif]-->

<!-- inline styles related to this page -->


<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
		<script src="/resource/site/js/html5shiv.min.js"></script>
		<script src="/resource/site/js/respond.min.js"></script>
		<![endif]-->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="/resource/site/js/jquery/jquery-2.1.1.min.js"></script>
<!-- <![endif]-->

<!--[if IE]>
		<script src="/resource/site/js/jquery/jquery-1.11.1.min.js"></script>
		<![endif]-->

<!--[if !IE]> -->
<script type="text/javascript">
			window.jQuery || document.write("<script src='/resource/site/js/jquery.min.js'>"+"<"+"/script>");
		</script>
<!-- <![endif]-->

<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='/resource/site/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->

<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='/resource/site/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
<script src="/resource/site/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->
<script src="/resource/site/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="/resource/site/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="/resource/site/js/jqGrid/i18n/grid.locale-en.js"></script>

<!-- ace settings handler -->
<script src="/resource/site/js/ace-extra.min.js"></script>

<!-- ace scripts -->
<script src="/resource/site/js/ace-elements.min.js"></script>
<script src="/resource/site/js/ace.min.js"></script>



<script src="/resource/site/js/bootbox.min.js"></script>

<script type="text/javascript"
		src="<c:url value ='/resource/site/js/jquery.form.js'/>"
		charset="UTF-8"></script>
		
<script
		src="<c:url value ='/resource/plugin/uploadify/jquery.uploadify.js'/>"></script>

<script src="/resource/common/common.js"></script>

<!-- jQuery-Validation-Engine -->
<link rel="stylesheet"
	href="<c:url value ='/resource/plugin/jQuery-Validation-Engine/css/validationEngine.jquery.css'/>">
<script
	src="<c:url value ='/resource/plugin/jQuery-Validation-Engine/js/jquery.validationEngine-zh_CN.js'/>"></script>
<script
	src="<c:url value ='/resource/plugin/jQuery-Validation-Engine/js/jquery.validationEngine.min.js'/>"></script>

