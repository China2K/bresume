<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<%@ include file="../common/common.jsp"%>
</head>

<body class="no-skin">
	<div class="page-content">
		<div class="page-content-area">
			<div class="page-header">
				<h1>
					Gallery <small> <i
						class="ace-icon fa fa-angle-double-right"></i> responsive photo
						gallery using colorbox
					</small>
				</h1>
			</div>
			<!-- /.page-header -->

			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<div>
						<ul class="ace-thumbnails clearfix">
					<!-- 	<li>
											<a href="assets/images/gallery/image-1.jpg" title="Photo Title" data-rel="colorbox">
												<img width="150" height="150" alt="150x150" src="assets/images/gallery/thumb-1.jpg" />
											</a>

											<div class="tags">
												<span class="label-holder">
													<span class="label label-info">breakfast</span>
												</span>

												<span class="label-holder">
													<span class="label label-danger">fruits</span>
												</span>

												<span class="label-holder">
													<span class="label label-success">toast</span>
												</span>

												<span class="label-holder">
													<span class="label label-warning arrowed-in">diet</span>
												</span>
											</div>

											<div class="tools">
												<a href="#">
													<i class="ace-icon fa fa-link"></i>
												</a>

												<a href="#">
													<i class="ace-icon fa fa-paperclip"></i>
												</a>

												<a href="#">
													<i class="ace-icon fa fa-pencil"></i>
												</a>

												<a href="#">
													<i class="ace-icon fa fa-times red"></i>
												</a>
											</div>
										</li> -->
							<c:forEach items="${hotTemplates}" var="template">
								<li><a href="${template.siteUrl}"  data-rel="colorbox"> 
										<img width="150" height="150" alt="150x150" src="${staticUrlPrefix}${template.coverUrl}" />
									</a>
		
									 <div class="tags">
										<span class="label-holder"> <span
											class="label label-info">breakfast</span>
										</span> <span class="label-holder"> <span
											class="label label-danger">fruits</span>
										</span> <span class="label-holder"> <span
											class="label label-success">toast</span>
										</span> <span class="label-holder"> <span
											class="label label-warning arrowed-in">diet</span>
										</span>
									</div>
		
									<div class="tools">
										<!-- <a href="#"> <i class="ace-icon fa fa-link"></i></a>
										<a href="#"> <i class="ace-icon fa fa-paperclip"></i></a> -->
										<a href="reorderHot('${template.id}');"> <i class="ace-icon fa fa-pencil"></i></a> 
										<a href="removeHot('${template.id}');"> <i class="ace-icon fa fa-times red"></i></a>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- page specific plugin scripts -->
	<script src="/resource/site/js/jquery.colorbox-min.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		/* jQuery(function($) {
			var $overflow = '';
			var colorbox_params = {
				rel : 'colorbox',
				reposition : true,
				scalePhotos : true,
				scrolling : false,
				previous : '<i class="ace-icon fa fa-arrow-left"></i>',
				next : '<i class="ace-icon fa fa-arrow-right"></i>',
				close : '&times;',
				current : '{current} of {total}',
				maxWidth : '100%',
				maxHeight : '100%',
				onOpen : function() {
					$overflow = document.body.style.overflow;
					document.body.style.overflow = 'hidden';
				},
				onClosed : function() {
					document.body.style.overflow = $overflow;
				},
				onComplete : function() {
					$.colorbox.resize();
				}
			};

			$('.ace-thumbnails [data-rel="colorbox"]')
					.colorbox(colorbox_params);
			$("#cboxLoadingGraphic").html(
					"<i class='ace-icon fa fa-spinner orange'></i>");//let's add a custom loading icon
		}) */
	</script>
</body>
</html>
