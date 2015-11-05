<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>添加概念</title>
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
	<link rel="stylesheet" type="text/css" href="/culture/media/css/bootstrap-fileupload.css" />
	<link rel="stylesheet" type="text/css" href="/culture/media/css/bootstrap-modal.css" />
	<link rel="stylesheet" type="text/css" href="/culture/media/css/chosen.css" />
	<link rel="stylesheet" type="text/css" href="/culture/media/css/select2_metro.css" />
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
							添加概念
						</h3>
						<ul class="breadcrumb">
							<li>
								<i class="icon-home"></i>
								<a href="index.html">主页</a> 
								<span class="icon-angle-right"></span>
							</li>
							<li>
								<a href="#">本体管理</a>
								<span class="icon-angle-right"></span>
							</li>
							<li><a href="#">添加类</a></li>
						</ul>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN SAMPLE FORM PORTLET-->   
						<div class="portlet box blue">
							<div class="portlet-title">
								<div class="caption"><i class="icon-reorder"></i></div>
							</div>
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form id="dtForm" action="/culture/doctor/save.do" class="form-horizontal" method="post" enctype="multipart/form-data" target="hidden_frame">
								<div class="control-group">
									<label class="control-label">名称</label>
									<div class="controls">
										<input id="cname" type="text" class="span6 m-wrap" name="cname" />
										<span id="cnameSpan" class="help-inline">必填</span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">父概念</label>
									<div class="controls">
										<select id="cfid" class="span6 chosen" data-placeholder="选择父概念" tabindex="1" name="cfid">
											<c:choose>
												<c:when test="${cfid == 0}">
													<option value="0">无</option>
												</c:when>
												<c:otherwise>
													<option value="${cfid}"><c:if test="${not empty cfname}">${cfname}</c:if></option>							
												</c:otherwise>
											</c:choose>
											<c:forEach  items="${oclist}"  var="item"  varStatus="status">
												<option value="${item.cid}">${item.cname}</option>
											</c:forEach>
										</select>
										<span class="help-inline">必填</span>
									</div>
								</div>
							</form>
							<div class="form-actions">
								<button type="submit" class="btn blue" onclick="submit();">提交</button>
								<button type="reset" class="btn">重置</button>                            
							</div>
						</div>
						</div>
						<!-- END EXTRAS PORTLET-->
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
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script type="text/javascript" src="/culture/media/js/bootstrap-fileupload.js"></script>
	<script type="text/javascript" src="/culture/media/js/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="/culture/media/js/jsonRespUtils.js"></script>
	<script type="text/javascript" src="/culture/media/js/validate.js"></script>
	<script type="text/javascript" src="/culture/media/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="/culture/media/js/additional-methods.min.js"></script>
	<script type="text/javascript" src="/culture/js/tableUtils.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="/culture/media/js/app.js"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<script>
	
		var check = true;	//验证概念名字是否重复
		
		jQuery(document).ready(function() {       
		   // initiate layout and plugins
		   App.init();
		});
		
		//验证概念名字是否存在
		$(function () {//加载事件
            var cnameInput = $("#cname");//id为用户名文本框id
            //光标离开事件
            cnameInput.blur(function(){//function为匿名函数
                var url="/culture/class/isCnameExist.do";
                var cname = $("#cname").val();
                $.post(url,{cname:cname},function(data){
                    var jresp = new JsonRespUtils(data);
                    if (jresp.isSuccessfully()){
                    	$("#cnameSpan").html('概念名字已存在！');
                    	$("#cnameSpan").css({"color":"red"});
                 		cnameInput.focus();
                 		check = false;
                    }else{
                    	$("#cnameSpan").html('必填');
                    	check = true;
                    }
                });
           });
 		});
		
		
		function submit(){
			if(!check){
				alert("概念名字已存在！");
				return;
			}
			var URL = "/culture/class/save.do";
			var cname = $("#cname").val();
			var cfid = $("#cfid").val();
			var cfname = $("#cfid option:selected").text();
		    $.ajax({
	        url: URL,
	    type: 'POST',
	        data:"cname="+cname+"&cfid="+cfid+"&cfname="+cfname,
		    success: function(transport)
		    {
		    	 var jresp = new JsonRespUtils(transport);
		    	 if (jresp.isSuccessfully()){
		    		 var res = jresp.getMessage();
		    		 alert("保存成功！");
		    	 }
		    	 location.href="/culture/class/addClass.do?cfid="+cfid+"&cfname="+cfname;
		    },
		     error: function(transport)
		     {
		    	alert("保存失败！");
		     }        
		    });

		}
		
	</script>
	<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>
	<!-- END BODY -->
</body>
