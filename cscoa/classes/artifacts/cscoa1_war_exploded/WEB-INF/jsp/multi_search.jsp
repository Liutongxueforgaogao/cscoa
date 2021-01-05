<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="telephone=no" name="format-detection" />
<!--自适应界面,如果出现,在某些设备出现界面偏小的话,检查一下有没有加入这句 -->
<meta http-equiv="Content-type" name="viewport"
	content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/theme.css">
<link rel="stylesheet"
	href="jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="jquery.mobile-1.4.5/jquery-1.11.3.min.js"></script> 
<script src="jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script> 

<!-- 时间控件包 -->
<script src="dev/js/mobiscroll.core-2.5.2.js" type="text/javascript"></script>
<script src="dev/js/mobiscroll.core-2.5.2-zh.js" type="text/javascript"></script>

<link href="dev/css/mobiscroll.core-2.5.2.css" rel="stylesheet"
	type="text/css" />
<link href="dev/css/mobiscroll.animation-2.5.2.css" rel="stylesheet"
	type="text/css" />
<script src="dev/js/mobiscroll.datetime-2.5.1.js" type="text/javascript"></script>
<script src="dev/js/mobiscroll.datetime-2.5.1-zh.js"
	type="text/javascript"></script>
	<style type="text/css">
ul li.bianse:hover {                            
 background-color: #E0E0E0;               
 color: #000000;                                     
 cursor: pointer;                             
}
</style>
<script type="text/javascript">
function setCookie(name, value, iDay) 
{
    var oDate=new Date();
     
    oDate.setDate(oDate.getDate()+iDay);
     
    document.cookie=name+'='+encodeURIComponent(value)+';expires='+oDate;
}
function getCookie(name)
{
    var arr=document.cookie.split('; ');
    var i=0;
    for(i=0;i<arr.length;i++)
    {
        //arr2->['username', 'abc']
        var arr2=arr[i].split('=');
         
        if(arr2[0]==name)
        {  
            var getC = decodeURIComponent(arr2[1]);
            return getC;
        }
    }
     
    return '';
}
 
function removeCookie(name)
{
    setCookie(name, '1', -1);
}
/* 获取当前时间,并且添加时间控件 */
$(function() {
	//var timestamp = new Date().getTime();//获取时间戳
	var mydate = new Date();
	var str = mydate.getFullYear() + "-";
	str += (mydate.getMonth() + 1) + "-";
	str += mydate.getDate();//时间的默认值
	shijian = str;
	var opt = {
		preset : 'date', //日期
		theme : 'jqm', //皮肤样式
		display : 'modal', //显示方式 
		mode : 'mixed', //日期选择模式
		dateFormat : 'yy-mm-dd', // 日期格式
		setText : '确定', //确认按钮名称
		cancelText : '取消',//取消按钮名籍我
		dateOrder : 'yymmdd', //面板中日期排列格式
		dayText : '日',
		monthText : '月',
		yearText : '年', //面板中年月日文字
		endYear : 2020
	//结束年份
	};
	$('input:jqmData(role="datebox")').mobiscroll(opt);//设置时间控件
	var pddh = getCookie("pddh");
	$("#pddhe").val(pddh);//设置默认单号
	var pdrqstart = getCookie("pdrqstart");
	if(pdrqstart==''){
		$("#pdrqstart").val(str);//盘点日期设置默认值
	}else{
		$("#pdrqstart").val(pdrqstart);//盘点日期设置默认值
	}
	var pdrqend = getCookie("pdrqend");
	if(pdrqend==''){
		$("#pdrqend").val(str);//盘点日期设置默认值
	}else{
		$("#pdrqend").val(pdrqend);//盘点日期设置默认值
	}
});
					
				
 
	/* 查询页面的查询按钮 */
	function chaxun(a){
		var pddh = eval(document.getElementById('pddhe')).value;//审核单号
		setCookie("pddh", pddh, 5000);
		var pdrqstart = eval(document.getElementById('pdrqstart')).value;//审核日期
		setCookie("pdrqstart", pdrqstart, 5000);
		var pdrqend = eval(document.getElementById('pdrqend')).value;//审核日期
		setCookie("pdrqend", pdrqend, 5000);
		var pdkw = document.getElementById("pdkwe");
		var pdkwi = pdkw.selectedIndex ; 
		var pdkwv = pdkw.options[pdkwi].text;//审核状态
		
		var json = {pddh:pddh,pdrqstart:pdrqstart,pdrqend:pdrqend,pdkwv:pdkwv}; 
		var pandian = encodeURI(JSON.stringify(json));
		
		location.href = "multi_searchByCondition?condition="+pandian;
	}

</script> 
</head>
<body>


<div data-role="page" id="page2">
		<!-- 页面开头 -->
		<div data-role="header" data-position="fixed">
			<a href="MultiAudit?pagenum=0" data-ajax="false" class="back" data-role="none"
				data-direction="reverse"><img src="image/back.png"></a>
			<h1>多部门条件查询</h1>
			<a href="main" data-ajax="false" class="back" data-role="none"
				data-direction="reverse"><img src="image/house.png"></a>
		</div>
		<!-- 页面主体 -->
		<div data-role="content" id="ndchaxun">
			<div class="ui-grid-a">
				<a><label for="pddhe">审核单号:</label> <input type="text"
					name="pddhe" id="pddhe"> </a> 
					<a><label for="pdrqe1">审核日期开始:</label>
					<input data-role="datebox" name="pdrq1" id="pdrqstart"> </a>
					<a><label for="pdrqe2">审核日期结束:</label>
					<input data-role="datebox" name="pdrq2" id="pdrqend"> </a>
					
				 <a><label for="pdkwe">审核状态:</label> 
					<select name="pdkwe" id="pdkwe">
						<option value="pdkwe">全部</option>
							<!-- <option value="">正在审核</option> -->
							<option value="">未通过</option>
							<option value="">已通过</option>
					</select></a>
	
			</div>
			<div align="right">
				<p>
					<a data-role="button" onclick="chaxun(1)">查询<span class="ui-li-count">${multi_size }</span></a>
				</p>
			</div>
			<ul data-role="listview" id="auditlist" data-inset="true" data-theme="a">
			<li class="ui-body ui-body-c"><fieldset class="ui-grid-c">
						<div class="ui-block-a" style="margin:0 auto;text-align:center">编号</div>
						
						<div class="ui-block-b" style="margin:0 auto;text-align:center">名称</div>
						<div class="ui-block-c" style="margin:0 auto;text-align:center">金额</div>
						<div class="ui-block-d" style="margin:0 auto;text-align:center">状态</div>
						</fieldset>
			</li>
			<c:forEach items="${requestScope.audits }" var="audit">
					<li  class="bianse"  onclick="goto('${audit.number}')" ><div class="ui-grid-c">
						
 <script type="text/javascript">
	function goto(checkid){
		location.href = "multi_details?checkid="+checkid;
	}
</script> 
						<div class="ui-block-a" style="margin:0 auto;text-align:center;"><font size="2" >${audit.number }</font></div>
						<div class="ui-block-b" style="margin:0 auto;text-align:center;"><font size="2" >${audit.name }</font></div>
						<div class="ui-block-c" style="margin:0 auto;text-align:center;"><font size="2" >${audit.totalamount }</font></div>
						<div class="ui-block-d" style="margin:0 auto;text-align:center;">
							<c:choose>  
							   <c:when test="${audit.zt=='1'}">   
							   	<font  size="2" color="green">已通过</font> 
							   </c:when>   
							   <c:when test="${audit.zt=='0'}">   
							   	<font  size="2" color="grey">未通过</font> 
							   </c:when>   
							   <c:otherwise>
							   	<font  size="2" color="red">未审核</font>
							   </c:otherwise>  
							</c:choose>
						</div>
					</div></li>
				</c:forEach>
			</ul>
		</div>
	</div>



</body>
	
</html>