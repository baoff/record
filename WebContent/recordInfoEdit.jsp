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
				 J('#img1').calendar({ id:'cal1',format: 'yyyy-MM-dd HH:mm:ss' });
			});
		</script>
		<title>主页</title>
		<script language="JavaScript">
			var message = '${editMessage}';
			if(message !='' ){
				if(message == "no assetTagNum"){
					alert("资产标签号不能为空");
					//window.close();
				}else if(message == "error"){
					alert("保存失败");
				}else if(message == "success"){
					alert("保存成功");
					window.opener.submitForm("search");
					window.close();
					//window.location.href="orderinfo.ak?method=search_orderInfo_action";
				}
			}
			//
			function load(){
				var imgSrc_2 = 'images/star2.png';
				var star_num = ${editeRecord.star};
				var imgArray = $("img");
				for(var j = 0 ; j < imgArray.length; j++){
					if(j < star_num){
						imgArray[j].src = imgSrc_2;
					}
					imgArray[j]._num=j;
				}
			}
			
			 function rate(oEvent) {
				// 图片地址设置
				var imgSrc = 'images/star.png'; //没有填色的星星
				var imgSrc_2 = 'images/star2.png'; //打分后有颜色的星星,这里的star_full图片时实心的有颜色的五星。
				//if(obj.rateFlag) return;
				var e = oEvent || window.event;
				var target = e.target || e.srcElement;
				var imgArray = $("img");
				if(target.tagName == "IMG") {
					for(var j = 0; j < imgArray.length; j++) {
					if(j <= target._num) {
						imgArray[j].src = imgSrc_2;
					} else {
					imgArray[j].src = imgSrc;
					}
					}
					} else {
					for(var k = 0; k < imgArray.length; k++) {
					imgArray[k].src = imgSrc;
					}
					}
				$("#star").val(target._num+1);
				//alert($("#star").val());
			}
			
			function checkEmpty(){
				  /* if($("#assetTagNum").val().trim()==""){
					  alert('资产标签号不能为空');
					  return false;
				  }else{
					  return true;
				  } */
				  return true;
				}
		
		</script>
	</head>
	<body onload="load()">
	<%@ include file="head.jsp" %>
		<br />
		<br />
		&nbsp;
		<section id="fact">
			<h1>信息记录系统</h>
		</section>
		<article class="content-box"> <header>
		<h2 style="padding-right: 90px;">
			资产编辑
		</h2>
		<a href="" title="Toggle Content Block"
			style="display: block; left: 170px; top: 170px;"></a> </header> <section>
		<article class="content-box"> <section>
		<form:form method="post" commandName="editeRecord"
			action="recordinfo.ak?method=edit_recordinfo_action" onsubmit="return checkEmpty()">
			<form:hidden path="id" />
			<form:hidden path="initDate" />
			<form:hidden path="backType" />
			<form:hidden path="star" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<label>
							开单部门
						</label>
					</td>
					<td style="text-align: left">
						<form:textarea path="department" rows="1" cols="70" />
					</td>
					<td>
						<label>
							区域
						</label>
					</td>
					<td style="text-align: left">
						<form:textarea path="region" rows="1" cols="70" />
					</td>
				</tr>
				<tr>
					<td>
						<label>
							注册地址
						</label>
					</td>
					<td style="text-align: left">
						<form:textarea path="registAddress" rows="1" cols="70" />
					</td>
					<td>
						<label>
							通讯地址
						</label>
					</td>
					<td style="text-align: left">
						<form:textarea path="postalAddress" rows="1" cols="70" />
					</td>
				</tr>
				<tr>
					<td>
						<label>
							联系人
						</label>
					</td>
					<td style="text-align: left">
						<form:textarea path="contacts" rows="1" cols="70" />
					</td>
					<td>
						<label>
							街道
						</label>
					</td>
					<td style="text-align: left">
						<form:textarea path="street" rows="1" cols="70" />
					</td>
				</tr>
				<tr>
					<td>
						<label>
							增值税
						</label>
					</td>
					<td style="text-align: left">
						<form:textarea path="addedTax" rows="1" cols="70" />
					</td>
					<td>
						<label>
							所得税
						</label>
					</td>
					<td style="text-align: left">
						<form:textarea path="incomeTax" rows="1" cols="70" />
					</td>
				
				</tr>
				<tr>
					<td>
						<label>
							管理费
						</label>
					</td>
					<td style="text-align: left">
						<form:textarea path="manageTax" rows="1" cols="70" />
					</td>
					<td>
						<label>
							返税
						</label>
					</td>
					<td style="text-align: left">
						<form:textarea path="returnTax" rows="1" cols="70" />
					</td>
				</tr>
				<tr>
					<td>
						<label>
							电话
						</label>
					</td>
					<td style="text-align: left">
						<form:textarea path="phone" rows="1" cols="70" />
					</td>
					<td>
						<label>
							微信号
						</label>
					</td>
					<td style="text-align: left">
						<form:textarea path="wxNumber" rows="1" cols="70" />
					</td>
				</tr>
				<tr>
					<td>
						<label>
							订单号
						</label>
					</td>
					<td style="text-align: left">
						<form:textarea path="orderNumber" rows="1" cols="70" />
					</td>
					<td>
						<label>
							客户类型
						</label>
					</td>
					<td style="text-align: left">
						<form:select path="customerType">
							<form:option value="-1">--请选择--</form:option>
							<form:option value="1">成交客户</form:option>
							<form:option value="2">商讨客户</form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							状态
						</label>
					</td>
					<td style="text-align: left">
						<form:select path="status">
							<form:option value="-1">--请选择--</form:option>
							<form:option value="1">已完成</form:option>
							<form:option value="2">注册中</form:option>
						</form:select>
					</td>		
					<td>
						<label>
							星级
						</label>
					</td>
					<td style="text-align: left">
						<div>
							<img onclick="rate(event)" src="images/star.png" title="1分"/>
							<img onclick="rate(event)" src="images/star.png" title="2分"/>
							<img onclick="rate(event)" src="images/star.png" title="3分"/>
							<img onclick="rate(event)" src="images/star.png" title="4分"/>
							<img onclick="rate(event)" src="images/star.png" title="5分"/>
						</div>
					</td>							
					</tr>
			</table>
			</br>
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
	</body>
　　</style>
</html>


