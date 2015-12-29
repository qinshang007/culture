<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar nav-collapse collapse">
		<!-- BEGIN SIDEBAR MENU -->        
			<ul class="page-sidebar-menu">
				<li>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler hidden-phone"></div>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				</li>
				<li class="">
					<a href="javascript:;">
					<i class="icon-globe"></i> 
					<span class="title">概念管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li >
							<a href="/culture/class/addClass.do">添加概念</a>
						</li>
						<li >
							<a href="/culture/class/classList.do?pageStart=1">概念列表</a>
						</li>
					</ul>
				</li>
				<li class="">
					<a href="javascript:;">
					<i class="icon-globe"></i> 
					<span class="title">属性管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li >
							<a href="/culture/property/addDataProperty.do">添加数据属性</a>
						</li>
						<li >
							<a href="/culture/property/addObjectProperty.do">添加对象属性</a>
						</li>
						<li >
							<a href="/culture/property/propertyList.do?pageStart=1">属性列表</a>
						</li>
					</ul>
				</li>
				<li class="">
					<a href="javascript:;">
					<i class="icon-globe"></i> 
					<span class="title">规则管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li >
							<a href="/culture/rule/addRule.do?type=1">添加一元规则</a>
						</li>
						<li >
							<a href="/culture/rule/addRule.do?type=2">添加二元规则</a>
						</li>
						<li >
							<a href="/culture/rule/genRule.do" target="_blank">半自动规则生成</a>
						</li>
						<li >
							<a href="/culture/rule/ruleList.do">规则列表</a>
						</li>
					</ul>
				</li>
				<li class="">
					<a href="javascript:;">
					<i class="icon-globe"></i> 
					<span class="title">实例管理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li >
							<a href="/culture/instance/chooseClass.do">添加实例</a>
						</li>
						<li >
							<a href="/culture/instance/culturalList.do?pageStart=1">文物实例列表</a>
						</li>
						<!-- 
						<li >
							<a href="/culture/instance/instanceList.do?pageStart=1">本体实例列表</a>
						</li>
						 -->
					</ul>
				</li>
				<!-- 
				<li class="">
					<a href="javascript:;">
					<i class="icon-globe"></i> 
					<span class="title">知识推理</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li >
							<a href="/culture/knowledge/recommend.do">知识推荐</a>
						</li>
						<li >
							<a href="/culture/knowledge/expansion.do">知识扩充</a>
						</li>
					</ul>
				</li>
				 -->
				<c:if test="${sessionScope.permission == 1}">
					<li class="">
						<a href="javascript:;">
						<i class="icon-globe"></i> 
						<span class="title">用户管理</span>
						<span class="arrow "></span>
						</a>
						<ul class="sub-menu">
							<li >
								<a href="/culture/user/addUser.do">添加用户</a>
							</li>
							<li >
								<a href="/culture/user/getUserList.do">用户列表</a>
							</li>
						</ul>
					</li>
				</c:if>
			</ul>
		<!-- END SIDEBAR MENU -->
		</div>
		<!-- END SIDEBAR -->
