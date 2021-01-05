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
function changepwd(){
	$.ajax({
        cache: true,
        type: "POST",
        url : "setPassword",
        data:$('#pwd').serialize(),// 你的formid
        async: false,
        error: function(request) {
            alert("修改失败");
        },
        success: function(data) {
        	alert("修改成功");
            //$("#commonLayout_appcreshi").parent().html(data);
        }
    });
	
}
</script>
</head>
<body>
	<div data-role="page" id="page1">
		<div data-role="content" id="navigation">
			<div data-role="header" data-position="fixed">
			<a href="main" data-ajax="false" class="back" data-role="none"
					data-direction="reverse"><img src="image/back.png"></a>
				<h1 >修改密码</h1>
			</div>
	
			<div data-role="content" >
			
			
				<form id="pwd">  
			        <span>请输入原密码:</span>     
			        <input type = "password"  name ="oldpwd" >  
			        <br/>  
			        <span>请输入新密码:</span>     
			        <input type = "password"  name ="newpwd" >  
			        <br/>  
			        <span>请再次输入新密码:</span>  
			        <input type = "password"  name ="confirm" >  
			        <br/> 
			        <input onclick="changepwd()"  value = "提交修改"/ >  
		        </form>  
			
			</div>
		</div>
	</div>
</body>
</html>










