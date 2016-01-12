<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>数据分析</title>
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
	<link rel="stylesheet" href="/culture/media/css/jquery.jqplot.min.css"  type="text/css"/>
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
							基于朝代的数据统计
						</h3>
						<ul class="breadcrumb">
							<li>
								<i class="icon-home"></i>
								<a href="index.html">主页</a> 
								<span class="icon-angle-right"></span>
							</li>
							<li>
								<a href="#">数据分析</a>
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
								<div class="caption"><i class="icon-reorder"></i>统计分析</div>
							</div>
							<div class="portlet-body form">
								<!-- BEGIN FORM-->
								<form id="classForm" action="/culture/analyze/dynastyAnalyze.do" class="horizontal-form" method="post" enctype="multipart/form-data">
									<div class="row-fluid">
										<div class="span12">
											<div class="control-group">
												<label class="control-label">创作朝代</label>
												<div class="controls">										
													<select id="creation_date" class="span12 chosen classification-select" data-placeholder="请选择朝代" tabindex="1" name="creation_date">
														<option value=""></option>
														<c:forEach  items="${creationDateList}"  var="item"  varStatus="status">
															<option value="${item.cname}">${item.cname}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</div>
									<div class="form-actions">
										<button type="submit" class="btn blue">查看</button>
									</div>
								</form>
							</div>
						</div>
						<!-- END EXTRAS PORTLET-->
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="portlet box yellow">
							<div class="portlet-title">
								<div class="caption"><i class="icon-reorder"></i>统计结果</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
									<a href="#portlet-config" data-toggle="modal" class="config"></a>
									<a href="javascript:;" class="reload"></a>
									<a href="javascript:;" class="remove"></a>
								</div>
							</div>
							<div class="portlet-body">
								<div class="row-fluid">
									<div class="span6">
										<div id="chart1" style="height:350px;"></div>
									</div>
									<div class="span6">
										 <div id="pie1" style="height:350px;"></div>
									</div>
								</div>
							</div>
						</div>
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
	<script type="text/javascript" src="/culture/media/js/jquery.flot.js"></script>
	<script type="text/javascript" src="/culture/media/js/jquery.flot.resize.js"></script>
	<script type="text/javascript" src="/culture/media/js/jquery.flot.pie.js"></script>
	<script type="text/javascript" src="/culture/media/js/jquery.flot.stack.js"></script>
	<script type="text/javascript" src="/culture/media/js/jquery.flot.crosshair.js"></script>
	<script type="text/javascript" src="/culture/media/js/jquery.jqplot.min.js"></script>
	<script type="text/javascript" src="/culture/media/js/jqplot.barRenderer.min.js"></script>
	<script type="text/javascript" src="/culture/media/js/jqplot.pieRenderer.min.js"></script>
	<script type="text/javascript" src="/culture/media/js/jqplot.categoryAxisRenderer.min.js"></script>
	<script type="text/javascript" src="/culture/media/js/jqplot.pointLabels.min.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="/culture/media/js/app.js"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<script class="code" type="text/javascript">
		$(document).ready(function(){
	        $.jqplot.config.enablePlugins = true;
	        var s1 = [parseInt('${qiwu}'), parseInt('${zhiwu}'), parseInt('${jianzhu}'), parseInt('${bihua}')];
	        var ticks = ['器物', '织物', '建筑', '壁画'];
	        
	        plot1 = $.jqplot('chart1', [s1], {
	            // Only animate if we're not using excanvas (not in IE 7 or IE 8)..
	            animate: !$.jqplot.use_excanvas,
	            seriesDefaults:{
	                renderer:$.jqplot.BarRenderer,
	                pointLabels: { show: true }
	            },
	            axes: {
	                xaxis: {
	                    renderer: $.jqplot.CategoryAxisRenderer,
	                    ticks: ticks
	                }
	            },
	            highlighter: { show: false }
	        });
	    
	        $('#chart1').bind('jqplotDataClick', 
	            function (ev, seriesIndex, pointIndex, data) {
	                $('#info1').html('series: '+seriesIndex+', point: '+pointIndex+', data: '+data);
	            }
	        );
	    });
	</script>

	<script class="code" type="text/javascript">
		$(document).ready(function(){
		    var plot1 = $.jqplot('pie1', [[['器物',parseInt('${qiwu}')],['织物',parseInt('${zhiwu}')],['建筑',parseInt('${jianzhu}')],['壁画',parseInt('${bihua}')]]], {
		        gridPadding: {top:0, bottom:38, left:0, right:0},
		        seriesDefaults:{
		            renderer:$.jqplot.PieRenderer, 
		            trendline:{ show:false }, 
		            rendererOptions: { padding: 8, showDataLabels: true }
		        },
		        legend:{
		            show:true, 
		            placement: 'outside', 
		            rendererOptions: {
		                numberRows: 1
		            }, 
		            location:'s',
		            marginTop: '15px'
		        }       
		    });
		});
	</script>

	<script>
		jQuery(document).ready(function() {       
		   // initiate layout and plugins
		   App.init();
		   $("#creation_date option[value='${creation_date}']").attr("selected",true);
		});
		
	</script>
	<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>
	<!-- END BODY -->
</body>
</html>