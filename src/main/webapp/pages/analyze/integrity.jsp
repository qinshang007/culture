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
							属性完整度
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
								<form id="classForm" action="/culture/analyze/getIntegrity.do" class="horizontal-form" method="post" enctype="multipart/form-data">
									<div class="row-fluid">
										<div class="span6">
											<div class="control-group">
												<label class="control-label">文物类别</label>
												<div class="controls">
													<select id="type" class="span12 chosen" data-placeholder="请选择类别" tabindex="1" name="type" onchange="getData()">
														<option value=""></option>
														<c:forEach  items="${oclist}"  var="item"  varStatus="status">
															<option value="${item.cname}">${item.cname}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="span6">
											<div class="control-group">
												<label class="control-label">详细分类</label>
												<div class="controls">
													<select id="classification" class="span12 chosen classification-select" data-placeholder="请选择类别" tabindex="1" name="classification">
														<option value=""></option>
														<c:forEach  items="${childlist}"  var="item"  varStatus="status">
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
						<div class="portlet box purple">
							<div class="portlet-title">
								<div class="caption"><i class="icon-calendar"></i>属性完整度</div>
							</div>
							<div class="portlet-body">
								<div class="row-fluid">
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number transactions"  data-percent="${title}"><span>${title}</span>%</div>
											<a class="title" href="#">名称 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number visits"  data-percent="${used_title}"><span>${used_title}</span>%</div>
											<a class="title" href="#">其他名称 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number bounce"  data-percent="${c_level}"><span>${c_level}</span>%</div>
											<a class="title" href="#">级别 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number transactions"  data-percent="${creation_date}"><span>${creation_date}</span>%</div>
											<a class="title" href="#">创作朝代 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number visits"  data-percent="${creation_time}"><span>${creation_time}</span>%</div>
											<a class="title" href="#">创作时间<i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number bounce"  data-percent="${place_of_origin}"><span>${place_of_origin}</span>%</div>
											<a class="title" href="#">产地 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number transactions"  data-percent="${creator}"><span>${creator}</span>%</div>
											<a class="title" href="#">创作者 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number visits"  data-percent="${measurement}"><span>${measurement}</span>%</div>
											<a class="title" href="#">度量<i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number bounce"  data-percent="${excavation_date}"><span>${excavation_date}</span>%</div>
											<a class="title" href="#">出土时间 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number transactions"  data-percent="${excavation_place }"><span>${excavation_place }</span>%</div>
											<a class="title" href="#">出土地点 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number visits"  data-percent="${current_location}"><span>${current_location}</span>%</div>
											<a class="title" href="#">现藏地点<i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number bounce"  data-percent="${exhibition_history}"><span>${exhibition_history}</span>%</div>
											<a class="title" href="#">展览历史 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number transactions"  data-percent="${location}"><span>${location}</span>%</div>
											<a class="title" href="#">地域环境 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number visits"  data-percent="${shape}"><span>${shape}</span>%</div>
											<a class="title" href="#">器形<i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number bounce"  data-percent="${pattern}"><span>${pattern}</span>%</div>
											<a class="title" href="#">纹饰 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number transactions"  data-percent="${color}"><span>${color}</span>%</div>
											<a class="title" href="#">色彩 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number visits"  data-percent="${structure}"><span>${structure}</span>%</div>
											<a class="title" href="#">结构<i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number bounce"  data-percent="${decoration}"><span>${decoration}</span>%</div>
											<a class="title" href="#">装饰 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number transactions"  data-percent="${scene}"><span>${scene}</span>%</div>
											<a class="title" href="#">使用情境 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number visits"  data-percent="${c_usage}"><span>${c_usage}</span>%</div>
											<a class="title" href="#">使用方式<i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number bounce"  data-percent="${symbolic_meaning}"><span>${symbolic_meaning}</span>%</div>
											<a class="title" href="#">象征意义 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number transactions"  data-percent="${aesthetic_desc}"><span>${aesthetic_desc}</span>%</div>
											<a class="title" href="#">审美 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number visits"  data-percent="${social_history_info}"><span>${social_history_info}</span>%</div>
											<a class="title" href="#">社会历史信息<i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number bounce"  data-percent="${material}"><span>${material}</span>%</div>
											<a class="title" href="#">材质<i class="m-icon-swapright"></i></a>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number transactions"  data-percent="${technique}"><span>${technique}</span>%</div>
											<a class="title" href="#">工艺 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number visits"  data-percent="${history_info}"><span>${history_info}</span>%</div>
											<a class="title" href="#">历史信息<i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number bounce"  data-percent="${folklore}"><span>${folklore}</span>%</div>
											<a class="title" href="#">民间典故 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number transactions"  data-percent="${relation}"><span>${relation}</span>%</div>
											<a class="title" href="#">关联 <i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number visits"  data-percent="${source}"><span>${source}</span>%</div>
											<a class="title" href="#">资料来源<i class="m-icon-swapright"></i></a>
										</div>
									</div>
									<div class="margin-bottom-10 visible-phone"></div>
									<div class="span2">
										<div class="easy-pie-chart">
											<div class="number bounce"  data-percent="${rights}"><span>${rights}</span>%</div>
											<a class="title" href="#">作品授权<i class="m-icon-swapright"></i></a>
										</div>
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
	<script type="text/javascript" src="/culture/media/js/jquery.easy-pie-chart.js" ></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="/culture/media/js/app.js"></script>
	<script src="/culture/media/js/index.js" type="text/javascript"></script>     
	<!-- END PAGE LEVEL SCRIPTS -->
	<script>
		jQuery(document).ready(function() {       
		   // initiate layout and plugins
		   App.init();
		   $("#type option[value='${type}']").attr("selected",true);
		   $("#classification option[value='${classification}']").attr("selected",true);
		   Index.initMiniCharts();
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
        
	    function getData()  
	    {  
	        var type = $("#type").val();  
	        if(type == '建筑')
	        	type = '建筑物';
	        $.ajax({          
	             url:"/culture/instance/getData.do",  
	             data:{type : type},  
	             type : "post",    
	             cache : false,    
	             dataType : "json",   
	             error:function(){  
	                alert("error occured!!!");  
	             },  
	             success:function(data){             
	                var classification = document.getElementById('classification');  
	                //清空数组  
	                classification.length = 0;  
	                for(var i=0;i<data.length;i++){  
	                     var xValue=data[i].cname;    
	                     var xText=data[i].cname;    
	                     var option=new Option(xText,xValue);    
	                     classification.add(option);   
	                }  
	                $(".classification-select").trigger("liszt:updated");
	           }  
	       });  
	    }  
        
	</script>
	<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>
	<!-- END BODY -->
</body>
</html>