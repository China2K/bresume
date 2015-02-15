<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html xmlns:wb="http://open.weibo.com/wb">
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script>
<footer>
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<span class="copyright">Copyright &copy; bresume.com
					2014-2015</span>
			</div>
			<div class="col-md-4">
				<ul class="list-inline social-buttons">
					<!-- <li><a href="#"><i class="fa fa-twitter"></i></a></li>
					<li><a href="#"><i class="fa fa-facebook"></i></a></li>
					<li><a href="#"><i class="fa fa-linkedin"></i></a></li> -->
					<li><a href="javascript:void(0);"
						onclick="window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url='+encodeURIComponent(document.location.href));return 
 false;"
						title="分享到QQ空间"><i class="fa fa-qq"></i></a></li>



					<li><a href="#"><i class="fa fa-weibo"><wb:share-button appkey="xxxxxx" addition="simple" type="icon"></wb:share-button></i></a></li>
				</ul>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
</footer>
</html>