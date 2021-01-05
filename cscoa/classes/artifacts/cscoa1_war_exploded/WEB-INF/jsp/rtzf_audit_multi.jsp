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
<title>暂付款单批量审批</title>
<link rel="stylesheet" href="jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="jquery.mobile-1.4.5/jquery-1.11.3.min.js"></script> 
<script src="jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script> 

<body>
<!-- ================================20170426==================================== -->
		<div data-role="page" id="page1">
		<div data-role="content" id="navigation">
		<div data-role="header" data-position="fixed">
		<a href="main" data-ajax="false" class="back" data-role="none"
				data-direction="reverse"><img src="image/back.png"></a>
			<h1>暂付款单批量审批列表</h1>
		</div>
		<!-- 页面主体 -->
		<div data-role="content" id="pdadd">
			
				<ul data-role="listview" id="auditlist" data-theme="a">
					<!-- 放listview的内容 -->
					<li ><a onclick="agrees()"  target="_top" class="ui-icon-search" data-ajax="false">同意</a></li>
					<li ><a onclick="disagrees()"  target="_top" class="ui-icon-search" data-ajax="false">不同意</a></li>
					<li class="ui-body ui-body-c"><fieldset class="ui-grid-c">
						<div class="ui-block-a" style="margin:0 auto;text-align:center"></div>
						<div class="ui-block-b" style="margin:0 auto;text-align:center">编号</div>
						<div class="ui-block-c" style="margin:0 auto;text-align:center">名称</div>
						<div class="ui-block-d" style="margin:0 auto;text-align:center">金额</div>
						</fieldset>
					</li>
					<c:forEach items="${requestScope.audits }" var="audit">
						<li  class="bianse" style="background:white"  onclick="selectone('${audit.number}',this)" ><div class="ui-grid-c">
<style type="text/css">
div ul li.bianse:hover {                            
 background-color: #E0E0E0;               
 color: #000000;                                     
 cursor: pointer;                             
}
</style>
</head>
							
							<div class="ui-block-a" style="margin:0 auto;text-align:center;"><input type="checkbox" name="cb0" value="${audit.number }" ></div>
							<div class="ui-block-b" style="margin:0 auto;text-align:center;"><font size="2" >${audit.number }</font></div>
							<div class="ui-block-c" style="margin:0 auto;text-align:center;"><font size="2" >${audit.name }</font></div>
							<div class="ui-block-d" style="margin:0 auto;text-align:center;"><font size="2" color="red" >${audit.totalamount }</font></div>
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
    var checkids = '';
	function goto1(checkid){
		location.href = "detailsRTZF?checkid="+checkid;
	}
	function selectone(checkid,obj){
		//TODO
		//因为JS会把HTML标签当做一个对象  
        //对象.属性 = 值;  
        if(obj.style.background == 'white'){
        	//如果从不选择变到选择状态，直接添加
			obj.style.background = '#E0E0E0';  
			checkids = checkids+checkid+','
        }else{
        	//如果从选择变到不选择状态，需要删除
			obj.style.background = 'white';  
			deleteone(checkid);
        }
	}
	function deleteone(checkid){
		
		//用checkid分割，再拼接上
		arr = checkids.split(checkid+",");
		checkids = arr[0]+arr[1];
		
	}
	/* function submitform(){
		//TODO
	} */
	 
	function agrees(){
		var replyMessage = document.getElementById("replyMessage").value;
		location.href = "agrees?checkids="+checkids+"&replyMessage="+replyMessage;
	}
	function disagrees(){
		var replyMessage = document.getElementById("replyMessage").value;
		location.href = "disagrees?checkids="+checkids+"&replyMessage="+replyMessage;
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
			location.href = "RTzfAudit?pagenum="+pagenum;
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
			location.href = "RTzfAudit?pagenum="+pagenum;
		}
	}
	
</script> 
			 </div>
		</div> 
<!--同意弹出框  -->
 		<div data-role="popup" id="spdialog1" class="ui-content" style="max-width: 280px" data-position-to="window">
		<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">Close</a>
		<div data-role="header" data-theme="a" class="ui-corner-top">
			<h1>审批意见</h1>
		</div>
			<div data-role="content" data-theme="d" class="ui-corner-bottom ui-content">
											
				<form id="form_1" onsubmit="return agrees();"  method="post">
					<textarea cols="30" rows="5" name="replyMessage"></textarea>			
					<div align="center" style="display: none;"><span style="color:red">您没有输入内容!</span></div>
					<br>
					<!-- 取消以及确定按钮，确认按钮点击后则将输入数据保存在数据库中 -->
					<center>
					<a data-rel='back' data-role="button" data-inline="true" data-theme="c">取消</a> 
					<a onclick="document.getElementById('form_1').submit();return false" target="_top" data-role="button" data-inline="true" data-theme="b">确定</a> 
					<!-- <input type="submit" value="确定" data-role="button" data-inline="true" data-theme="b" />  -->
					</center>
				</form>
			</div>
		</div> 
		<!--打回弹出框  -->
 		<div data-role="popup" id="spdialog2" class="ui-content" style="max-width: 280px" data-position-to="window">
		<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">Close</a>
		<div data-role="header" data-theme="a" class="ui-corner-top">
			<h1>审批意见</h1>
		</div>
			<div data-role="content" data-theme="d" class="ui-corner-bottom ui-content">
				<form id="form_2" onsubmit="return disagrees();" method="post">
					<textarea cols="30" rows="5" name="replyMessage"></textarea>			
					<div align="center" style="display: none;"><span style="color:red">您没有输入内容!</span></div>
					<br>
					<!-- 取消以及确定按钮，确认按钮点击后则将输入数据保存在数据库中 -->
					<center>
					<a data-rel='back' data-role="button" data-inline="true" data-theme="c">取消</a> 
					<a onclick="document.getElementById('form_2').submit();return false" target="_top" data-role="button" data-inline="true" data-theme="b">确定</a> 
					<!-- <input type="submit" value="确定" data-role="button" data-inline="true" data-theme="b" />  -->
					</center>
				</form>
			</div>
		</div> 
 
	</div>
</body>
</html>