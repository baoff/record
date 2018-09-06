<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.List"%>
<%@page import="com.foot.record.entity.User"%>
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
#fact {
	text-align: right;
}

#bg{ 
   display: none; 
   /*position: absolute;*/ 
   position: fixed; 
   top: 0%; 
   left: 0%; 
   width: 100%;
   height: 100%; 
   background-color: black; 
   z-index:1001; 
   -moz-opacity: 0.7; 
   opacity:.70; 
   filter: alpha(opacity=70);
 }
   
#show{
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
   z-index:1002; 
   -moz-opacity:0.9; 
   opacity:.90; 
   filter: alpha(opacity=90);
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
	       document.getElementById("bg").style.display ='none';
	       document.getElementById("show").style.display ='none';
	   }
	
	function createRecord() {
		 var newWin = window.open("recordinfo.ak?method=add_record_form","添加资产");
		 newWin.focus();
	}

	function uploadFile() {
		window.location.href = "recordInfoUpload.jsp";
	}
	
	function checkAllSchema() {
	    $("input[name='orderIds']").each(function () {
	      $(this).attr("checked", $("#cas").attr("checked"));
	    });
	}
	
	function deleteOrders() {
		if (isChecked("orderIds")) {
			if (confirm("您确认要删除它们吗?")) {
				var array = document.getElementsByName("orderIds");
				var ids = "";
				for(var i = 0; i < array.length;i++){
					if(array[i].checked){
						ids = ids + array[i].value +",";
					}
				}
			     $.ajax({
				        type: "POST",
				        cache:false,
				        dataType:"text",
				        url: "./orderinfo.ak?method=deletes_orderinfo_action",
				        data:{ids:ids},
				        success: function (data) {
				            if(data=="success"){
				            	alert("删除成功");
				            	submitForm("search");
				                //location.replace("orderinfo.ak?method=search_orderInfo_action");
				            }else if(data=="error"){
				                alert("失败");
				            }else if(data == "no user"){
				            	window.location.href="index.jsp";
				            }else if(data == "inFactory"){
				            	alert("已入库状态下不允许操作");
				            }
				        },
				        error: function () {
				            alert("网络异常,与服务器连接失败...");
				        }
				    });
			}
		} else {
			alert("至少选中一个!");
		}
	}
	
	function deleteRecord(id){
		if (confirm("您确认要删除它们吗?")) {
			//submitForm("search");
		     $.ajax({
			        type: "POST",
			        cache:false,
			        dataType:"text",
			        url: "./recordinfo.ak?method=delete_recordinfo_action",
			        data:{recordId:id},
			        success: function (data) {
			            if(data=="success"){
			            	alert("删除成功");
			            	submitForm("search");
			                //location.replace("orderinfo.ak?method=search_orderInfo_action");
			            }else if(data=="error"){
			                alert("失败");
			            }else if(data == "no user"){
			            	window.location.href="index.jsp";
			            }else if(data == "inFactory"){
			            	alert("已完成状态下不允许操作");
			            }
			        },
			        error: function () {
			            alert("网络异常,与服务器连接失败...");
			        }
			    });
		}
	}

	 function editeRecord(recordId){
		 var newWin = window.open("recordinfo.ak?method=edit_recordinfo_form&recordId=" + recordId, "资产编辑");
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
	
	function gotoPage(currentPage){
		$("#query").append('<input value="'+currentPage+'" id="currentPage" type="hidden" name="currentPage"/>')
		$("#query").submit();  
	}
	
	function exportExcel(){
		$("#isexport").val("1");
		$("#query").submit();
		$("#isexport").val("0");
	}
	
	function importAppoint(){
		window.location.href = "importAppoint.jsp";
	}
	
	function importAtFactory(){
		window.location.href = "importAtFactory.jsp";
	}
	
	function importStatus(){
		window.location.href = "importStatus.jsp";
	}
	
	function importInFactory(){
		window.location.href = "importInFactory.jsp";
	}
	
	function importBalance(){
		window.location.href = "importBalance.jsp";
	}
	
	function importInModify(){
		window.location.href = "importInModify.jsp";
	}
	
	function submitForm(type){
		if(type== 'search'){
			var currentPage = $('#page').val();
			$("#query").append('<input value="'+currentPage+'" id="currentPage" type="hidden" name="currentPage"/>')
		}
		document.query.submit();
	}
	</script>
<%
	User user = (User) session.getAttribute("currentuser");
	String roleName = user.getRole().getName();
%>
<title>主页</title>
</head>
<body>
	<%@ include file="head.jsp" %>
	<br />
	<br /> &nbsp;
	<section id="fact">
	<h1>信息记录系统</h>
	</section>
	<article class="content-box"> <section> <article
		class="content-box"> <section>
	<table>
		<form id="query" name="query" action="recordinfo.ak?method=search_recordInfo"
			method="post" commandName="recordInfoForm">
			<input type="hidden" name="isexport" id="isexport" value="0"/>
			<input value="${page.currentPage}" id="page" type="hidden" name="page"/>
			<tr>
				<td style="width:15% ;text-align: left">
				<span style="width: 40%;text-align: right">
					开单部门: 
				</span>
				<input style="width:60%" type="text"
					id="departmentid" name="department"
					value="${recordInfoForm.department}" />
				</td>
				
				<td style="width:15% ;text-align: left">
				<span style="width: 40%;text-align: right">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区域:
				</span>
				 <input style="width:60%" type="text"
					id="regionid" name="region" value="${recordInfoForm.region}" />
				</td>
				
				<td style="width:15% ;text-align: left">
				<span style="width: 40%;text-align: right">
					注册地址: 
				</span>
				<input style="width:60%" type="text"
					id="postalAddressid" name="registAddress"
					value="${recordInfoForm.registAddress}" />
				</td>
				
				<td style="width:15%;text-align: left">
				<span style="width: 40%;text-align: right">
					通讯地址: 
				</span>
				<input style="width:60%" type="text"
					id="postalAddressid" name="postalAddress"
					value="${recordInfoForm.postalAddress}" />
				</td>
				
				<td style="width:15%; text-align: left">
				<span style="width: 40%;text-align: right">
					联系人: 
				</span>
				<input  style="width:60%" type="text"
					id="contactsid" name="contacts"
					value="${recordInfoForm.contacts}" />
				</td>
				
				<td style="width:15%; text-align: left">
				<span style="width: 40%;text-align: right">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;街道: 
				</span>
				<input style="width:60%" type="text"
					id="street" name="street"
					value="${recordInfoForm.street}" />
				</td>
			</tr>
			<tr>
				<td style="width:15%; text-align: left">
					<span style="width: 40%;text-align: right">
						&nbsp;&nbsp;&nbsp;&nbsp;增值税: 
					</span>
					<input style="width:60%" type="text" id="addedTaxid" name="addedTax" value="${recordInfoForm.addedTax  }"/>
				</td>
					
							<td style="width:15%; text-align: left">
				<span style="width: 40%;text-align: right">
					&nbsp;&nbsp;&nbsp;所得税: 
				</span>
				<input style="width:60%" type="text" id="incomeTaxid" name="incomeTax" value="${recordInfoForm.incomeTax }" />
				</td>
				
					<td style="width:15%; text-align: left">
					<span style="width: 40%;text-align: right">
						&nbsp;&nbsp;&nbsp;&nbsp;管理费: 
					</span>
					<input style="width:60%" type="text"
						id="manageTaxid" name="manageTax"
						value="${recordInfoForm.manageTax}" />
					</td>
					
					<td style="width:15%; text-align: left">
						<span style="width: 40%;text-align: right">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;返税: 
						</span>
					<input style="width:60%" type="text"
						id="returnTaxid" name="returnTax"
						value="${recordInfoForm.returnTax}" />
					</td>
					
					<td style="width:15%; text-align: left">
					<span style="width: 40%;text-align: right">
						&nbsp;&nbsp;&nbsp;&nbsp;电话: 
					</span>
					<input style="width:60%" type="text"
						id="phoneid" name="phone" value="${recordInfoForm.phone}" />
					</td>
					
					<td style="width:15%; text-align: left">
					<span style="width: 40%;text-align: right">
						&nbsp;客户类型: 
					</span>	
					<select style="width:60%" name="customerType"
						id="customerType">
						<option value="-1" <c:if test="${recordInfoForm.customerType==-1}">selected = "selected"</c:if>>--请选择--</option>
						<option value="0" <c:if test="${recordInfoForm.customerType==0}">selected = "selected"</c:if>>全部</option>
						<option value="1" <c:if test="${recordInfoForm.customerType==1}">selected = "selected"</c:if>>成交客户</option>
						<option value="2" <c:if test="${recordInfoForm.customerType==2}">selected = "selected"</c:if>>商讨客户</option>
					</select>
					</td>
				</tr>
				<tr>
				<td style="width:15%;text-align: left">
				<span style="width: 40%;text-align: right">
					&nbsp;&nbsp;&nbsp;&nbsp;微信号:  
				</span>
				<input style="width:60%" type="text"
					id="postalAddressid" name="wxNumber"
					value="${recordInfoForm.wxNumber}" />
				</td>
				
				<td style="width:15%; text-align: left">
				<span style="width: 40%;text-align: right">
					&nbsp;&nbsp;&nbsp;订单号: 
				</span>
				<input  style="width:60%" type="text"
					id="contactsid" name="orderNumber"
					value="${recordInfoForm.orderNumber}" />
				</td>
				<td style="width:15%; text-align: left">
					<span style="width: 40%;text-align: right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态: 
					</span>
						<select style="width:60%" id="statusid" name="status">
							<option value="-1">--请选择--</option>
							<option value="0"
								<c:if test="${recordInfoForm.status==0}">selected = "selected"</c:if>>全部</option>
							<option value="1"
								<c:if test="${recordInfoForm.status==1}">selected = "selected"</c:if>>已完成</option>
							<option value="2"
								<c:if test="${recordInfoForm.status==2}">selected = "selected"</c:if>>注册中</option>
						</select>
					</td>
					<td style="width:15%; text-align: left">
					<span style="width: 40%;text-align: right">
					创建时间: 
					</span>
					<input style="width:60%" type="text"
						id="cal1" name="initDateStart"
						value="${recordInfoForm.initDateStart}"
						placeholder="选择日期" />
					</td>
					<td style="width:15%; text-align: left">
					<span style="width: 40%;text-align: right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至: 
					</span>
					<input style="width:60%" type="text" id="cal2"
						name="initDateEnd" value="${recordInfoForm.initDateEnd}"
						placeholder="选择日期" />
					</td>
					<td style="width:15%; text-align: left">
						<span style="width: 40%;text-align: right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;星级: 
						</span>
						<select style="width:60%" id="starid" name="star">
							<option value="-1">--请选择--</option>
							<option value="0"
								<c:if test="${recordInfoForm.star==0}">selected = "selected"</c:if>>0星</option>
							<option value="1"
								<c:if test="${recordInfoForm.star==1}">selected = "selected"</c:if>>1星</option>
							<option value="2"
								<c:if test="${recordInfoForm.star==2}">selected = "selected"</c:if>>2星</option>
							<option value="3"
								<c:if test="${recordInfoForm.star==3}">selected = "selected"</c:if>>3星</option>
							<option value="4"
								<c:if test="${recordInfoForm.star==4}">selected = "selected"</c:if>>4星</option>
							<option value="5"
								<c:if test="${recordInfoForm.star==2}">selected = "selected"</c:if>>5星</option>
						</select>
					</td>
				</tr>
					<tr>
					<td></td>
					<td>
					<input type="button" class="button stats-view tooltip"
						original-title="View new registrations" value="查询" onclick="submitForm()"/>
					</td>
					<td>
					<c:if test="${currentuser.role.name eq 'add' || currentuser.role.name eq 'manage' }">
						<input type="button" class="button stats-view tooltip"
						original-title="View new registrations" onclick="createRecord()"
						value="添加资产" />
					</c:if>
					</td>						
					<td>
						<input type="button" class="button stats-view tooltip"
						original-title="View new registrations" onclick="exportExcel()" value="导出" />
					</td>
					<td>
					<c:if test="${currentuser.role.name eq 'add' || currentuser.role.name eq 'manage' }">
						<input type="button" class="button stats-view tooltip"
						original-title="View new registrations" value="清单导入"
						onclick="uploadFile()" />
					</c:if>
					</td>
					<td></td>
				</tr>
		</form>
	</table>
	</div>
	<table class="data" data-chart="line">
		<thead>
			<tr>
				 
				<th scope="col">全选<input type="checkbox" id="cas"
					onclick="checkAllSchema()" name="checkAll" value="">
				</th>
				<th scope="col">开单部门</th>
				<th scope="col">区域</th>
				<th scope="col">注册地址</th>
				<th scope="col">通讯地址</th>
				<th scope="col">联系人</th>
				<th scope="col">街道</th>
				<th scope="col">增值税</th>
				<th scope="col">所得税</th>
				<th scope="col">管理费</th>
				<th scope="col">返税</th>
				<th scope="col">电话</th>
				<th scope="col">客户类别</th>
				<th scope="col">微信号</th>
				<th scope="col">订单号</th>
				<th scope="col">星级</th>
				<th scope="col">状态</th>
				<th scope="col">创建时间</th>
				<th scope="col">操作人</th>
				<c:if test="${currentuser.role.name eq 'add' || currentuser.role.name eq 'manage' }">
					<th scope="col">修改</th>
				</c:if>
				<<c:if test="${currentuser.role.name eq 'delete' || currentuser.role.name eq 'manage' }">
					<th scope="col">删除</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="record" items="${recordLists}">
				<tr>
					<td><input type="checkbox" name="orderIds"
						value="${record.id}"></td>
					<td>${record.department }</td>
					<td>${record.region}</td>
					<td>${record.registAddress}</td>
					<td>${record.postalAddress}</td>
					<td>${record.contacts}</td>
					<td>${record.street}</td>
					<td>${record.addedTax}</td>
					<td>${record.incomeTax}</td>
					<td>${record.manageTax }</td>
					<td>${record.returnTax}</td>
					<td>${record.phone }</td>
					<td>
						<c:if test="${record.customerType==1 }">
							成交客户
						</c:if>
						<c:if test="${record.customerType==2 }">
							商讨客户
						</c:if>
					</td>
					<td>${record.wxNumber }</td>
					<td>${record.orderNumber }</td>
					<td>
						${record.star}星
					</td>
					<td><c:if test="${record.status==1}">
								已完成
							</c:if> <c:if test="${record.status==2}">
								注册中
							</c:if>
					</td>
					<td>
					<fmt:formatDate value="${record.initDate}" pattern="yyyy/MM/dd HH:mm:ss"/>
					</td>
					<td>${record.nickname}</td>
					<td>
					<c:if test="${currentuser.role.name eq 'add' || currentuser.role.name eq 'manage' }">
						<a href="javascript:void(0)" onclick="editeRecord(${record.id})">修改</a>
						</c:if>
					</td>
					<td>
					<c:if test="${currentuser.role.name eq 'delete' || currentuser.role.name eq 'manage' }">
						<a href="javascript:void(0)" onclick="deleteRecord(${record.id})">删除</a>
					</c:if>
					</td>
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
			if(pageCount >0 && currPage >1){
				out.print(String.format(
						"<a class=\"prev\" href=\"javascript:void(0)\" onclick='gotoPage(1)'>首页</a>",
						currPage - 1) + "&nbsp;");
			}
			
			if (currPage > 1) {
				out.print(String.format(
						"<a class=\"prev\" href=\"javascript:void(0)\" onclick='gotoPage(%d)'>上一页</a>",
						currPage - 1) + "&nbsp;");
			}
			for (int i = start; i <= end; i++) {
				if (i > pageCount) {
					break;
				}
				String pageinfo = String
						.format("<a href=\"javascript:void(0)\" onclick='gotoPage(%d)'>%d</a>", i, i);
				if (i == currPage) {
					pageinfo = String.format("<span>%d<span>", i);
				}
				out.print(pageinfo + "&nbsp;");
			}
			if (currPage < pageCount) {
				out.print(String.format(
						"<a class=\"prev\" href=\"javascript:void(0)\" onclick='gotoPage(%d)'>下一页</a>",
						currPage + 1) + "&nbsp;");
			}
			if (pageCount != 0) {
				out.print(String.format(
						"<a class=\"prev\" href=\"javascript:void(0)\" onclick='gotoPage(%d)'>尾页</a>",
						pageCount));
			}
		%>
	
	
	<div class=""
		style="text-align: center; color: black; font-size: 18px; height: 60px; margin-top: 60px">
		<div class="footer-inner" style="margin: auto;">
			<p style="">copyright ©2018 XXXXXX有限公司版权所有</p>
		</div>
	</div>
	</section> </article> </section> </article>

</body>
</html>

