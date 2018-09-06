<?xml version="1.0" encoding="UTF-8" ?>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>信息记录系统</title>
		<link rel="stylesheet" href="./mobile/jquery.mobile-1.4.5.css">
		<script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
		<script src="./mobile/jquery.mobile-1.4.5.js"></script>
		<script type="text/javascript" src="js/login.js"></script>
	<style type="text/css">
		#loginIsErro{
			dislpay:none;
		}
	</style>

	</head>
	<body onload="isLoad()">
	<%
		HttpSession seee=request.getSession();
		String loginIsErro=(String)seee.getAttribute("loginIsErro");
		if(loginIsErro!=null){
			System.out.print(loginIsErro);
	%>
		<script>loginIsErro=<%=loginIsErro %></script>
	<%
		session.setAttribute("loginIsErro","0");
		System.out.print(loginIsErro);
		}
	%>
		<div data-role="page" id="login">
			<div data-role="header">
    			<h1>欢迎登录</h1>
  			</div>
			<div data-role="main" class="ui-content">
			<div id="myPopup"  align="center">
								<br />
								<span id="loginIsErro" style="color:red;display:none">用户名或密码错误</span>
							</div>
				<form id="form1" action="userinfo.ak?method=logincheck" onsubmit="return checkEmpty()" method="post" data-ajax="false">
					<div class="ui-field-contain">
								<label for="username">用户名:</label>
								<input type="text" id="username" name="username" data-clear-btn="true"/>
								<label for="password">密码:</label>
								<input type="password" id="password" name="password"/>
								
							 <div align="center"> 
            					<input value="登录" id="btnLogin" type=submit data-role="button" data-transition="flow"> 
							</div>
					</div>
				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript">
	function checkEmpty(){
	  if($("#username").val().trim()==""||$("#username").val().trim()=="用户名"){
		  alert('用户名不能为空');
		  return false;
	  }else if($("#password").val().trim()==""||$("#password").val().trim()=="密码"){
		  alert('密码不能为空');
		  return false;
	  }else{
		  return true;
	  }
	}
	
</script>
</html>