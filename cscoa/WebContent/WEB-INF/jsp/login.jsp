<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" deferredSyntaxAllowedAsLiteral="true"%>
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
<meta http-equiv="Content-type" name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/theme.css">
<link rel="stylesheet" href="jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="jquery.mobile-1.4.5/jquery-1.11.3.min.js"></script>
<script src="jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script>
	<!-- 引入mui，并初始化-->
<script src="js/mui.min.js"></script>
	<script type="text/javascript">
		mui.init();
	</script>
<!-- 登入和注册按钮的样式 -->
<style type="text/css">
    p {
        font-size: 1.5em;
        font-weight: bold;
    }
    #submit{
        float:right; margin:10px;
    }
    #toregist{
        float:left; margin:10px;
    }
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

</head>
<body>
<%--	<div id="tipDiv">--%>
<%--		<center><img style="width:25px;" src="picture/ajax-loader.gif"></center>--%>
<%--		<div id="tipInfo"></div>--%>
<%--	</div>--%>
<%--	<div id="tipInfo">123</div>--%>
	<form id="form1" name="form1" action="mainPage" method="get">
		<div data-role="page" id="login">
			<div data-role="header" data-position="fixed">
				<h1 id="test">个人登录</h1>
			</div>
			<br>
<%--			<div id="test1"></div>--%>
            	<br>	<br>
            	<br>
			<div align="center">
				<a><span><font color="red">${requestScope.error }</font></span></a>
			</div>
			<div data-role="content" id="loginContent" >
				<label for="username">用户名：</label>
				<input type="text" name="username" id="username" placeholder="工号" />
				<label for="userpassword">密码：</label>
				<input type="password" name="password" id="password"  placeholder="请输入密码 " />
				
				<div class="chose">
                	<a class="login" data-ajax="false" data-role="none" data-transition="slide" onclick="javascript:login()">登陆</a>
            	</div>
<%--				<input type="submit" class="login" data-ajax="false" data-role="none" data-transition="slide" value="登录">--%>
            	<br>
            	<br>
			<div id="pic" align="center">
				<img src="picture/logo.jpg">
			</div>
			</div>
		</div>
	</form>
<script type="text/javascript">
	mui.plusReady(function () {
		/*利用缓存记住密码*/
		var userInfoStr = plus.storage.getItem("userInfo");
		if(userInfoStr){
			var username=document.getElementById('username')
			var pd=document.getElementById('password')
			var json = JSON.parse(userInfoStr);
			username.value=json.username;
			pd.value=json.password;
		}
	})
	function login() {
		var username=document.getElementById('username')
		var pd=document.getElementById('password')
		var userinfo=JSON.stringify({
			username:username.value,
			password:pd.value
		})
		//兼容手机登录和浏览器登录
		if(window.plus){
			plus.storage.setItem("userInfo",userinfo)
		}
		$('#form1').submit();

	}
</script>

</body>
</html>



