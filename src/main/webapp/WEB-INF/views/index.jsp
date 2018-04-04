
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/static/common/common.jsp" %>
    <script src="/static/js/index.js"></script>
    <link rel="stylesheet" href="/static/css/reset.css">
    <link rel="stylesheet" href="/static/css/public.css">
</head>
<body>
<div class="easyui-layout" style="width:700px;height:350px;" data-options="fit:true">
    <div data-options="region:'north',split:true" style="height:70px">
        <div class="public-header-warrp">
            <div class="public-header">
                <div class="content">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="http://www.wolfcode.cn/img/wolfcode/logo.png"/>
                    <div class="public-header-admin fr">
                        <p class="admin-name"><font color="green"><shiro:principal property="username"/> 您好！</font></p>
                        <div class="public-header-fun fr">
                            <a href="/logout.do" class="public-header-loginout">安全退出</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div data-options="region:'west',collapsible:false" title="系统菜单" style="width:120px;">
        <ul id="menu" class="easyui-tree"></ul>
    </div>

    <div data-options="region:'center'">
        <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
            <div title="首页" style="">
                欢迎登录
            </div>
        </div>
    </div>
    <div data-options="region:'south',maxHeight:20" style="height:50px;">
        <center><span style="">©2018,杨菲菲</span></center>
    </div>
</div>
</body>
</html>