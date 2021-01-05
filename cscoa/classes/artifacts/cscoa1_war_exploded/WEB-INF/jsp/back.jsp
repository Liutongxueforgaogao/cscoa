<%--
  Created by IntelliJ IDEA.
  User: liuyunteng
  Date: 2020/9/19
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>back</title>
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
</head>
<body>
<div id="tipDiv">
    <center><img style="width:25px;" src="picture/ajax-loader.gif"></center>
    <div id="tipInfo"></div>
</div>
<a  id="back"  data-ajax="false" class="back" data-role="none"
    data-direction="reverse"><img src="image/back.png"></a>
<script type="text/javascript">
    function login() {
        //显示提示
        function showTip(info) {
            $('#tipInfo').html(info);
            $('#tipDiv').show();
        }

        //初始加载提示
        showTip('内容正在加载...');

    }
    var back=document.getElementById('back')
    back.onclick=function(){
        login();
        location.href="main"
    }
</script>

</body>
</html>
