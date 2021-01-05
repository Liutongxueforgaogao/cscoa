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
<title>水电暂付款单</title>
<link rel="stylesheet" href="jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="jquery.mobile-1.4.5/jquery-1.11.3.min.js"></script> 
<script src="jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script>
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
<body>
<!-- ================================20170426==================================== -->
		<div data-role="page" id="page1">
		<div data-role="content" id="navigation">
		<div data-role="header" data-position="fixed">
			<a  id="back"  onclick="javascript:backLoading()" data-ajax="false" class="back" data-role="none"
				data-direction="reverse"><img src="image/back.png"></a>
			<h1>水电暂付款单审批列表</h1>
			<div id="tipDiv">
				<center><img style="width:25px;" src="picture/ajax-loader.gif"></center>
				<div id="tipInfo"></div>
			</div>
		</div>
			<script type="text/javascript">
				function backLoading() {
					//显示提示
					function showTip(info) {
						$('#tipInfo').html(info);
						$('#tipDiv').show();
					}
					//初始加载提示
					showTip('正在加载...');
					location.href="main"
				}
			</script>
		</div>
		<!-- 页面主体 -->
		<div data-role="content" id="pdadd">
			
				<ul data-role="listview" id="auditlist" data-theme="a">
					<!-- 放listview的内容 -->
					<li ><a href="searchtoSdzf" target="_top" class="ui-icon-search" data-ajax="false">条件查询</a></li>
					<li class="ui-body ui-body-c"><fieldset class="ui-grid-c">
						<div class="ui-block-a" style="margin:0 auto;text-align:center">编号</div>
						<div class="ui-block-b" style="margin:0 auto;text-align:center">名称</div>
						<div class="ui-block-c" style="margin:0 auto;text-align:center">金额</div>
						<div class="ui-block-d" style="margin:0 auto;text-align:center">状态</div>
						</fieldset>
					</li>
					<c:forEach items="${requestScope.audits }" var="audit">
						<li  class="bianse"  onclick="goto1('${audit.number}')" ><div class="ui-grid-c">
<style type="text/css">
div ul li.bianse:hover {                            
 background-color: #E0E0E0;               
 color: #000000;                                     
 cursor: pointer;                             
}
</style>
</head>
							<div class="ui-block-a" style="margin:0 auto;text-align:center;"><font size="2" >${audit.number }</font></div>
							<div class="ui-block-b" style="margin:0 auto;text-align:center;"><font size="2" >${audit.name }</font></div>
							<div class="ui-block-c" style="margin:0 auto;text-align:center;"><font size="2" >${audit.totalamount }</font></div>
							<div class="ui-block-d" style="margin:0 auto;text-align:center;"><font size="2" color="red" >未审核</font></div>
						</div></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div data-role="footer" data-position="fixed">
			<!--  20170503修改 -->		
			<div data-role="navbar">
				<ul>
					<li onclick="pagedown('${pagenum}','${maxpage }')"><a >上一页</a></li>
					<li onclick="pageup('${pagenum}','${maxpage }')"><a >下一页</a></li>
				</ul> 
			</div>
			
<script type="text/javascript">
	function goto1(checkid){
		location.href = "detailsSdzf?checkid="+checkid;
	}
	//分页功能
	var pagenum=0;
	var maxpage=0;
	function pagedown(pagenumin,maxpagein){

		if(pagenumin!=null){
			pagenum=pagenumin;
		}
		if(maxpagein!=null){
			maxpage=maxpagein;
		}
		pagenum=parseInt(pagenum);//字符串转化为整数
		if(pagenum==0){
			alert("已到首页");
		}else{
			pagenum=pagenum-1;
			location.href = "SdzfAudit?pagenum="+pagenum;
		}
	}
	function pageup(pagenumin,maxpagein){
		if(pagenumin!=null){
			pagenum=pagenumin;
		}
		if(maxpagein!=null){
			maxpage=maxpagein;
		}
		pagenum=parseInt(pagenum);
		maxpage=parseInt(maxpage);
		if(pagenum==maxpage){
			alert("已到最末页");
		}else{
			pagenum=pagenum+1;
			location.href = "SdzfAudit?pagenum="+pagenum;
		}
	}
	
</script> 
			 </div>
		</div> 

</body>
</html>