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
		<script type="text/javascript" src="js/lhgcore.min.js"></script>
		<script type="text/javascript" src="js/lhgcalendar.min.js"></script>
		<style type="text/css">
		#fact{
			text-align: right;
			}
		#fact h1 {
			text-align: center;
			}
		</style> 
		<title>主页</title>
		<script language="JavaScript">
			var message = '${result}';
			if(message !='' ){
				 if(message == "error"){
					alert("添加失败");
					
				}else if(message == "success"){
					alert("添加成功");
					window.location.href="orderInfoUpload.jsp";
				}else{
					alert("有重复部门,行号："+message);
					window.location.href="orderInfoUpload.jsp";
				}
			}
			
			function checkEmpty(){
					var excelFile = $("#importExcel").val();
				  if(excelFile == ''){
					  alert('请选择excle再上传');
					  return false;
				  }else if(excelFile.lastIndexOf(".xls")<0){
					  alert("只能上传Excel文件");
					  return false;
				  }else {
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
			清单导入
		</h2>
		<a href="" title="Toggle Content Block"
			style="display: block; left: 170px; top: 170px;"></a> </header> <section>
		<!-- Stats Summary --> <article class="content-box"> <section>
		<form:form method="post" enctype="multipart/form-data"
			action="recordinfo.ak?method=upload_recordinfo_action" onsubmit="return checkEmpty()">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<input type="file" name="importExcel" id="importExcel"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="导入" />
					</td>
				</tr>
			</table>
		</form:form>
		<div class="" style="text-align:center;color:black;font-size:18px;height:60px;margin-top:60px">
		<div class="footer-inner" style="margin:auto;">
		<p style="">copyright ©2018 XXXXXX有限公司版权所有</p>
		</div>
	</body>
</html>


