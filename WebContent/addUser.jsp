<?xml version="1.0" encoding="UTF-8" ?>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<meta name="author" content="" />
		<meta name="robots" content="index, follow" />
		<meta name="copyright" content="lhgcore.com"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="css/screen.css" />
		<link rel="stylesheet" href="css/colors.css" />
		<link rel="stylesheet" href="css/jquery.tipsy.css" />
		<link rel="stylesheet" href="css/jquery.wysiwyg.css" />
		<link rel="stylesheet" href="css/jquery.datatables.css" />
		<link rel="stylesheet" href="css/jquery.nyromodal.css" />
		<link rel="stylesheet" href="css/jquery.datepicker.css" />
		<link rel="stylesheet" href="css/jquery.fileinput.css" />
		<link rel="stylesheet" href="css/jquery.fullcalendar.css" />
		<link rel="stylesheet" href="css/jquery.visualize.css" />
		<link rel="stylesheet" href="css/jquery.snippet.css" />
		<link href="assets/plugins/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css"/>
		<link href="js/lhgcalendar.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script> 
		<script type="text/javascript" src="js/lhgcore.min.js"></script>
		<script type="text/javascript" src="js/lhgcalendar.min.js"></script>
		<style type="text/css">
		#fact{
			text-align: right;
			}
		#fact h1 {
			text-align: center;
			}
			img {
				width: 30px;
				height: 30px;
			}
		</style> 
		<script type="text/javascript">
			J(function(){  
				 J('#cal3').calendar({format: 'yyyy-MM-dd HH:mm:ss'});
			});
		</script>
		<title>主页</title>
		<script language="JavaScript">
			var message = '${addMessage}';
			if(message !='' ){
				if(message == "no department"){
					alert("部门不能为空");
				}else if(message == "has department"){
					alert("部门已存在");
				}else if(message == "success"){
					alert("添加成功");
					window.opener.submitForm("search");
					window.close();
					//window.location.href="orderinfo.ak?method=search_orderInfo_action";
				}
			}
			function checkEmpty(){
				   if($("#department").val().trim()==""){
					  alert('部门不能为空');
					  return false;
				  }else{
					  return true;
				   } 
				}
		</script>
	</head>
	<body>
		<%@ include file="head.jsp" %>
		<br />
		<br />
		&nbsp;
		<section id="fact">
			<h1>信息记录系统</h>
		</section>
		<article class="content-box"> <header>
		<h2 style="padding-right: 90px;">
			添加用户
		</h2>
		<a href="" title="Toggle Content Block"
			style="display: block; left: 170px; top: 170px;"></a> </header> <section>
		<article class="content-box"> <section>
		<form:form method="post" commandName="userInfoBean"
			action="userinfo.ak?method=add_userinfo_action" onsubmit="return checkEmpty()">
			<form:hidden path="id" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<label>
							登录名
						</label>
					</td>
					<td style="text-align: left">
						<form:textarea path="loginName" rows="1" cols="70" />
					</td>
					<td></td>
					<td></td>
				</tr>	
				<tr>
					<td>
						<label>
							昵称
						</label>
					</td>
					<td style="text-align: left">
						<form:textarea path="nickname" rows="1" cols="70" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>
						<label>
							密码
						</label>
					</td>
					<td style="text-align: left">
						<form:password path="password" />
					</td>
					<td></td>
					<td></td>
			</tr>
			<tr>
					<td>
						<label>
							确认密码
						</label>
					</td>
					<td style="text-align: left">
						<input type="password" name="repassword" id="repasswordid"/>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>
						<label>
							邮箱
						</label>
					</td>
					<td style="text-align: left">
						<form:textarea path="email" rows="1" cols="70" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>
						<label>
							手机号
						</label>
					</td>
					<td style="text-align: left">
						<form:textarea path="phoneNum" rows="1" cols="70" />
					</td>
				</tr>
			</table>
			<header>
				<h2 style="padding-right: 90px;">
					关联权限
				</h2>
				<a href="" title="Toggle Content Block"
					style="display: block; left: 170px; top: 170px;"></a> </header>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
						<c:forEach items="${roles}" var="ro">
						<td>
							<span>
								<input type="checkbox" name="ro_${ro.id}" value="${ro.id}" id="ro_${ro.id}"/>
								<c:if test="${ro.name eq 'manage'}">管理员</c:if>
								<c:if test="${ro.name eq 'add'}">添加</c:if>
								<c:if test="${ro.name eq 'delete'}">删除</c:if>
							</span>
						</td>
						</c:forEach>
					
				</tr>
			</table>
			<table>
				<tr>
					<td>
						<input type="submit" value="保存" />
					</td>
				</tr>
			</table>
		</form:form>
		<div class="" style="text-align:center;color:black;font-size:18px;height:60px;margin-top:60px">
		<div class="footer-inner" style="margin:auto;">
		<p style="">copyright ©2018 XXXXXX有限公司版权所有</p>
		</div>
	</div>
	</body>
</html>


