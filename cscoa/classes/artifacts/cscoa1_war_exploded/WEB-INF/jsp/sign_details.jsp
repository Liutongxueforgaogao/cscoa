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
<title>暂付款审批</title>
<link rel="stylesheet" href="jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="jquery.mobile-1.4.5/jquery-1.11.3.min.js"></script>
<script src="jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script>
<style type="text/css">
a{text-decoration:none}
li{
 white-space: nowrap;
}
textarea.ui-input-text { min-height: 90px;  }
</style>
<script type="text/javascript">

</script>
</head>
<body>
	<div data-role="page" id="page2">
		
		<div data-role="header" data-position="fixed">
			<a href="SignAudit?pagenum=0" data-ajax="false" class="back" data-role="none"
				data-direction="reverse"><img src="image/back.png"></a>
			<h1>详情显示</h1>
			<a href="main" data-ajax="false" class="back" data-role="none"
				data-direction="reverse"><img src="image/house.png"></a>
		</div>
		<!-- 正文部分 -->
		<div data-role="content" id="navigation" >
			<table border="0" background="white">
			 	<tr>
					<td width="85%">
					<img src="image/username.png" style="vertical-align:middle;"/><br>
					<font color="grey"><font size="1">
					</td>
					<c:if test="${atag=='0' }">
						<td id="once1" target="_top" align="center">
							<a href="#spdialog1" data-rel="popup"><img src="image/dui3.png"/><br><font color="grey">同意</font></a>
							<%-- <a href="agree?checkid=${details.checkid }" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-mini"  font-size="8px">同意</a> --%>
						</td>
						
						<td id="once0" align="center">
							<a href="#spdialog2" data-rel="popup"><img src="image/cuo3.png"/><br><font color="grey">打回</font></a>
							<%-- <a href="disagree?checkid=${details.checkid }" class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-mini"  font-size="8px">打回</a> --%>
						</td>
					</c:if>
					
				</tr> 
				<!-- <tr>
						<td width="85%"><img src="image/username.png" style="vertical-align:middle;"/></td>
						<td onclick="spty('tongyi')" align="center">
							<img src="image/dui3.png"/><br><font color="grey">同意</font>
						</td>
						<td onclick="spty('dahui')" align="center">
							<img src="image/cuo3.png"/><br><font color="grey">打回</font>
						</td>
					</tr> -->
			</table>
			<!-- <HR width="100%" color=#987cb9 SIZE=2 /> -->
<!-- 	private String checkid;				//验收单号:checkid
		private String inputdate;			//申请日期：[inputdate]
		private String staff;				//申请人：[staff]
		private String companyno;			//所属公司编号：[companyno]
		private String feeunitno;			//部分编码：[feeunitno]
		private String feeunit;				//部门名称：[feeunit]
		private String payee;				//受款人：[payee]
		private String payeename;			//受款人姓名：[payeename]
		private String proceedingname;		//费用事项名称：[proceedingname]
		private String revcompany;			//收款单位：revcompany
		private String revcompanyname;		//收款单位名称;[revcompanyname] 
-->		
			<table border="0" background="white">
					<tr>
						<td colspan="3"><img  src="image/ht1.png" style="vertical-align:middle;"/>&nbsp;&nbsp;<font color="grey" >单据号：${details.checkid }</font></td> 
					</tr>
					<tr>
						<td colspan="3"><img src="image/user22.png" style="vertical-align:middle;"/>&nbsp;&nbsp;<font color="grey" >审批人：${username }</font></td> 
					</tr>
					<tr>
						<td colspan="3"><img src="image/gs.png" style="vertical-align:middle;"/>&nbsp;&nbsp;<font color="grey" >申请人：${details.staff }</font></td>
					</tr>
					<tr>
						<td colspan="3"><img src="image/shijian2.png" style="vertical-align:middle;"/>&nbsp;&nbsp;<font color="grey" >申请时间:${details.checkdate }</font></td>
					</tr>
				</table>
			<!-- <HR width="100%" color=#987cb9 SIZE=2 /> -->
<!-- ========================================================================= -->

			<div data-role="content">
			 	
<!--回复列表  -->
<ul style="white-space: normal;" data-role="listview" id="listsi" data-inset="true" data-theme="a">
<%-- <c:forEach items="${requestScope.suggests }" var="suggests">
		<li style="font-size: 12px">${suggest.userLevelname }:${suggests }</li>
</c:forEach> --%>
<c:forEach items="${requestScope.allPeopleSuggest }" var="allPeopleSuggest">
	<li style="font-size: 12px;white-space: normal;">${allPeopleSuggest.user }:${allPeopleSuggest.suggest }</li>
</c:forEach>
</ul>
				<p>
				<table  width="100%">
				<!-- border="0" -->
<tr>
	<td colspan="2">
		<label style="font-size:13px;color:#bf2020;">
			${details.feecompany eq null?"":"公司:" }${details.feecompany}
		</label>
	</td>
</tr>
<%-- <tr>
	<td colspan="2">
		<label style="font-size:13px;color:#bf2020;">
			${details.revtype eq null?"":"文件对象:" }${details.revtype}
		</label>
	</td>
</tr> --%>
<tr>
	<td colspan="2">
		<label style="font-size:13px;color:#bf2020;">
			${details.filetype eq null?"":"文件类型:" }${details.filetype}
		</label>
		<!-- 1,合同;2,申请书;3,证明书;4,公函;5,公告事项;6,其他  -->
	</td>
</tr>
 <tr>
	<td colspan="2">
		<label style="font-size:13px;color:#bf2020;">
			${details.stampertype eq null?"":"所属印章1:" }${details.stampertype}
		</label>
	</td>
</tr> 
 <tr>
	<td colspan="2">
		<label style="font-size:13px;color:#bf2020;">
			${details.stampertype1 eq null?"":"所属印章2:" }${details.stampertype1}
		</label>
	</td>
</tr> 
 <tr>
	<td colspan="2">
		<label style="font-size:13px;color:#bf2020;">
			${details.stampertype2 eq null?"":"所属印章3:" }${details.stampertype2}
		</label>
	</td>
</tr> 
<tr>
	<td colspan="2">
		<label style="font-size:13px;color:#bf2020;">
			${details.counts eq null?"":"份数:" }${details.counts }
		</label>
	</td>	
</tr>
<tr>
	<td colspan="2">
		<label style="font-size:13px;color:#bf2020;">
			${details.header eq null?"":"文件标题:" }${details.header }
		</label>
	</td>	
</tr>
<tr>
	<td colspan="2">
		<label style="font-size:13px;color:#bf2020;">
			${details.startdate eq null?"":"有效期起:" }${details.startdate }
		</label>
	</td>	
</tr>
<tr>
	<td colspan="2">
		<label style="font-size:13px;color:#bf2020;">
			${details.enddate eq null?"":"有效期止:" }${details.enddate }
		</label>
	</td>	
</tr>
<tr>
	<td colspan="2">
		<label style="font-size:13px;color:#bf2020;">
			${details.remarks eq null?"":"用印说明:" }${details.remarks }
		</label>
	</td>	
</tr>

				</table>	
<hr>

<!-- ========================================================================= -->
			
			<!--       图片预览           -->
		<label style="font-size:13px;color:#bf2020;">图片预览:</label>
		<%-- <div align="center">
			<c:if test="${details.image!='' }">
				<a href="${details.image }" data-rel="popup" data-position-to="window" data-transition="fade">
					<embed class="popphoto1" data-rel="popup"  src="${details.image }"  style="width:auto;height:auto;max-width:100%;max-height:100%;" align="middle">
					</embed>
				</a>
			</c:if>
			<c:if test="${details.imageTwo!='' }">
				<a href="${details.imageTwo }" data-rel="popup" data-position-to="window" data-transition="fade">
					<embed class="popphoto2" data-rel="popup"  src="${details.imageTwo }"  style="width:auto;height:auto;max-width:100%;max-height:100%;" align="middle">
					</embed>
				</a>
			</c:if>
			<c:if test="${details.imageThree!='' }">
				<a href="${details.imageThree }" data-rel="popup" data-position-to="window" data-transition="fade">
					<embed class="popphoto3" data-rel="popup"  src="${details.imageThree }"  style="width:auto;height:auto;max-width:100%;max-height:100%;" align="middle">
					</embed>
				</a>
			</c:if>
			<c:if test="${details.imageFour!='' }">
				<a href="${details.imageFour }" data-rel="popup" data-position-to="window" data-transition="fade">
					<embed class="popphoto4" data-rel="popup"  src="${details.imageFour }"  style="width:auto;height:auto;max-width:100%;max-height:100%;" align="middle">
					</embed>
				</a>
			</c:if>
			<c:if test="${details.imageFive!='' }">
				<a href="${details.imageFive }" data-rel="popup" data-position-to="window" data-transition="fade">
					<embed class="popphoto5" data-rel="popup"  src="${details.imageFive }" style="width:auto;height:auto;max-width:100%;max-height:100%;" align="middle">
					</embed>
				</a>
			</c:if>
		</div> --%>
		<div align="center">
		<c:if test="${image1status=='1' }">
			<c:if test="${details.image!='' }">
				<a target="_blank" href="${details.image }" data-rel="popup" data-position-to="window" data-transition="fade">
				${image1name }</a><br/>
			</c:if>
		</c:if>
		<c:if test="${image1status=='0' }">
			<c:if test="${details.image!='' }">
				<a href="#popupParis1" data-rel="popup" data-position-to="window" data-transition="fade">
					<img class="popphoto1" data-rel="popup"  src="${details.image }"  style="width:auto;height:auto;max-width:100%;max-height:100%;" align="middle">
				</a>
				<div data-role="popup" id="popupParis1" data-overlay-theme="b" data-theme="b" data-corners="false">
			  			<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">
			  			Close</a><img class="popphoto1" src="${details.image }" width="100%" height="100%" alt="预览图">
				</div>
			</c:if>
		</c:if>	
		<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->	
		<c:if test="${image2status=='1' }">
			<c:if test="${details.imageTwo!='' }">
				<a target="_blank" href="${details.imageTwo }" data-rel="popup" data-position-to="window" data-transition="fade">
				${image2name }</a><br/>
			</c:if>
		</c:if>
		<c:if test="${image2status=='0' }">
			<c:if test="${details.imageTwo!='' }">
				<a href="#popupParis2" data-rel="popup" data-position-to="window" data-transition="fade">
					<img class="popphoto2" data-rel="popup"  src="${details.imageTwo }"  style="width:auto;height:auto;max-width:100%;max-height:100%;" align="middle">
				</a>
				<div data-role="popup" id="popupParis2" data-overlay-theme="b" data-theme="b" data-corners="false">
			  			<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">
			  			Close</a><img class="popphoto2" src="${details.imageTwo }" width="100%" height="100%" alt="预览图">
				</div>
			</c:if>
		</c:if>		
		<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->	
		<c:if test="${image3status=='1' }">
			<c:if test="${details.imageThree!='' }">
				<a target="_blank" href="${details.imageThree }" data-rel="popup" data-position-to="window" data-transition="fade">
				${image3name }</a><br/>
			</c:if>
		</c:if>
		<c:if test="${image3status=='0' }">
			<c:if test="${details.imageThree!='' }">
				<a href="#popupParis3" data-rel="popup" data-position-to="window" data-transition="fade">
					<img class="popphoto3" data-rel="popup"  src="${details.imageThree }"  style="width:auto;height:auto;max-width:100%;max-height:100%;" align="middle">
				</a>
				<div data-role="popup" id="popupParis3" data-overlay-theme="b" data-theme="b" data-corners="false">
			  			<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">
			  			Close</a><img class="popphoto3" src="${details.imageThree }" width="100%" height="100%" alt="预览图">
				</div>
			</c:if>
		</c:if>		
		<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->	
		<c:if test="${image4status=='1' }">
			<c:if test="${details.imageFour!='' }">
				<a target="_blank" href="${details.imageFour }" data-rel="popup" data-position-to="window" data-transition="fade">
				${image4name }</a><br/>
			</c:if>
		</c:if>
		<c:if test="${image4status=='0' }">
			<c:if test="${details.imageFour!='' }">
				<a href="#popupParis4" data-rel="popup" data-position-to="window" data-transition="fade">
					<img class="popphoto4" data-rel="popup"  src="${details.imageFour }"  style="width:auto;height:auto;max-width:100%;max-height:100%;" align="middle">
				</a>
				<div data-role="popup" id="popupParis4" data-overlay-theme="b" data-theme="b" data-corners="false">
			  			<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">
			  			Close</a><img class="popphoto4" src="${details.imageFour }" width="100%" height="100%" alt="预览图">
				</div>
			</c:if>
		</c:if>		
		<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->	
		<c:if test="${image5status=='1' }">
			<c:if test="${details.imageFive!='' }">
				<a target="_blank" href="${details.imageFive }" data-rel="popup" data-position-to="window" data-transition="fade">
				${image5name }</a><br/>
			</c:if>
		</c:if>
		<c:if test="${image5status=='0' }">
			<c:if test="${details.imageFive!='' }">
				<a href="#popupParis5" data-rel="popup" data-position-to="window" data-transition="fade">
					<img class="popphoto5" data-rel="popup"  src="${details.imageFive }"  style="width:auto;height:auto;max-width:100%;max-height:100%;" align="middle">
				</a>
				<div data-role="popup" id="popupParis5" data-overlay-theme="b" data-theme="b" data-corners="false">
			  			<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">
			  			Close</a><img class="popphoto5" src="${details.imageFive }" width="100%" height="100%" alt="预览图">
				</div>
			</c:if>
		</c:if>		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		<%-- 	<c:if test="${details.imageTwo!='' }">
				<a target="_blank" href="${details.imageTwo }" data-rel="popup" data-position-to="window" data-transition="fade">
				${details.image2name }</a><br/>
			</c:if>
			
			<c:if test="${details.imageThree!='' }">
				<a target="_blank" href="${details.imageThree }" data-rel="popup" data-position-to="window" data-transition="fade">
				${details.image3name }</a><br/>
			</c:if>
			
			<c:if test="${details.imageFour!='' }">
				<a target="_blank" href="${details.imageFour }" data-rel="popup" data-position-to="window" data-transition="fade">
				${details.image4name }</a><br/>
			</c:if>
			
			<c:if test="${details.imageFive!='' }">
				<a target="_blank" href="${details.imageFive }" data-rel="popup" data-position-to="window" data-transition="fade">
				${details.image5name }</a><br/>
			</c:if> --%>
		</div>
	<%-- 图片放大
	<div data-role="popup" id="popupParis1" data-overlay-theme="b" data-theme="b" data-corners="false">
  			<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">
  					Close</a><img class="popphoto1" src="${details.image }" width="100%" height="100%" alt="预览图">
	</div> 
	<div data-role="popup" id="popupParis2" data-overlay-theme="b" data-theme="b" data-corners="false">
  			<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">
  					Close</a><img class="popphoto2" src="${details.imageTwo }" width="100%" height="100%" alt="预览图">
	</div> 
	<div data-role="popup" id="popupParis3" data-overlay-theme="b" data-theme="b" data-corners="false">
  			<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">
  					Close</a><img class="popphoto3" src="${details.imageThree }" width="100%" height="100%" alt="预览图">
	</div> 
	<div data-role="popup" id="popupParis4" data-overlay-theme="b" data-theme="b" data-corners="false">
  			<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">
  					Close</a><img class="popphoto4" src="${details.imageFour }" width="100%" height="100%" alt="预览图">
	</div> 
	<div data-role="popup" id="popupParis5" data-overlay-theme="b" data-theme="b" data-corners="false">
  			<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">
  					Close</a><img class="popphoto5" src="${details.imageFive }" width="100%" height="100%" alt="预览图">
	</div>  
	--%>
			
			</p>
			
			<!-- <HR width="100%" color=#987cb9 SIZE=2 /> -->
		
			
			<!--同意弹出框  -->
 		<div data-role="popup" id="spdialog1" class="ui-content" style="max-width: 280px;background-color:green" data-position-to="window">
		<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">Close</a>
		<div data-role="header" data-theme="a" class="ui-corner-top">
			<h1>审批（同意）</h1>
		</div>
			<div data-role="content" data-theme="d" class="ui-corner-bottom ui-content">
											
				<form id="form_1" action="sign_agree?checkid=${details.checkid }" method="post">
					<textarea style="resize: none;width:240px;height:100px;max-width:240px;max-height: 100px;"  cols="30" rows="8" name="replyMessage"></textarea>			
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
 		<div data-role="popup" id="spdialog2" class="ui-content" style="max-width: 280px;background-color:red" data-position-to="window">
		<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">Close</a>
		<div data-role="header" data-theme="a" class="ui-corner-top">
			<h1>审批（打回）</h1>
		</div>
			<div data-role="content" data-theme="d" class="ui-corner-bottom ui-content">
				<form id="form_2" action="sign_disagree?checkid=${details.checkid }" method="post">
					<textarea style="resize: none;width:240px;height:100px;max-width:240px;max-height: 100px;"  cols="30" rows="8" name="replyMessage"></textarea>			
					
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