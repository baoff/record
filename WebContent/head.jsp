<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<header id="navigation"> 
	<div class="navigation-wrapper">
		<nav id="nav-main"> 
		<ul id="nav-main-navigation">
			<li style="color: Gray; margin-left: 10px;"><a
				href="recordinfo.ak?method=search_recordInfo" title=""
				class="nav-main-subnav">订单管理</a></li>
			<li style="color: Gray; margin-left: 10px;"><a
				href="recordCount.jsp" title=""
				class="nav-main-subnav">订单统计</a></li>
		</ul>
		<ul id="nav-main-user">
			<li>${currentuser.nickname}&nbsp;
			<a href="userinfo.ak?method=logout" title="退出">退出
			</a></li>
		</ul>
	</div>
	</header>