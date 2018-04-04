
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="/static/js/permission.js"></script>
</head>
<body>
<div id="permission_datagrid"></div>
<div id="permission_toolbar">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">加载权限</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="fresh">刷新</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="del">删除</a>
</div>
<div id="permission_dialog">
    <form id="permission_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top:20px">
            <tr>
                <%--validType="remote['/static/json/permissionname_remote.json','name']"--%>
                <td>名称:</td>
                <td><input name="sn" class="easyui-textbox" required="true"
                           <%--data-options="panelHeight:'auto',valueField:'id',textField:'name',
                           url:'/outComeType/selectAll.do'"--%>></td>
            </tr>
            <tr>
                <td>权限表达式:</td>
                <td><input name="name" class="easyui-textbox" required="true"></td>
            </tr>

        </table>
    </form>
</div>

<div id="permission_buttons">
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" data-cmd="cancel">取消</a>
</div>

</body>
</html>
