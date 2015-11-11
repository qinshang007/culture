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
							修改实例
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
							<li><a href="/culture/doctor/dtAdd.do">修改实例</a></li>
						</ul>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN SAMPLE FORM PORTLET-->   
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form id="instanceForm" action="/culture/instance/updateCultural.do" class="form-horizontal" method="post" enctype="multipart/form-data" target="hidden_frame">
								<input type="hidden" id="type" name="type" value="${type}" />
								<input type="hidden" id="classification" name="classification" value="${classification}" />
								<input type="hidden" id="identifier" name="identifier" value="${cb.identifier}" />
								<input type="hidden" id="mainpic" name="mainpic" value="${cb.mainpic}" />
								<input type="hidden" id="oldTitle" name="oldTitle" value="${cb.title}" />
								<div class="portlet box blue">
									<div class="portlet-title">
										<div class="caption"><i class="icon-cogs"></i>基本信息</div>
									</div>
									<div class="portlet-body">
		 								<div class="control-group">
											<label class="control-label">名称</label>
											<div class="controls">
												<input id="title" type="text" class="span6 m-wrap" name="title" value="${cb.title}"/>
												<span class="help-inline">必填</span>
											</div>
										</div>
		 								<div class="control-group">
											<label class="control-label">其他名称</label>
											<div class="controls">
												<input id="used_title" type="text" class="span6 m-wrap" name="used_title" value="${cb.used_title}"/>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">主图</label>
											<div class="controls">
												<div class="fileupload fileupload-new" data-provides="fileupload">
													<div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
														<img src="/crelicBase/upload${cb.mainpic}" alt="" />
													</div>
													<div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
													<div>
														<span class="btn btn-file"><span class="fileupload-new">更改主图</span>
														<span class="fileupload-exists">Change</span>
														<input id="mainpicId" name="mainpicfile" type="file" class="default" /></span>
														<a href="#" class="btn fileupload-exists" data-dismiss="fileupload">Remove</a>
													</div>
												</div>
											</div>
										</div>								
										<div class="control-group">
											<label class="control-label">级别</label>
											<div class="controls">										
												<select id="c_level" class="span6 chosen classification-select" data-placeholder="请选择级别" tabindex="1" name="c_level">
													<c:forEach  items="${levelList}"  var="item"  varStatus="status">
														<option value="${item}">${item}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">创作朝代</label>
											<div class="controls">										
												<select id="creation_date" class="span6 chosen classification-select" data-placeholder="请选择朝代" tabindex="1" name="creation_date">
													<c:forEach  items="${creationDateList}"  var="item"  varStatus="status">
														<option value="${item.cname}">${item.cname}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">创作时期</label>
											<div class="controls">										
												<input id="creation_time" type="text" class="span6 m-wrap" name="creation_time" value="${cb.creation_time }" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">创作者</label>
											<div class="controls">										
												<input id="creator" type="text" class="span6 m-wrap" name="creator" value="${cb.creator}" />
											</div>
										</div>
										<div id="placeOfOriginDiv" class="control-group">
											<label class="control-label">产地</label>
											<div class="controls">										
												<input id="place_of_originr" type="text" class="span6 m-wrap" name="place_of_origin" value="${cb.place_of_origin}" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">度量</label>
											<div class="controls">										
												<input id="measurement" type="text" class="span6 m-wrap" name="measurement" value="${cb.measurement}"/>
											</div>
										</div>
										<div id="locationDiv" class="control-group">
											<label class="control-label">地域环境</label>
											<div class="controls">										
												<input id="location" type="text" class="span6 m-wrap" name="location" value="${cb.location}"/>
											</div>
										</div>
										<div id="excavationDateDiv" class="control-group">
											<label class="control-label">出土时间</label>
											<div class="controls">										
												<input id="excavation_date" type="text" class="span6 m-wrap" name="excavation_date" value="${cb.excavation_date }"/>
											</div>
										</div>
										<div id="excavationPlaceDiv" class="control-group">
											<label class="control-label">出土地点</label>
											<div class="controls">										
												<input id="excavation_place" type="text" class="span6 m-wrap" name="excavation_place" value="${cb.excavation_place }" />
											</div>
										</div>
										<div id="currentLocationDiv" class="control-group">
											<label class="control-label">现藏地点</label>
											<div class="controls">										
												<input id="current_location" type="text" class="span6 m-wrap" name="current_location" value="${cb.current_location }"  />
											</div>
										</div>
										<div id="exhibitonHistoryDiv" class="control-group">
											<label class="control-label">展览历史</label>
											<div class="controls">										
												<input id="exhibition_history" type="text" class="span6 m-wrap" name="exhibition_history" value="${cb.exhibition_history }"/>
											</div>
										</div>
									</div>
								</div>
								<div id="modelDiv" class="portlet box blue">
									<div class="portlet-title">
										<div class="caption"><i class="icon-cogs"></i>造型要素</div>
									</div>
									<div class="portlet-body">
										<div id="shapeDiv" class="control-group">
											<label class="control-label">器型</label>
											<div class="controls">										
												<textarea class="span6 m-wrap" rows="3" name="shape">${cb.shape}</textarea>
											</div>
										</div>
										<div id="patternDiv" class="control-group">
											<label class="control-label">纹饰</label>
											<div class="controls">										
												<textarea class="span6 m-wrap" rows="3" name="pattern">${cb.pattern}</textarea>
											</div>
										</div>
										<div id="structureDiv" class="control-group">
											<label class="control-label">结构</label>
											<div class="controls">										
												<textarea class="span6 m-wrap" rows="3" name="structure">${cb.structure}</textarea>
											</div>
										</div>
										<div id="decorationDiv" class="control-group">
											<label class="control-label">装饰</label>
											<div class="controls">										
												<textarea class="span6 m-wrap" rows="3" name="decoration">${cb.decoration}</textarea>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">色彩</label>
											<div class="controls">										
												<textarea class="span6 m-wrap" rows="3" name="color">${cb.color}</textarea>
											</div>
										</div>
									</div>
								</div>
								<div id="functionDiv" class="portlet box blue">
									<div class="portlet-title">
										<div class="caption"><i class="icon-cogs"></i>功能要素</div>
									</div>
									<div class="portlet-body">
										<div id="sceneDiv" class="control-group">
											<label class="control-label">使用情境</label>
											<div class="controls">										
												<input id="scene" type="text" class="span6 m-wrap" name="scene" value="${cb.scene }" />
											</div>
										</div>
										<div id="c_usageDiv" class="control-group">
											<label class="control-label">使用方式</label>
											<div class="controls">										
												<input id="c_usage" type="text" class="span6 m-wrap" name="c_usage" value="${cb.c_usage }" />
											</div>
										</div>
										<div id="symbolicDiv" class="control-group">
											<label class="control-label">象征意义</label>
											<div class="controls">										
												<textarea class="span6 m-wrap" rows="3" name="symbolic_meaning">${cb.symbolic_meaning}</textarea>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">审美</label>
											<div class="controls">										
												<textarea class="span6 m-wrap" rows="3" name="aesthetic_desc">${cb.aesthetic_desc}</textarea>
											</div>
										</div>
									</div>
								</div>
								<div id="techonologyDiv" class="portlet box blue">
									<div class="portlet-title">
										<div class="caption"><i class="icon-cogs"></i>技术要素</div>
									</div>
									<div class="portlet-body">
										<div class="control-group">
											<label class="control-label">材质</label>
											<div class="controls">										
												<input id="scene" type="text" class="span6 m-wrap" name="material" value="${cb.material}" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">工艺</label>
											<div class="controls">										
												<textarea class="span6 m-wrap" rows="3" name="technique">${technique}</textarea>
											</div>
										</div>
									</div>
								</div>
								<div id="cultureDiv" class="portlet box blue">
									<div class="portlet-title">
										<div class="caption"><i class="icon-cogs"></i>文化要素</div>
									</div>
									<div class="portlet-body">
										<div id="historyInfoDiv" class="control-group">
											<label class="control-label">历史信息</label>
											<div class="controls">										
												<input id="scene" type="text" class="span6 m-wrap" name="history_info" value="${cb.history_info }"/>
											</div>
										</div>
										<div id="folkloreDiv" class="control-group">
											<label class="control-label">民间典故</label>
											<div class="controls">										
												<textarea class="span6 m-wrap" rows="3" name="folklore">${cb.folklore}</textarea>
											</div>
										</div>
										<div id="socialHistoryInfoDiv" class="control-group">
											<label class="control-label">社会历史信息</label>
											<div class="controls">	
												<textarea class="span6 m-wrap" rows="3" name="social_history_info">${cb.social_history_info }</textarea>									
											</div>
										</div>
									</div>
								</div>
 								<div class="portlet box blue">
									<div class="portlet-title">
										<div class="caption"><i class="icon-cogs"></i>其他要素</div>
									</div>
									<div class="portlet-body">
										<div class="control-group">
											<label class="control-label">关联</label>
											<div class="controls">										
												<input id="relation" type="text" class="span6 m-wrap" name="relation" value="${cb.relation }"/>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">资料来源</label>
											<div class="controls">										
												<textarea class="span6 m-wrap" rows="3" name="source">${cb.source}</textarea>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">作品授权</label>
											<div class="controls">										
												<select id="rights" class="span6 chosen classification-select" data-placeholder="请选择授权" tabindex="1" name="rights">
													<option value="禁止商用">禁止商用</option>
													<option value="禁止匿名转载">禁止匿名转载</option>
													<option value="仅限个人使用">仅限个人使用</option>
													<option value="禁止截图">禁止截图</option>
													<option value="禁止右键另存为">禁止右键另存为</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="form-actions">
									<button type="submit" class="btn blue">下一步</button>
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
	<script type="text/javascript" src="/culture/media/js/bootstrap-fileupload.js"></script>
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
		   $("#c_level option[value='${cb.c_level}']").attr("selected",true);
		   $("#creation_date option[value='${cb.creation_date}']").attr("selected",true);
		   $("#rights option[value='${cb.rights}']").attr("selected",true);
		   FormValidation.init($('#instanceForm'),rule,creatSubmitForm('instanceForm'),failedForm);
		   init();
		});
		
		function init(){
			var type = $("#type").val();
			if(type=='器物'){
				//基本信息
				$("#locationDiv").hide();
				//造型要素
				$("#structureDiv").hide();
				$("#decorationDiv").hide();
				//文化要素
				$("#socialHistoryInfoDiv").hide();
			}else if(type=='织物'){
				//基本要素
				$("#locationDiv").hide();
				//造型要素
				$("#shapeDiv").hide();
				$("#structureDiv").hide();
				$("#decorationDiv").hide();
				//功能要素
				$("#c_usageDiv").hide();
				//文化要素
				$("#socialHistoryInfoDiv").hide();
			}else if(type='建筑'){
				//基本信息
				$("#placeOfOriginDiv").hide();
				$("#excavationDateDiv").hide();
				$("#excavationPlaceDiv").hide();
				$("#currentLocationDiv").hide();
				$("#exhibitonHistoryDiv").hide();
				//造型要素
				$("#shapeDiv").hide();
				$("#patternDiv").hide();
				//功能要素
				$("#sceneDiv").hide();
				$("#c_usageDiv").hide();
				$("#symbolicDiv").hide();
				//文化要素
				$("#historyInfoDiv").hide();
				$("#folkloreDiv").hide();
			}else if(type=='壁画'){
				//基本信息
				$("#locationDiv").hide();
				//造型要素
				$("#shapeDiv").hide();
				$("#structureDiv").hide();
				$("#decorationDiv").hide();
				//功能要素
				$("#sceneDiv").hide();
				$("#c_usageDiv").hide();
				//文化要素
				$("#socialHistoryInfoDiv").hide();
			}
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
			    		 var identifier = $("#identifier").val();
			    		 var creation_date = $("#creation_date").val();
				    	 var type = $("#type").val();
				    	 var classification = $("#classification").val();
				    	 var title = $("#title").val();
				    	 var used_title = $("#used_title").val();
				    	 var creator = $("#creator").val();
				    	 var oldTitle = $("#oldTitle").val();
				    	 location.href="/culture/instance/editInstance.do?type="+type+"&classification="+classification+"&title="+title+"&used_title="+used_title+"&creator="+creator+"&identifier="+identifier+"&creation_date="+creation_date+"&oldTitle="+oldTitle;
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
