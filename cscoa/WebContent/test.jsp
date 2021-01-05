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
<title>康是美审批</title>
<link rel="stylesheet" href="jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="jquery.mobile-1.4.5/jquery-1.11.3.min.js"></script> 
<script src="jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script> 
<style type="text/css">
li{
cursor: pointer; 
}


</style>
</head>
<body>
<!-- ================================20170426==================================== -->
		<div data-role="page" id="page1">
		<div data-role="content" id="navigation">
		<div data-role="header" data-position="fixed">
			<h1>康是美审批列表</h1>
		</div>
		<!-- 页面主体 -->
		<div data-role="content" id="pdadd">
			
				<ul data-role="listview" id="auditlist" data-theme="a">
					<!-- 放listview的内容 -->
					<li ><a href="ksm_searchto" target="_top" class="ui-icon-search" data-ajax="false">条件查询</a></li>
					<li class="ui-body ui-body-c"><fieldset class="ui-grid-c">
						<div class="ui-block-a" style="margin:0 auto;text-align:center">编号</div>
						<div class="ui-block-b" style="margin:0 auto;text-align:center">名称</div>
						<div class="ui-block-c" style="margin:0 auto;text-align:center">金额</div>
						<div class="ui-block-d" style="margin:0 auto;text-align:center">状态</div>
						</fieldset>
					</li>
					<li  ><div class="ui-grid-c">
						<div class="ui-block-a" style="margin:0 auto;text-align:center;"><font size="2" >1</font></div>
						<div class="ui-block-b" style="margin:0 auto;text-align:center;"><font size="2" >2</font></div>
						<div class="ui-block-c" style="margin:0 auto;text-align:center;"><font size="2" >3</font></div>
						<div class="ui-block-d" style="margin:0 auto;text-align:center;"><font size="2" color="red" >未审核</font></div>
					</div>
					</li>
					<li  ><div class="ui-grid-c">
						<div class="ui-block-a" style="margin:0 auto;text-align:center;"><font size="2" >1</font></div>
						<div class="ui-block-b" style="margin:0 auto;text-align:center;"><font size="2" >2</font></div>
						<div class="ui-block-c" style="margin:0 auto;text-align:center;"><font size="2" >3</font></div>
						<div class="ui-block-d" style="margin:0 auto;text-align:center;"><font size="2" color="red" >未审核</font></div>
					</div>
					</li>
					<li  ><div class="ui-grid-c">
						<div class="ui-block-a" style="margin:0 auto;text-align:center;"><font size="2" >1</font></div>
						<div class="ui-block-b" style="margin:0 auto;text-align:center;"><font size="2" >2</font></div>
						<div class="ui-block-c" style="margin:0 auto;text-align:center;"><font size="2" >3</font></div>
						<div class="ui-block-d" style="margin:0 auto;text-align:center;"><font size="2" color="red" >未审核</font></div>
					</div>
					</li>
					<li  ><div class="ui-grid-c">
						<div class="ui-block-a" style="margin:0 auto;text-align:center;"><font size="2" >1</font></div>
						<div class="ui-block-b" style="margin:0 auto;text-align:center;"><font size="2" >2</font></div>
						<div class="ui-block-c" style="margin:0 auto;text-align:center;"><font size="2" >3</font></div>
						<div class="ui-block-d" style="margin:0 auto;text-align:center;"><font size="2" color="red" >未审核</font></div>
					</div>
					</li>
					
				</ul>
			</div>
		</div>

</body>
</html>