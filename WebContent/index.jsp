<?xml version="1.0" encoding="UTF-8" ?>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<title>信息记录系统</title>
		<link rel="stylesheet" href="./css/reset.css" type="text/css"
			media="screen" title="no title" />
		<link rel="stylesheet" href="./css/text.css" type="text/css"
			media="screen" title="no title" />
		<link rel="stylesheet" href="./css/form.css" type="text/css"
			media="screen" title="no title" />
		<link rel="stylesheet" href="./css/buttons.css" type="text/css"
			media="screen" title="no title" />
		<link rel="stylesheet" href="./css/login.css" type="text/css"
			media="screen" title="no title" />
	<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/> 
	<link href="assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2_metro.css" />
	<link href="assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/plugins.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/pages/login-soft.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/custom.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link rel="shortcut icon" href="images/favicon.ico" />
	<script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
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
	%>
		<script>loginIsErro=<%=loginIsErro %></script>
	<%
		session.setAttribute("loginIsErro","0");
		}
	%>
		<div id="login">
			<h1>
				Dashboard
			</h1>
			<div id="login_panel">
				<form id="form1" action="userinfo.ak?method=logincheck" onsubmit="return checkEmpty()" method="post">
					<div class="login_fields">
						<div class="field">
							<div align="center"> <br />
								<span id="loginIsErro" style="color:red;display:none">用户名或密码错误</span>
							</div>
							<label for="username">
								用户名
							</label>
							<input type="text" id="username" name="username" value="" tabindex="1" />
						</div>

						<div class="field">
							<label for="password">
								密码
							</label>
							<input type="password" id="password" name="password" value="" tabindex="2" />
						</div>
					</div>
					<!-- .login_fields -->
					<div class="login_actions">
						<input type="submit" class="btn btn-orange" tabindex="3" value="Login" />
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