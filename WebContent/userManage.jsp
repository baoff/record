<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.List"%>
<%@page import="com.foot.record.entity.User"%>
<%@page import="com.foot.record.page.PageBean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
#fact {
	text-align: right;
}

#bg {
	display: none;
	/*position: absolute;*/
	position: fixed;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.7;
	opacity: .70;
	filter: alpha(opacity = 70);
}

#show {
	display: none;
	position: fixed;
	border-radius: 15px;
	top: 25%;
	left: 22%;
	width: 35%;
	height: 40%;
	padding: 4px;
	border: 4px solid #E8E9F7;
	background-color: #E8E9F7;
	z-index: 1002;
	-moz-opacity: 0.9;
	opacity: .90;
	filter: alpha(opacity = 90);
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

	function hidediv() {
		$("#order_id").val("");
		$("#order_appointDate").val("");
		$("#order_appointPlan").val("0");
		document.getElementById("bg").style.display = 'none';
		document.getElementById("show").style.display = 'none';
	}

	function createUser() {
		var newWin = window
				.open("userinfo.ak?method=add_user_form", "添加资产");
		newWin.focus();
	}
	
	function checkAllSchema() {
		$("input[name='orderIds']").each(function() {
			$(this).attr("checked", $("#cas").attr("checked"));
		});
	}

	function deleteOrders() {
		if (isChecked("orderIds")) {
			if (confirm("您确认要删除它们吗?")) {
				var array = document.getElementsByName("orderIds");
				var ids = "";
				for (var i = 0; i < array.length; i++) {
					if (array[i].checked) {
						ids = ids + array[i].value + ",";
					}
				}
				$.ajax({
					type : "POST",
					cache : false,
					dataType : "text",
					url : "./orderinfo.ak?method=deletes_orderinfo_action",
					data : {
						ids : ids
					},
					success : function(data) {
						if (data == "success") {
							alert("删除成功");
							submitForm("search");
							//location.replace("orderinfo.ak?method=search_orderInfo_action");
						} else if (data == "error") {
							alert("失败");
						} else if (data == "no user") {
							window.location.href = "index.jsp";
						} else if (data == "inFactory") {
							alert("已入库状态下不允许操作");
						}
					},
					error : function() {
						alert("网络异常,与服务器连接失败...");
					}
				});
			}
		} else {
			alert("至少选中一个!");
		}
	}

	function deleteRecord(id) {
		if (confirm("您确认要删除它们吗?")) {
			//submitForm("search");
			$.ajax({
				type : "POST",
				cache : false,
				dataType : "text",
				url : "./recordinfo.ak?method=delete_recordinfo_action",
				data : {
					recordId : id
				},
				success : function(data) {
					if (data == "success") {
						alert("删除成功");
						submitForm("search");
						//location.replace("orderinfo.ak?method=search_orderInfo_action");
					} else if (data == "error") {
						alert("失败");
					} else if (data == "no user") {
						window.location.href = "index.jsp";
					} else if (data == "inFactory") {
						alert("已完成状态下不允许操作");
					}
				},
				error : function() {
					alert("网络异常,与服务器连接失败...");
				}
			});
		}
	}

	function editeRecord(recordId) {
		var newWin = window.open(
				"recordinfo.ak?method=edit_recordinfo_form&recordId="
						+ recordId, "资产编辑");
		newWin.focus();
	}

	function isChecked(target) {
		var obj = document.getElementsByName(target);
		if (obj == null) {
			return false
		}
		if (obj.length == undefined) {
			if (obj.checked) {
				return true;
			}
		} else {
			for (var j = 0; j < obj.length; j++) {
				if (obj[j].checked) {
					return true;
				}
			}
		}
		return false;
	}

	function gotoPage(currentPage) {
		$("#query")
				.append(
						'<input value="'+currentPage+'" id="currentPage" type="hidden" name="currentPage"/>')
		$("#query").submit();
	}

	function importInModify() {
		window.location.href = "importInModify.jsp";
	}

	function submitForm(type) {
		if (type == 'search') {
			var currentPage = $('#page').val();
			$("#query")
					.append(
							'<input value="'+currentPage+'" id="currentPage" type="hidden" name="currentPage"/>')
		}
		document.query.submit();
	}
</script>
<%
	User user = (User) session.getAttribute("currentuser");
	String roleName = user.getRole().getName();
%>
<title>用户列表</title>
</head>
<body>
	<%@ include file="head.jsp"%>
	<br />
	<br /> &nbsp;
	<section id="fact">
	<h1>
		信息记录系统
		</h>
	</section>
	<article class="content-box"> <section> <articleclass="content-box">
	<section>
	<table>
		<form id="query" name="query"
			action="userinfo.ak?method=search_userInfo" method="post"
			commandName="userInfoForm">
			<input
				value="${page.currentPage}" id="page" type="hidden" name="page" />
			<tr>
				<td style="width: 15%; text-align: left"></td>
				<td style="width: 15%; text-align: left"><span
					style="width: 40%; text-align: right"> 登录名: </span> <input
					style="width: 60%" type="text" id="loginNameid" name="loginName"
					value="${userInfoForm.loginName}" /></td>

				<td style="width: 15%; text-align: left"><span
					style="width: 40%; text-align: right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;昵称: </span> <input
					style="width: 60%" type="text" id="nickNameid" name="nickName"
					value="${userInfoForm.nickName}" /></td>

				<td style="width: 15%; text-align: left"><span
					style="width: 40%; text-align: right"> 邮箱: </span> <input
					style="width: 60%" type="text" id="emailid" name="email"
					value="${userInfoForm.email}" /></td>

				<td style="width: 15%; text-align: left"><span
					style="width: 40%; text-align: right"> 手机号: </span> <input
					style="width: 60%" type="text" id="phoneid" name="phone"
					value="${userInfoForm.phone}" /></td>
				<td style="width: 15%; text-align: left"></td>
			</tr>
		<tr>
			<td></td>
			<td></td>
			<td><input type="button" class="button stats-view tooltip"
				original-title="View new registrations" value="查询"
				onclick="submitForm()" /></td>
			<td><c:if test="${currentuser.role.name eq 'manage' }">
					<input type="button" class="button stats-view tooltip"
						original-title="View new registrations" onclick="createUser()"
						value="添加用户" />
				</c:if></td>
			<td></td>
			<td></td>
		</tr>
		</form>
	</table>
	<table class="data" data-chart="line">
		<thead>
			<tr>

				<th scope="col">全选<input type="checkbox" id="cas"
					onclick="checkAllSchema()" name="checkAll" value=""></th>
				<th scope="col">登录名</th>
				<th scope="col">昵称</th>
				<th scope="col">邮箱</th>
				<th scope="col">手机号</th>
				<th scope="col">创建时间</th>
				<th scope="col">修改</th>
				<th scope="col">删除</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="nuser" items="${userLists}">
				<tr>
					<td><input type="checkbox" name="orderIds" value="${nuser.id}"></td>
					<td>${nuser.loginName }</td>
					<td>${nuser.nickname}</td>
					<td>${nuser.email}</td>
					<td>${nuser.phoneNum}</td>
					<td><fmt:formatDate value="${nuser.regesiterTime}"
							pattern="yyyy/MM/dd HH:mm:ss" /></td>
					<td><a href="javascript:void(0)"
						onclick="editeRecord(${nuser.id})">修改</a></td>
					<td><a href="javascript:void(0)"
						onclick="deleteRecord(${nuser.id})">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
	<div align="right">
		<%
			PageBean pagebean = (PageBean) request.getAttribute("page");
			long recordCount = pagebean.getRecordCount();
			int start = 1;
			int end = 10;
			int pageCount = pagebean.getPageCount();
			int currPage = pagebean.getCurrentPage();
			currPage = currPage < 1 ? 1 : currPage;
			currPage = currPage > pageCount ? pageCount : currPage;
			if (currPage >= 7) {
				start = currPage - 5;
				end = currPage + 4;
			}
			if (start > (pageCount - 10) && (pageCount - 10) > 0) {
				start = pageCount - 9;
			}
			if (pageCount > 0 && currPage > 1) {
				out.print(String.format("<a class=\"prev\" href=\"javascript:void(0)\" onclick='gotoPage(1)'>首页</a>",
						currPage - 1) + "&nbsp;");
			}

			if (currPage > 1) {
				out.print(String.format("<a class=\"prev\" href=\"javascript:void(0)\" onclick='gotoPage(%d)'>上一页</a>",
						currPage - 1) + "&nbsp;");
			}
			for (int i = start; i <= end; i++) {
				if (i > pageCount) {
					break;
				}
				String pageinfo = String.format("<a href=\"javascript:void(0)\" onclick='gotoPage(%d)'>%d</a>", i, i);
				if (i == currPage) {
					pageinfo = String.format("<span>%d<span>", i);
				}
				out.print(pageinfo + "&nbsp;");
			}
			if (currPage < pageCount) {
				out.print(String.format("<a class=\"prev\" href=\"javascript:void(0)\" onclick='gotoPage(%d)'>下一页</a>",
						currPage + 1) + "&nbsp;");
			}
			if (pageCount != 0) {
				out.print(String.format("<a class=\"prev\" href=\"javascript:void(0)\" onclick='gotoPage(%d)'>尾页</a>",
						pageCount));
			}
		%>
		<div class=""
			style="text-align: center; color: black; font-size: 18px; height: 60px; margin-top: 60px">
			<div class="footer-inner" style="margin: auto;">
				<p style="">copyright ©2018 XXXXXX有限公司版权所有</p>
			</div>
		</div>
	</section></article>
	</section>
	</article>
</body>
</html>