<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>修改实例</title>
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
							添加${type}
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
							<li><a href="/culture/doctor/dtAdd.do">修改${type}</a></li>
						</ul>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN SAMPLE FORM PORTLET-->   
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form id="instanceForm" action="/culture/instance/updateInstance.do" class="form-horizontal" method="post" enctype="multipart/form-data" target="hidden_frame">
								<input type="hidden" id="type" name="type" value="${cb.type}" />
								<input type="hidden" id="classification" name="classification" value="${cb.classification}" />
								<input type="hidden" id="identifier" name="identifier" value="${cb.identifier}" />
								<input type="hidden" id="oldTitle" name="oldTitle" value="${oldTitle}" />
								<div class="portlet box blue">
									<div class="portlet-title">
										<div class="caption"><i class="icon-cogs"></i>本体信息</div>
									</div>
									<div class="portlet-body">
		 								<div class="control-group">
											<label class="control-label">名称</label>
											<div class="controls">
												<input id="title" type="text" class="span6 m-wrap" name="title" value="${cb.title}" readonly/>
												<span class="help-inline">必填</span>
											</div>
										</div>
		 								<div class="control-group">
											<label class="control-label">其他名称</label>
											<div class="controls">
												<input id="used_title" type="text" class="span6 m-wrap" name="used_title" value="${cb.used_title}" readonly />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">创作朝代</label>
											<div class="controls">										
												<input id="creation_date" type="text" class="span6 m-wrap" name="creation_date" value="${cb.creation_date }" readonly />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">创作者</label>
											<div class="controls">										
												<input id="creator" type="text" class="span6 m-wrap" name="creator" value="${cb.creator}" readonly />
											</div>
										</div>
										<div id="shapeDiv" class="control-group">
											<label class="control-label">器形</label>
											<div class="controls">										
												<select id="shape" class="span6 chosen shape-select" data-placeholder="请选择器形" multiple="multiple" tabindex="6" name="shape">
													<c:forEach  items="${shapeList}"  var="item"  varStatus="status">
														<option value="${item.cname}">${item.cname}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div id="patternDiv" class="control-group">
											<label class="control-label">纹饰</label>
											<div class="controls">										
												<select id="pattern" class="span6 chosen pattern-select" data-placeholder="请选择纹饰" multiple="multiple" tabindex="6" name="pattern">
													<c:forEach  items="${patternList}"  var="item"  varStatus="status">
														<option value="${item.cname}">${item.cname}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div id="structureDiv" class="control-group">
											<label class="control-label">结构</label>
											<div class="controls">										
												<select id="structure" class="span6 chosen structure-select" data-placeholder="请选择结构" multiple="multiple" tabindex="6" name="structure">
													<c:forEach  items="${structureList}"  var="item"  varStatus="status">
														<option value="${item.cname}">${item.cname}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div id="colorDiv" class="control-group">
											<label class="control-label">色彩</label>
											<div class="controls">										
												<select id="color" class="span6 chosen color-select" data-placeholder="请选择色彩" multiple="multiple" tabindex="6" name="color">
													<c:forEach  items="${colorList}"  var="item"  varStatus="status">
														<option value="${item.cname}">${item.cname}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div id="sceneDiv" class="control-group">
											<label class="control-label">使用情境</label>
											<div class="controls">										
												<select id="scene" class="span6 chosen scene-select" data-placeholder="请选择使用情境" multiple="multiple" tabindex="6" name="scene">
													<c:forEach  items="${sceneList}"  var="item"  varStatus="status">
														<option value="${item.cname}">${item.cname}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div id="meaningDiv" class="control-group">
											<label class="control-label">象征意义</label>
											<div class="controls">										
												<select id="meaning" class="span6 chosen meaning-select" data-placeholder="请选择象征意义" multiple="multiple" tabindex="6" name="symbolic_meaning">
													<c:forEach  items="${meaningList}"  var="item"  varStatus="status">
														<option value="${item.cname}">${item.cname}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div id="aestheticDiv" class="control-group">
											<label class="control-label">审美</label>
											<div class="controls">										
												<select id="aesthetic" class="span6 chosen aesthetic-select" data-placeholder="请选择审美" multiple="multiple" tabindex="6" name="aesthetic_desc">
													<c:forEach  items="${aestheticList}"  var="item"  varStatus="status">
														<option value="${item.cname}">${item.cname}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="form-actions">
									<button type="submit" class="btn blue">提交</button>
									<button type="reset" class="btn">重置</button>                            
								</div>
							</form>
						</div>
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
	<script src="/culture/media/js/jsonRespUtils.js" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script type="text/javascript" src="/culture/media/js/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="/culture/media/js/jsonRespUtils.js"></script>
	<script type="text/javascript" src="/culture/media/js/jquery.validate.min.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="/culture/media/js/app.js"></script>
	<script src="/culture/media/js/form-validation.js"></script> 
	<!-- END PAGE LEVEL SCRIPTS -->
	<script>
		jQuery(document).ready(function() {       
		   // initiate layout and plugins
		   App.init();
		   var rule={type:{required:true},classification:{required:true}};
		   FormValidation.init($('#instanceForm'),rule,creatSubmitForm('instanceForm'),failedForm);
		   init();
		   setValues();
		});
		
		function init(){
			var type = $("#type").val();
			if(type=='器物'){
				$("#structureDiv").hide();
				$("#aestheticDiv").hide();
			}else if(type=='织物'){
				$("#structureDiv").hide();
				$("#shapeDiv").hide();
				$("#aestheticDiv").hide();
			}else if(type=='建筑'){
				$("#shapeDiv").hide();
				$("#patternDiv").hide();
				$("#colorDiv").hide();
				$("#meaningDiv").hide();
				$("#sceneDiv").hide();
			}else if(type=='壁画'){
				$("#structureDiv").hide();
				$("#shapeDiv").hide();
				$("#aestheticDiv").hide();
				$("#sceneDiv").hide();
			}
		}
		
		function setValues(){
			//纹饰数据
			var pattern = '${instance.pattern}';
			$("#pattern").val(pattern.split(','));
			$(".pattern-select").trigger("liszt:updated");
			//器形数据
			var shape = '${instance.shape}';
			$("#shape").val(shape.split(','));
			$(".shape-select").trigger("liszt:updated");
			//结构数据
			var structure = '${instance.structure}';
			$("#structure").val(structure.split(','));
			$(".structure-select").trigger("liszt:updated");
			//色彩数据
			var color = '${instance.color}';
			$("#color").val(color.split(','));
			$(".color-select").trigger("liszt:updated");
			//使用情境数据
			var scene = '${instance.scene}';
			$("#scene").val(scene.split(','));
			$(".scene-select").trigger("liszt:updated");
			//审美数据
			var aesthetic_desc = '${instance.aesthetic_desc}';
			$("#aesthetic").val(aesthetic_desc.split(','));
			$(".aesthetic-select").trigger("liszt:updated");
			//象征意义数据
			var symbolic_meaning = '${instance.symbolic_meaning}';
			$("#meaning").val(symbolic_meaning.split(','));
			$(".meaning-select").trigger("liszt:updated");
		}
		
		function failedForm(){
			console.log("failedForm");
		}
		
		function creatSubmitForm(formId){
			return function submitForm(){
				var formObj = $("#"+formId);
			    var formURL = formObj.attr("action");
			    var formData = new FormData(formObj[0]);
			    $.ajax({
			        url: formURL,
			    type: 'POST',
			        data:  formData,
			    mimeType:"multipart/form-data",
			    contentType: false,
			    cache: false,
			    processData:false,
			    success: function(transport)
			    {
			    	 var jresp = new JsonRespUtils(transport);
			    	 if (jresp.isSuccessfully()){
			    		 var res = jresp.getMessage();
			    		 alert("保存成功！");
			    		 location.reload();
			    	 }
			    },
			     error: function(transport) 
			     {
			    	alert("保存失败！");
			     }          
			    });
			}
		}

		
	</script>
	<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>
	<!-- END BODY -->
</body>
