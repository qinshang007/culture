<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>添加二元规则</title>
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
							添加二元规则
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
						</ul>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN SAMPLE FORM PORTLET-->   
						<div class="portlet box blue">
							<div class="portlet-title">
								<div class="caption"><i class="icon-reorder"></i>文物推理规则</div>
							</div>
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form id="dtForm" action="/culture/doctor/save.do" class="form-horizontal" method="post" enctype="multipart/form-data" target="hidden_frame">
								<input id="opBodyType1" type="hidden" name="opBodyType1" value="${first.ptype}" />
								<input id="opBodyType2" type="hidden" name="opBodyType2" value="${first.ptype}" />
								<input id="opHeadType" type="hidden" name="opHeadType" value="${first.ptype}" />
								<h3 class="form-section">条件1</h3>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label">属性名称</label>
											<div class="controls">
												<select id="opBody1" class="span6 chosen" data-placeholder="选择属性" tabindex="1" name="opBody" onchange="getData(1)">
													<c:forEach  items="${opList}"  var="item"  varStatus="status">
														<option value="${item.pname}">${item.pname}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label">属性值</label>
											<div class="controls" id="bodyTextDiv1">
												<input id="opBodyTextValue1" type="text" class="m-wrap span12" name="opBodyTextValue1" />
											</div>
											<div class="controls" id="bodySelectDiv1">
												<select id="opBodySelectValue1" class="span6 chosen opBodySelectValue-select1" data-placeholder="选择属性" tabindex="1" name="opBodySelectValue1">
													<c:forEach  items="${first.rangeList}"  var="item"  varStatus="status">
														<option value="${item.cname}">${item.cname}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<h3 class="form-section">条件2</h3>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label">属性名称</label>
											<div class="controls">
												<select id="opBody2" class="span6 chosen" data-placeholder="选择属性" tabindex="1" name="opBody" onchange="getData(2)">
													<c:forEach  items="${opList}"  var="item"  varStatus="status">
														<option value="${item.pname}">${item.pname}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label">属性值</label>
											<div class="controls" id="bodyTextDiv2">
												<input id="opBodyTextValue2" type="text" class="m-wrap span12" name="opBodyTextValue2" />
											</div>
											<div class="controls" id="bodySelectDiv2">
												<select id="opBodySelectValue2" class="span6 chosen opBodySelectValue-select2" data-placeholder="选择属性" tabindex="1" name="opBodySelectValue2">
													<c:forEach  items="${first.rangeList}"  var="item"  varStatus="status">
														<option value="${item.cname}">${item.cname}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<h3 class="form-section">结论</h3>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label">属性名称</label>
											<div class="controls">
												<select id="opHead" class="span6 chosen" data-placeholder="选择属性" tabindex="1" name="opHead" onchange="getData(3)">
													<c:forEach  items="${opList}"  var="item"  varStatus="status">
														<option value="${item.pname}">${item.pname}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label">属性值</label>
											<div class="controls" id="headTextDiv">
												<input id="opHeadTextValue" type="text" class="m-wrap span12" name="opHeadTextValue" />
											</div>
											<div class="controls" id="headSelectDiv">
												<select id="opHeadSelectValue" class="span6 chosen opHeadSelectValue-select" data-placeholder="选择属性" tabindex="1" name="opHeadSelectValue">
													<c:forEach  items="${first.rangeList}"  var="item"  varStatus="status">
														<option value="${item.cname}">${item.cname}</option>
													</c:forEach>
												</select>
											</div>
										</div>
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
		jQuery(document).ready(function() {       
		   // initiate layout and plugins
		   App.init();
		   var firstType = ${first.ptype};	//初始的属性类型，1表示对象属性，2表示数据属性
		   if(firstType == 1){
			   $("#bodySelectDiv1").show();
			   $("#bodySelectDiv2").show();
			   $("#headSelectDiv").show();
			   $("#bodyTextDiv1").hide();
			   $("#bodyTextDiv2").hide();
			   $("#headTextDiv").hide()
		   }else if(firstType == 2){
			   $("#bodySelectDiv1").hide();
			   $("#bodySelectDiv2").hide();
			   $("#headSelectDiv").hide();
			   $("#bodyTextDiv1").show();
			   $("#bodyTextDiv2").show();
			   $("#headTextDiv").show()
		   }
		});

	    function getData(type)  
	    {  
	        var pname = '';
	        if(type == 1 || type == 2){
	        	var opBody = "opBody"+type;
	        	pname =  $("#"+opBody).val();
	        }
	        else if(type == 3)
	        	pname =  $("#opHead").val();
	        $.ajax({          
	             url:"/culture/property/getPropertyByName.do",  
	             data:{pname : pname},  
	             type : "post",    
	             cache : false,    
	             dataType : "json",   
	             error:function(){  
	                alert("error occured!!!");  
	             },  
	             success:function(data){  
	            	if(type == 1 || type == 2){	//
		            	if(data.ptype == 1){	//对象属性
			                var opBodySelectValue = document.getElementById('opBodySelectValue'+type);  
			                //清空数组  
			                opBodySelectValue.length = 0;  
			                var ocList = data.rangeList;
			                for(var i=0;i<ocList.length;i++){  
			                     var xValue=ocList[i].cname;    
			                     var xText=ocList[i].cname;    
			                     var option=new Option(xText,xValue);    
			                     opBodySelectValue.add(option);   
			                }  
			                $(".opBodySelectValue-select"+type).trigger("liszt:updated");
			                $("#bodyTextDiv"+type).hide();
			                $("#bodySelectDiv"+type).show();
			                $("#opBodyTextValue"+type).val('');	
			                $("#opBodyType"+type).val('1');	//设置属性类型为对象属性
		            	}else if(data.ptype == 2){	//数据属性
			                $("#bodyTextDiv"+type).show();
			                $("#bodySelectDiv"+type).hide();
			                $("#opBodySelect"+type).val('');
			                $("#opBodyType"+type).val('2');	//设置属性类型为数据属性
		            	}
	            	}else if(type==3){
		            	if(data.ptype == 1){	//对象属性
			                var opHeadSelectValue = document.getElementById('opHeadSelectValue');  
			                //清空数组  
			                opHeadSelectValue.length = 0;  
			                var ocList = data.rangeList;
			                for(var i=0;i<ocList.length;i++){  
			                     var xValue=ocList[i].cname;    
			                     var xText=ocList[i].cname;    
			                     var option=new Option(xText,xValue);    
			                     opHeadSelectValue.add(option);   
			                }  
			                $(".opHeadSelectValue-select").trigger("liszt:updated");
			                $("#headTextDiv").hide();
			                $("#headSelectDiv").show();
			                $("#opHeadTextValue").val('');
			                $("#opHeadType").val('1');	//设置属性类型为对象属性
		            	}else if(data.ptype == 2){	//数据属性
			                $("#headTextDiv").show();
			                $("#headSelectDiv").hide();
			                $("#opHeadSelectValue").val('');
			                $("#opHeadType").val('2');	//设置属性类型为对象属性
		            	}
	            		
	            	}
	           }  
	       });  
	    }  
		
		function submit(){
			var URL = "/culture/rule/save.do";
			var type = 2;
			//设置条件属性1的属性名和属性值
			var opBodyName1 = $("#opBody1").val();
			var opBodyValue1 = '';
			var opBodyTextValue1 = $("#opBodyTextValue1").val();
			var opBodySelectValue1 = $("#opBodySelectValue1").val();
			//设置opBodyValue的值
			if(opBodyTextValue1 != null && opBodyTextValue1 != ''){
				opBodyValue1 = opBodyTextValue1;
			}else if(opBodySelectValue1 != null && opBodySelectValue1 != ''){
				opBodyValue1 = opBodySelectValue1;
			}
			//设置设置条件属性2的属性名和属性值
			var opBodyName2 = $("#opBody2").val();
			var opBodyValue2 = '';
			var opBodyTextValue2 = $("#opBodyTextValue2").val();
			var opBodySelectValue2 = $("#opBodySelectValue2").val();
			//设置opBodyValue的值
			if(opBodyTextValue2 != null && opBodyTextValue2 != ''){
				opBodyValue2 = opBodyTextValue2;
			}else if(opBodySelectValue2 != null && opBodySelectValue2 != ''){
				opBodyValue2 = opBodySelectValue2;
			}
			//设置结论属性的属性名和属性值
			var opHeadName = $("#opHead").val();
			var opHeadValue = '';
			var opHeadTextValue = $("#opHeadTextValue").val();
			var opHeadSelectValue = $("#opHeadSelectValue").val();
			//设置opHeadValue的值
			if(opHeadTextValue != null && opHeadTextValue != ''){
				opHeadValue = opHeadTextValue;
			}else if(opHeadSelectValue != null && opHeadSelectValue != ''){
				opHeadValue = opHeadSelectValue;
			}
			var opBodyType1 = $("#opBodyType1").val();
			var opBodyType2 = $("#opBodyType2").val();
			var opHeadType = $("#opHeadType").val();
		    $.ajax({
	        url: URL,
	    type: 'POST',
	        data:"type="+type+"&opBodyType1="+opBodyType1+"&opBodyType2="+opBodyType2+"&opHeadType="+opHeadType+"&opBodyName1="+opBodyName1+"&opBodyValue1="+opBodyValue1+"&opBodyName2="+opBodyName2+"&opBodyValue2="+opBodyValue2+"&opHeadName="+opHeadName+"&opHeadValue="+opHeadValue,
		    success: function(transport)
		    {
		    	 var jresp = new JsonRespUtils(transport);
		    	 if (jresp.isSuccessfully()){
		    		 var res = jresp.getMessage();
		    		 alert("保存成功！");
		    	 }
		    	 location.reload();
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
