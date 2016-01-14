<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>知识获取工具</title>
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
	<link rel="stylesheet" type="text/css" href="/culture/media/css/bootstrap-fileupload.css" />
	<link rel="stylesheet" href="/culture/media/css/DT_bootstrap.css" />
	<link rel="stylesheet" href="/culture/media/css/search.css"  type="text/css"/>
	<link rel="stylesheet" href="/culture/media/css/jquery.easy-pie-chart.css"  type="text/css" media="screen"/>
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
							知识获取工具
						</h3>
						<ul class="breadcrumb">
							<li>
								<i class="icon-home"></i>
								<a href="index.html">主页</a> 
								<span class="icon-angle-right"></span>
							</li>
							<li>
								<a href="#">知识获取工具</a>
								<span class="icon-angle-right"></span>
							</li>
						</ul>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN SAMPLE FORM PORTLET-->   
						<div class="portlet box blue">
							<div class="portlet-title">
								<div class="caption"><i class="icon-reorder"></i>文件上传</div>
							</div>
							<div class="portlet-body form">
								<!-- BEGIN FORM-->
								<form id="classForm" action="/culture/analyze/getIntegrity.do" class="horizontal-form" method="post" enctype="multipart/form-data">
									<div class="row-fluid">
										<div class="span12">
											<div class="control-group">
												<label class="control-label">文物类别</label>
												<div class="controls">
													<select id="type" class="span12 chosen" data-placeholder="请选择类别" tabindex="1" name="type">
														<option value=""></option>
														<c:forEach  items="${oclist}"  var="item"  varStatus="status">
															<option value="${item.cname}">${item.cname}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</div>
									<div class="row-fluid">
										<div class="span12">
											<div class="control-group">
												<label class="control-label">数据文件</label>
												<div class="controls">
													<div class="fileupload fileupload-new" data-provides="fileupload">
														<div class="input-append">
															<div class="uneditable-input">
																<i class="icon-file fileupload-exists"></i> 
																<span class="fileupload-preview"></span>
															</div>
															<span class="btn btn-file">
															<span class="fileupload-new">选择文件</span>
															<span class="fileupload-exists">更改</span>
															<input type="file" class="default" />
															</span>
															<a href="#" class="btn fileupload-exists" data-dismiss="fileupload">删除</a>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								<div class="form-actions">
									<button type="submit" class="btn blue">提交</button>
								</div>
								</form>
							</div>
						</div>
						<!-- END EXTRAS PORTLET-->
					</div>
				</div>
				<div class="row-fluid">
					<div id="tab_1_5" class="tab-pane">
						<!-- BEGIN SAMPLE FORM PORTLET--> 
						<div class="portlet-body">
							<table class="table table-striped table-hover">
								<thead>
									<tr>
										<th>标题</th>
										<th>查看</th>
										<th>修改</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach  items="${instList}"  var="item"  varStatus="status">
										<tr class="">
											<td>${item.title}</td>
											<td><a  href="/culture/instance/viewInstance.do?culId=${item.identifier}" target="_blank">查看</a></td>
											<td><a  href="/culture/instance/editInstance.do?culId=${item.identifier}" target="_blank">修改</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="space5"></div>
						<p id="dynamic_pager_demo2" class="pagination pagination-right"></p>
					</div>
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
	<script type="text/javascript" src="/culture/media/js/bootstrap-fileupload.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="/culture/media/js/app.js"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<script>
		jQuery(document).ready(function() {       
		   // initiate layout and plugins
		   App.init();
		   $("#type option[value='${type}']").attr("selected",true);
		});
		
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