<%@ page pageEncoding="utf-8"%>
	<div class="header navbar navbar-inverse navbar-fixed-top">
		<!-- BEGIN TOP NAVIGATION BAR -->
		<div class="navbar-inner">
			<div class="container-fluid">
				<!-- BEGIN LOGO -->
				<a class="brand" href="#">
					<img src="/culture/media/image/logo.png" alt="logo" />
				</a>
				<!-- END LOGO -->
				<ul class="nav pull-right">
					<li class="dropdown user">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<img alt="" src="/culture/media/image/avatar1_small.jpg" />
						<span class="username">${sessionScope.userName}</span>
						<i class="icon-angle-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li><a href="/culture/user/logout.do"><i class="icon-key"></i> 登出</a></li>
						</ul>
					</li>
					<!-- END USER LOGIN DROPDOWN -->
				</ul>
			</div>
		</div>
		<!-- END TOP NAVIGATION BAR -->
	</div>
