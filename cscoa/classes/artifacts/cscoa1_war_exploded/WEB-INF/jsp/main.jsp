<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="telephone=no" name="format-detection" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="keywords" content="www.cnblogs.com/jihua" />
<meta http-equiv="cache-control"
	content="no-cache,no-store, must-revalidate" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<!--自适应界面,如果出现,在某些设备出现界面偏小的话,检查一下有没有加入这句 -->
<meta http-equiv="Content-type" name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width">
<link rel="stylesheet" href="jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="jquery.mobile-1.4.5/jquery-1.11.3.min.js"></script>
<script src="jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var error = "${setpwd }";
	 if(error=='ok'){
		 alert("修改密码成功");
	 }
});
</script>
	<style>
		#tipDiv{
			display:none;
			position: absolute;
			left: 39%;
			top: 230px;
			z-index: 9999;
			background: #d9d9d9;
			padding: 10px;
			border-radius: 5px;
		}
		#tipInfo{
			margin-top: 10px;
		}
	</style>
<%--<script>--%>
<%--		//监听加载状态改变--%>
<%--		document.onreadystatechange = completeLoading;--%>
<%--		//加载状态为complete时移除loading效果--%>
<%--		function completeLoading() {--%>
<%--			closeTip()--%>
<%--		}--%>
<%--</script>--%>
</head>
<body>
<%--	<div id="tipDiv">--%>
<%--		<center><img style="width:25px;" src="http://img.lanrentuku.com/img/allimg/1212/5-121204193R5-50.gif"></center>--%>
<%--		<div id="tipInfo"></div>--%>
<%--	</div>--%>
<%--	<script type="text/javascript">--%>
<%--		//显示提示--%>
<%--		function showTip(info){--%>
<%--			$('#tipInfo').html(info);--%>
<%--			$('#tipDiv').show();--%>
<%--		}--%>
<%--		//初始加载提示--%>
<%--		showTip('内容正在加载...');--%>
<%--		//关闭提示--%>
<%--		function closeTip(){--%>
<%--			$('#tipDiv').hide();--%>
<%--		}--%>
<%--	</script>--%>
	<form id='fForm' action="upload" encType="multipart/form-data" target="uploadf" method="post">
		<div data-role="page" id="page1">
			<!-- 页面开头 -->
			<div data-role="header" data-position="fixed">
			<a id="back" href="login"  data-ajax="false" class="back" data-role="none"><img src="image/back.png"></a>
				<h1>主页面</h1>
			</div>
			<div data-role="content" id="ndtcContent">
			<!-- 页面主体 -->
			<ul data-role="listview">
			
				<li><a href="audit?pagenum=0">验收单(单部门)审批<span class="ui-li-count">${single_count }</span></a></li>
				<li><a href="MultiAudit?pagenum=0">验收单(多部门)审批<span class="ui-li-count">${multi_count }</span></a></li>
				<li><a href="TrafficAudit?pagenum=0">验收单(交通费)审批<span class="ui-li-count">${traffic_count }</span></a></li>
				<li><a href="TravelAudit?pagenum=0">验收单(差旅费)审批<span class="ui-li-count">${travel_count }</span></a></li>
				<li><a href="TempAudit?pagenum=0">暂付款申请单审批<span class="ui-li-count">${temp_count }</span></a></li>
				<li><a href="SignAudit?pagenum=0">用印申请单审批<span class="ui-li-count">${sign_count }</span></a></li>
				<%-- <li><a href="KsmAudit?pagenum=0">康是美的验收单审批<span class="ui-li-count">${ksm_count }</span></a></li> --%>
				<li><a href="KsmAudit?pagenum=0">集团BU的验收单审批<span class="ui-li-count">${ksm_count }</span></a></li>
				 
			<li>
			<div data-role="collapsible">
				<h1>超商租金水电审批<span class="ui-li-count">${rtys_count+rtzf_count+rtyf_count+zjjk_count+sdjk_count+zjzf_count+sdzf_count }</span></h1>
				<ul data-role="listview">
				<li><a href="RTysAudit?pagenum=0">租金类付款验收单审批<span class="ui-li-count">${rtys_count }</span></a></li>
				<li><a href="RTzfAudit?pagenum=0">租金类暂付款单审批<span class="ui-li-count">${rtzf_count }</span></a></li>
				<li><a href="RTyfAudit?pagenum=0">租金类预付款单审批<span class="ui-li-count">${rtyf_count }</span></a></li>
				<li><a href="ZjjkAudit?pagenum=0">租金类批量付款审批<span class="ui-li-count">${zjjk_count }</span></a></li>
				<li><a href="SdjkAudit?pagenum=0">水电类批量付款审批<span class="ui-li-count">${sdjk_count }</span></a></li>
				<li><a href="ZjzfAudit?pagenum=0">租金类批量暂付审批<span class="ui-li-count">${zjzf_count }</span></a></li>
				<li><a href="SdzfAudit?pagenum=0">水电类批量暂付审批<span class="ui-li-count">${sdzf_count }</span></a></li>
				</ul>
			</div>
			  </li>
			  
			  
			  
			  <li><a href="updataPassword">修改密码</a></li>
		
			
			</ul>
			</div>
		</div>
	</form>
<%--	<iframe name="uploadf" style="display:none"></iframe>--%>
</body>
</html>