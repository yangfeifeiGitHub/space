
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="/static/js/siteInfo.js"></script>
</head>
<body>
<div id="site_datagrid"></div>
<div id="site_toolbar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="fresh">刷新</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="del">删除</a>

    <input class="easyui-textbox" id="keyword" prompt="请输入用地名称/编码">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchs"></a>
</div>
<div id="site_dialog">
    <form id="site_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top:20px">
            <tr>
                <%--validType="remote['/static/json/sitename_remote.json','name']"--%>
                <td>用地名称:</td>
                <td><input name="name" class="easyui-textbox" required="true"
                           <%--data-options="panelHeight:'auto',valueField:'id',textField:'name',
                           url:'/outComeType/selectAll.do'"--%>></td>
            </tr>
            <tr>
                <td>用地编码:</td>
                <td><input name="sn" class="easyui-textbox" required="true"></td>
            </tr>
            <tr>
                <td>用地面积:</td>
                <td><input name="area" class="easyui-textbox"
                           data-options="required:true"></td>
            </tr>


        </table>
    </form>
</div>

<div id="site_buttons">
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" data-cmd="cancel">取消</a>
</div>

<%--<div id="resetPassword_dialog">
    <form id="resetPassword_form" method="post">
        <table align="center" style="margin-top:20px">
            <tr>
                &lt;%&ndash;validType="remote['/static/json/sitename_remote.json','name']"&ndash;%&gt;
                <td>新密码:<input id="pwd" name="newPassword" class="easyui-passwordbox" required="true"
                ></td>
            </tr>
            <tr>
                <td>确认密码:<input class="easyui-passwordbox" required="true" validType="equals['#pwd']"
                ></td>
            </tr>
        </table>
    </form>
</div>

<div id="resetPassword_buttons">
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" data-cmd="savePassword">更改密码</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" data-cmd="pwdCancel">取消</a>
</div>--%>
</body>
</html>
