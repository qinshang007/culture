<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>文物实例列表</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="/culture/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="/culture/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="/culture/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="/culture/media/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="/culture/media/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="/culture/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="/culture/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="/culture/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link rel="stylesheet" type="text/css" href="/culture/media/css/select2_metro.css" />
	<link rel="stylesheet" href="/culture/media/css/DT_bootstrap.css" />
	<link rel="stylesheet" href="/culture/media/css/search.css"  type="text/css"/>
	<!-- END PAGE LEVEL STYLES -->
	<link rel="shortcut icon" href="/culture/media/image/favicon.ico" />	
	
</head>
<body class="page-header-fixed">
	<!-- BEGIN HEADER -->
	<%@ include file="/pages/includes/top_banner.jsp"%>
	<!-- END HEADER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container row-fluid">
	<%@ include file="/pages/includes/left_navigation.jsp"%>
		<!-- BEGIN PAGE -->  
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div id="portlet-config" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close" type="button"></button>
					<h3>portlet Settings</h3>
				</div>
				<div class="modal-body">
					<p>Here will be a configuration form</p>
				</div>
			</div>
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN PAGE CONTAINER-->
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							文物实例列表
						</h3>
						<ul class="breadcrumb">
							<li>
								<i class="icon-home"></i>
								<a href="index.html">主页</a> 
								<span class="icon-angle-right"></span>
							</li>
							<li>
								<a href="#">实例管理</a>
								<span class="icon-angle-right"></span>
							</li>
							<li><a href="/culture/instance/culturalList.do?pageStart=1">文物实例列表</a></li>
						</ul>
					</div>
				</div>
				<div class="row-fluid">
					<div id="tab_1_5" class="tab-pane">
						<!-- BEGIN SAMPLE FORM PORTLET--> 
						<div class="row-fluid search-forms search-default">
							<form class="form-search" action="#">
								<div class="chat-form">
									<div class="input-cont">   
										<input id="title" type="text" placeholder="请输入文物名称..." class="m-wrap" name="title"/>
									</div>
									<button id="search" type="button" class="btn green">检索 &nbsp; <i class="m-icon-swapright m-icon-white"></i></button>
								</div>
							</form>
						</div>
						<div class="portlet-body">
							<table class="table table-striped table-hover">
								<thead>
									<tr>
										<th>图片</th>
										<th>标题</th>
										<th>查看</th>
										<th>修改</th>
										<th>删除</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach  items="${cbList}"  var="item"  varStatus="status">
										<tr class="">
											<td><img src="/crelicBase/upload/${item.mainpic}" alt="" style="height:45px;width:45px" /></td>
											<td>${item.title}</td>
											<td><a  href="/culture/instance/viewCultural.do?culId=${item.identifier}" target="_blank">查看</a></td>
											<td><a  href="/culture/instance/editCultural.do?culId=${item.identifier}" target="_blank">修改</a></td>
											<td><a  href="javascript:deleteInstance('${item.identifier}','${item.title}')">删除</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="space5"></div>
						<p id="dynamic_pager_demo2" class="pagination pagination-right"></p>
					</div>
					<!-- END EXTRAS PORTLET-->
				</div>
			</div>
		</div>
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<div class="footer">
		<div class="footer-inner">
			2013 &copy; Metronic by keenthemes.
		</div>
		<div class="footer-tools">
			<span class="go-top">
			<i class="icon-angle-up"></i>
			</span>
		</div>
	</div>
	<iframe name='hidden_frame' id="hidden_frame" style="display:none" ></iframe>  
	<!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<script src="/culture/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="/culture/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="/culture/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
	<script src="/culture/media/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="/culture/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="/culture/media/js/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="/culture/media/js/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="/culture/media/js/jquery.uniform.min.js" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script type="text/javascript" src="/culture/media/js/select2.min.js"></script>
	<script type="text/javascript" src="/culture/media/js/jsonRespUtils.js"></script>
	<script type="text/javascript" src="/culture/media/js/jquery.bootpag.min.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="/culture/media/js/app.js"></script>
	<script src="/culture/media/js/ui-general.js"></script>  
	<!-- END PAGE LEVEL SCRIPTS -->
	<script>
		jQuery(document).ready(function() {       
		   // initiate layout and plugins
		   App.init();
		   var total = '${count}';
		   var now = '${now}';
		   var url = '${url}';
		   UIGeneral.init(total,now,url);
		});
		
		$("#search").click(function () {
			var title = $("#title").val();
			var url="/culture/instance/culturalList.do?title="+title+"&pageStart=1";
	      	location.href = url;
		})
		
	  	function deleteInstance(culId,title){
            if (!confirm("确信要删除吗？")) return;
            var url="/culture/instance/del.do";
            $.post(url,{culId:culId,title:title},function(data){
            	postDelInstance(data);
            });
        }

        function postDelInstance(transport){
            var jresp = new JsonRespUtils(transport);
            if (jresp.isSuccessfully()){
         		location.reload();
            }else{
                alert(jresp.getMessage());
            }
        }
	</script>
	<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>
	<!-- END BODY -->
</body>
</html>