<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="com.foot.record.page.PageBean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="" />
<meta name="robots" content="index, follow" />
<meta name="copyright" content="lhgcore.com" />
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
<link href="assets/plugins/fullcalendar/fullcalendar/fullcalendar.css"
	rel="stylesheet" type="text/css" />
<link href="js/lhgcalendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/lhgcore.min.js"></script>
<script type="text/javascript" src="js/lhgcalendar.min.js"></script>
<script type="text/javascript" src="js/infolist.js"></script>
<style type="text/css">
	#fact{
	text-align: right;
}
#fact h1 {
	text-align: center;
}
</style>
<script type="text/javascript">
	J(function() {
		J('#cal1').calendar();
		J('#cal2').calendar();
	});
</script>

<title>订单统计</title>
</head>
<body>
	<body>
	<%@ include file="head.jsp" %>
	<br />
	<br /> &nbsp;
	<section id="fact">
			<h1>信息记录系统</h1>
	</section>
	<article class="content-box"> <section> <article
		class="content-box"> <section>
	<table>
			<form id="query" action="recordinfo.ak?method=count_recordInfo_action"
			method="post" commandName="countForm">
			<tr>
				<td style="width:15% ;text-align: left">
					<span style="width: 40%;text-align: right">
						开单部门: 
					</span>
					<input style="width:60%" type="text"
					id="departmentid" name="department"
					value="${countForm.department}" />
				</td>
				<td style="width:15% ;text-align: left">
					<span style="width: 40%;text-align: right">
						区域: 
					</span>
					<input style="width:60%" type="text"
					id="regionid" name="region"
					value="${countForm.region}" />
				</td>
				<td style="width:15% ;text-align: left">
					<span style="width: 40%;text-align: right">
						邀约: 
					</span>
					<input style="width:60%" type="text"
					id="wxNumberid" name="wxNumber"
					value="${countForm.wxNumber}" />
				</td>
				<td style="width:15% ;text-align: left">
				<span style="width: 40%;text-align: right">
					客户类型: 
				</span>
					<select style="width:60%" name="customerType"
						id="customerType">
						<option value="-1"
							<c:if test="${countForm.customerType==-1}">selected = "selected"</c:if>>--请选择--</option>
						<option value="20"
							<c:if test="${countForm.customerType==20}">selected = "selected"</c:if>>全部</option>
						<option value=1	
							<c:if test="${countForm.customerType==1}">selected = "selected"</c:if>>成交客户</option>
						<option value="2"
							<c:if test="${countForm.customerType==2}">selected = "selected"</c:if>>商讨客户</option>
					</select>
				</td>
						<td style="width:15% ;text-align: left">
				<span style="width: 40%;text-align: right">
					创建日期:
				</span>	 
					<input style="width:60%" type="text"
					id="cal1" name="startDate"
					value="${countForm.startDate}" placeholder="选择日期" />
				</td>
				
				<td style="width:15% ;text-align: left">
				<span style="width: 40%;text-align: right">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至:
				</span>
				<input style="width:60%" type="text"
					id="cal2" name="endDate"
					value="${countForm.endDate}" placeholder="选择日期" />
				</td>
				</tr>
				
				<tr>
				<td></td>
				<td></td>
				<td><input type="submit" class="button stats-view tooltip"
					original-title="View new registrations" value="查询" /></td>
					<td>
					</td>
					<td></td>
					<td></td>
			</tr>
			</form>
		</table>
		<table class="data" data-chart="line">
		<thead>
			<tr>
				<th scope="col">资产状态</th>
				<th scope="col">数量</th>
				<th scope="col">时间段</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${countAsset_zong >0 }">
					<tr>
					<td>已完成</td>
					<td>${countAsset_1 }</td>
					
					<td>${start_1}~${end_1}</td>
				</tr>
				<tr>
					<td>注册中</td>
					<td>${countAsset_2 }</td>
					
					<td>${start_2}~${end_2}</td>
				</tr>
				<tr>
					<td>总计</td>
					<td>${countAsset_zong }</td>
					<td>${start_zong}~${end_zong}</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<br />
	<div class="" style="text-align:center;color:black;font-size:18px;height:60px;margin-top:60px">
		<div class="footer-inner" style="margin:auto;">
		<p style="">copyright ©2018 XXXXXX有限公司版权所有</p>
		</div>
	</div>
	</section> </article> </section> </article>

</body>
</html>
